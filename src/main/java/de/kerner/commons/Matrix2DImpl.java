/**********************************************************************
Copyright (c) 2009-2010 Alexander Kerner. All rights reserved.
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
package de.kerner.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import de.kerner.commons.file.FileUtils;

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
 * @ThreadSave
 * 
 */
public class Matrix2DImpl<T> implements Matrix2D<T> {
	
	// lastVisit 2010-08-17

	protected final List<List<T>> m = new ArrayList<List<T>>();

	/**
	 * 
	 * <p>
	 * TODO description
	 * </p>
	 * 
	 * <p>
	 * TODO example
	 * </p>
	 * 
	 * @param template
	 */
	public Matrix2DImpl(Matrix2D<T> template) {
		for (List<T> l : template.getRows()) {
			this.m.add(l);
		}
	}

	/**
	 * 
	 * <p>
	 * TODO description
	 * </p>
	 * 
	 * <p>
	 * TODO example
	 * </p>
	 * 
	 * @param m
	 */
	public Matrix2DImpl(List<List<T>> m) {
		for (List<T> l : m) {
			this.m.add(l);
		}
	}

	/**
	 * 
	 * <p>
	 * TODO description
	 * </p>
	 * 
	 * <p>
	 * TODO example
	 * </p>
	 * 
	 */
	public Matrix2DImpl() {

	}

	/**
	 * 
	 * <p>
	 * TODO description
	 * </p>
	 * 
	 * <p>
	 * TODO example
	 * </p>
	 * 
	 * @param m
	 */
	public Matrix2DImpl(T[][] m) {
		for (T[] tt : m) {
			List<T> row = new ArrayList<T>();
			for (T t : tt) {
				row.add(t);
			}
			this.m.add(row);
		}
	}

	public synchronized int getMaxRowLength() {
		int result = -1;
		for (List<T> l : m) {
			if (l.size() > result) {
				result = l.size();
			}
		}
		return result;
	}

	public synchronized List<T> getRow(int index) {
		if (index > m.size())
			throw new NoSuchElementException();
		return new ArrayList<T>(m.get(index));
	}

	public synchronized List<List<T>> getRows() {
		return new ArrayList<List<T>>(m);
	}

	public synchronized int getRowNumber() {
		return m.size();
	}

	public synchronized List<T> getColumn(int index) {
		final List<T> result = new ArrayList<T>();
			for (List<T> l : m) {
				if (index > l.size())
					throw new NoSuchElementException();
				result.add(l.get(index));
			}
		return result;
	}

	public synchronized List<List<T>> getColumns() {
		final List<List<T>> result = new ArrayList<List<T>>();
		final int index = getMaxRowLength();
		for (int i = 0; i < index; i++) {
			result.add(getColumn(i));
		}
		return result;
	}

	public synchronized int getColumnNumber() {
		return getMaxRowLength();
	}

	public synchronized Matrix2D<T> invert() {
		final List<List<T>> mm = new ArrayList<List<T>>();
		
		// TODO
		
		return new Matrix2DImpl<T>(mm);
	}

	public synchronized boolean isColumnConsistent() {
		final AbstractCounter cnt = new AbstractCounter() {
			@Override
			public void perform() {
				// TODO Auto-generated method stub
			}
		};

		cnt.setCount(1);
		int length = getColumns().iterator().next().size();
		for (List<T> c : getColumns()) {
			cnt.count();
			if (length != c.size()) {
				return false;
			}
		}
		return true;
	}

	public synchronized boolean isRowConsistent() {
		final AbstractCounter cnt = new AbstractCounter() {
			@Override
			public void perform() {
				// TODO Auto-generated method stub
			}
		};

		cnt.setCount(1);
		int length = getRows().iterator().next().size();
		for (List<T> c : getRows()) {
			cnt.count();
			if (length != c.size()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public synchronized int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m == null) ? 0 : m.hashCode());
		return result;
	}

	@Override
	public synchronized boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Matrix2DImpl<?>))
			return false;
		Matrix2DImpl<?> other = (Matrix2DImpl<?>) obj;
		if (m == null) {
			if (other.m != null)
				return false;
		} else if (!m.equals(other.m))
			return false;
		return true;
	}

	@Override
	public synchronized String toString() {
		StringBuilder sb = new StringBuilder();
		for (List<T> l : m) {
			for (T t : l) {
				sb.append(t);
				sb.append(", ");
			}
			sb.append(FileUtils.NEW_LINE);
		}
		// TODO remove last ","
		return sb.toString();
	}

	public synchronized int getMaxColumnLength() {
		return getRowNumber();
	}

	public boolean isConsistent() {
		return isRowConsistent() && isColumnConsistent();
	}

}
