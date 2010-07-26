/**
 * 
 */
package de.kerner.commons.mvc.view;

import java.util.ArrayList;
import java.util.Collection;

import de.kerner.commons.mvc.controller.ControllerIn;
import de.kerner.commons.mvc.controller.ControllerOut;

/**
 * @author kerner
 *
 */
public abstract class AbstractView implements View {
	
	Collection<ControllerIn> controls = new ArrayList<ControllerIn>();
	
	public AbstractView(ControllerIn... control){
		if(control == null || control.length == 0)
			throw new NullPointerException();
		for(ControllerIn c : control)
			controls.add(c);
	}

}
