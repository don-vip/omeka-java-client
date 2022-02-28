package org.wikimedia.commons.donvip.omeka.client.classic;

import java.util.List;

public class OmekaPage<T> {

	/**
	 * non-standard Omeka-Total-Results header set to the total count of request results.
	 */
	private final long totalResults;
	
	private final List<T> objects;

	public OmekaPage(long totalResults, List<T> objects) {
		this.totalResults = totalResults;
		this.objects = objects;
	}

	public long getTotalResults() {
		return totalResults;
	}

	public List<T> getObjects() {
		return objects;
	}
}
