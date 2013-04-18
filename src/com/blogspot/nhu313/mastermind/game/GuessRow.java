package com.blogspot.nhu313.mastermind.game;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;

import com.blogspot.nhu313.mastermind.GameProperties;

public class GuessRow {
	protected final static int NUM_COMPONENT_IN_ROW = GameProperties.CODE_SIZE + 2;
	private final static int NUM_COMPONENT_IN_PANEL = NUM_COMPONENT_IN_ROW * GameProperties.MAX_GUESS;
	
	protected int col = 0;
	private JPanel currentPanel;
	
	public void setColor(Color color) {
		if (col < NUM_COMPONENT_IN_PANEL){
			Component code = currentPanel.getComponent(col++);
			code.setBackground(color);

			if (col == GameProperties.CODE_SIZE || isEndOfRow()){
				col += 2;
				showSubmitButton();
			}
		}
	}

	private boolean isEndOfRow(){
		boolean result = false;
		if (((col + 2) % NUM_COMPONENT_IN_ROW) == 0){
			result = true;
		}
		return result;
	}

	private void showSubmitButton() {
		if (col < NUM_COMPONENT_IN_PANEL){
			Component submitButton = currentPanel.getComponent(col - 1);
			submitButton.setVisible(true);
		}
	}

	public void add(JPanel panel) {
		currentPanel = panel;
	}	
}
