/**********************************************************************
Copyright (c) 2009-2010 Alexander Kerner. All rights reserved.
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
package de.kerner.commons.mvc.view;

import java.util.ArrayList;
import java.util.Collection;

import de.kerner.commons.mvc.controller.ControllerIn;

/**
 * 
 * <p>
 * {@code AbstractView} is a prototype implementation for {@link View}.
 * </p>
 * 
 * <p>
 * TODO example
 * </p>
 *
 * @autor Alexander Kerner
 *
 */
public abstract class AbstractView implements View {
	
	// lastVisit 2010-08-15
	
	private final Collection<ControllerIn> controls = new ArrayList<ControllerIn>();
	
	public AbstractView(ControllerIn... control){
		if(control == null || control.length == 0)
			throw new NullPointerException();
		for(ControllerIn c : control)
			controls.add(c);
	}

}
