package org.wikimedia.commons.donvip.omeka.client.classic.resources;

import org.wikimedia.commons.donvip.omeka.client.classic.OmekaResourceType;

public record OmekaItemType(

    /**
     * Unique ID for the record.
     */
    int id,

    String url,

    OmekaResourceType resource,

    /**
     * Name of this ItemType.
     */
    String name,

    /**
     * Description for this ItemType.
     */
    String description

) implements OmekaRecord {

}
