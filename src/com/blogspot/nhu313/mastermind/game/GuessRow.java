package com.blogspot.nhu313.mastermind.game;

import java.awt.Color;
import java.awt.Component;
import java.util.Deque;
import java.util.LinkedList;

import javax.swing.JPanel;

import com.blogspot.nhu313.mastermind.GameProperties;

public class GuessRow {
	private static final int SUBMIT_POSITION = GameProperties.CODE_SIZE+1;
	private Deque<JPanel> guessRows = new LinkedList<JPanel>();
	protected int col = GameProperties.CODE_SIZE;
	private JPanel currentPanel;
	
	public void setColor(Color color) {
		if (col == GameProperties.CODE_SIZE){
			currentPanel = guessRows.poll();
			col = 0;
		}

		if (currentPanel != null){
			Component code = currentPanel.getComponent(col++);			
			code.setBackground(color);
			
			if (col == GameProperties.CODE_SIZE){
				showSubmitButton();
			}
		}
	}

	private void showSubmitButton() {
		if (currentPanel != null){
			Component submitButton = currentPanel.getComponent(SUBMIT_POSITION);
			submitButton.setVisible(true);
		}
	}

	public void add(JPanel panel) {
		guessRows.add(panel);
	}	
}