package org.wikimedia.commons.donvip.omeka.client.classic.resources.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OmekaExifComputed(

		String html,

		@JsonProperty("Height")
		int height,

		@JsonProperty("Width")
		int width,

		@JsonProperty("IsColor")
		short isColor,

		@JsonProperty("ByteOrderMotorola")
		short byteOrderMotorola
) {

}
