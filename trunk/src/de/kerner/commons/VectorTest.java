/**
 * 
 */
package de.kerner.commons;

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
 * @lastVisit 2010-07-27
 * 
 */
@SuppressWarnings("serial")
public class VectorTest {

	Vector<String, String, String> v, v2;
	Map<String, String> m = new LinkedHashMap<String, String>() {
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
		v = new Vector<String, String, String>();
		v2 = new Vector<String, String, String>();
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#Vector(java.lang.Object)}
	 * .
	 */
	@Test
	public final void testVectorK() {
		v = new Vector<String, String, String>("key");
		assertEquals("key", v.getIdentifier());
	}
	
	/**
	 * Test method for {@link de.kerner.commons.Vector#Vector(java.lang.Object)}
	 * .
	 */
	@Test
	public final void testVectorK01() {
		Object o = new Object();
		Vector<Object, String, String> v = new Vector<Object, String, String>(o);
		assertEquals(o, v.getIdentifier());
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#Vector(java.util.List)}.
	 */
	@Test
	public final void testVectorListOfV() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#Vector(V[])}.
	 */
	@Test
	public final void testVectorVArray() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#Vector(java.util.Map)}.
	 */
	@Test
	public final void testVectorMapOfIV() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.Vector#Vector(java.lang.Object, java.util.List)}
	 * .
	 */
	@Test
	public final void testVectorKListOfV() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.Vector#Vector(java.lang.Object, java.util.Map)}.
	 */
	@Test
	public final void testVectorKMapOfIV() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#getAtIndex(int)}.
	 */
	@Test
	public final void testGetAtIndexInt() {
		v = new Vector<String, String, String>(m);
		assertEquals("eins", v.getAtIndex(0));
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#getAtIndex(int)}.
	 */
	@Test
	public final void testGetAtIndexInt01() {
		v = new Vector<String, String, String>(m);
		assertEquals("zwei", v.getAtIndex(1));
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#getAtIndex(int)}.
	 */
	@Test
	public final void testGetAtIndexInt02() {
		v = new Vector<String, String, String>(m);
		assertEquals("drei", v.getAtIndex(2));
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#getAtIndex(int)}.
	 */
	@Test(expected = NoSuchElementException.class)
	public final void testGetAtIndexInt03() {
		v = new Vector<String, String, String>(m);
		v.getAtIndex(3);
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.Vector#getAtIndex(java.lang.Object)}.
	 */
	@Test
	public final void testGetAtIndexI() {
		v = new Vector<String, String, String>(m);
		assertEquals("drei", v.getAtIndex("3"));
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.Vector#getAtIndex(java.lang.Object)}.
	 */
	@Test
	public final void testGetAtIndexI01() {
		v = new Vector<String, String, String>(m);
		assertEquals("zwei", v.getAtIndex("2"));
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.Vector#getAtIndex(java.lang.Object)}.
	 */
	@Test(expected = NoSuchElementException.class)
	public final void testGetAtIndexI02() {
		v = new Vector<String, String, String>(m);
		v.getAtIndex("5");
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#getValues()}.
	 */
	@Test
	public final void testGetValues() {
		v = new Vector<String, String, String>(m);
		assertArrayEquals(m.values().toArray(), v.getValues().toArray());
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.Vector#setIdentifier(java.lang.Object)}.
	 */
	@Test
	public final void testSetIdentifier() {
		v = new Vector<String, String, String>(m);
		v.setIdentifier("ident");
		assertEquals("ident", v.getIdentifier());
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#getIdentifier()}.
	 */
	@Test
	public final void testGetIdentifier() {
		v = new Vector<String, String, String>("ident", m);
		assertEquals("ident", v.getIdentifier());
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#equals(java.lang.Object)}
	 * .
	 */
	@Test
	public final void testEqualsObject() {
		v = new Vector<String, String, String>("ident", m);
		v2 = new Vector<String, String, String>(m);
		v2.setIdentifier("ident");
		assertEquals(v, v2);
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#equals(java.lang.Object)}
	 * .
	 */
	@Test
	public final void testEqualsObject01() {
		v = new Vector<String, String, String>(m);
		v2 = new Vector<String, String, String>(m);
		assertEquals(v, v2);
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#equals(java.lang.Object)}
	 * .
	 */
	@Test
	public final void testEqualsObject02() {
		v = new Vector<String, String, String>();
		v2 = new Vector<String, String, String>();
		assertEquals(v, v2);
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#equals(java.lang.Object)}
	 * .
	 */
	@Test
	public final void testEqualsObject03() {
		v = new Vector<String, String, String>("ident");
		v2 = new Vector<String, String, String>();
		assertNotSame(v, v2);
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#equals(java.lang.Object)}
	 * .
	 */
	@Test
	public final void testEqualsObject04() {
		v = new Vector<String, String, String>(m);
		v2 = new Vector<String, String, String>();
		assertNotSame(v, v2);
	}

	/**
	 * Test method for {@link de.kerner.commons.Vector#equals(java.lang.Object)}
	 * .
	 */
	@Test
	public final void testEqualsObject05() {
		v = new Vector<String, String, String>(m);
		v2 = new Vector<String, String, String>("ident");
		assertNotSame(v, v2);
	}

}
