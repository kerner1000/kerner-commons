/**
 * 
 */
package de.kerner.commons.collection;

import java.util.Collection;

/**
 * @author kerner
 *
 */
public class MappedVectorMutable<V> extends MappedVector<V> {

	/**
	 * 
	 */
	public MappedVectorMutable() {
		
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
	
	public synchronized void add(Object key, V value){
		values.add(value);
		map.put(key, value);
	}
	
	public synchronized void add(int i, V value){
		values.add(i, value);
	}
	
	public synchronized void add(int i, Object key, V value){
		values.add(i, value);
		map.put(key, value);
	}

}
