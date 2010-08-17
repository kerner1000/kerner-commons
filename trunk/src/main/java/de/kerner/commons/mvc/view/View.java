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

import javax.swing.JFrame;

/**
 * <p>
 * View component in the MVC pattern.
 * </p>
 * <p>
 * TODO Example of usage
 * </p>
 * 
 * @author Alexander Kerner
 * 
 */
public interface View extends ViewIn, ViewOut {

	// lastVisit 2010-08-15
	
	/**
	 * 
	 * <p>
	 * Enable this view. 
	 * </p>
	 * <p>
	 * A Swing view for example will call {@link JFrame#show()}.
	 * </p>
	 * 
	 */
	void showView();

	/**
	 * <p>
	 * Disable this view temporally. 
	 * </p>
	 * <p>
	 * A Swing view for example will call {@link JFrame#hide()}.
	 * </p>
	 * 
	 * 
	 */
	void hideView();

	/**
	 * 
	 * <p>
	 * Destroy this {@codeView}.
	 * </p>
	 * <p>
	 * A Swing view for example will call {@link JFrame#dispose()}.
	 * </p>
	 * 
	 * 
	 */
	void destroyView();

}
