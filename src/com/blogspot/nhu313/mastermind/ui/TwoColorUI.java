package com.blogspot.nhu313.mastermind.ui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.blogspot.nhu313.mastermind.Game;
import com.blogspot.nhu313.mastermind.GameProperties;
import com.blogspot.nhu313.mastermind.game.GuessRow;

public class TwoColorUI {
	
	private final static int FRAME_SIZE = 600;
	private final static String FONT = "Arial";
	private final static Color BACKGROUND_BG = new Color(42, 58, 66);
	private final static Color BACKGROUND_INSIDE = new Color(136, 165, 188);
	private static final Dimension COLOR_BOX_DIMENSION = new Dimension(30, 30);

	private JFrame frame;
	private GuessRow guessRow;
	
	public void displayGUI(){
		frame.setVisible(true);
	}
	
	public void createGUI(Game game) {
        frame = createFrame();
        drawNewUI(game);
    }

	public void drawNewUI(Game game) {
		Container content = frame.getContentPane();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(BACKGROUND_BG);
		content.removeAll();
		content.add(getTitle());
        content.add(getSecretCodeRow(game));
        content.add(getGameMessage());
        content.add(getGuessRows(game));
        content.add(getColorLayout());
        content.add(getNewGameButton(game));
	}

	private Component getGameMessage() {
		return new JLabel();
	}

	private Component getNewGameButton(Game game) {
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new NewGameListener(game, this));
		return newGameButton;
	}

	private Component getSecretCodeRow(Game game) {
		JPanel panel = new JPanel();
		panel.setBackground(BACKGROUND_BG);
		
		ColorCode[] colors = game.getSecretColorPattern();
		for (int i = 0; i < colors.length; i++){
			JButton button = new JButton();
			button.setBackground(colors[i].getColor());
			button.setVisible(false);
			button.setPreferredSize(COLOR_BOX_DIMENSION);
			panel.add(button);
		}
		
		JButton reveal = new JButton("Reveal Secret Code");
		reveal.addActionListener(new RevealPatternListener());
		panel.add(reveal);
        
		return panel;
	}

	private Component getColorLayout() {
		JPanel panel = new JPanel();
		panel.setBackground(BACKGROUND_BG);
		ColorCode[] colors = ColorCode.values();
		for (int i = 0; i < GameProperties.CODE_SIZE; i++){
			JButton button = new JButton();
			button.addActionListener(new GuessColorListener(guessRow));
			button.setBackground(colors[i].getColor());
			button.setPreferredSize(COLOR_BOX_DIMENSION);
			panel.add(button);
		}		
		return panel;
	}

	private JPanel getGuessRows(Game game) {
		guessRow = new GuessRow();
		GridLayout gridLayout = new GridLayout(GameProperties.MAX_GUESS, GameProperties.CODE_SIZE + 2);
		gridLayout.setVgap(5);
		gridLayout.setHgap(5);
		
		JPanel panel = new JPanel();
		panel.setLayout(gridLayout);
		panel.setBackground(BACKGROUND_BG);

		for (int i = 0; i < GameProperties.MAX_GUESS; i++){	
			for (int row = 0; row < GameProperties.CODE_SIZE; row++){
				Button button = new Button();
				button.setPreferredSize(new Dimension(50, 50));
				panel.add(button);
			}
			panel.add(getGuessResultLayout());
			
			JButton submitButton = new JButton("Submit");
			submitButton.addActionListener(new SubmitGuessListener(game));
			submitButton.setVisible(false);
			panel.add(submitButton);
		}

		guessRow.add(panel);
		return panel;
	}
	
	private Component getGuessResultLayout(){
		final int defaultRowNum = 2;
		final int numOfCol = GameProperties.CODE_SIZE/2;
		
		JPanel panel = new JPanel();
		Border panelBorder = new EmptyBorder(1, 0, 0, 0);
		panel.setBorder(panelBorder);
		panel.setLayout(new GridLayout(defaultRowNum, GameProperties.CODE_SIZE/2));
		
		for (int col = 0; col < defaultRowNum; col++){
			for (int i = 0; i < numOfCol; i++){
				JButton resultBox = new JButton();
				resultBox.setPreferredSize(new Dimension(20, 20));
				resultBox.setBackground(BACKGROUND_INSIDE);
				panel.add(resultBox);
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
		setCommonTextProperty(title, 50, Color.LIGHT_GRAY, Font.BOLD);
		return title;
	}

	private void setCommonTextProperty(JComponent component, int fontSize, Color fontColor, int fontWeight){
		component.setFont(new Font(FONT, Font.BOLD, fontSize));
		component.setForeground(fontColor);
		component.setAlignmentX(Component.CENTER_ALIGNMENT);
		component.setFocusable(false);
	}
}