package org.wikimedia.commons.donvip.omeka.client.classic.resources;

import org.wikimedia.commons.donvip.omeka.client.classic.OmekaResourceType;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OmekaElementSet(

	    /**
	     * Unique ID for the record.
	     */
	    int id,

	    String url,

	    OmekaResourceType resource,

        /**
         * Type of record this set applies to.
         */
        @JsonProperty("record_type")
        String recordType,

        /**
         * A human-readable name
         */
        String name,

        /**
         * A human-readable description
         */
        String description

) implements OmekaRecord {

}
