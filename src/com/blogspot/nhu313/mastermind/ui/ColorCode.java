package com.blogspot.nhu313.mastermind.ui;

import java.awt.Color;

public enum ColorCode {
	BLUE(7, 21, 205), 
	YELLOW(255, 177, 0), 
	GREEN(31, 148, 0), 
	RED(224, 7, 7), 
	BLACK(0, 0, 0),
	PURPLE(119, 0, 60), 
	WHITE(255, 255, 255);
	
	private final Color color;
	
	private ColorCode(int red, int green, int blue){
		color = new Color(red, green, blue);
	}
	
	public Color getColor(){
		return color;
	}

	public static ColorCode valueOf(Color color) {
		//TODO Be smarter
		ColorCode[] values = ColorCode.values();
		ColorCode code = null;
		
		for (ColorCode value : values){
			if (value.getColor().equals(color)){
				code = value;
				break;
			}
		}
		
		return code;
	}
}
