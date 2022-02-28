package org.wikimedia.commons.donvip.omeka.app;

import java.net.URL;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wikimedia.commons.donvip.omeka.client.classic.OmekaClassicRestClient;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class OmekaConfiguration {

	@Bean
	public ObjectMapper jackson() {
		return OmekaClassicRestClient.createDefaultObjectMapper();
	}

	@Bean
	public CloseableHttpClient httpClient() {
		return HttpClientBuilder.create().build();
	}

	@Bean
	public OmekaClassicRestClient restClient(@Autowired ObjectMapper jackson, @Value("${api.endpoint}") URL apiEndpoint) {
		return new OmekaClassicRestClient(jackson, apiEndpoint);
	}
}
