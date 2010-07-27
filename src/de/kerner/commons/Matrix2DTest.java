/**
 * 
 */
package de.kerner.commons;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
 * 
 */
public class Matrix2DTest {

	private static Matrix2D<String> m;

	private static Matrix2D<String> m2;

	private final static String[][] array = { { "1-1", "1-2", "1-3" },
			{ "2-1", "2-2", "2-3" }, { "3-1", "3-2" } };

	private final static String[][] array2 = { { "1-1", "2-1", "3-1" },
			{ "1-2", "2-2", "3-2" }, { "1-3", "2-3" } };

	private final static List<String> row1 = new ArrayList<String>();
	private final static List<String> row2 = new ArrayList<String>();
	private final static List<String> row3 = new ArrayList<String>();
	private final static List<String> column1 = new ArrayList<String>();
	private final static List<String> column2 = new ArrayList<String>();
	private final static List<String> column3 = new ArrayList<String>();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		row1.add(array[0][0]);
		row1.add(array[0][1]);
		row1.add(array[0][2]);

		row2.add(array[1][0]);
		row2.add(array[1][1]);
		row2.add(array[1][2]);

		row3.add(array[2][0]);
		row3.add(array[2][1]);

		column1.add(array[0][0]);
		column1.add(array[1][0]);
		column1.add(array[2][0]);

		column2.add(array[0][1]);
		column2.add(array[1][1]);
		column2.add(array[2][1]);

		column3.add(array[0][2]);
		column3.add(array[1][2]);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.Matrix2D#Matrix2D(de.kerner.commons.Matrix2D)}.
	 */
	@Test
	public final void testMatrix2DMatrix2DOfT() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.Matrix2D#Matrix2D(java.util.List)}.
	 */
	@Test
	public final void testMatrix2DListOfListOfT() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#Matrix2D()}.
	 */
	@Test
	public final void testMatrix2D() {
		m = new Matrix2D<String>();
		assertNotNull(m);
	}

	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#Matrix2D(T[][])}.
	 */
	@Test
	public final void testMatrix2DTArrayArray() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#addRow(java.util.List)}
	 * .
	 */
	@Test
	public final void testAddRow() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#getRow(int)}.
	 */
	@Test
	public final void testGetRow() {
		m = new Matrix2D<String>(array);

		List<List<String>> l = new ArrayList<List<String>>();
		l.add(row1);
		l.add(row2);

		assertArrayEquals(row1.toArray(), m.getRow(0).toArray());

		assertArrayEquals(row2.toArray(), m.getRow(1).toArray());
	}
	
	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#getRow(int)}.
	 */
	@Test(expected = NoSuchElementException.class)
	public final void testGetRow01() {
		m = new Matrix2D<String>(array);
		m.getRow(3);
	}
	
	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#getRow(int)}.
	 */
	@Test(expected = NoSuchElementException.class)
	public final void testGetRow02() {
		m = new Matrix2D<String>(array);
		m.getRow(-1);
	}

	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#getRows()}.
	 */
	@Test
	public final void testGetRows() {
		m = new Matrix2D<String>(array);

		List<List<String>> l = new ArrayList<List<String>>();
		l.add(row1);
		l.add(row2);
		l.add(row3);

		assertArrayEquals(l.toArray(), m.getRows().toArray());
	}
	
	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#getRows()}.
	 */
	@Test
	public final void testGetRows01() {

		m = new Matrix2D<String>();

		List<List<String>> l = new ArrayList<List<String>>();

		// log.info("all rows=" + l);

		assertEquals(l, m.getRows());
	}
	
	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#getRowNumber()}.
	 */
	@Test
	public final void testGetRowNumber() {
		m = new Matrix2D<String>(array);

		assertEquals(array.length, m.getRowNumber());

	}

	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#getColumn(int)}.
	 */
	@Test
	public final void testGetColumn() {
		m = new Matrix2D<String>(array);
		assertArrayEquals(column1.toArray(), m.getColumn(0).toArray());
	}
	
	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#getColumn(int)}.
	 */
	@Test
	public final void testGetColumn01() {
		m = new Matrix2D<String>(array);
		assertEquals(column2, m.getColumn(1));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#getColumn(int)}.
	 */
	@Test
	public final void testGetColumn02() {
		m = new Matrix2D<String>(array);
		assertEquals(column3, m.getColumn(2));
	}

	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#getColumn(int)}.
	 */
	@Test(expected = NoSuchElementException.class)
	public final void testGetColumn03() {
		m = new Matrix2D<String>(array);
		m.getColumn(-1);
	}

	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#getColumn(int)}.
	 */
	@Test(expected = NoSuchElementException.class)
	public final void testGetColumn04() {
		m = new Matrix2D<String>(array);
		m.getColumn(4);
	}

	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#getColumns()}.
	 */
	@Test
	public final void testGetColumns() {
		fail("Not yet implemented"); // TODO
	}
	
	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#getColumnNumber()}.
	 */
	@Test
	public final void testGetColumnNumber() {
		m = new Matrix2D<String>(array);
		// TODO remove hardcoded number of columns
		assertEquals(3, m.getColumnNumber());

	}

	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#invert()}.
	 */
	@Test
	public final void testInvert() {
		m = new Matrix2D<String>(array);
		m2 = new Matrix2D<String>(array2);
		assertEquals(m.invert(), m2);
	}
	
	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#invert()}.
	 */
	@Test
	public final void testInvert01() {
		m = new Matrix2D<String>(array);
		m2 = new Matrix2D<String>(array2);
		assertEquals(m2.invert(), m);
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.Matrix2D#equals(java.lang.Object)}.
	 */
	@Test
	public final void testEqualsObject() {
		m = new Matrix2D<String>(array);
		m2 = new Matrix2D<String>(array2);
		assertEquals(m.invert(), m2);
	}
	
	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#equals()}.
	 */
	@Test
	public final void testEqualsObject01() {
		m = new Matrix2D<String>(array);
		m2 = new Matrix2D<String>(array);
		assertNotSame(m.invert(), m2);
	}
	
	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#equals()}.
	 */
	@Test
	public final void testEqualsObject02() {
		m = new Matrix2D<String>(array);
		m2 = new Matrix2D<String>(array);
		assertEquals(m, m2);
	}
	
	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#equals()}.
	 */
	@Test
	public final void testEqualsObject03() {
		m = new Matrix2D<String>(array);
		m2 = new Matrix2D<String>(array);
		assertEquals(m2, m);
	}

}
