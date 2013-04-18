package com.blogspot.nhu313.mastermind.pattern;


import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.blogspot.nhu313.mastermind.ui.ColorCode;

public class PatternTest {
	
	private static final Pattern pattern = new Pattern(ColorCode.BLUE, ColorCode.BLACK);
	private List<ResultCode> resultCode;
	
	@Before
	public void setUp(){
		resultCode = new ArrayList<ResultCode>();
	}
	
	@After
	public void tearDown(){
		resultCode = null;
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor_WithNullCode(){
		ColorCode[] colors = null;
		new Pattern(colors);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructor_WithNoCode(){
		ColorCode[] colors = new ColorCode[0];
		new Pattern(colors);
	}
	
	@Test
	public void testContructor_With1Code(){
		new Pattern(ColorCode.BLUE);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testIsSamePattern_IncorrectSize(){
		Pattern otherPattern = new Pattern(ColorCode.BLUE, ColorCode.BLACK, ColorCode.BLUE);
		pattern.isSamePattern(otherPattern);
	}
	
	@Test
	public void testIsSamePattern_AllCorrect(){
		resultCode.add(ResultCode.CORRECT);
		resultCode.add(ResultCode.CORRECT);
		final Pattern otherPattern = new Pattern(ColorCode.BLUE, ColorCode.BLACK);
		
		Assert.assertEquals(resultCode, pattern.isSamePattern(otherPattern));
	}
	
	@Test
	public void testIsSamePattern_AllIncorrect(){
		resultCode.add(ResultCode.INCORRECT);
		resultCode.add(ResultCode.INCORRECT);
		final Pattern otherPattern = new Pattern(ColorCode.RED, ColorCode.RED);
		
		Assert.assertEquals(resultCode, pattern.isSamePattern(otherPattern));
	}
	
	@Test
	public void testIsSamePattern_AllColorCorrectOnly(){
		resultCode.add(ResultCode.CORRECT_COLOR_ONLY);
		resultCode.add(ResultCode.CORRECT_COLOR_ONLY);
		final Pattern otherPattern = new Pattern(ColorCode.BLACK, ColorCode.BLUE);
		
		Assert.assertEquals(resultCode, pattern.isSamePattern(otherPattern));
	}
	
	@Test
	public void testIsSamePattern_FirstCodeIncorrect(){
		resultCode.add(ResultCode.CORRECT);
		resultCode.add(ResultCode.INCORRECT);
		final Pattern otherPattern = new Pattern(ColorCode.RED, ColorCode.BLACK);
		
		Assert.assertEquals(resultCode, pattern.isSamePattern(otherPattern));
	}
	
	@Test
	public void testIsSamePattern_LastCodeIncorrect(){
		resultCode.add(ResultCode.CORRECT);
		resultCode.add(ResultCode.INCORRECT);
		final Pattern otherPattern = new Pattern(ColorCode.BLUE, ColorCode.RED);
		
		Assert.assertEquals(resultCode, pattern.isSamePattern(otherPattern));
	}
	
	@Test
	public void testIsSamePattern_MiddleCodeIncorrect(){
		resultCode.add(ResultCode.CORRECT);
		resultCode.add(ResultCode.CORRECT);
		resultCode.add(ResultCode.INCORRECT);
		final Pattern pattern = new Pattern(ColorCode.BLUE, ColorCode.BLUE, ColorCode.BLUE);
		final Pattern otherPattern = new Pattern(ColorCode.BLUE, ColorCode.BLACK, ColorCode.BLUE);
		
		Assert.assertEquals(resultCode, pattern.isSamePattern(otherPattern));
	}
	
	@Test
	public void testIsSamePattern_TwoIncorrectCode(){
		resultCode.add(ResultCode.CORRECT);
		resultCode.add(ResultCode.CORRECT);
		resultCode.add(ResultCode.INCORRECT);
		resultCode.add(ResultCode.INCORRECT);
		final Pattern pattern = new Pattern(ColorCode.BLUE, ColorCode.BLUE, ColorCode.BLUE, ColorCode.BLUE);
		final Pattern otherPattern = new Pattern(ColorCode.BLUE, ColorCode.BLACK, ColorCode.BLACK, ColorCode.BLUE);
		
		Assert.assertEquals(resultCode, pattern.isSamePattern(otherPattern));
	}
	
	@Test
	public void testIsSamePattern_WithAllPossibleResult(){
		resultCode.add(ResultCode.CORRECT);
		resultCode.add(ResultCode.CORRECT);
		resultCode.add(ResultCode.CORRECT_COLOR_ONLY);
		resultCode.add(ResultCode.INCORRECT);
		final Pattern pattern = new Pattern(ColorCode.BLUE, ColorCode.GREEN, ColorCode.RED, ColorCode.GREEN);
		final Pattern otherPattern = new Pattern(ColorCode.BLUE, ColorCode.RED, ColorCode.BLACK, ColorCode.GREEN);
		
		Assert.assertEquals(resultCode, pattern.isSamePattern(otherPattern));
	}
	
	@Test
	public void testRemoveSameItemInSamePosition(){
		List<ColorCode> original = new ArrayList<ColorCode>();
		List<ColorCode> other = new ArrayList<ColorCode>();
		
		int listSize = 3;
		for (int i = 0; i < listSize; i++){
			original.add(ColorCode.BLACK);
			other.add(ColorCode.BLACK);
		}
		
		original.add(1, ColorCode.BLUE);
		other.add(1, ColorCode.GREEN);
		
		Assert.assertEquals(listSize + 1, original.size());
		Assert.assertEquals(listSize + 1, other.size());
		
		pattern.removeSameItemInSamePosition(original, other);
		
		Assert.assertEquals(1, original.size());
		Assert.assertEquals(1, other.size());
	}
}