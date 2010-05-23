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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.kerner.commons.collection.ExtendedIterator;

/**
 * <p>
 * TODO description
 * </p>
 * <p>
 * TODO Example of usage
 * </p>
 * 
 * @author Alexander Kerner
 * 
 */
public class ExtendedIteratorTest {

	private TestStuff stuff;
	private ExtendedIterator<Integer> i;

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
		stuff = new TestStuff();
		i =new ExtendedIterator<Integer>(stuff
				.getElements());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		i = null;
		stuff = null;
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#ExtendedIterator(java.util.Collection)}
	 * .
	 */
	@Test
	public final void testExtendedIterator() {
		final ExtendedIterator<Integer> i = new ExtendedIterator<Integer>(stuff
				.getElements());
		assertNotNull(i);
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#ExtendedIterator(java.util.Collection)}
	 * .
	 */
	@Test(expected = NullPointerException.class)
	public final void testExtendedIterator1() {
		@SuppressWarnings("unused")
		final ExtendedIterator<Integer> i = new ExtendedIterator<Integer>(null);
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#contains(java.lang.Object)}
	 * .
	 */
	@Test
	public final void testContains() {
		assertTrue(i.contains(stuff.getElements().get(0)));		
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#contains(java.lang.Object)}
	 * .
	 */
	@Test
	public final void testContains1() {
		assertTrue(i.contains(stuff.getElements().get(1)));		
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#contains(java.lang.Object)}
	 * .
	 */
	@Test
	public final void testContains2() {
		assertTrue(i.contains(stuff.getElements().get(2)));		
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#contains(java.lang.Object)}
	 * .
	 */
	@Test
	public final void testContains3() {
		assertFalse(i.contains(new Integer(4)));		
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#getCurrent()}.
	 */
	@Test
	public final void testGetCurrent() {
		assertEquals(stuff.getElements().get(0), i.getCurrent());
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#getCurrent()}.
	 */
	@Test
	public final void testGetCurrent1() {
		i.next();
		assertEquals(stuff.getElements().get(1), i.getCurrent());
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#getCurrent()}.
	 */
	@Test
	public final void testGetCurrent2() {
		i.next();
		i.next();
		assertEquals(stuff.getElements().get(2), i.getCurrent());
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#getSize()}.
	 */
	@Test
	public final void testGetSize() {
		assertEquals(stuff.getElements().size(), i.getSize());
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#getSize()}.
	 */
	@Test
	public final void testGetSize1() {
		i.next();
		assertEquals(stuff.getElements().size(), i.getSize());
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#getSize()}.
	 */
	@Test
	public final void testGetSize2() {
		i.add(new Integer(4));
		assertEquals(stuff.getElements().size() + 1, i.getSize());
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#getSize()}.
	 */
	@Test
	public final void testGetSize3() {
		i.remove(new Integer(1));
		assertEquals(stuff.getElements().size() - 1, i.getSize());
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#getSize()}.
	 */
	@Test
	public final void testGetSize4() {
		i.remove(new Integer(1));
		i.remove(new Integer(2));
		i.remove(new Integer(3));
		assertEquals(0, i.getSize());
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#hasNext()}.
	 */
	@Test
	public final void testHasNext() {
		assertTrue(i.hasNext());
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#hasNext()}.
	 */
	@Test
	public final void testHasNext1() {
		i.remove(new Integer(1));
		i.remove(new Integer(2));
		i.remove(new Integer(3));
		assertFalse(i.hasNext());
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#isEmpty()}.
	 */
	@Test
	public final void testIsEmpty() {
		i.remove(new Integer(1));
		i.remove(new Integer(2));
		i.remove(new Integer(3));
		assertTrue(i.isEmpty());
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#isEmpty()}.
	 */
	@Test
	public final void testIsEmpty1() {
		assertFalse(i.isEmpty());
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#next()}.
	 */
	@Test
	public final void testNext() {
		i.next();
		assertEquals(stuff.getElements().get(1), i.getCurrent());
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#next()}.
	 */
	@Test
	public final void testNext1() {
		i.next();
		i.next();
		assertEquals(stuff.getElements().get(2), i.getCurrent());
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#next()}.
	 */
	@Test
	public final void testNext2() {
		i.next();
		i.next();
		i.next();
		assertNull(i.getCurrent());
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#next()}.
	 */
	@Test
	public final void testNext3() {
		i.next();
		i.next();
		i.next();
		i.next();
		assertNull(i.getCurrent());
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#next()}.
	 */
	@Test
	public final void testNext4() {
		i.next();
		i.next();
		i.next();
		i.next();
		i.next();
		assertNull(i.getCurrent());
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#reset()}.
	 */
	@Test
	public final void testReset() {
		i.next();
		i.reset();
		assertEquals(stuff.getElements().get(0), i.getCurrent());
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#reset()}.
	 */
	@Test
	public final void testReset1() {
		i.next();
		i.next();
		i.reset();
		assertEquals(stuff.getElements().get(0), i.getCurrent());
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#reset()}.
	 */
	@Test
	public final void testReset2() {
		i.next();
		i.next();
		i.next();
		i.reset();
		assertEquals(stuff.getElements().get(0), i.getCurrent());
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#add(java.lang.Object)}
	 * .
	 */
	@Test
	public final void testAdd() {
		i.add(new Integer(4));
		assertTrue(i.contains(new Integer(4)));
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#add(java.lang.Object)}
	 * .
	 */
	@Test
	public final void testAdd1() {
		i.add(new Integer(4));
		i.next();
		i.next();
		i.next();
		assertEquals(new Integer(4),i.getCurrent());
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#add(java.lang.Object)}
	 * .
	 */
	@Test
	public final void testAdd2() {
		i.next();
		i.add(new Integer(4));
		i.next();
		i.reset();
		i.next();
		i.next();
		i.next();
		assertEquals(new Integer(4),i.getCurrent());
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#remove(java.lang.Object)}
	 * .
	 */
	@Test
	public final void testRemove() {
		i.remove(new Integer(1));
		assertFalse(i.contains(new Integer(1)));
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#remove(java.lang.Object)}
	 * .
	 */
	@Test
	public final void testRemove1() {
		i.remove(new Integer(1));
		assertEquals(new Integer(2), i.getCurrent());
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.collection.ExtendedIterator#remove(java.lang.Object)}
	 * .
	 */
	@Test
	public final void testRemove2() {
		i.remove(new Integer(1));
		i.remove(new Integer(2));
		assertEquals(new Integer(3), i.getCurrent());
	}

}
