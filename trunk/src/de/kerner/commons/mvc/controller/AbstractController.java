/**
 * 
 */
package de.kerner.commons.mvc.controller;

import java.util.ArrayList;
import java.util.Collection;

import de.kerner.commons.mvc.model.Model;
import de.kerner.commons.mvc.view.View;

/**
 * @author kerner
 *
 */
public abstract class AbstractController implements Controller {
	
	private final Collection<Model> m = new ArrayList<Model>();
	 
	private final Collection<View> v = new ArrayList<View>();
	
	public void addModel(Model model) {
		synchronized (m) {
			m.add(model);
		}
		
	}
	
	public void addView(View view) {
		synchronized (v) {
			v.add(view);
		}
	}

}
