package com.blogspot.nhu313.mastermind;

import com.blogspot.nhu313.mastermind.ui.TwoColorUI;

public class Mastermind {
	
	public static void main(String[] args){
    	Game game = new Game();
    	game.newGame(GameProperties.CODE_SIZE);

		TwoColorUI gui = new TwoColorUI();
		gui.createGUI(game);
		gui.displayGUI();
	}
}
