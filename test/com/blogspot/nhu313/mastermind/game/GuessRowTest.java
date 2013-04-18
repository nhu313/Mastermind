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

	private GuessRow row;
	private JPanel panel;
	
	@Before
	public void setUp() throws Exception {
		panel = new JPanel();
		row = new GuessRow();
		row.add(panel);
	}

	@After
	public void tearDown() throws Exception {
		row = null;
		panel = null;
	}

	@Test
	public void testSetColorFirstRow() {
		int numberOfRow = 1;
		addComponent(numberOfRow);
		
		row.col = GameProperties.CODE_SIZE - 1;
		row.setColor(Color.BLACK);
		
		Component component = panel.getComponent(GuessRow.NUM_COMPONENT_IN_ROW - 1);
		Assert.assertTrue(component.isVisible());
	}

	@Test
	public void testSetColorSecondRow() {
		int numberOfRow = 4;
		addComponent(numberOfRow);
		
		row.col = 21; //GameProperties.CODE_SIZE - 1 + (GuessRow.NUM_COMPONENT_IN_ROW * (numberOfRow - 1));
		row.setColor(Color.BLACK);
		
		Component secondSubmitButton = panel.getComponent(23);//(GuessRow.NUM_COMPONENT_IN_ROW * numberOfRow) - 1);
		Assert.assertTrue(secondSubmitButton.isVisible());
	}
	
	private void addComponent(int numberOfRow) {
		int numOfComponentToAdd = (GuessRow.NUM_COMPONENT_IN_ROW * numberOfRow) - 1;
		for (int i = 0; i < numOfComponentToAdd; i++){
			panel.add(new JButton());
		}
		
		JButton jButton = new JButton();
		jButton.setVisible(false);
		panel.add(jButton);		
	}
}