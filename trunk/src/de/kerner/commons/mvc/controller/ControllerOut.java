/**
 * 
 */
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

}
