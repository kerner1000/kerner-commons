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
public interface Matrix2D<T> extends Matrix<T> {
	
	// lastVisit 2010-08-17

	/**
	 * <p>
	 * Since not all rows have necessarily same number of columns, this method
	 * will find the row containing the most elements ("longest" row / row with
	 * the most columns).
	 * </p>
	 * 
	 * @return length of biggest column in this matrix
	 */
	int getMaxRowLength();

	int getMaxColumnLength();

	int getRowNumber();
	
	int getColumnNumber();

	List<T> getRow(int index);

	List<List<T>> getRows();
	
	List<T> getColumn(int index);
	
	boolean isColumnConsistent();
	
	boolean isRowConsistent();
	
	boolean isConsistent();
	
	Matrix2D<T> invert();

}
