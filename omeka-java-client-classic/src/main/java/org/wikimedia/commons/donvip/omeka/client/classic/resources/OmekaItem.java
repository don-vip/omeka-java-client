package org.wikimedia.commons.donvip.omeka.client.classic.resources;

import java.time.ZonedDateTime;
import java.util.List;

import org.wikimedia.commons.donvip.omeka.client.classic.OmekaResourceType;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OmekaItem(

    /**
     * Unique ID for the record.
     */
    int id,

    String url,

    OmekaResourceType resource,

    /**
     * this Item's ItemType, if any.
     */
    @JsonProperty("item_type")
    OmekaItemType itemType,

    /**
     * this Item's Collection, if any.
     */
    OmekaCollection collection,

    /**
     * Whether this Item is featured.
     */
    @JsonProperty("featured")
    boolean isFeatured,

    /**
     * Whether this Item is publicly accessible.
     */
    @JsonProperty("public")
    boolean isPublic,

    /**
     * The date this Item was added.
     */
    ZonedDateTime added,

    /**
     * The date this Item was last modified.
     */
    ZonedDateTime modified,

    /**
     * the User who created this Item.
     */
    OmekaOwner owner,

    OmekaRecords files,

    List<OmekaTag> tags,

    @JsonProperty("element_texts")
    List<OmekaElementText> elementTexts,

    @JsonProperty("extended_resources")
    OmekaExtendedResources extendedResources

) implements OmekaRecord {

}
