/**********************************************************************
Copyright (c) 2010 Alexander Kerner. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 ***********************************************************************/

package de.kerner.commons.collection;

import java.util.Map.Entry;

/**
 * <p>
 * Simple Key-Value mapping.
 * </p>
 * <p>
 * {@code key} may not be {@code null}, {@code value} may be {@code null}.
 * </p>
 * 
 * @author Alexander Kerner
 * @threadSave
 * 
 * @param <K>
 *            type of {@code key}
 * @param <V>
 *            type of {@code value}
 */
public class KeyValue<K, V> implements Entry<K, V> {

	private final K key;

	private volatile V value;

	// Constructor //

	public KeyValue(K key, V value) {
		if (key == null)
			throw new NullPointerException("key must not be null");
		this.key = key;
		this.value = value;
	}

	public KeyValue(K key) {
		if (key == null)
			throw new NullPointerException("key must not be null");
		this.key = key;
		this.value = null;
	}
	
	public KeyValue(KeyValue<K,V> template){
		this.key = template.getKey();
		this.value = template.getValue();
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
