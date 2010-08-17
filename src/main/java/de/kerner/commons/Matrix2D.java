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
 * @lastVisit 2010-07-27
 * @notThreadSave
 * 
 * @param <T>
 */
public class Matrix2D<T> {

	private final List<List<T>> m = new ArrayList<List<T>>();

	public Matrix2D(Matrix2D<T> template) {
		for (List<T> l : template.getRows()) {
			this.m.add(l);
		}
	}

	public Matrix2D(List<List<T>> m) {
		for (List<T> l : m) {
			this.m.add(l);
		}
	}

	public Matrix2D() {

	}

	public Matrix2D(T[][] m) {
		for (T[] tt : m) {
			List<T> row = new ArrayList<T>();
			for (T t : tt) {
				row.add(t);
			}
			this.m.add(row);
		}
	}

	/**
	 * <p>
	 * Since not all rows have necessarily same number of columns, this method
	 * will find the row containing the most elements ("longest" row / row with
	 * the most columns).
	 * </p>
	 * 
	 * @return length of biggest column in this matrix
	 */
	private int getMaxRowLength() {
		int result = -1;
		for (List<T> l : m) {
			if (l.size() > result) {
				result = l.size();
			}
		}
		return result;
	}

	public void addRow(List<T> row) {
		m.add(row);
	}

	public List<T> getRow(int index) {
		try {
			return m.get(index);
		} catch (IndexOutOfBoundsException e) {
			throw new NoSuchElementException(e.getLocalizedMessage());
		}
	}

	public List<List<T>> getRows() {
		return m;
	}

	public int getRowNumber() {
		return m.size();
	}

	public List<T> getColumn(int index) {
		final List<T> result = new ArrayList<T>();
		try {
			for (List<T> l : m) {
				result.add(l.get(index));
			}
		} catch (IndexOutOfBoundsException e) {
			if (index < 0 || getMaxRowLength() < index)
				throw new NoSuchElementException(e.getLocalizedMessage());
			// else
			// result.add(null);
		}
		return result;
	}

	public List<List<T>> getColumns() {
		final List<List<T>> result = new ArrayList<List<T>>();
		final int index = getMaxRowLength();
		for (int i = 0; i < index; i++) {
			result.add(getColumn(i));
		}
		return result;
	}

	public int getColumnNumber() {
		return getMaxRowLength();
	}

	public Matrix2D<T> invert() {
		final Matrix2D<T> result = new Matrix2D<T>();
		for (int i = 0; i < getColumnNumber(); i++) {
			// System.err.println(getColumn(i));
			result.addRow(getColumn(i));
		}
		return result;
	}

	public boolean isColumnConsistent() {

		final AbstractCounter cnt = new AbstractCounter() {

			@Override
			public void perform() {
				// TODO Auto-generated method stub

			}
		};

		cnt.setCount(1);

		// TODO not unit tested

		int length = getColumns().iterator().next().size();
		for (List<T> c : getColumns()) {
			cnt.count();
			if (length != c.size()) {
				System.err.println("column " + cnt
						+ " not consistent (length expected: " + length
						+ ", actual: " + c.size() + ")");
				return false;
			}
		}
		return true;
	}

	public boolean isRowConsistent() {

		final AbstractCounter cnt = new AbstractCounter() {

			@Override
			public void perform() {
				// TODO Auto-generated method stub

			}
		};

		cnt.setCount(1);

		// TODO not unit tested

		int length = getRows().iterator().next().size();
		for (List<T> c : getRows()) {
			cnt.count();
			if (length != c.size()) {
				System.err.println("row " + cnt
						+ " not consistent (length expected: " + length
						+ ", actual: " + c.size() + ")");
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m == null) ? 0 : m.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Matrix2D<?>))
			return false;
		Matrix2D<?> other = (Matrix2D<?>) obj;
		if (m == null) {
			if (other.m != null)
				return false;
		} else if (!m.equals(other.m))
			return false;
		return true;
	}

	@Override
	public String toString() {
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

}
