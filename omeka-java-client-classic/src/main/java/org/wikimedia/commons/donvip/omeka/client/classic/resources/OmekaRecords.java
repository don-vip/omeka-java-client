package org.wikimedia.commons.donvip.omeka.client.classic.resources;

import java.net.URL;

import org.wikimedia.commons.donvip.omeka.client.classic.OmekaResourceType;

public record OmekaRecords(
		int count, URL url, OmekaResourceType resource
) {

}
