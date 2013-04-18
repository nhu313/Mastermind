package com.blogspot.nhu313.mastermind.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
		final int codeSize = GameProperties.CODE_SIZE;
		
		Component submitButton = (Component) e.getSource();

		if (submitButton.isVisible()){
			JPanel row = (JPanel) submitButton.getParent();
			ColorCode[] colors = getColorCodes(row, codeSize);

			List<ResultCode> results = game.submitGuess(new Pattern(colors));

			JPanel resultPanel = (JPanel) row.getComponent(codeSize);
			setResultCode(results, resultPanel);
			submitButton.setVisible(false);
		}
	}

	protected void setResultCode(List<ResultCode> results, JPanel resultBox) {
		int codeSize = results.size();
		for (int i = 0; i < codeSize; i++){
			Component component = resultBox.getComponent(i);
			ResultCode resultCode = results.get(i);

			if (resultCode == ResultCode.CORRECT){
				component.setBackground(Color.BLACK);
			} else if (resultCode == ResultCode.CORRECT_COLOR_ONLY){
				component.setBackground(Color.WHITE);
			}
		}
	}

	protected ColorCode[] getColorCodes(JPanel row, int codeSize) {
		ColorCode[] colors = new ColorCode[codeSize];
		for (int i = 0; i < codeSize; i++){
			Color color = row.getComponent(i).getBackground();
			colors[i] = ColorCode.valueOf(color);
		}
		return colors;
	}
}
