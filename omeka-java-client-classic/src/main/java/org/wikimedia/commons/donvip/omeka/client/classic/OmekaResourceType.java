package org.wikimedia.commons.donvip.omeka.client.classic;

import org.wikimedia.commons.donvip.omeka.client.classic.resources.OmekaCollection;
import org.wikimedia.commons.donvip.omeka.client.classic.resources.OmekaElement;
import org.wikimedia.commons.donvip.omeka.client.classic.resources.OmekaElementSet;
import org.wikimedia.commons.donvip.omeka.client.classic.resources.OmekaExhibitPage;
import org.wikimedia.commons.donvip.omeka.client.classic.resources.OmekaFile;
import org.wikimedia.commons.donvip.omeka.client.classic.resources.OmekaItem;
import org.wikimedia.commons.donvip.omeka.client.classic.resources.OmekaItemType;
import org.wikimedia.commons.donvip.omeka.client.classic.resources.OmekaRecord;
import org.wikimedia.commons.donvip.omeka.client.classic.resources.OmekaTag;
import org.wikimedia.commons.donvip.omeka.client.classic.resources.OmekaUser;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OmekaResourceType {

	@JsonProperty("collections")
	collection(OmekaCollection.class),
	@JsonProperty("items")
	item(OmekaItem.class),
	@JsonProperty("files")
	file(OmekaFile.class),
	@JsonProperty("item_types")
	item_type(OmekaItemType.class),
	@JsonProperty("elements")
	element(OmekaElement.class),
	@JsonProperty("element_sets")
	element_set(OmekaElementSet.class),
	@JsonProperty("exhibit_pages")
	exhibit_pages(OmekaExhibitPage.class),
	@JsonProperty("tags")
	tag(OmekaTag.class),
	@JsonProperty("users")
	user(OmekaUser.class);

	private <T extends OmekaRecord> OmekaResourceType(Class<T> resourceClass) {
		this.resourceClass = resourceClass;
	}

	private final Class<? extends OmekaRecord> resourceClass;

	public Class<? extends OmekaRecord> getResourceClass() {
		return resourceClass;
	}
}
