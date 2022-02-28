package org.wikimedia.commons.donvip.omeka.client.classic.resources.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OmekaExifFile(
		@JsonProperty("FileName")
		String filename,

		@JsonProperty("FileDateTime")
		long fileDateTime,

		@JsonProperty("FileSize")
		long filesize,

		@JsonProperty("FileType")
		short filetype,

		@JsonProperty("MimeType")
		String mimeType,

		@JsonProperty("SectionsFound")
		String sectionsFound
) {

}
