package com.blogspot.nhu313.mastermind;

import com.blogspot.nhu313.mastermind.colorcode.ColorCodeComparator;
import com.blogspot.nhu313.mastermind.game.Game;
import com.blogspot.nhu313.mastermind.ui.DarkBlueUI;

public class Mastermind {
	
	public static void main(String[] args){
    	Game game = new Game();
    	game.newGame(GameProperties.CODE_SIZE);
    	game.setColorCodeComparator(new ColorCodeComparator());

		DarkBlueUI gui = new DarkBlueUI();
		gui.createGUI(game);
		gui.displayGUI();
	}
}
