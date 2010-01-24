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

package de.kerner.commons.collection.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.kerner.commons.collection.CollectionUtils;

/**
 * @author Alexander Kerner
 *
 */
public class CollectionUtilsTest {

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
	 * Test method for {@link de.kerner.commons.collection.CollectionUtils#flattenMap(java.util.Map)}.
	 */
	@Test
	public final void testFlattenMap01() {
		final Collection<String> values = new ArrayList<String>();
		values.add("B");
		values.add("C");
		values.add("D");
		final Map<String, Collection<String>> map = new LinkedHashMap<String, Collection<String>>();
		map.put("A", values);
		final Collection<String> reference = new ArrayList<String>();
		reference.add("A");
		reference.add("B");
		reference.add("C");
		reference.add("D");
		
		assertArrayEquals(reference.toArray(), CollectionUtils.flattenMap(map).toArray());
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.CollectionUtils#flattenMap(java.util.Map)}.
	 */
	@Test
	public final void testFlattenMap02() {
		final Collection<String> values0 = new ArrayList<String>();
		values0.add("B");
		values0.add("C");
		final Collection<String> values1 = new ArrayList<String>();
		values1.add("E");
		values1.add("F");
		final Map<String, Collection<String>> map = new LinkedHashMap<String, Collection<String>>();
		map.put("A", values0);
		map.put("D", values1);
		final Collection<String> reference = new LinkedHashSet<String>();
		reference.add("A");
		reference.add("B");
		reference.add("C");
		reference.add("D");
		reference.add("E");
		reference.add("F");
		
		assertArrayEquals(reference.toArray(), CollectionUtils.flattenMap(map).toArray());
	}

}
