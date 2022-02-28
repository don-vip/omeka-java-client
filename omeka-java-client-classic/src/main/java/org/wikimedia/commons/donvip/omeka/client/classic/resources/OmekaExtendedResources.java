package org.wikimedia.commons.donvip.omeka.client.classic.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OmekaExtendedResources(

		@JsonProperty("exhibit_pages")
		OmekaRecords exhibitPages
) {

}
