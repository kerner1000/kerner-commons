package de.kerner.commons.collection;

import java.util.Map.Entry;

/**
 * <p> Simple Key-Value mapping.
 * </p>
 * @author Alexander Kerner
 * @lastVisit 2009-11-06
 * @threadSave
 *
 * @param <K> 
 * @param <V>
 */
public class KeyValue<K, V> implements Entry<K, V>{
	
	private final K key;
	
	private volatile V value;
	
	// Constructor //
	
	public KeyValue(K key, V value){
		this.key = key;
		this.value = value;
	}

	// Implement //
	
	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	public V setValue(V value) {
		final V result = this.value;
		this.value = value;
		return result;
	}

}
