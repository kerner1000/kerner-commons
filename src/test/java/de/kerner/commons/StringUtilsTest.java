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

package de.kerner.commons;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

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
 * @lastVisit 2010-08-03
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
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#splitToList(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	@Ignore
	public final void testSplitToListStringString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#splitToList(java.lang.String, java.lang.String, boolean)}
	 * .
	 */
	@Test
	@Ignore
	public final void testSplitToListStringStringBoolean() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#splitToArray(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	@Ignore
	public final void testSplitToArray() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#getString(java.lang.Object[])}.
	 */
	@Test
	@Ignore
	public final void testGetString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#removeAllAfterFirstWhiteSpace(java.lang.String)}
	 * .
	 */
	@Test
	@Ignore
	public final void testRemoveAllAfterFirstWhiteSpace() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#removeAllAfterFirstOccurence(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testRemoveAllAfterFirstOccurence() {
		String s = "1..17";
		assertEquals("1", StringUtils.removeAllAfterFirstOccurence("\\.+", s));
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#removeAllAfterFirstOccurence(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testRemoveAllAfterFirstOccurence01() {
		String s = "aaBBccDDee";
		assertEquals("aa", StringUtils
				.removeAllAfterFirstOccurence("[A-Z]+", s));
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#removeAllBevoreFirstOccurence(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testRemoveAllBevoreFirstOccurence() {
		String s = "1..17";
		assertEquals("17", StringUtils.removeAllBevoreFirstOccurence("\\.+", s));

	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#removeAllBevoreFirstOccurence(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testRemoveAllBevoreFirstOccurence01() {
		String s = "aaBBccDDee";
		assertEquals("ccDDee", StringUtils.removeAllBevoreFirstOccurence(
				"[A-Z]+", s));
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#removeAllBevoreFirstOccurence(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testRemoveAllBevoreFirstOccurence02() {
		String s = "aaBBccDDee";
		s = StringUtils.removeAllBevoreFirstOccurence("[A-Z]+", s);
		s = StringUtils.removeAllAfterFirstOccurence("[A-Z]+", s);
		assertEquals("cc", s);
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#removeAllAfterLastOccurence(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testRemoveAllAfterLastOccurence() {
		String s = "aaBBcc.DD.ee";
		assertEquals("aaBBcc.DD", StringUtils.removeAllAfterLastOccurence(
				"\\.", s));
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#removeAllBeforeLastOccurence(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testRemoveAllBeforeLastOccurence() {
		String s = "aaBBcc.DD.ee";
		assertEquals("ee", StringUtils.removeAllBeforeLastOccurence("\\.", s));
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#reverse(java.lang.Strin)}
	 * .
	 */
	@Test
	public final void testReverse() {
		String s1 = "aaBBccDDee";
		String s2 = "eeDDccBBaa";
		assertEquals(s1, StringUtils.reverse(s2));
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#firstIndexOf(java.lang.String, java.lang.String, boolean)}
	 * .
	 */
	@Test
	public final void testFirstIndexOfExclusive() {
		String s = "aaBBccDDee";
		assertEquals(2, StringUtils.firstIndexOf("[A-Z]+", s, false));
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#firstIndexOf(java.lang.String, java.lang.String, boolean)}
	 * .
	 */
	@Test
	public final void testFirstIndexOfExclusive01() {
		String s = "aaBBBBccDDDDee";
		assertEquals(2, StringUtils.firstIndexOf("[A-Z]+", s, false));
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#firstIndexOf(java.lang.String, java.lang.String, boolean)}
	 * .
	 */
	@Test
	public final void testFirstIndexOfInclusive() {
		String s = "aaBBccDDee";
		assertEquals(3, StringUtils.firstIndexOf("[A-Z]+", s, true));
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#firstIndexOf(java.lang.String, java.lang.String, boolean)}
	 * .
	 */
	@Test
	public final void testFirstIndexOfInclusive01() {
		String s = "aaBBBBccDDDee";
		assertEquals(5, StringUtils.firstIndexOf("[A-Z]+", s, true));
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#lastIndexOf(java.lang.String, java.lang.String, boolean)}
	 * .
	 */
	@Test
	public final void testLastIndexOfExclusive() {
		String s = "aaBBccDDee";
		assertEquals(6, StringUtils.lastIndexOf("[A-Z]+", s, false));
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#lastIndexOf(java.lang.String, java.lang.String, boolean)}
	 * .
	 */
	@Test
	public final void testLastIndexOfExclusive01() {
		String s = "aaBBBBccDDDDee";
		assertEquals(8, StringUtils.lastIndexOf("[A-Z]+", s, false));
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#lastIndexOf(java.lang.String, java.lang.String, boolean)}
	 * .
	 */
	@Test
	public final void testLastIndexOfInclusive() {
		String s = "aaBBccDDee";
		assertEquals(7, StringUtils.lastIndexOf("[A-Z]+", s, true));
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#lastIndexOf(java.lang.String, java.lang.String, boolean)}
	 * .
	 */
	@Test
	public final void testLastIndexOfInclusive01() {
		String s = "aaBBBccDDDDDee";
		assertEquals(11, StringUtils.lastIndexOf("[A-Z]+", s, true));
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#removeAllNonMatching(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testRemoveAllNonMatching() {
		String s = "aaBBccDDee";
		assertEquals("aaccee", StringUtils.removeAllNonMatching("[A-Z]+", s));
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#replace(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testReplace() {
		String s = "aaBBccDDee";
		String ss = "XX";
		assertEquals("aaXXccXXee", StringUtils.replace("[A-Z]+", s, ss));
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#replace(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testReplace02() {
		String s = "\tfoo\tbar\t";
		String ss = "\t";
		assertEquals("\tfoo\tbar\t_", StringUtils.replace(ss + "$", s, "\t_"));
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#replace(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testReplace03() {
		String s = "\tfoo\tbar\t";
		String ss = "\t";
		assertEquals("_\tfoo\tbar\t", StringUtils.replace("^" + ss, s, "_\t"));
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#formatMultiLineStringToLength(java.lang.String, int)}
	 * .
	 */
	@Test
	public final void testFormatMultiLineStringToLength() {
		String s = "AAAAAAAAAABBBBBBBBBBCCCCCCCCCCDDDD";
		String s2 = "AAAAAAAAAA" + FileUtils.NEW_LINE + "BBBBBBBBBB"
				+ FileUtils.NEW_LINE + "CCCCCCCCCC" + FileUtils.NEW_LINE
				+ "DDDD";
		assertEquals(s2, StringUtils.formatMultiLineStringToLength(s, 10));
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.StringUtils#formatMultiLineStringToLength(java.lang.String, int)}
	 * .
	 */
	@Test
	public final void testFormatMultiLineStringToLength01() {
		String s = "AAAAAAAAAABBBBBBBBBBCCCCCCCCCCDDDD" + FileUtils.NEW_LINE;
		String s2 = "AAAAAAAAAA" + FileUtils.NEW_LINE + "BBBBBBBBBB"
				+ FileUtils.NEW_LINE + "CCCCCCCCCC" + FileUtils.NEW_LINE
				+ "DDDD";
		assertEquals(s2, StringUtils.formatMultiLineStringToLength(s, 10));
	}

}
