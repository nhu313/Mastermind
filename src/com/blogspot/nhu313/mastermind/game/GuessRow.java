package com.blogspot.nhu313.mastermind.game;

import java.awt.Color;
import java.awt.Component;
import java.util.Deque;
import java.util.LinkedList;

import javax.swing.JPanel;

import com.blogspot.nhu313.mastermind.GameProperties;

public class GuessRow {
	private Deque<JPanel> guessRows = new LinkedList<JPanel>();
	private int col = GameProperties.SIZE;
	private JPanel currentPanel;
	
	public void setColor(Color color) {
		if (col == GameProperties.SIZE){
			currentPanel = guessRows.pop();
			col = 0;
		}

		Component code = currentPanel.getComponent(col++);
		code.setBackground(color);
	}

	public void add(JPanel panel) {
		guessRows.add(panel);
	}	
}
