package com.blogspot.nhu313.mastermind;

import java.util.List;

import com.blogspot.nhu313.mastermind.pattern.Pattern;
import com.blogspot.nhu313.mastermind.pattern.ResultCode;
import com.blogspot.nhu313.mastermind.ui.ColorCode;

public class Game {	
	private Pattern pattern;
	private int numOfGuess;

	public void newGame(int size) {
		ColorCode[] colorCodes = getSecretColors(size);
		pattern = new Pattern(colorCodes);
		numOfGuess = 0;
	}
	
	private ColorCode[] getSecretColors(int size) {
		ColorCode[] colorCodes = new ColorCode[size];
		ColorCode[] availableColors = ColorCode.values();
		
		for (int i = 0; i < size; i++){
			int randomColorIndex = getRandomNumber(size);
			ColorCode color = availableColors[randomColorIndex];
			colorCodes[i] = color;
		}
		
		return colorCodes;
	}

	protected int getRandomNumber(int size) {
		double randomNumber = Math.random() * 10;
		return (int) (randomNumber % size);
	}

	public List<ResultCode> submitGuess(Pattern guess){
		if (numOfGuess >= GameProperties.MAX_GUESS){
			return null;
		} else {
			numOfGuess++;
			return pattern.isSamePattern(guess);
		}
	}
	
	public ColorCode[] getSecretColorPattern(){
		return pattern.getSecretCode();
	}
}
