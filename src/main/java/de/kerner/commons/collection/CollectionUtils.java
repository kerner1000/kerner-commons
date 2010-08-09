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
import java.util.List;
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

	public static boolean isCollection(Object obj) {
		if (obj != null)
			return obj instanceof Collection<?>;
		return false;
	}

	public static boolean isList(Object obj) {
		if (obj != null)
			return obj instanceof List<?>;
		return false;
	}

	/**
	 * <p>
	 * Extract all Objects of type <code>T</code> from an array that contains
	 * objects of different types.
	 * </p>
	 * 
	 * @param <T>
	 *            wanted type
	 * @param array
	 *            Array from which Objects are retrieved.
	 * @param c
	 * @return an array containing all Objects that are an instance of wanted
	 *         type.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] getAllOfTypeAsArray(Object[] array, Class<T> c) {
		return (T[]) getAllOfTypeAsList(array, c).toArray();
	}

	/**
	 * <p>
	 * Extract all Objects of type <code>T</code> from an array that contains
	 * objects of different types.
	 * </p>
	 * 
	 * @param <T>
	 *            wanted type
	 * @param array
	 *            Array from which Objects are retrieved.
	 * @param c
	 * @return a list containing all Objects that are an instance of wanted
	 *         type.
	 */
	public static <T> List<T> getAllOfTypeAsList(Object[] array, Class<T> c) {
		final List<T> result = new ArrayList<T>();
		for (Object o : array) {
			if (o.getClass().equals(c)) {
				result.add(c.cast(o));
//			} else {
//				System.err.println("NO MATCH: " + o.getClass() + " - " + c);
//				System.err
//						.println(o.getClass() + " is an array: " + isArray(o));
//				if (isArray(o)) {
//					Object[] o2 = (Object[]) o;
//					for (Object o1 : o2) {
//						System.err.println(o1);
//					}
//				}
			}
		}
		return result;
	}

	/**
	 * <p>
	 * Extract all Objects of type <code>T</code> from a <code>List</code> that
	 * contains objects of different types.
	 * </p>
	 * 
	 * @param <T>
	 *            wanted type
	 * @param list
	 *            <code>List</code> from which Objects are retrieved.
	 * @param c
	 * @return a list containing all Objects that are an instance of wanted
	 *         type.
	 */
	public static <T> List<T> getAllOfTypeAsList(List<?> list, Class<T> c) {
		return getAllOfTypeAsList(list.toArray(), c);
	}
}
