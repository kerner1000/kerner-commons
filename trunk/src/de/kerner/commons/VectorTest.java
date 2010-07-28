/**
 * 
 */
package de.kerner.commons;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

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
@SuppressWarnings("serial")
public class VectorTest {

	private static Vector<String, String> v, v2;

	private final static Map<String, String> m = new LinkedHashMap<String, String>() {
		{
			put("1", "eins");
			put("2", "zwei");
			put("3", "drei");
		}
	};
	
	private final static Set<String> s = new LinkedHashSet<String>(){
		{
			add("s1");
			add("s2");
			add("s3");
		}
	};
	
	private final static Set<String> ss = new LinkedHashSet<String>(){
		{
			add("ss1");
			add("ss2");
		}
	};
	
	private final static Set<String> sss = new LinkedHashSet<String>(){
		{
			add("sss1");
			add("sss2");
			add("sss3");
			add("sss4");
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
		v = new Vector<String, String>(m);
		v2 = new Vector<String, String>(m);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#Vector()}.
	 */
	@Test
	public final void testVector() {
		v = new Vector<String, String>();
		assertNotNull(v);
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#Vector(java.util.Set)}.
	 */
	@Test
	public final void testVectorSetOfV() {
		v = new Vector<String, String>(m.values());
		assertArrayEquals(m.values().toArray(), v.getAsList().toArray());
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#Vector(java.util.Map)}.
	 */
	@Test
	public final void testVectorMapOfIV() {
		v = new Vector<String, String>(m);
		assertEquals(m, v.getMap());
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#getAtIndex(int)}.
	 */
	@Test
	public final void testGetAtIndexInt() {
		assertEquals("eins", v.getAtIndex(0));
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#getAtIndex(int)}.
	 */
	@Test
	public final void testGetAtIndexInt01() {
		assertEquals("zwei", v.getAtIndex(1));
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#getAtIndex(int)}.
	 */
	@Test
	public final void testGetAtIndexInt02() {
		assertEquals("drei", v.getAtIndex(2));
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.Vector#getAtIndex(java.lang.Object)}.
	 */
	@Test
	public final void testGetAtIndexI() {
		assertEquals("drei", v.getAtIndex("3"));
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.Vector#getAtIndex(java.lang.Object)}.
	 */
	@Test
	public final void testGetAtIndexI01() {
		assertEquals("eins", v.getAtIndex("1"));
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.Vector#assign(java.lang.Object, java.lang.Object)}
	 * .
	 */
	@Test
	public final void testAssignIV() {
		v.assign("11", "eins");
		assertEquals("eins", v.getAtIndex("11"));
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.Vector#assign(java.lang.Object, java.lang.Object)}
	 * .
	 */
	@Test
	public final void testAssignIV01() {
		v.assign("22", "zwei");
		assertEquals("zwei", v.getAtIndex("22"));

	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.Vector#assign(java.lang.Object, java.lang.Object)}
	 * .
	 */
	@Test(expected = NoSuchElementException.class)
	public final void testAssignIV02() {
		v.assign("55", "funf");
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.Vector#assign(java.lang.Object, int)}.
	 */
	@Test
	public final void testAssignIInt() {
		v.assign("11", 0);
		assertEquals("eins", v.getAtIndex("11"));
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.Vector#assign(java.lang.Object, int)}.
	 */
	@Test
	public final void testAssignIInt02() {
		v.assign("22", 1);
		assertEquals("zwei", v.getAtIndex("22"));
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.Vector#assign(java.lang.Object, int)}.
	 */
	@Test(expected=NoSuchElementException.class)
	public final void testAssignIInt03() {
		v.assign("55", 5);
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.Vector#assignAll(java.util.Set)}.
	 */
	@Test
	public final void testAssignAllSetOfI() {
		v.assignAll(s);
		assertEquals("eins", v.getAtIndex("s1"));
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.Vector#assignAll(java.util.Set)}.
	 */
	@Test
	public final void testAssignAllSetOfI01() {
		v.assignAll(s);
		assertEquals("zwei", v.getAtIndex("s2"));
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.Vector#assignAll(java.util.Set)}.
	 */
	@Test
	public final void testAssignAllSetOfI02() {
		v.assignAll(s);
		assertEquals("drei", v.getAtIndex("s3"));
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.Vector#assignAll(java.util.Set)}.
	 */
	@Test
	public final void testAssignAllSetOfI03() {
		v.assignAll(ss);
		assertEquals("eins", v.getAtIndex("ss1"));
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.Vector#assignAll(java.util.Set)}.
	 */
	@Test
	public final void testAssignAllSetOfI04() {
		v.assignAll(ss);
		assertEquals("zwei", v.getAtIndex("ss2"));
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.Vector#assignAll(java.util.Set)}.
	 */
	@Test(expected=NoSuchElementException.class)
	public final void testAssignAllSetOfI05() {
		v.assignAll(ss);
		v.getAtIndex("ss3");
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.Vector#assignAll(java.util.Set)}.
	 */
	@Test
	public final void testAssignAllSetOfI06() {
		v.assignAll(sss);
		assertEquals("zwei", v.getAtIndex("sss2"));
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.Vector#assignAll(java.util.Set)}.
	 */
	@Test
	public final void testAssignAllSetOfI07() {
		v.assignAll(sss);
		assertEquals("eins", v.getAtIndex("sss1"));
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.Vector#assignAll(java.util.Set)}.
	 */
	@Test
	public final void testAssignAllSetOfI08() {
		v.assignAll(sss);
		assertEquals("drei", v.getAtIndex("sss3"));
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#getAsList()}.
	 */
	@Test
	public final void testGetAsList() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#getMap()}.
	 */
	@Test
	public final void testGetMap() {
		assertEquals(m, v.getMap());
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#equals(java.lang.Object)}
	 * .
	 */
	@Test
	public final void testEqualsObject() {
		assertEquals(v, v2);
	}
	
	/**
	 * Test method for {@link de.kerner.commons.Vector#equals(java.lang.Object)}
	 * .
	 */
	@Test
	public final void testEqualsObject01() {
		v2.assignAll(s);
		assertNotSame(v, v2);
	}

//	/**
//	 * Test method for {@link de.kerner.commons.Vector#toString()}.
//	 */
//	@Test
//	public final void testToString() {
//		fail("Not yet implemented"); // TODO
//	}

}
