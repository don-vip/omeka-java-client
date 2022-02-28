package org.wikimedia.commons.donvip.omeka.client.classic.resources;

import org.wikimedia.commons.donvip.omeka.client.classic.OmekaResourceType;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OmekaElementText(

	/**
	 * Unique ID for the record.
	 */
	int id,

    String url,

    OmekaResourceType resource,

	/**
	 * ID of the associated record.
	 *
	 * @var int
	 */
	int record_id,

	/**
	 * Type of the associated record.
	 *
	 * @var string
	 */
	String record_type,

	/**
	 * this text's Element.
	 */
	OmekaElement element,

	/**
	 * Whether this text is HTML.
	 */
	boolean html,

	/**
	 * The text itself.
	 */
	String text,

	@JsonProperty("element_set")
	OmekaElementSet elementSet

) implements OmekaRecord {

}
