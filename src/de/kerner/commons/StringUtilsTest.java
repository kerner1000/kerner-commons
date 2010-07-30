/**
 * 
 */
package de.kerner.commons;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author kerner
 *
 */
public class StringUtilsTest {

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
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link de.kerner.commons.StringUtils#splitToList(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testSplitToListStringString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.kerner.commons.StringUtils#splitToList(java.lang.String, java.lang.String, boolean)}.
	 */
	@Test
	public final void testSplitToListStringStringBoolean() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.kerner.commons.StringUtils#splitToArray(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testSplitToArray() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.kerner.commons.StringUtils#getString(java.lang.Object[])}.
	 */
	@Test
	public final void testGetString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.kerner.commons.StringUtils#removeAllAfterFirstWhiteSpace(java.lang.String)}.
	 */
	@Test
	public final void testRemoveAllAfterFirstWhiteSpace() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.kerner.commons.StringUtils#removeAllAfterFirstOccurence(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testRemoveAllAfterFirstOccurence() {
		String s = "1..17";
		assertEquals("1", StringUtils.removeAllAfterFirstOccurence("\\.+", s));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.StringUtils#removeAllAfterFirstOccurence(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testRemoveAllAfterFirstOccurence01() {
		String s = "aaBBccDDee";
		assertEquals("aa", StringUtils.removeAllAfterFirstOccurence("[A-Z]+", s));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.StringUtils#removeAllAfterLastOccurence(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testRemoveAllAfterLastOccurence() {
		String s = "aaBBcc.DD.ee";
		assertEquals("aaBBcc.DD", StringUtils.removeAllAfterLastOccurence("\\.", s));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.StringUtils#removeAllBeforeLastOccurence(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testRemoveAllBeforeLastOccurence() {
		String s = "aaBBcc.DD.ee";
		assertEquals("ee", StringUtils.removeAllBeforeLastOccurence("\\.", s));
	}

	/**
	 * Test method for {@link de.kerner.commons.StringUtils#removeAllBevoreFirstOccurence(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testRemoveAllBevoreFirstOccurence() {
		String s = "1..17";
		assertEquals("17", StringUtils.removeAllBevoreFirstOccurence("\\.+", s));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.StringUtils#removeAllBevoreFirstOccurence(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testRemoveAllBevoreFirstOccurence01() {
		String s = "aaBBccDDee";
		assertEquals("ccDDee", StringUtils.removeAllBevoreFirstOccurence("[A-Z]+", s));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.StringUtils#removeAllBevoreFirstOccurence(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testRemoveAllBevoreFirstOccurence02() {
		String s = "aaBBccDDee";
		s = StringUtils.removeAllBevoreFirstOccurence("[A-Z]+", s);
		s = StringUtils.removeAllAfterFirstOccurence("[A-Z]+", s);
		assertEquals("cc", s);
	}

	/**
	 * Test method for {@link de.kerner.commons.StringUtils#replaceAllNonMatching(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testReplaceAllNonMatching() {
		fail("Not yet implemented"); // TODO
	}

}
