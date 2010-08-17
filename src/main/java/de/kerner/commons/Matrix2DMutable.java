/**
 * <p>
 * TODO description
 * </p>
 * 
 * <p>
 * TODO example
 * </p>
 *
 */
package de.kerner.commons;

import java.util.List;

/**
 * <p>
 * TODO description
 * </p>
 * 
 * <p>
 * TODO example
 * </p>
 * 
 * @autor Alexander Kerner
 * 
 */
public class Matrix2DMutable<T> extends Matrix2DImpl<T> {

	// lastVisit 2010-08-17
	
	/**
	 * <p>
	 * TODO description
	 * </p>
	 * 
	 * <p>
	 * TODO example
	 * </p>
	 *
	 */
	public Matrix2DMutable() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
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
	public Matrix2DMutable(List<List<T>> m) {
		super(m);
		// TODO Auto-generated constructor stub
	}

	/**
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
	public Matrix2DMutable(Matrix2D<T> template) {
		super(template);
		// TODO Auto-generated constructor stub
	}

	/**
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
	public Matrix2DMutable(T[][] m) {
		super(m);
		// TODO Auto-generated constructor stub
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
	 * @param row
	 */
	public void addRow(List<T> row) {
		// TODO
	}
	
	public void addAllRows(List<List<T>> row) {
		// TODO
	}

	public void addColumn(List<T> column) {
		// TODO
	}
	
	public void addAllColumns(List<List<T>> columns) {
		// TODO
	}

	public void replaceRow(List<T> row, int index) {
		// TODO
	}

	public void replaceColumn(List<T> column, int index) {
		// TODO
	}
	
	public void replaceElement(T element, int indexRow, int indexColumn) {
		// TODO
	}
	
	public void removeRow(int index) {
		// TODO
	}
	
	public void removeColumn(int index) {
		// TODO
	}
	
	public synchronized void clear(){
		super.m.clear();
	}

}
