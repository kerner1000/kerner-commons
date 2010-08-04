/**
 * 
 */
package de.kerner.commons.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import de.kerner.commons.AbstractCounter;

/**
 * <p>
 * A {@code MappedVector} extends {@link java.util.Vector} so that all elements
 * may be additionally indexed in an object-based manner.
 * </p>
 * <p>
 * In contrast to {@link java.util.Vector}, {@code MappedVector} is immutable.
 * For a mutable version, see {@link MappedVectorMutable}
 * <p>
 * example // TODO
 * </p>
 * 
 * @author Alexander Kerner
 * @see java.util.Vector
 * @threadSave
 * @lastVisit 2010-08-04
 * 
 * @param <V>
 *            Type of Values
 */
public class MappedVector<V> implements Iterable<V> {

	/**
	 * Note: {@link java.util.Vector} is thread save
	 */
	protected final Vector<V> values;

	/**
	 * Note: Access thread save
	 */
	protected final Map<Object, V> map = new ConcurrentHashMap<Object, V>();

	/**
	 * 
	 */
	public MappedVector() {
		values = new Vector<V>();
	}

	/**
	 * @param arg0
	 */
	public MappedVector(int arg0) {
		values = new Vector<V>(arg0);
	}

	/**
	 * @param arg0
	 */
	public MappedVector(Collection<V> arg0) {
		values = new Vector<V>(arg0);
	}

	/**
	 * @param arg0
	 */
	public MappedVector(Map<Object, V> arg0) {
		synchronized (MappedVector.class) {
			values = new Vector<V>(arg0.values());
			map.putAll(arg0);
		}
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public MappedVector(int arg0, int arg1) {
		values = new Vector<V>(arg0, arg1);
	}

	/**
	 * <p>
	 * Assign an object index to {@code value}.
	 * </p>
	 * 
	 * @param key
	 *            key for given {@code value}
	 * @param value
	 *            {@code value} that is to be mapped by {@code key}
	 */
	public void assign(Object key, V value) {
		synchronized (this) {
			if (values.contains(value)) {
				map.put(key, value);
			} else {
				throw new NoSuchElementException("No such element for index \""
						+ key + "\"");
			}

		}
	}

	/**
	 * 
	 * <p>
	 * Assign an object index to {@code value} at given index {@code
	 * valueAtIndex}.
	 * </p>
	 * 
	 * @param key
	 *            key for given {@code value}
	 * @param valueAtIndex
	 *            {@code value} that is to be mapped by {@code key}
	 * @throws NoSuchElementException
	 *             if given index is out of range
	 */
	public void assign(Object key, int valueAtIndex) {
		try {
			synchronized (this) {
				V value = values.get(valueAtIndex);
				map.put(key, value);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchElementException("No such element for index \""
					+ valueAtIndex + "\"");
		}
	}

	public void assignAll(Set<Object> keySet) {
		List<Object> list = new ArrayList<Object>(keySet);
		synchronized (this) {
			map.clear();
			for (int i = 0; i < keySet.size(); i++) {
				if (values.size() > i) {
					V v = values.get(i);
					map.put(list.get(i), v);
				} else {
					// ignore additional identifiers
				}
			}
		}
	}

	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	public boolean containsValue(V value) {
		return values.contains(value);
	}

	public V getAtIndex(int i) {
		return values.get(i);
	}

	public V getAtIndex(Object key) {
		synchronized (this) {
			V v = map.get(key);
			if (v == null)
				throw new NoSuchElementException("No such element for index \""
						+ key + "\"");
			return v;
		}
	}

	public int getIndexForIdentifier(Object key) {

		AbstractCounter cnt = new AbstractCounter() {
			@Override
			public void perform() {
			}
		};

		synchronized (this) {

			for (Object o : map.keySet()) {
				if (o.equals(key))
					return cnt.getCount();
				cnt.count();
			}
			return -1;
		}
	}

	public List<V> getAsList() {

		// TODO refactor to asList()

		return new ArrayList<V>(values);

	}

	public Vector<V> getAsVector() {
		return new Vector<V>(values);
	}

	public Map<Object, V> getMap() {

		// TODO refactor to getMappings()

		return new LinkedHashMap<Object, V>(map);
	}

	// Implement //

	public Iterator<V> iterator() {
		return values.iterator();
	}

	// Override //

	@Override
	public String toString() {
		return values.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((values == null) ? 0 : values.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MappedVector))
			return false;
		MappedVector other = (MappedVector) obj;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}

}
