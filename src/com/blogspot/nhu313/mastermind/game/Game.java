package com.blogspot.nhu313.mastermind.game;

import java.util.List;

import com.blogspot.nhu313.mastermind.GameProperties;
import com.blogspot.nhu313.mastermind.colorcode.ColorCode;
import com.blogspot.nhu313.mastermind.colorcode.ColorCodeComparator;
import com.blogspot.nhu313.mastermind.result.ResultCode;

public class Game {	
	private ColorCodeComparator codeComparator;
	private ColorCode[] secretCode;
	private int numOfGuess;

	public void setColorCodeComparator(ColorCodeComparator codeComparator){
		this.codeComparator = codeComparator;
	}
	
	public void newGame(int size) {
		secretCode = getSecretColors(size);
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

	public List<ResultCode> submitGuess(ColorCode[] guess){
		if (numOfGuess >= GameProperties.MAX_GUESS){
			return null;
		} else {
			numOfGuess++;
			return codeComparator.isSameColors(secretCode, guess);
		}
	}
	
	public ColorCode[] getSecretColorCode(){
		return secretCode;
	}
}