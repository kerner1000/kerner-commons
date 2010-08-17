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
package de.kerner.commons.mvc.controller;

/**
 * <p>
 * {@code ControllerOut} is a controller component, that takes events coming
 * from [@code Model}. direction of events is:
 * </p>
 * <p>
 * {@code Model} -> {@code ControllerOut} -> {@code View}
 * </p>
 * <p>
 * Notice that all events must be dropped to the AWT event dispatch thread!
 * </p>
 * TODO Example of usage </p>
 * 
 * @author Alexander Kerner
 * @see Model
 * @see Controller
 * @see ControllerIn
 * 
 */
public interface ControllerOut {

	void infoMsg(String msg);

	void warnMsg(String msg);

	void errorMsg(String msg);

}
