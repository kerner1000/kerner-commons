/**
 * 
 */
package de.kerner.commons.mvc.view;

/**
 * <p>
 * TODO description
 * </p>
 * <p>
 * TODO Example of usage
 * </p>
 * 
 * @author Alexander Kerner
 * 
 */
public interface View extends ViewIn, ViewOut{
	
	void showView();

	void hideView();

	void destroyView();
	
}
