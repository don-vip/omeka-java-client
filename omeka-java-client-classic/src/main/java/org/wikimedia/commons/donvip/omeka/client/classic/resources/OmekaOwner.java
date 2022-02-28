package org.wikimedia.commons.donvip.omeka.client.classic.resources;

import org.wikimedia.commons.donvip.omeka.client.classic.OmekaResourceType;

public record OmekaOwner(

    /**
     * Unique ID for the record.
     */
    int id,

    String url,

    OmekaResourceType resource

) implements OmekaRecord {

}
