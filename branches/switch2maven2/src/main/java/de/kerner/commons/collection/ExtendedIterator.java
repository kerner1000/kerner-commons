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

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * <p>
 * TODO description
 * </p>
 * <p>
 * TODO Example of usage
 * </p>
 * 
 * @author Alexander Kerner
 * 
 */
public class ExtendedIterator<T> implements Iterator<T> {
	
//	private final static Log log = new Log(ExtendedIterator.class);
	
	// Use LinkedList since it implements hashcode, equals, and serializable
	private final Queue<T> toGo = new LinkedList<T>();
	
	private final Stack<T> done = new Stack<T>();
	
	public ExtendedIterator(Collection<? extends T> elements) {
		if(elements == null)
			throw new NullPointerException();
		this.toGo.addAll(elements);
	}

	public boolean contains(T t) {
		return toGo.contains(t) || done.contains(t);
	}

	public T getCurrent() {
		return toGo.peek();
	}

	public int getSize() {
		return toGo.size() + done.size();
	}

	public boolean hasNext() {
		return toGo.iterator().hasNext();
	}

	public boolean isEmpty() {
		return (done.isEmpty() && toGo.isEmpty());
	}

	public void next() {
		// we do not need to check weather collection is empty, since poll()
		// will return null in such case.
		if(getCurrent() != null)
		done.add(toGo.poll());

	}

	public void reset() {
		// when resetting, we also restore previous ordering of elements! //
		while(!done.isEmpty()){
			toGo.add(done.pop());
		}
		done.clear();
	}

	public boolean add(T e) {
		if (e == null) {
			throw new NullPointerException();
		}
		return toGo.add(e);
	}

	public boolean remove(T e) {
		if (e == null) {
			throw new NullPointerException();
		}
		boolean result;
		if (toGo.contains(e))
			result = toGo.remove(e);
		else if (done.contains(e))
			result = done.remove(e);			
		else
			throw new RuntimeException("something wrong here!");
		return result;
	}
}
