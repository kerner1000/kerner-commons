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
