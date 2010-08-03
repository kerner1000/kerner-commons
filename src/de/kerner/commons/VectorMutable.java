/**
 * 
 */
package de.kerner.commons;

import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @author kerner
 * 
 */
public class VectorMutable<I, V> extends Vector<I, V> {

	/**
	 * 
	 */
	public VectorMutable() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param values
	 */
	public VectorMutable(Collection<V> values) {
		super(values);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param values
	 */
	public VectorMutable(Map<I, V> values) {
		super(values);
		// TODO Auto-generated constructor stub
	}

	public synchronized void setAtIndex(I s, V v) {

		// TODO unit testing

		final V result = map.get(s);
		if (result == null)
			throw new NoSuchElementException("No element for identifier \"" + s
					+ "\"");
		map.put(s, v);
	}

	public synchronized void setAtIndex(int i, V v) {

		// TODO unit testing

		try {
			getAsList().set(i, v);
		} catch (IndexOutOfBoundsException e) {
			throw new NoSuchElementException(e.getLocalizedMessage());
		}
	}

}
