package com.blogspot.nhu313.mastermind.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.blogspot.nhu313.mastermind.Game;
import com.blogspot.nhu313.mastermind.GameProperties;

public class NewGameListener implements ActionListener {
	
	private TwoColorUI ui;
	private Game game;

	public NewGameListener(Game game, TwoColorUI twoColorUI) {
		this.ui = twoColorUI;
		this.game = game;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		game.newGame(GameProperties.CODE_SIZE);
		ui.createGUI(game);
		ui.drawNewUI(game);
	}

}
