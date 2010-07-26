/**
 * 
 */
package de.kerner.commons.mvc.controller;

import de.kerner.commons.mvc.model.Model;
import de.kerner.commons.mvc.view.View;


/**
 * @author kerner
 *
 */
public interface Controller extends ControllerIn, ControllerOut{

	void addView(View view);

	void addModel(Model model);

}
