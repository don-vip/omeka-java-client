package org.wikimedia.commons.donvip.omeka.client.classic.resources.metadata;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public record OmekaExifIfd0(
		@JsonProperty("Software")
		String software,

		@JsonProperty("DateTime")
		@JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss")
		LocalDateTime dateTime,

		@JsonProperty("Artist")
		String artist
		) {

}
