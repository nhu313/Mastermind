package com.blogspot.nhu313.mastermind.ui;

import java.awt.Color;

import junit.framework.Assert;

import org.junit.Test;

import com.blogspot.nhu313.mastermind.colorcode.ColorCode;

public class CodeColorTest {

	@Test
	public void testIncorrectValue() {
		Color color = new Color(11, 11, 11);
		Assert.assertNull(ColorCode.valueOf(color));
	}
	
	@Test
	public void testCorrectColor(){
		Color color = ColorCode.GREEN.getColor();
		Assert.assertEquals(ColorCode.GREEN, ColorCode.valueOf(color));
	}

}
