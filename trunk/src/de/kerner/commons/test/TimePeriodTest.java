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

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.kerner.commons.TimePeriod;

/**
 * @author Alexander Kerner
 *
 */
public class TimePeriodTest {

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
	 * Test method for {@link de.kerner.commons.TimePeriod#getStart()}.
	 */
	@Test
	public final void testGetStart01() {
		TimePeriod p = new TimePeriod(1, 2, TimeUnit.SECONDS);
		assertEquals(1000, p.getStart());
	}
	
	/**
	 * Test method for {@link de.kerner.commons.TimePeriod#getStart(java.util.concurrent.TimeUnit))}.
	 */
	@Test
	public final void testGetStart02() {
		TimePeriod p = new TimePeriod(1, 2, TimeUnit.SECONDS);
		assertEquals(1, p.getStart(TimeUnit.SECONDS));
	}

	/**
	 * Test method for {@link de.kerner.commons.TimePeriod#getStop()}.
	 */
	@Test
	public final void testGetStop01() {
		TimePeriod p = new TimePeriod(1, 2, TimeUnit.SECONDS);
		assertEquals(2000, p.getStop());
	}
	
	/**
	 * Test method for {@link de.kerner.commons.TimePeriod#getStop(java.util.concurrent.TimeUnit))}.
	 */
	@Test
	public final void testGetStop02() {
		TimePeriod p = new TimePeriod(1, 2, TimeUnit.SECONDS);
		assertEquals(2, p.getStop(TimeUnit.SECONDS));
	}

	/**
	 * Test method for {@link de.kerner.commons.TimePeriod#getDuration()}.
	 */
	@Test
	public final void testGetDuration01() {
		TimePeriod p = new TimePeriod(1, 2, TimeUnit.SECONDS);
		assertEquals(1000, p.getDuration());
	}

	/**
	 * Test method for {@link de.kerner.commons.TimePeriod#getDuration(java.util.concurrent.TimeUnit)}.
	 */
	@Test
	public final void testGetDuration02() {
		TimePeriod p = new TimePeriod(1, 2, TimeUnit.SECONDS);
		assertEquals(1, p.getDuration(TimeUnit.SECONDS));
	}

	/**
	 * Test method for {@link de.kerner.commons.TimePeriod#toString()}.
	 */
	@Test
	public final void testToString() {
		TimePeriod p = new TimePeriod(1, 2, TimeUnit.SECONDS);
		assertEquals("100", p.toString());
	}

}
