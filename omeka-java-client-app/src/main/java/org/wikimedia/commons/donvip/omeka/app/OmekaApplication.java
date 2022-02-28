package org.wikimedia.commons.donvip.omeka.app;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.util.Arrays;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.wikimedia.commons.donvip.omeka.client.classic.OmekaClassicRestClient;
import org.wikimedia.commons.donvip.omeka.client.classic.resources.OmekaCollection;
import org.wikimedia.commons.donvip.omeka.client.classic.resources.OmekaFile;
import org.wikimedia.commons.donvip.omeka.client.classic.resources.OmekaItem;
import org.wikimedia.commons.donvip.omeka.client.classic.resources.OmekaRecord;

@SpringBootApplication
public class OmekaApplication implements ApplicationListener<ApplicationReadyEvent> {

	private static final Logger logger = LoggerFactory.getLogger(OmekaApplication.class);

	@Autowired
	private OmekaClassicRestClient restClient;

	@Autowired
	private CloseableHttpClient httpClient;

	public static void main(String[] args) {
		SpringApplication.run(OmekaApplication.class, args);
	}

	public void downloadCollection(int collectionId) throws IOException {
		logger.info("Fetching collection {}...", collectionId);
		OmekaCollection coll = restClient.getCollection(collectionId);
		logger.info("Fetching {} items...", coll.items().count());
		for (OmekaRecord itemRecord : restClient.getRecords(coll.items())) {
			if (itemRecord instanceof OmekaItem item) {
				logger.info("Fetched item {}. Fetching its {} file(s)...", item.id(), item.files().count());
				for (OmekaRecord fileRecord : restClient.getRecords(item.files())) {
					if (fileRecord instanceof OmekaFile file) {
						logger.info("Fetched file {}, downloading fullsize...", file.id());
						try (CloseableHttpResponse response = httpClient
								.execute(new HttpGet(file.fileUrls().fullsize().toExternalForm()))) {
							if (response.getStatusLine().getStatusCode() >= 400) {
								logger.error("Can't download file {} : {}", file.id(), response.getStatusLine());
								continue;
							}
							String filename = file.filename().replace('\\', '_').replace('/', '_');
							try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
									FileChannel fileChannel = fileOutputStream.getChannel();) {
								long bytes = fileOutputStream.getChannel().transferFrom(
										Channels.newChannel(response.getEntity().getContent()), 0, Long.MAX_VALUE);
								logger.info("Downloaded {} bytes to {}", bytes, filename);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		String[] args = event.getArgs();
		if (args.length == 2) {
			try {
				downloadCollection(Integer.parseInt(args[1]));
				exitIfNotTest(event.getApplicationContext(), 0);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException(
						"Invalid argument. Usage: -Dspring-boot.run.profiles=<profile> -Dspring-boot.run.arguments=<collection_id>",
						e);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				exitIfNotTest(event.getApplicationContext(), 1);
			}
		} else {
			logger.info(
					"Wrong number of arguments. Usage: -Dspring-boot.run.profiles=<profile> -Dspring-boot.run.arguments=<collection_id>");
			exitIfNotTest(event.getApplicationContext(), 2);
		}
	}

	private static void exitIfNotTest(ConfigurableApplicationContext applicationContext, int exitCode) {
		if (Arrays.stream(applicationContext.getEnvironment().getActiveProfiles()).noneMatch("test"::equals)) {
			SpringApplication.exit(applicationContext, () -> exitCode);
		}
	}
}
