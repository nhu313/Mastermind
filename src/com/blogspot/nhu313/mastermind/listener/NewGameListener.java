package com.blogspot.nhu313.mastermind.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.blogspot.nhu313.mastermind.GameProperties;
import com.blogspot.nhu313.mastermind.game.Game;
import com.blogspot.nhu313.mastermind.ui.DarkBlueUI;

public class NewGameListener implements ActionListener {
	
	private DarkBlueUI ui;
	private Game game;

	public NewGameListener(Game game, DarkBlueUI twoColorUI) {
		this.ui = twoColorUI;
		this.game = game;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		game.newGame(GameProperties.CODE_SIZE);
		ui.drawNewUI(game);
	}

}
