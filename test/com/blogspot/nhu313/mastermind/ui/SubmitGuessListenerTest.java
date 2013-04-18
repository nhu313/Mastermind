package com.blogspot.nhu313.mastermind.ui;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.blogspot.nhu313.mastermind.pattern.ResultCode;

public class SubmitGuessListenerTest {

	SubmitGuessListener submitListener = new SubmitGuessListener(null);	
	
	@Before
	public void setUp(){
		submitListener = new SubmitGuessListener(null);	
	}
	
	@After
	public void tearDown(){
		submitListener = null;
	}
	
	@Test
	public void testGetColorCodes(){
		JPanel panel = getPanelWithAllColors();
		
		ColorCode[] expectedColors = ColorCode.values();
		ColorCode[] colors = submitListener.getColorCodes(panel, expectedColors.length);
		
		Assert.assertArrayEquals(expectedColors, colors);
	}

	private JPanel getPanelWithAllColors() {
		JPanel panel = new JPanel();
		ColorCode[] colors = ColorCode.values();
		for (int i = 0; i < colors.length; i++){
			JButton button = new JButton();
			button.setBackground(colors[i].getColor());
			panel.add(button);
		}
		return panel;
	}
	
	@Test
	public void testSetResultCode(){
		List<ResultCode> results = new ArrayList<ResultCode>();
		results.add(ResultCode.CORRECT);
		results.add(ResultCode.CORRECT_COLOR_ONLY);
		results.add(ResultCode.CORRECT_COLOR_ONLY);
		results.add(ResultCode.INCORRECT);
		
		JPanel panel = new JPanel();
		for (int i = 0; i < results.size(); i++){
			panel.add(new JButton());
		}
		
		submitListener.setResultCode(results, panel);
		
		Component correctButton = panel.getComponent(0);
		Assert.assertEquals(ColorCode.BLACK.getColor(), correctButton.getBackground());

		Component correctColorButton = panel.getComponent(1);
		Assert.assertEquals(ColorCode.WHITE.getColor(), correctColorButton.getBackground());
		Component correctColorButton2 = panel.getComponent(2);
		Assert.assertEquals(ColorCode.WHITE.getColor(), correctColorButton2.getBackground());
		
		Color incorrectBgColor = new Color(238, 238, 238);
		Component incorrectButton = panel.getComponent(3);
		Assert.assertEquals(incorrectBgColor, incorrectButton.getBackground());
	}
}
