/**
 * 
 */
package de.kerner.commons;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * <p>
 * description // TODO
 * </p>
 * 
 * <p>
 * example // TODO
 * </p>
 * 
 * @author Alexander Kerner
 * @threadSave
 * @lastVisit 2010-07-27
 * 
 * @param <K>
 *            Identifier for this {@code Vector}
 * @param <I>
 *            Identifier for elements
 * @param <V>
 *            Value of elements
 */
public class Vector<K, I, V> {

	private volatile K key;
	private final Map<I, V> values = new LinkedHashMap<I, V>();

	/**
	 * <p>
	 * Construct a new empty {@code Vector} with a {@code null} identifier
	 * </p>
	 */
	public Vector() {
		key = null;
	}

	/**
	 * <p>
	 * Construct a new empty {@code Vector} with given identifier
	 * </p>
	 * 
	 * @param k
	 *            identifier
	 */
	public Vector(K k) {
		this.key = k;
	}

	public Vector(List<V> values) {
		synchronized (this.values) {
			for(V v : values){
				this.values.put(null, v);
			}
		}
	}
	
	public Vector(V... values) {
		synchronized (this.values) {
			for(V v : values){
				this.values.put(null, v);
			}
		}
	}

	public Vector(Map<I, V> values) {
		synchronized (this.values) {
			this.values.clear();
			this.values.putAll(values);
		}
	}

	public Vector(K key, List<V> values) {
		synchronized (this) {
			for(V v : values){
				this.values.put(null, v);
			}
			this.key = key;
		}
	}

	public Vector(K key, Map<I, V> values) {
		synchronized (this) {
			this.values.putAll(values);
			this.key = key;
		}
	}

	public synchronized V getAtIndex(int i) {
		try {
			return getValues().get(i);
		} catch (IndexOutOfBoundsException e) {
			throw new NoSuchElementException(e.getLocalizedMessage());
		}
	}

	public synchronized V getAtIndex(I s) {
		final V result = values.get(s);
		if (result == null)
			throw new NoSuchElementException("No element for identifier \"" + s
					+ "\"");
		return result;
	}

	public synchronized List<V> getValues() {
		return new LinkedList<V>(values.values());
	}

	public void setIdentifier(K k) {
		this.key = k;
	}

	public K getIdentifier() {
		return this.key;
	}

	@Override
	public synchronized int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((values == null) ? 0 : values.hashCode());
		return result;
	}

	@Override
	public synchronized boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector<?, ?, ?>))
			return false;
		Vector<?, ?, ?> other = (Vector<?, ?, ?>) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return values.toString();
	}

}
