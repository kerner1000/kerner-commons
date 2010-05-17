package de.kerner.commons.swing;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class ButtonBuilder {
	
	private final JPanel content = new JPanel();
	private final static int vGap = 0;
	private final static int hGap = 0;
	
	ButtonBuilder(){
		final GridLayout g = new GridLayout();
		g.setHgap(hGap);
		g.setVgap(vGap);
		content.setLayout(g);
	}

	public ButtonBuilder add(JButton button){
		content.add(button);
		return this;
	}

	public JComponent build() {
		return content;
	}

}
