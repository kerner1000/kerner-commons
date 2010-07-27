/**
 * 
 */
package de.kerner.commons;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.List;

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
 * @param <I>
 *            Identifier for elements
 * @param <V>
 *            Value of elements
 */
public class Vector<I, V> {

	private final Map<I, V> map = new LinkedHashMap<I, V>();
	private final List<V> values = new LinkedList<V>();

	/**
	 * <p>
	 * Construct a new empty {@code Vector}.
	 * </p>
	 */
	public Vector() {

	}

	public Vector(Collection<V> values) {
		synchronized (this.values) {
			this.values.addAll(values);
		}
	}

	public Vector(Map<I, V> values) {
		synchronized (this) {
			this.map.putAll(values);
			this.values.addAll(values.values());
		}
	}

	public synchronized V getAtIndex(int i) {
		try {
			return getAsList().get(i);
		} catch (IndexOutOfBoundsException e) {
			throw new NoSuchElementException(e.getLocalizedMessage());
		}
	}

	public synchronized V getAtIndex(I s) {
		final V result = map.get(s);
		if (result == null)
			throw new NoSuchElementException("No element for identifier \"" + s
					+ "\"");
		return result;
	}

	public synchronized void assign(I id, V v) {
		for (V vv : values) {
			if (vv.equals(v)) {
				map.put(id, v);
				return;
			}
		}
		throw new NoSuchElementException("No such element \"" + v + "\"");
	}

	public synchronized void assign(I id, int i) {
		V v = getAtIndex(i);
		map.put(id, v);
	}

	public synchronized List<V> getAsList() {
		return new LinkedList<V>(this.values);
	}
	
	public synchronized Map<I, V> getMap(){
		return new LinkedHashMap<I, V>(map);
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
		if (!(obj instanceof Vector<?,?>))
			return false;
		Vector<?,?> other = (Vector<?,?>) obj;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.values.toString();
	}
}
