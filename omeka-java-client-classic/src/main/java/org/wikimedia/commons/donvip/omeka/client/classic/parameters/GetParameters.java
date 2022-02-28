package org.wikimedia.commons.donvip.omeka.client.classic.parameters;

import static java.util.stream.Collectors.joining;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record GetParameters(int page, String sortField, OmekaSortDirection sortDir) {

	public String getQueryString() {
		Map<String, String> arguments = new HashMap<>();
		if (page > 0) {
			arguments.put("page", Integer.toString(page));
		}
		if (StringUtils.isNotBlank(sortField)) {
			arguments.put("sortField", sortField);
		}
		if (sortDir != null) {
			arguments.put("sortDir", sortDir.name());
		}
		return arguments.isEmpty() ? ""
				: arguments.entrySet().stream().map(e -> e.getKey() + '=' + e.getValue())
						.collect(joining("&", "?", ""));
	}
}
