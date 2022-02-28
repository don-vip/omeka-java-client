package org.wikimedia.commons.donvip.omeka.client.classic;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.wikimedia.commons.donvip.omeka.client.classic.resources.OmekaFile;
import org.wikimedia.commons.donvip.omeka.client.classic.resources.OmekaItem;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;

/**
 * Unit tests of {@link OmekaClassicRestClient}
 */
@WireMockTest
class OmekaClassicRestClientTest {

	@Test
	void testGetCollection(WireMockRuntimeInfo wm) throws Exception {
		stubGet("/api/collections/45", "/getCollection.json");
		assertNotNull(client(wm).getCollection(45));
	}

	@Test
	void testGetItems(WireMockRuntimeInfo wm) throws Exception {
		stubGet("/api/items?collection=45", "/getItems.json");
		assertNotNull(client(wm).getValues(url(wm, "/api/items?collection=45").toExternalForm(), OmekaItem.class));
	}

	@Test
	void testGetFiles(WireMockRuntimeInfo wm) throws Exception {
		stubGet("/api/files?item=73824", "/getFiles.json");
		assertNotNull(client(wm).getValues(url(wm, "/api/files?item=73824").toExternalForm(), OmekaFile.class));
	}

	private void stubGet(String url, String file) throws IOException {
		try (InputStream is = getClass().getResourceAsStream(file)) {
			stubFor(get(url).willReturn(ok().withBody(is.readAllBytes())));
		}
	}

	private static OmekaClassicRestClient client(WireMockRuntimeInfo wm) throws MalformedURLException {
		return new OmekaClassicRestClient(url(wm, "/api"));
	}

	private static URL url(WireMockRuntimeInfo wm, String url) throws MalformedURLException {
		return new URL("http", "localhost", wm.getHttpPort(), url);
	}
}
