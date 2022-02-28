package org.wikimedia.commons.donvip.omeka.client.classic.resources;

import java.time.ZonedDateTime;
import java.util.List;

import org.wikimedia.commons.donvip.omeka.client.classic.OmekaResourceType;
import org.wikimedia.commons.donvip.omeka.client.classic.resources.metadata.OmekaMetadata;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OmekaFile(

    /**
     * Unique ID for the record.
     */
    int id,

    String url,

    OmekaResourceType resource,

    /**
     * the Item this File belongs to.
     */
    OmekaItem item,

    /**
     * Relative order of this File within the parent Item.
     */
    int order,

    /**
     * Current filename, as stored.
     */
    String filename,

    /**
     * Original filename, as uploaded.
     */
    @JsonProperty("original_filename")
    String originalFilename,

    /**
     * Size of the file, in bytes.
     */
    long size,

    /**
     * MD5 hash of the file.
     */
    String authentication,

    /**
     * MIME type of the file.
     */
    @JsonProperty("mime_type")
    String mimeType,

    /**
     * Longer description of the file's type.
     */
    @JsonProperty("type_os")
    String typeOs,

    /**
     * Whether the file has derivative images.
     */
    @JsonProperty("has_derivative_image")
    boolean hasDerivativeImage,

    /**
     * Date the file was added.
     */
    ZonedDateTime added,

    /**
     * Date the file was last modified.
     */
    ZonedDateTime modified,

    /**
     * Whether the file has been moved to storage.
     */
    boolean stored,

    /**
     * Embedded metadata from the file.
     */
    OmekaMetadata metadata,

    @JsonProperty("file_urls")
    OmekaFileUrls fileUrls,

    @JsonProperty("element_texts")
    List<OmekaElementText> elementTexts,

    @JsonProperty("extended_resources")
    List<OmekaExtendedResources> extendedResources

) implements OmekaRecord {

}
