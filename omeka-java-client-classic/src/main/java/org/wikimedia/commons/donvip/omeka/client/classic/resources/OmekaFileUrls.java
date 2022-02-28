package org.wikimedia.commons.donvip.omeka.client.classic.resources;

import java.net.URL;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OmekaFileUrls(
		URL original,
		URL fullsize,
		URL thumbnail,
		@JsonProperty("square_thumbnail")
		URL squareThumbnail
) {

}
