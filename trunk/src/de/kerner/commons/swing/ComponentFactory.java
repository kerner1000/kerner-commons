package de.kerner.commons.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComponentFactory {
	
	private ComponentFactory(){
		
	}
	
	public static JComponent makeTextPanel(String text) {
		// TODO not double buffered?
        final JPanel panel = new JPanel(false);
        
        final JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
	
	public static JComponent makeContentPanel(JComponent content) {
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
        panel.add(content);
        return panel;
    }
	
	public static DefaultBuilder getDefaultBuilder(){
		return new DefaultBuilder();
	}
	
	public static ButtonBuilder getDButtonBuilder(){
		return new ButtonBuilder();
	}
	
	

}
