/**
 * 
 */
package de.kerner.commons.collection;

import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * <p>
 * A {@code MappedVectorMutable} extends {@link MappedVector} by providing
 * additionall support for modification of elements.
 * </p>
 * example // TODO </p>
 * 
 * @author Alexander Kerner
 * @see MappedVector
 * @threadSave
 * @lastVisit 2010-08-04
 * 
 * @param <V>
 *            Type of Values
 */
public class MappedVectorMutable<V> extends MappedVector<V> {

	/**
	 * 
	 */
	public MappedVectorMutable() {
		super();
	}

	/**
	 * @param arg0
	 */
	public MappedVectorMutable(Collection<V> arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public MappedVectorMutable(int arg0, int arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 */
	public MappedVectorMutable(int arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public MappedVectorMutable(Map<Object, V> arg0) {
		super(arg0);
	}

	/**
	 * <p>
	 * Add a new element to this {@code MappedVectorMutable} that is mapped by
	 * key {@code key}.
	 * </p>
	 * 
	 * @param key
	 *            mapping to element {@code value}
	 * @param value
	 *            element that is added to this {@code MappedVectorMutable}
	 */
	public synchronized void add(Object key, V value) {
		values.add(value);
		map.put(key, value);
	}

	/**
	 * <p>
	 * Inserts the specified element at the specified position in this {@code
	 * MappedVectorMutable}. Shifts the element currently at that position (if
	 * any) and any subsequent elements to the right (adds one to their
	 * indices).
	 * </p>
	 * 
	 * @param index
	 *            index at which the specified element is to be inserted
	 * @param value
	 *            element to be inserted
	 * @throws ArrayIndexOutOfBoundsException
	 *             if index out of range
	 */
	public synchronized void add(int index, V value) {
		values.add(index, value);
	}

	/**
	 * <p>
	 * Inserts the specified element at the specified position in this {@code
	 * MappedVectorMutable}. Shifts the element currently at that position (if
	 * any) and any subsequent elements to the right (adds one to their
	 * indices).
	 * 
	 * @param i
	 *            index at which the specified element is to be inserted
	 * @param key
	 *            mapping to this element
	 * @param value
	 *            element to be inserted
	 */
	public synchronized void add(int i, Object key, V value) {
		values.add(i, value);
		map.put(key, value);
	}

	public synchronized void replaceAtIndex(Object key, V value) {
		if (map.containsKey(key)) {

			// first replace numeric index

			V oldValue = map.get(key);

			if (oldValue == null)
				throw new RuntimeException();

			int i = values.lastIndexOf(oldValue);

			if (i < 0)
				throw new RuntimeException();

			boolean b = values.remove(oldValue);

			if (b == false)
				throw new RuntimeException();

			values.add(i, value);

			map.put(key, value);

		} else {
			throw new NoSuchElementException("No such element for index \""
					+ key + "\"");
		}
	}

	public synchronized void replaceAtIndex(int i, V value) {
		try {

			V oldValue = values.get(i);
			
			if (oldValue == null)
				throw new RuntimeException();
			
			boolean b = values.remove(oldValue);

			if (b == false)
				throw new RuntimeException();

			values.add(i, value);

		} catch(ArrayIndexOutOfBoundsException e) {
			throw new NoSuchElementException("No such element for index \""
					+ i + "\"");
		}

	}

	public synchronized void replaceAtIndex(int i, Object key, V value) {
		try {
			V v = values.get(i);
			values.remove(v);
			values.add(i, value);
			map.put(key, value);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchElementException("No such element for index \"" + i
					+ "\"");
		}

	}

}
