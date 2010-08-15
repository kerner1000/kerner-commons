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
package de.kerner.commons.mvc.model;

import de.kerner.commons.mvc.controller.ControllerOut;

/**
 * 
 * <p>
 * A {@code Model} represents the model component in the MVC pattern.
 * </p>
 * 
 * <p>
 * TODO example
 * </p>
 *
 * @autor Alexander Kerner
 *
 */
public interface Model {
	
	/**
	 * 
	 * <p>
	 * Register a controller to this {@code Model}.
	 * </p>
	 * 
	 * <p>
	 * TODO example
	 * </p>
	 *
	 * @param control
	 */
	void addController(ControllerOut control);

	/**
	 * 
	 * <p>
	 * Shut down this {@code Model}.
	 * </p>
	 * 
	 * <p>
	 * TODO example
	 * </p>
	 *
	 */
	void shutdown();
}
