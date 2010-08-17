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

import java.util.Collection;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * 
 * <p>
 * {@code ViewManager} is an implementation prototype for {@link AbstractView},
 * that further divides the View component into several different View types
 * that are all managed by the {@code ViewManger}.
 * </p>
 * <p>
 * A {@code ViewManager} is registered as a {@code View} proxy to the
 * {@code Controller} component.
 * </p>
 * 
 * <p>
 * TODO example
 * </p>
 * 
 * @autor Alexander Kerner
 * 
 */
public abstract class ViewManager extends AbstractView {
	
	// lastVisit 2010-08-15

	public void destroyView() {
		for (View v : getViews()) {
			v.destroyView();
		}
	}

	public void hideView() {
		for (View v : getViews()) {
			v.hideView();
		}
	}

	public void showView() {
		for (View v : getViews()) {
			v.showView();
		}
	}

	/**
	 * 
	 * <p>
	 * get all managed views.
	 * </p>
	 *
	 * @return all views that are registered to this {@code ViewManager}
	 * @see View
	 */
	public abstract Collection<View> getViews();

}
