package de.kerner.commons.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class DefaultBuilder {
	
	private final JPanel content = new JPanel();
	
	DefaultBuilder(){
		content.setLayout(new BorderLayout());
	}

	public DefaultBuilder addMainComponent(JComponent component) {
		content.add(component, BorderLayout.CENTER);
		return this;
	}
	
	public DefaultBuilder addButtomComponent(JComponent component) {
		content.add(component, BorderLayout.SOUTH);
		return this;
	}

	public JComponent build() {
		return content;
	}

	public DefaultBuilder addRightComponent(JComponent component) {
		content.add(component, BorderLayout.EAST);
		return this;
	}

}
