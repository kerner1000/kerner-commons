/**
 * 
 */
package de.kerner.commons.collection;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;
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
 * @lastVisit 2010-08-04
 * 
 */
@SuppressWarnings("serial")
public class MappedVectorMutableTest {
	
	private static MappedVectorMutable<String> v1;
	
	private final static Map<Object, String> m = new LinkedHashMap<Object, String>() {
		{
			put("1", "eins");
			put("2", "zwei");
			put("3", "drei");
		}
	};

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
		v1 = new MappedVectorMutable<String>(m);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		v1 = null;
	}

	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#MappedVectorMutable()}.
	 */
	@Test
	public final void testMappedVectorMutable() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#MappedVectorMutable(int)}.
	 */
	@Test
	public final void testMappedVectorMutableInt() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#MappedVectorMutable(java.util.Collection)}.
	 */
	@Test
	public final void testMappedVectorMutableCollectionOfV() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#MappedVectorMutable(int, int)}.
	 */
	@Test
	public final void testMappedVectorMutableIntInt() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#add(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testAddObjectV() {
		v1.add("4","vier");
		assertEquals("vier", v1.getAtIndex("4"));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#add(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testAddObjectV01() {
		v1.add("4","vier");
		assertEquals("vier", v1.getAtIndex(3));
	}

	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#add(int, java.lang.Object)}.
	 */
	@Test
	public final void testAddIntV() {
		v1.add(3,"vier");
		assertEquals("vier", v1.getAtIndex(3));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#add(int, java.lang.Object)}.
	 */
	@Test(expected=NoSuchElementException.class)
	public final void testAddIntV01() {
		v1.add(3,"vier");
		assertEquals("vier", v1.getAtIndex("4"));
	}

	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#add(int, java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testAddIntObjectV() {
		v1.add(3,"4","vier");
		assertEquals("vier", v1.getAtIndex("4"));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#add(int, java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testAddIntObjectV01() {
		v1.add(0,"4","vier");
		assertEquals("vier", v1.getAtIndex("4"));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#add(int, java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testAddIntObjectV02() {
		v1.add(0,"4","vier");
		assertEquals("eins", v1.getAtIndex(1));
	}

	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(int, java.lang.Object)}.
	 */
	@Test
	public final void testReplaceAtIndexIntV() {
		v1.replaceAtIndex(0, "vier");
		assertEquals("vier", v1.getAtIndex(0));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(int, java.lang.Object)}.
	 */
	@Test
	public final void testReplaceAtIndexIntV02() {
		v1.replaceAtIndex(0, "vier");
		assertTrue(v1.containsKey("1"));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(int, java.lang.Object)}.
	 */
	@Test
	public final void testReplaceAtIndexIntV03() {
		v1.replaceAtIndex(0, "vier");
		assertFalse(v1.containsValue("eins"));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(int, java.lang.Object)}.
	 */
	@Test
	public final void testReplaceAtIndexIntV04() {
		v1.replaceAtIndex(0, "vier");
		assertEquals("zwei", v1.getAtIndex(1));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(int, java.lang.Object)}.
	 */
	@Test
	public final void testReplaceAtIndexIntV05() {
		v1.replaceAtIndex(0, "vier");
		assertEquals("zwei", v1.getAtIndex("2"));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(int, java.lang.Object)}.
	 */
	@Test
	public final void testReplaceAtIndexIntV06() {
		v1.replaceAtIndex(0, "vier");
		assertEquals("drei", v1.getAtIndex(2));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(int, java.lang.Object)}.
	 */
	@Test
	public final void testReplaceAtIndexIntV07() {
		v1.replaceAtIndex(0, "vier");
		assertEquals("drei", v1.getAtIndex("3"));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(int, java.lang.Object)}.
	 */
	@Test(expected=NoSuchElementException.class)
	public final void testReplaceAtIndexIntV08() {
		v1.replaceAtIndex(0, "vier");
		v1.getAtIndex(3);
	}

	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(int, java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testReplaceAtIndexIntObjectV() {
		v1.replaceAtIndex(0, "4", "vier");
		assertEquals("vier", v1.getAtIndex("4"));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(int, java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testReplaceAtIndexIntObjectV01() {
		v1.replaceAtIndex(0, "4", "vier");
		assertEquals("vier", v1.getAtIndex(0));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(int, java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testReplaceAtIndexIntObjectV02() {
		v1.replaceAtIndex(0, "4", "vier");
		assertTrue(v1.containsKey("4"));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(int, java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testReplaceAtIndexIntObjectV03() {
		v1.replaceAtIndex(0, "4", "vier");
		assertFalse(v1.containsValue("eins"));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(int, java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testReplaceAtIndexIntObjectV04() {
		v1.replaceAtIndex(0, "4", "vier");
		assertEquals("zwei", v1.getAtIndex(1));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(int, java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testReplaceAtIndexIntObjectV05() {
		v1.replaceAtIndex(0, "4", "vier");
		assertEquals("zwei", v1.getAtIndex("2"));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(int, java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testReplaceAtIndexIntObjectV06() {
		v1.replaceAtIndex(0, "4", "vier");
		assertEquals("drei", v1.getAtIndex(2));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(int, java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testReplaceAtIndexIntObjectV07() {
		v1.replaceAtIndex(0, "4", "vier");
		assertEquals("drei", v1.getAtIndex("3"));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(int, java.lang.Object, java.lang.Object)}.
	 */
	@Test(expected=NoSuchElementException.class)
	public final void testReplaceAtIndexIntObjectV08() {
		v1.replaceAtIndex("1", "vier");
		v1.getAtIndex(3);
	}
	
	

	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testReplaceAtIndexObjectV() {
		v1.replaceAtIndex("1", "vier");
		assertEquals("vier", v1.getAtIndex("1"));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testReplaceAtIndexObjectV01() {
		v1.replaceAtIndex("1", "vier");
		assertEquals("vier", v1.getAtIndex(0));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testReplaceAtIndexObjectV02() {
		v1.replaceAtIndex("1", "vier");
		assertTrue(v1.containsKey("1"));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testReplaceAtIndexObjectV03() {
		v1.replaceAtIndex("1", "vier");
		assertFalse(v1.containsValue("eins"));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testReplaceAtIndexObjectV04() {
		v1.replaceAtIndex("1", "vier");
		assertEquals("zwei", v1.getAtIndex(1));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testReplaceAtIndexObjectV05() {
		v1.replaceAtIndex("1", "vier");
		assertEquals("zwei", v1.getAtIndex("2"));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testReplaceAtIndexObjectV06() {
		v1.replaceAtIndex("1", "vier");
		assertEquals("drei", v1.getAtIndex(2));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testReplaceAtIndexObjectV07() {
		v1.replaceAtIndex("1", "vier");
		assertEquals("drei", v1.getAtIndex("3"));
	}
	
	/**
	 * Test method for {@link de.kerner.commons.collection.MappedVectorMutable#replaceAtIndex(java.lang.Object, java.lang.Object)}.
	 */
	@Test(expected=NoSuchElementException.class)
	public final void testReplaceAtIndexObjectV08() {
		v1.replaceAtIndex("1", "vier");
		v1.getAtIndex(3);
	}

}
