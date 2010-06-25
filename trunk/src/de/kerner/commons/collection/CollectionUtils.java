/**********************************************************************
Copyright (c) 2010 Alexander Kerner. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 ***********************************************************************/

package de.kerner.commons.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

/**
 * <p>
 * Utility class, that provides static methods for all kind of
 * {@link java.util.Collection}-related operations.
 * </p>
 * 
 * @author Alexander Kerner
 * @see java.util.Collection
 * 
 */
public class CollectionUtils {

	private CollectionUtils() {

	}

	/**
	 * <p>
	 * Flattening is the same like {@code Map.values()}, with the difference,
	 * that value must be a Collection, and corresponding key is added to this
	 * collection of values. (at position 0).
	 * </p>
	 * <p>
	 * Example:
	 * 
	 * <pre>
	 * final Collection<String> values0 = new ArrayList<String>();
	 * values0.add("B");
	 * values0.add("C");
	 * final Collection<String> values1 = new ArrayList<String>();
	 * values1.add("E");
	 * values1.add("F");
	 * final Map<String, Collection<String>> map = new LinkedHashMap<String, Collection<String>>();
	 * map.put("A", values0);
	 * map.put("D", values1);
	 * final Collection<String> reference = new LinkedHashSet<String>();
	 * reference.add("A");
	 * reference.add("B");
	 * reference.add("C");
	 * reference.add("D");
	 * reference.add("E");
	 * reference.add("F");
	 * 	
	 * reference == {A, B, C, D, E, F}
	 * CollectionUtils.flattenMap(map) == {A, B, C, D, E, F}
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param <V>
	 *            type of keys and values
	 * @param map
	 *            that is to be flattened
	 * @return the flat map
	 * @see java.util.Map
	 */
	public static <V> Collection<V> flattenMap(Map<V, Collection<V>> map) {
		final ArrayList<V> result = new ArrayList<V>();
		for (Entry<V, Collection<V>> e : map.entrySet()) {
			result.add(e.getKey());
			result.addAll(e.getValue());
		}
		return result;
	}

	/**
	 * Check if the given object is an array (primitve or native).
	 * 
	 * @param obj
	 *            Object to test.
	 * @return True if the object is an array; false otherwise.
	 */
	public static boolean isArray(final Object obj) {
		if (obj != null)
			return obj.getClass().isArray();
		return false;
	}
}
