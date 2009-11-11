package de.kerner.commons.collection;

import java.util.Iterator;

public abstract class AbstractIterableMapImpl<K, V> implements IterableMap<K, V>{
	
	public Iterator<K> iterator() {
		return keySet().iterator();
	}

}
