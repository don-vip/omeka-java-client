package org.wikimedia.commons.donvip.omeka.client.classic.resources;

import java.util.List;

import org.wikimedia.commons.donvip.omeka.client.classic.OmekaResourceType;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OmekaCollection(

    /**
     * Unique ID for the record.
     */
    int id,

    String url,

    OmekaResourceType resource,

    /**
     * Whether or not the collection is publicly accessible.
     */
    @JsonProperty("public")
    boolean isPublic,

    /**
     * Whether or not the collection is featured.
     */
    @JsonProperty("featured")
    boolean isFeatured,

    /**
     * Date the collection was added.
     */
    String added,

    /**
     * Date the collection was last modified.
     */
    String modified,

    /**
     * User that created this collection.
     */
    OmekaOwner owner,

    OmekaRecords items,

    @JsonProperty("element_texts")
    List<OmekaElementText> elementTexts,

    @JsonProperty("extended_resources")
    List<OmekaExtendedResources> extendedResources

) implements OmekaRecord {
}
