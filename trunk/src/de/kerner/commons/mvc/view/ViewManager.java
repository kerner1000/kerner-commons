/**
 * 
 */
package de.kerner.commons.mvc.view;

import java.util.Collection;

/**
 * @author kerner
 *
 */
public abstract class ViewManager extends AbstractView {

	public void destroyView() {
		for(View v : getViews()){
			v.destroyView();
		}
	}

	public void hideView() {
		for(View v : getViews()){
			v.hideView();
		}
	}

	public void showView() {
		for(View v : getViews()){
			v.showView();
		}
	}
	
	public abstract Collection<View> getViews();

}
