package org.wikimedia.commons.donvip.omeka.client.classic.resources.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OmekaVideo(

		String dataformat,

		boolean lossless,

		@JsonProperty("bits_per_sample")
		short bitsPerSample,

		@JsonProperty("pixel_aspect_ratio")
		short pixelAspectRatio,

		@JsonProperty("resolution_x")
		int resolutionX,

		@JsonProperty("resolution_y")
		int resolutionY,

		@JsonProperty("compression_ratio")
		double compressionRatio
) {

}
