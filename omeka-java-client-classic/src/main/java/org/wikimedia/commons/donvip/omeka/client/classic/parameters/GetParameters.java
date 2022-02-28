package org.wikimedia.commons.donvip.omeka.client.classic.parameters;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record GetParameters(int page, String sortField, OmekaSortDirection sortDir) {
}
