package de.kerner.commons.collection;

import java.util.Iterator;
import java.util.Map;

public abstract class IterableMap<K, V> implements Map<K, V>, Iterable<K>{
	
	// Implement //
	
	public Iterator<K> iterator() {
		return keySet().iterator();
	}

}
