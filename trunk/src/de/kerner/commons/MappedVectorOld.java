/**
 * 
 */
package de.kerner.commons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
 * A {@code MappedList} is 
 * </p>
 * 
 * <p>
 * example // TODO
 * </p>
 * 
 * @author Alexander Kerner
 * @threadSave
 * @lastVisit 2010-08-04
 * @deprecated
 * 
 * @param <I>
 *            Identifier for elements
 * @param <V>
 *            Value of elements
 */
public class MappedVectorOld<I, V> implements Iterable<V> {

	private final static Log log = new Log(MappedVectorOld.class);

	protected final Map<I, V> map = new LinkedHashMap<I, V>();
	protected final List<V> values = new ArrayList<V>();

	/**
	 * <p>
	 * Construct a new empty {@code Vector}.
	 * </p>
	 */
	public MappedVectorOld() {

	}

	public MappedVectorOld(Collection<V> values) {
		synchronized (this.values) {
			this.values.addAll(values);
		}
	}

	public MappedVectorOld(Map<I, V> values) {
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
	
	public boolean containsValue(V value){
		
		// TODO unit test
		
//		System.err.println("fffff  " + values);
		
		synchronized (values) {
			return values.contains(value);
		}
	}
	
	public boolean containsKey(I key){
		
		// TODO unit test
		
		synchronized (map) {
			return map.containsKey(key);
		}
	}
	
	public synchronized int getIndexForIdentifier(String identifier){
		
		// TODO unit test
		
		AbstractCounter cnt = new AbstractCounter() {
			@Override
			public void perform() {
				// TODO Auto-generated method stub
			}
		};
		for(I i : map.keySet()){
			if(i.equals(identifier))
				return cnt.getCount();
			cnt.count();
		}
		return -1;
	}
	
	// Implement //
	
	public Iterator<V> iterator() {
		return map.values().iterator();
	}
	
	// Override //

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
		if (!(obj instanceof MappedVectorOld<?, ?>))
			return false;
		MappedVectorOld<?, ?> other = (MappedVectorOld<?, ?>) obj;
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
