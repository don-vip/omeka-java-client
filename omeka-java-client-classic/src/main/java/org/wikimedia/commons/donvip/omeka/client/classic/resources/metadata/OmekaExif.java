package org.wikimedia.commons.donvip.omeka.client.classic.resources.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OmekaExif(
		@JsonProperty("FILE")
		OmekaExifFile file,
		@JsonProperty("COMPUTED")
		OmekaExifComputed computed,
		@JsonProperty("IFD0")
		OmekaExifIfd0 ifd0
		) {

}
