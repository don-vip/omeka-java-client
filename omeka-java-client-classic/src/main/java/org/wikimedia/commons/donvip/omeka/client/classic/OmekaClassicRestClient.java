package org.wikimedia.commons.donvip.omeka.client.classic;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.wikimedia.commons.donvip.omeka.client.classic.parameters.GetParameters;
import org.wikimedia.commons.donvip.omeka.client.classic.parameters.GetParametersBuilder;
import org.wikimedia.commons.donvip.omeka.client.classic.resources.OmekaCollection;
import org.wikimedia.commons.donvip.omeka.client.classic.resources.OmekaElement;
import org.wikimedia.commons.donvip.omeka.client.classic.resources.OmekaElementSet;
import org.wikimedia.commons.donvip.omeka.client.classic.resources.OmekaFile;
import org.wikimedia.commons.donvip.omeka.client.classic.resources.OmekaItem;
import org.wikimedia.commons.donvip.omeka.client.classic.resources.OmekaRecord;
import org.wikimedia.commons.donvip.omeka.client.classic.resources.OmekaRecords;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class OmekaClassicRestClient {

	private final ObjectMapper jackson;

	private final String endpoint;

	private final CloseableHttpClient httpClient;

	private GetParameters defaultGetParameters = GetParametersBuilder.builder().build();

	public OmekaClassicRestClient(URL endpoint) {
		this(createDefaultObjectMapper(), endpoint);
	}

	public OmekaClassicRestClient(ObjectMapper jackson, URL endpoint) {
		this(jackson, endpoint, HttpClientBuilder.create().build());
	}

	public OmekaClassicRestClient(ObjectMapper jackson, URL endpoint, CloseableHttpClient httpClient) {
		this.jackson = Objects.requireNonNull(jackson, "jackson");
		this.endpoint = Objects.requireNonNull(endpoint, "endpoint").toExternalForm();
		this.httpClient = Objects.requireNonNull(httpClient, "httpClient");
	}

	public ObjectMapper getObjectMapper() {
		return jackson;
	}

	public String getEndpoint() {
		return endpoint;
	}

	private String fetchJsonContent(CloseableHttpResponse response) throws IOException, UnsupportedEncodingException {
		if (response.getStatusLine().getStatusCode() >= 400) {
			throw new IOException(response.toString());
		}
		HttpEntity entity = response.getEntity();
		return new String(entity.getContent().readAllBytes(), Optional.ofNullable(entity.getContentEncoding())
				.map(Header::getValue).orElse(StandardCharsets.UTF_8.name()));
	}

	protected <T extends OmekaRecord> T getValue(String uri, Class<T> resultClass) throws IOException {
		try (CloseableHttpResponse response = httpClient.execute(new HttpGet(uri))) {
			return jackson.readValue(fetchJsonContent(response), resultClass);
		}
	}

	protected <T extends OmekaRecord> OmekaPage<T> getValues(String uri, Class<T> resultClass) throws IOException {
		try (CloseableHttpResponse response = httpClient.execute(new HttpGet(uri))) {
			List<T> list = jackson.readValue(fetchJsonContent(response),
					jackson.getTypeFactory().constructCollectionType(List.class, resultClass));
			return new OmekaPage<>(Optional.ofNullable(response.getFirstHeader("Omeka-Total-Results"))
					.map(h -> Integer.parseInt(h.getValue())).orElse(list.size()), list);
		}
	}

	@SuppressWarnings("unchecked")
	public List<OmekaRecord> getRecords(OmekaRecords records) throws IOException {
		return (List<OmekaRecord>) getValues(records.url().toExternalForm(), records.resource().getResourceClass()).getObjects();
	}

	/**
	 * Return data about the specified resource (most often a specific record)
	 *
	 * @param resource
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	protected <T extends OmekaRecord> T getResource(OmekaResourceType resource, int id) throws IOException {
		return (T) getValue(String.format("%s/%ss/%d", endpoint, resource.name(), id), resource.getResourceClass());
	}

	/**
	 * Return data about resources (most often records)
	 *
	 * @param resource
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T extends OmekaRecord> OmekaPage<T> getResources(OmekaResourceType resource, GetParameters params)
			throws IOException {
		return (OmekaPage<T>) getValues(String.format("%s/%ss", endpoint, resource.name()), resource.getResourceClass());
	}

	/**
	 * Create a new resource
	 *
	 * @param resource
	 * @param object
	 */
	protected <T extends OmekaRecord> void postResource(OmekaResourceType resource, T object) throws IOException {
		// TODO
	}

	/**
	 * Update an existing resource
	 *
	 * @param resource
	 * @param object
	 */
	protected <T extends OmekaRecord> void putResource(OmekaResourceType resource, T object) throws IOException {
		// TODO
	}

	/**
	 * Delete a resource
	 *
	 * @param resource
	 * @param id
	 */
	protected void deleteResource(OmekaResourceType resource, String id) throws IOException {
		// TODO
	}

	// -- Collections

	public OmekaCollection getCollection(int id) throws IOException {
		return getResource(OmekaResourceType.collection, id);
	}

	public OmekaPage<OmekaCollection> getCollections() throws IOException {
		return getCollections(defaultGetParameters);
	}

	public OmekaPage<OmekaCollection> getCollections(GetParameters params) throws IOException {
		return getResources(OmekaResourceType.collection, params);
	}

	public void postCollection(OmekaCollection collection) throws IOException {
		postResource(OmekaResourceType.collection, collection);
	}

	public void putCollection(OmekaCollection collection) throws IOException {
		putResource(OmekaResourceType.collection, collection);
	}

	public void deleteCollection(String id) throws IOException {
		deleteResource(OmekaResourceType.collection, id);
	}

	// -- Elements

	public OmekaElement getElement(int id) throws IOException {
		return getResource(OmekaResourceType.element, id);
	}

	public OmekaPage<OmekaElement> getElements() throws IOException {
		return getElements(defaultGetParameters);
	}

	public OmekaPage<OmekaElement> getElements(GetParameters params) throws IOException {
		return getResources(OmekaResourceType.element, params);
	}

	public void postElement(OmekaElement element) throws IOException {
		postResource(OmekaResourceType.element, element);
	}

	public void putElement(OmekaElement element) throws IOException {
		putResource(OmekaResourceType.element, element);
	}

	public void deleteElement(String id) throws IOException {
		deleteResource(OmekaResourceType.element, id);
	}

	// -- Element Sets

	public OmekaElementSet getElementSet(int id) throws IOException {
		return getResource(OmekaResourceType.element_set, id);
	}

	public OmekaPage<OmekaElementSet> getElementSets() throws IOException {
		return getElementSets(defaultGetParameters);
	}

	public OmekaPage<OmekaElementSet> getElementSets(GetParameters params) throws IOException {
		return getResources(OmekaResourceType.element_set, params);
	}

	public void postElementSet(OmekaElementSet elementSet) throws IOException {
		postResource(OmekaResourceType.element_set, elementSet);
	}

	public void putElementSet(OmekaElementSet elementSet) throws IOException {
		putResource(OmekaResourceType.element_set, elementSet);
	}

	public void deleteElementSet(String id) throws IOException {
		deleteResource(OmekaResourceType.element_set, id);
	}

	// -- Files

	public OmekaFile getFile(int id) throws IOException {
		return getResource(OmekaResourceType.file, id);
	}

	public OmekaPage<OmekaFile> getFiles() throws IOException {
		return getFiles(defaultGetParameters);
	}

	public OmekaPage<OmekaFile> getFiles(GetParameters params) throws IOException {
		return getResources(OmekaResourceType.file, params);
	}

	public void postFile(OmekaFile file) throws IOException {
		postResource(OmekaResourceType.file, file);
	}

	public void putFile(OmekaFile file) throws IOException {
		putResource(OmekaResourceType.file, file);
	}

	public void deleteFile(String id) throws IOException {
		deleteResource(OmekaResourceType.file, id);
	}

	// -- Items

	public OmekaItem getItem(int id) throws IOException {
		return getResource(OmekaResourceType.item, id);
	}

	public OmekaPage<OmekaItem> getItems() throws IOException {
		return getItems(defaultGetParameters);
	}

	public OmekaPage<OmekaItem> getItems(GetParameters params) throws IOException {
		return getResources(OmekaResourceType.item, params);
	}

	public void postItem(OmekaItem item) throws IOException {
		postResource(OmekaResourceType.item, item);
	}

	public void putItem(OmekaItem item) throws IOException {
		putResource(OmekaResourceType.item, item);
	}

	public void deleteItem(String id) throws IOException {
		deleteResource(OmekaResourceType.item, id);
	}

	// -- Misc

	public static ObjectMapper createDefaultObjectMapper() {
		return JsonMapper.builder().addModule(new JavaTimeModule()).build();
	}
}
