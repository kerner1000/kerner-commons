/**
 * 
 */
package de.kerner.commons.mvc.model;

import java.util.ArrayList;
import java.util.Collection;

import de.kerner.commons.mvc.controller.ControllerOut;

/**
 * @author kerner
 *
 */
public class AbstractModel implements Model {
	
	private Collection<ControllerOut> c = new ArrayList<ControllerOut>();
	
	public AbstractModel(ControllerOut... control) {
		if(control == null || control.length == 0)
			throw new NullPointerException();
		for(ControllerOut c : control)
			this.c.add(c);
	}
	
	public void addController(ControllerOut control) {
		this.c.add(control);
	}

}
