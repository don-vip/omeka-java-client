package org.wikimedia.commons.donvip.omeka.client.classic.resources.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OmekaMetadata(
		@JsonProperty("mime_type")
		String mimeType,

		OmekaVideo video,

		OmekaJpg jpg
) {

}
