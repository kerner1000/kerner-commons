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

package de.kerner.commons.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.kerner.commons.AbstractCounter;

/**
 * @author Alexander Kerner
 * @lastVisit 2010-01-16
 * 
 * 
 */
public class AbstractCounterTest {

	private static class Cnt extends AbstractCounter {
		@Override
		public void perform() {
			performed++;
			System.out.println(performed + " performed");
		}
	}

	private static volatile int performed = 0;
	private static Cnt cnt;

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
		cnt = new Cnt();
		performed = 0;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		cnt = null;
		performed = 0;
	}

	/**
	 * Test method for {@link de.kerner.commons.AbstractCounter#count()}.
	 */
	@Test
	public final void testCount01() {
		for (int i = 0; i < 10; i++) {
			cnt.count();
		}
		assertEquals(cnt.getCount(), 10);
	}

	/**
	 * Test method for {@link de.kerner.commons.AbstractCounter#count()}.
	 */
	@Test
	public final void testCount02() {
		for (int i = 0; i < 20; i++) {
			cnt.count();
		}
		assertEquals(cnt.getCount(), 20);
	}

	/**
	 * Test method for {@link de.kerner.commons.AbstractCounter#stop()}.
	 */
	@Test
	public final void testStop01() {
		for (int i = 0; i < 20; i++) {
			cnt.count();
			// ten runs performed, ten to go.
			if (i == 9)
				cnt.stop();
		}
		assertEquals(cnt.getCount(), 10);
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.AbstractCounter#setInterval(int)}.
	 */
	@Test
	public final void testSetInterval01() {
		cnt.setInterval(10);
		assertEquals(cnt.getInterval(), 10);
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.AbstractCounter#setInterval(int)}.
	 */
	@Test (expected = NumberFormatException.class)
	public final void testSetInterval02() {
		cnt.setInterval(-10);
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.AbstractCounter#setInterval(int)}.
	 */
	@Test (expected = NumberFormatException.class)
	public final void testSetInterval03() {
		cnt.setInterval(0);
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.AbstractCounter#setInterval(int)}.
	 */
	@Test (expected = NumberFormatException.class)
	public final void testSetInterval04() {
		cnt.setInterval(-1);
	}

	/**
	 * Test method for {@link de.kerner.commons.AbstractCounter#getCount()}.
	 */
	@Test
	public final void testGetCount() {
		for (int i = 0; i < 20; i++) {
			cnt.count();
		}
		assertEquals(cnt.getCount(), 20);
	}

	/**
	 * Test method for {@link de.kerner.commons.AbstractCounter#perform()}.
	 */
	@Test
	public final void testPerform01() {
		cnt.setInterval(1);
		for (int i = 0; i < 10; i++) {
			cnt.count();
		}
		assertEquals(performed, 10);
	}

	/**
	 * Test method for {@link de.kerner.commons.AbstractCounter#perform()}.
	 */
	@Test
	public final void testPerform02() {
		cnt.setInterval(2);
		for (int i = 0; i < 10; i++) {
			cnt.count();
		}
		assertEquals(performed, 5);
	}
	
	/**
	 * Test method for {@link de.kerner.commons.AbstractCounter#perform()}.
	 */
	@Test
	public final void testPerform03() {
		cnt.setInterval(3);
		for (int i = 0; i < 10; i++) {
			cnt.count();
		}
		assertEquals(performed, 3);
	}

}
