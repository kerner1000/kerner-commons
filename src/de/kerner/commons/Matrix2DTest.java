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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;

import de.kerner.commons.logging.Log;

/**
 * @author kerner
 * 
 */
public class Matrix2DTest {

	private final static Log log = new Log(Matrix2DTest.class);

//	@Rule
//	public MethodRule watchman = new TestWatchman() {
//		public void starting(FrameworkMethod method) {
//			log.info("now test " + method.getName());
//		}
//	};

	private Matrix2D<String> m;

	private Matrix2D<String> m2;

	private final String[][] array = { { "1-1", "1-2", "1-3" },
									   { "2-1", "2-2", "2-3" },
									   { "3-1", "3-2" } };

	private final String[][] array2 = { { "1-1", "2-1", "3-1" },
										{ "1-2", "2-2", "3-2" },
										{ "1-3", "2-3" } };

	private List<String> row1 = new ArrayList<String>();
	private List<String> row2 = new ArrayList<String>();
	private List<String> row3 = new ArrayList<String>();
	private List<String> column1 = new ArrayList<String>();
	private List<String> column2 = new ArrayList<String>();
	private List<String> column3 = new ArrayList<String>();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.Matrix2D#Matrix2D(java.util.List)}.
	 */
	@Test
	public final void testMatrix2D() {

	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.Matrix2D#getRows(java.util.List)}.
	 */
	@Test
	public final void testGetRows() {

		m = new Matrix2D<String>(array);

		List<List<String>> l = new ArrayList<List<String>>();
		l.add(row1);
		l.add(row2);
		l.add(row3);

//		log.info("row1=" + row1);
//		log.info("row2=" + row2);
//		log.info("all rows=" + l);

		assertEquals(l, m.getRows());
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.Matrix2D#getRows(java.util.List)}.
	 */
	@Test
	public final void testGetRows01() {

		m = new Matrix2D<String>();

		List<List<String>> l = new ArrayList<List<String>>();

//		log.info("all rows=" + l);

		assertEquals(l, m.getRows());
	}

	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#getRows(int)}.
	 */
	@Test
	public final void testGetRow() {
		m = new Matrix2D<String>(array);

		List<List<String>> l = new ArrayList<List<String>>();
		l.add(row1);
		l.add(row2);

		assertEquals(row1, m.getRow(0));

		assertEquals(row2, m.getRow(1));
	}

	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#getRows(int)}.
	 */
	@Test(expected = NoSuchElementException.class)
	public final void testGetRow01() {
		m = new Matrix2D<String>(array);
		m.getRow(3);
	}

	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#getRows(int)}.
	 */
	@Test(expected = NoSuchElementException.class)
	public final void testGetRow02() {
		m = new Matrix2D<String>(array);
		m.getRow(-1);
	}

	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#getColumn(int)}.
	 */
	@Test
	public final void testGetColumn() {
		m = new Matrix2D<String>(array);
		assertEquals(column1, m.getColumn(0));
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
	 * Test method for
	 * {@link de.kerner.commons.Matrix2D#getColumns(java.util.list)}.
	 */
	@Test
	public final void testGetColumns() {
		m = new Matrix2D<String>(array);

		List<List<String>> l = new ArrayList<List<String>>();
		l.add(column1);
		l.add(column2);
		l.add(column3);

//		log.info("all columns=" + m.getColumns());

		assertEquals(column1, m.getColumn(0));
		assertEquals(column2, m.getColumn(1));
		assertEquals(column3, m.getColumn(2));
	}

	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#getRowNumber()}.
	 */
	@Test
	public final void testGetRowNumber() {
		m = new Matrix2D<String>(array);

//		log.info("number of rows=" + m.getRowNumber());

		assertEquals(array.length, m.getRowNumber());

	}

	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#getColumnNumber()}.
	 */
	@Test
	public final void testGetColumnNumber() {
		m = new Matrix2D<String>(array);

//		log.info("number of columns=" + m.getColumnNumber());

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
//		log.info("orig.="+m);
//		log.info("inverted.="+m.invert());
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
	 * Test method for {@link de.kerner.commons.Matrix2D#equals()}.
	 */
	@Test
	public final void testEquals() {
		m = new Matrix2D<String>(array);
		m2 = new Matrix2D<String>(array2);
		assertEquals(m.invert(), m2);
	}
	
	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#equals()}.
	 */
	@Test
	public final void testEquals01() {
		m = new Matrix2D<String>(array);
		m2 = new Matrix2D<String>(array);
		assertNotSame(m.invert(), m2);
	}
	
	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#equals()}.
	 */
	@Test
	public final void testEquals02() {
		m = new Matrix2D<String>(array);
		m2 = new Matrix2D<String>(array);
		assertEquals(m, m2);
	}
	
	/**
	 * Test method for {@link de.kerner.commons.Matrix2D#equals()}.
	 */
	@Test
	public final void testEquals03() {
		m = new Matrix2D<String>(array);
		m2 = new Matrix2D<String>(array);
		assertEquals(m2, m);
	}

}
