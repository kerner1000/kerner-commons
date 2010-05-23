/**
 * 
 */
package de.kerner.commons.collection.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alex
 *
 */
public class TestStuff {
	
	private final int numElements = 3;
	
	private final List<Integer> elements;
	
	public TestStuff() {
		elements = new ArrayList<Integer>(){
			{
				add(1);
				add(2);
				add(3);
			}
		};
	}

	public int getNumelements() {
		return numElements;
	}

	public List<Integer> getElements() {
		return elements;
	}	
}
