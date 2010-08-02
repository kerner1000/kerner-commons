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
import org.junit.Test;


/**
 * @author Alexander Kerner
 *
 */
public class HumanReadableAmountPrinterTest {

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

	@Test
	public final void test01() {
		assertEquals("1B", new HumanReadableAmountPrinter(1, true).toString());
		assertEquals("1B", new HumanReadableAmountPrinter(1, false).toString());
	}

	@Test
	public final void test02() {
		assertEquals("10B", new HumanReadableAmountPrinter(10, true).toString());
		assertEquals("10B", new HumanReadableAmountPrinter(10, false).toString());
	}

	@Test
	public final void test03() {
		assertEquals("100B", new HumanReadableAmountPrinter(100, true).toString());
		assertEquals("100B", new HumanReadableAmountPrinter(100, false).toString());
	}
	
	@Test
	public final void test04() {
		assertEquals("1K", new HumanReadableAmountPrinter(1000, true).toString());
		assertEquals("1000B", new HumanReadableAmountPrinter(1000, false).toString());
		assertEquals("1K", new HumanReadableAmountPrinter(1024, false).toString());
	}
	
	@Test
	public final void test05() {
		System.out.println(new HumanReadableAmountPrinter(10000, false));
		assertEquals("10K", new HumanReadableAmountPrinter(10000, true).toString());
		assertEquals("10K", new HumanReadableAmountPrinter(10000, false).toString());
		
	}

}
