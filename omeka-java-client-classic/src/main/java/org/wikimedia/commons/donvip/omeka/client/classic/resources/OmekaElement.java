package org.wikimedia.commons.donvip.omeka.client.classic.resources;

import org.wikimedia.commons.donvip.omeka.client.classic.OmekaResourceType;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OmekaElement(

		/**
	     * Unique ID for the record.
	     */
	    int id,

	    String url,

	    OmekaResourceType resource,

        /**
         * ID of the ElementSet this Element belongs to.
         */
        @JsonProperty("element_set_id")
        int elementSetId,

        /**
         * This Element's order within the parent ElementSet.
         */
        int order,

        /**
         * A human-readable name
         */
        String name,

        /**
         * A human-readable description
         */
        String description,

        /**
         * A user-generated comment
         */
        String comment

) implements OmekaRecord {

}
