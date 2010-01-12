package de.kerner.commons.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

public class CollectionUtils {

	private CollectionUtils() {

	}

	/**
	 * <p>
	 * Flattening is the same like Map.values(), with the differnence, that
	 * value must be a Collection, and corresponding key is added to this collection of
	 * values. (at position 0).
	 * </p>
	 * 
	 * @param <V>
	 * @param map
	 *            that is to be flattened.
	 * @return the flat map.
	 */
	public <V> Collection<V> flattenMap(Map<V, Collection<V>> map) {
		final ArrayList<V> result = new ArrayList<V>();
		for (Entry<V, Collection<V>> e : map.entrySet()) {
			result.add(e.getKey());
			result.addAll(e.getValue());
		}
		return result;
	}
}
