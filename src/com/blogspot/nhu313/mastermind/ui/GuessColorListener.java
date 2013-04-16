package com.blogspot.nhu313.mastermind.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.blogspot.nhu313.mastermind.game.GuessRow;

public class GuessColorListener implements ActionListener{
	private GuessRow guessRow;
	
	public GuessColorListener(GuessRow guessRow) {
		this.guessRow = guessRow;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		JButton guess = (JButton) event.getSource();
		guessRow.setColor(guess.getBackground());
	}

}
