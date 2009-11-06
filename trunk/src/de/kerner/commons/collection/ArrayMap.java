package de.kerner.commons.collection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * An implementation of {@code Map}, that retains order of elements by their
 * addition. That way its access to keys behaves just like accessing elements
 * of an {@code ArrayList}.
 * </p>
 * 
 * @see Map
 * @see ArrayList
 * @see IterableMap
 * @author Alexander Kerner
 * @lastVisit 2009-11-06
 * @threadSave
 * 
 * @param <K>
 *            type of key
 * @param <V>
 *            type of value
 */
public class ArrayMap<K, V> implements IterableMap<K, V>, Serializable {

	private static final long serialVersionUID = 3193996520387392707L;
	private final ArrayList<K> keys = new ArrayList<K>();
	private final ArrayList<V> values = new ArrayList<V>();

	public synchronized void clear() {
		keys.clear();
		values.clear();
	}

	public synchronized boolean containsKey(Object key) {
		return keys.contains(key);
	}

	public synchronized boolean containsValue(Object value) {
		return values.contains(value);
	}

	public synchronized Set<Map.Entry<K, V>> entrySet() {
		final LinkedHashSet<Map.Entry<K, V>> result = new LinkedHashSet<Entry<K, V>>();
		if (keys.size() == 0) {
			return result;
		}
		for (int i = 0; i < keys.size(); i++) {
			result.add(new KeyValue<K, V>(keys.get(i), values.get(i)));
		}
		return result;
	}

	public synchronized V get(Object key) {
		final int index = keys.indexOf(key);
		return values.get(index);
	}

	public synchronized boolean isEmpty() {
		return keys.isEmpty();
	}

	public synchronized Set<K> keySet() {
		return new LinkedHashSet<K>(keys);
	}

	public synchronized V put(K key, V value) {
		V result = null;
		if (keys.contains(key)) {
			final int index = keys.indexOf(key);
			result = values.get(index);
			keys.set(index, key);
			values.add(index, value);
		} else {
			keys.add(key);
			final int index = keys.indexOf(key);
			values.add(index, value);
		}
		return result;

	}

	public synchronized void putAll(Map<? extends K, ? extends V> t) {
		for (Entry<? extends K, ? extends V> e : t.entrySet()) {
			final K key = e.getKey();
			final V value = e.getValue();
			put(key, value);
		}

	}

	public synchronized V remove(Object key) {
		if (keys.contains(key)) {
			final int index = keys.indexOf(key);
			keys.remove(index);
			return values.remove(index);
		} else {
			return null;
		}
	}

	public synchronized int size() {
		return keys.size();
	}

	public synchronized Collection<V> values() {
		return new ArrayList<V>(values);
	}

	public synchronized Iterator<K> iterator() {
		return keys.iterator();
	}

}
