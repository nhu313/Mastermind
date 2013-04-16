package com.blogspot.nhu313.mastermind.ui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.blogspot.nhu313.mastermind.Game;
import com.blogspot.nhu313.mastermind.GameProperties;
import com.blogspot.nhu313.mastermind.game.GuessRow;

public class TwoColorUI {
	
	private final static int FRAME_SIZE = 450;

	private final static String FONT = "Arial";

	private JFrame frame;
	private GuessRow guessRow;
	
	public void displayGUI(){
		frame.setVisible(true);
	}
	
	public void createGUI(Game game) {
        frame = createFrame();
        Container content = frame.getContentPane();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(getTitle());
        addGuessRow(content, game);
        content.add(getColorLayout());
        
    }

	private Component getColorLayout() {
		JPanel panel = new JPanel();
		ColorCode[] colors = ColorCode.values();
		for (int i = 0; i < GameProperties.SIZE; i++){
			JButton button = new JButton();
			button.addActionListener(new GuessColorListener(guessRow));
			button.setBackground(colors[i].getColor());
			panel.add(button);
		}		
		return panel;
	}

	private void addGuessRow(Container content, Game game) {
		guessRow = new GuessRow();
		for (int i = 0; i < GameProperties.MAX_GUESS; i++){
			JPanel panel = new JPanel();
			for (int row = 0; row < GameProperties.SIZE; row++){
				panel.add(new Button("B"));
			}
			panel.add(getGuessResultLayout());
			
			Button submitButton = new Button("Submit");
			submitButton.addActionListener(new SubmitGuessListener(game));
			panel.add(submitButton);
			
			content.add(panel);
			guessRow.add(panel);
		}
	}
	
	private Component getGuessResultLayout(){
		final int defaultRowNum = 2;
		final int numOfCol = GameProperties.SIZE/2;
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(defaultRowNum, GameProperties.SIZE/2));
		

		for (int col = 0; col < defaultRowNum; col++){
			for (int i = 0; i < numOfCol; i++){
				panel.add(new Button("result"));
			}
		}
		
		return panel;
	}

	private JFrame createFrame() {
		JFrame frame = new JFrame(GameProperties.GAME_NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_SIZE, FRAME_SIZE);
		return frame;
	}
	
	private Component getTitle() {
		JLabel title = new JLabel(GameProperties.GAME_NAME);
		setCommonComponentProperty(title, 50, ColorCode.GREEN.getColor(), Font.BOLD);
		return title;
	}

	private void setCommonComponentProperty(JComponent component, int fontSize, Color fontColor, int fontWeight){
		component.setFont(new Font(FONT, Font.PLAIN, fontSize));
		component.setForeground(fontColor);
		component.setAlignmentX(Component.CENTER_ALIGNMENT);
		component.setFocusable(false);
	}
}