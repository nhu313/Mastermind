package com.blogspot.nhu313.mastermind.game;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.blogspot.nhu313.mastermind.GameProperties;

public class GuessRowTest {

	private static final int NUM_COMPONENT_IN_ROW = GameProperties.CODE_SIZE + 2;
	private GuessRow row;
	private JPanel currentPanel;
	
	@Before
	public void setUp() throws Exception {
		row = new GuessRow();
	}

	@After
	public void tearDown() throws Exception {
		row = null;
		currentPanel = null;
	}

	@Test
	public void testSetColorFirstRow() {
		final int numberOfRow = 1;
		addComponent(numberOfRow);
		setColorToEntireRow(numberOfRow);
		testSetColor();
	}

	private void testSetColor() {
		Component colorBox = currentPanel.getComponent(GameProperties.CODE_SIZE - 1);
		Assert.assertEquals(Color.BLACK, colorBox.getBackground());
				
		Component submitButton = currentPanel.getComponent(NUM_COMPONENT_IN_ROW - 1);
		Assert.assertTrue(submitButton.isVisible());
	}

	private void setColorToEntireRow(int numberOfRow) {
		for (int i = 0; i < GameProperties.CODE_SIZE * numberOfRow; i++){
			row.setColor(Color.BLACK);
		}
	}

	@Test
	public void testSetColorLastRow() {
		int numberOfRow = GameProperties.MAX_GUESS;
		
		addComponent(numberOfRow);
		setColorToEntireRow(numberOfRow);
		testSetColor();
	}
	
	private void addComponent(int numberOfRow) {
		for (int i = 0; i < numberOfRow; i++){
			currentPanel = new JPanel();
			row.add(currentPanel);
			addRowComponent();
		}
	}

	private void addRowComponent() {
		int numOfComponentToAdd = (NUM_COMPONENT_IN_ROW) - 1;
		for (int i = 0; i < numOfComponentToAdd; i++){
			currentPanel.add(new JButton());
		}
		
		JButton jButton = new JButton();
		jButton.setVisible(false);
		currentPanel.add(jButton);
	}
}