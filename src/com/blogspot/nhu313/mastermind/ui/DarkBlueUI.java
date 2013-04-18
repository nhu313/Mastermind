package com.blogspot.nhu313.mastermind.ui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.blogspot.nhu313.mastermind.Game;
import com.blogspot.nhu313.mastermind.GameProperties;
import com.blogspot.nhu313.mastermind.game.GuessRow;

public class DarkBlueUI {
	
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
        BoxLayout mainLayout = new BoxLayout(content, BoxLayout.Y_AXIS);
        content.setLayout(mainLayout);
        content.setBackground(BACKGROUND_BG);
		content.removeAll();
		
		content.add(getTitle());
        content.add(getSecretCode(game));
        content.add(getGuessRows(game));
        content.add(getColorSelections());
        content.add(getNewGame(game));
        
        content.revalidate();
        content.repaint();
	}

	private Component getNewGame(Game game) {
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new NewGameListener(game, this));
		return newGameButton;
	}

	private Component getSecretCode(Game game) {
		JPanel panel = getDefaultPanel(new FlowLayout());

		addSecretColor(game, panel);		
		panel.add(getRevealCode());
        
		return panel;
	}

	private void addSecretColor(Game game, JPanel panel) {
		ColorCode[] colors = game.getSecretColorPattern();
		for (int i = 0; i < colors.length; i++){
			JButton button = new JButton();
			button.setVisible(false);
			setColorBoxAttributes(button, colors[i].getColor());
			panel.add(button);
		}
	}

	private JButton getRevealCode() {
		JButton reveal = new JButton("Reveal Secret Code");
		reveal.addActionListener(new RevealPatternListener());
		return reveal;
	}

	private Component getColorSelections() {
		JPanel panel = new JPanel();
		panel.setBackground(BACKGROUND_BG);

		ColorCode[] colors = ColorCode.values();
		for (int i = 0; i < GameProperties.CODE_SIZE; i++){
			JButton button = new JButton();
			button.addActionListener(new GuessColorListener(guessRow));
			setColorBoxAttributes(button, colors[i].getColor());
			panel.add(button);
		}
		
		return panel;
	}

	private void setColorBoxAttributes(JButton button, Color color) {
		button.setBackground(color);
		button.setPreferredSize(COLOR_BOX_DIMENSION);
	}

	private JPanel getGuessRows(Game game) {
		guessRow = new GuessRow();
		GridLayout parentLayout = new GridLayout(GameProperties.MAX_GUESS, 1);
		parentLayout.setVgap(5);

		JPanel parentPanel = getDefaultPanel(parentLayout);
		
		for (int i = 0; i < GameProperties.MAX_GUESS; i++){
			JPanel rowPanel = getGuessRow(game);
			
			parentPanel.add(rowPanel);
			guessRow.add(rowPanel);
		}

		return parentPanel;
	}

	private JPanel getGuessRow(Game game) {
		GridLayout rowLayout = new GridLayout(1, GameProperties.CODE_SIZE + 2);
		rowLayout.setHgap(5);
		
		JPanel rowPanel = getDefaultPanel(rowLayout);
		
		addGuessBox(rowPanel);
		rowPanel.add(getGuessResultLayout());
		rowPanel.add(getGuessRowSubmit(game));

		return rowPanel;
	}

	private JPanel getDefaultPanel(LayoutManager layout) {
		JPanel panel = new JPanel(layout);
		panel.setBackground(BACKGROUND_BG);
		return panel;
	}

	private void addGuessBox(JPanel rowPanel) {
		for (int row = 0; row < GameProperties.CODE_SIZE; row++){
			Button button = new Button();
			button.setPreferredSize(new Dimension(50, 50));
			rowPanel.add(button);
		}
	}

	private JButton getGuessRowSubmit(Game game) {
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new SubmitGuessListener(game));
		submitButton.setVisible(false);
		return submitButton;
	}
	
	private Component getGuessResultLayout(){
		final int defaultRowNum = 2;
		final int numOfCol = GameProperties.CODE_SIZE/2;
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(defaultRowNum, GameProperties.CODE_SIZE/2));
		
		for (int col = 0; col < defaultRowNum; col++){
			for (int i = 0; i < numOfCol; i++){
				JButton resultBox = getGuessResultBox();
				panel.add(resultBox);
			}
		}
		
		return panel;
	}

	private JButton getGuessResultBox() {
		JButton resultBox = new JButton();
		resultBox.setPreferredSize(new Dimension(20, 20));
		resultBox.setBackground(BACKGROUND_INSIDE);
		return resultBox;
	}

	private JFrame createFrame() {
		JFrame frame = new JFrame(GameProperties.GAME_NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_SIZE, FRAME_SIZE);
		return frame;
	}
	
	private Component getTitle() {
		JLabel title = new JLabel(GameProperties.GAME_NAME);

		title.setFont(new Font(FONT, Font.BOLD, 50));
		title.setForeground(Color.LIGHT_GRAY);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setFocusable(false);
		
		return title;
	}
}