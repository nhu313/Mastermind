package com.blogspot.nhu313.mastermind.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.blogspot.nhu313.mastermind.GameProperties;

public class RevealPatternListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent event) {
		Component revealButton = (Component) event.getSource();
		JPanel parent = (JPanel) revealButton.getParent();
		for (int i = 0; i < GameProperties.CODE_SIZE; i++){
			Component child = parent.getComponent(i);
			child.setVisible(true);
		}
	}

}
