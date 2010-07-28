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
import java.util.Set;

import de.kerner.commons.logging.Log;

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

	private final static Log log = new Log(Vector.class);

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

	/**
	 * <p>
	 * Retrieve element at given index.
	 * </p>
	 * 
	 * @param i
	 *            index of returned element
	 * @return element at index {@code i}
	 */
	public synchronized V getAtIndex(int i) {
		try {
			return getAsList().get(i);
		} catch (IndexOutOfBoundsException e) {
			throw new NoSuchElementException(e.getLocalizedMessage());
		}
	}

	/**
	 * <p>
	 * Retrieve element that is mapped to given identifier.
	 * </p>
	 * 
	 * @param s
	 *            identifier of returned element
	 * @return element that is mapped by identifier {@code s}
	 * @throws NoSuchElementException
	 *             if there is no element that is mapped by given identifier
	 */
	public synchronized V getAtIndex(I s) {
		final V result = map.get(s);
		if (result == null)
			throw new NoSuchElementException("No element for identifier \"" + s
					+ "\"");
		return result;
	}

	/**
	 * <p>
	 * Assign an identifier to that element in this {@code Vector}, that equals
	 * given object {@code v}, so that it will be mapped by this identifier.
	 * </p>
	 * 
	 * @param id
	 *            new identifier for object {@code v}
	 * @param v
	 *            object in this {@code Vector} that will be mapped by
	 *            identifier {@code id}
	 * @throws NoSuchElementException
	 *             if there is no element in this {@code Vector} that equals
	 *             given object {@code v}
	 */
	public synchronized void assign(I id, V v) {
		try {
			for (V vv : values) {
				if (vv.equals(v)) {
					final V vvv = map.put(id, v);
					if (vvv != null)
						log.debug("element replaced=" + vvv);
					return;
				}
			}
			throw new NoSuchElementException("No such element \"" + v + "\"");
		} finally {
			// log.debug("now content=" + map);
		}
	}

	/**
	 * <p>
	 * Assign an identifier to the element at position {@code i} in this {@code
	 * Vector}, so that it will be mapped by given identifier {@code id}.
	 * </p>
	 * 
	 * @param id
	 *            new identifier for element
	 * @param i
	 *            index of element
	 * @throws NoSuchElementException
	 *             if there is no element in this {@code Vector} at position
	 *             {@code i}
	 */
	public synchronized void assign(I id, int i) {
		V v = getAtIndex(i);
		final V vv = map.put(id, v);
		if (vv != null)
			log.debug("element replaced=" + vv);
		// log.debug("now content=" + map);
	}

	/**
	 * <p>
	 * Assign new identifiers to all elements of this {@code Vector}.
	 * </p>
	 * <p>
	 * Notice: Identifiers in given {@link java.util.Set} are assigned to
	 * elements according to their order in this set.
	 * </p>
	 * 
	 * @param ids
	 *            identifiers to assign to this {@code Vector}s elements
	 */
	public synchronized void assignAll(Set<I> ids) {

		// log.debug("content to override=" + map);

		map.clear();

//		if (ids.size() != values.size()) {
//			log
//					.warn("number of ids to assin differs from numer of values (ids="
//							+ ids.size() + ", values=" + values.size() + ")");
//		}

		final LinkedList<I> l = new LinkedList<I>(ids);

		for (int i = 0; i < values.size(); i++) {
			if (l.size() > i) {
				assign(l.get(i), values.get(i));
			} else {
				// log.debug("skipping element \"" + values.get(i) + "\"");
			}
		}
		// log.debug("now content=" + map);
	}

	/**
	 * <p>
	 * Retrieve a {@linkplain java.util.List} representation of this {@code
	 * Vector}, discarding all mappings.
	 * </p>
	 * 
	 * @return {@linkplain java.util.List} representation of this {@code Vector}
	 */
	public synchronized List<V> getAsList() {
		return new LinkedList<V>(this.values);
	}

	/**
	 * <p>
	 * Retrieve a {@linkplain java.util.Map} representation of this {@code
	 * Vector}, containing all assigned mappings.
	 * </p>
	 * 
	 * @return a {@linkplain java.util.Map} representation of this {@code
	 *         Vector}
	 */
	public synchronized Map<I, V> getMap() {
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
		if (!(obj instanceof Vector<?, ?>))
			return false;
		Vector<?, ?> other = (Vector<?, ?>) obj;
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
