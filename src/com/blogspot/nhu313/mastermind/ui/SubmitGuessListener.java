package com.blogspot.nhu313.mastermind.ui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.blogspot.nhu313.mastermind.Game;
import com.blogspot.nhu313.mastermind.GameProperties;
import com.blogspot.nhu313.mastermind.pattern.Pattern;
import com.blogspot.nhu313.mastermind.pattern.ResultCode;

public class SubmitGuessListener implements ActionListener{
	
	private Game game;

	public SubmitGuessListener(Game game) {
		this.game = game;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Button button = (Button) e.getSource();
		
		JPanel row = (JPanel) button.getParent();
		ColorCode[] colors = getColorCodes(row);
		
		ResultCode[] results = game.submitGuess(new Pattern(colors));
		setResultCode(results, row);
	}

	private void setResultCode(ResultCode[] results, JPanel parent) {
		JPanel resultBox = (JPanel) parent.getComponent(GameProperties.SIZE);
		for (int i = 0; i < results.length; i++){
			Component component = resultBox.getComponent(i);
			ResultCode resultCode = results[i];
			
			if (resultCode == ResultCode.INCORRECT){
				break;
			}
			
			ColorCode color = null;

			if (resultCode == ResultCode.CORRECT){
				color = ColorCode.GREEN;
			} else if (resultCode == ResultCode.CORRECT_COLOR_ONLY){
				color = ColorCode.YELLOW;
			}
			
			component.setBackground(color.getColor());
		}
	}

	private ColorCode[] getColorCodes(JPanel row) {
		ColorCode[] colors = new ColorCode[GameProperties.SIZE];
		for (int i = 0; i < GameProperties.SIZE; i++){
			Color color = row.getComponent(i).getBackground();
			colors[i] = ColorCode.valueOf(color);
		}
		return colors;
	}
}
