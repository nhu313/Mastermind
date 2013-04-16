package com.blogspot.nhu313.mastermind.pattern;


import org.junit.Assert;
import org.junit.Test;

import com.blogspot.nhu313.mastermind.ui.ColorCode;

public class PatternTest {
	
	private static final Pattern pattern = new Pattern(ColorCode.BLUE, ColorCode.BLACK);

	
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
		final  ResultCode[] expectedResult = new ResultCode[]{ResultCode.CORRECT, ResultCode.CORRECT};
		final Pattern otherPattern = new Pattern(ColorCode.BLUE, ColorCode.BLACK);
		
		Assert.assertArrayEquals(expectedResult, pattern.isSamePattern(otherPattern));
	}
	
	@Test
	public void testIsSamePattern_AllIncorrect(){
		final  ResultCode[] expectedResult = new ResultCode[]{ResultCode.INCORRECT, ResultCode.INCORRECT};
		final Pattern otherPattern = new Pattern(ColorCode.RED, ColorCode.RED);
		
		Assert.assertArrayEquals(expectedResult, pattern.isSamePattern(otherPattern));
	}
	
	@Test
	public void testIsSamePattern_AllColorCorrectOnly(){
		final  ResultCode[] expectedResult = new ResultCode[]{ResultCode.CORRECT_COLOR_ONLY, ResultCode.CORRECT_COLOR_ONLY};
		final Pattern otherPattern = new Pattern(ColorCode.BLACK, ColorCode.BLUE);
		
		Assert.assertArrayEquals(expectedResult, pattern.isSamePattern(otherPattern));
	}
	
	@Test
	public void testIsSamePattern_FirstCodeIncorrect(){
		final  ResultCode[] expectedResult = new ResultCode[]{ResultCode.CORRECT, ResultCode.INCORRECT};
		final Pattern otherPattern = new Pattern(ColorCode.RED, ColorCode.BLACK);
		
		Assert.assertArrayEquals(expectedResult, pattern.isSamePattern(otherPattern));
	}
	
	@Test
	public void testIsSamePattern_LastCodeIncorrect(){
		final  ResultCode[] expectedResult = new ResultCode[]{ResultCode.CORRECT, ResultCode.INCORRECT};
		final Pattern otherPattern = new Pattern(ColorCode.BLUE, ColorCode.RED);
		
		Assert.assertArrayEquals(expectedResult, pattern.isSamePattern(otherPattern));
	}
	
	@Test
	public void testIsSamePattern_MiddleCodeIncorrect(){
		final  ResultCode[] expectedResult = new ResultCode[]{ResultCode.CORRECT, ResultCode.CORRECT, ResultCode.INCORRECT};
		final Pattern pattern = new Pattern(ColorCode.BLUE, ColorCode.BLUE, ColorCode.BLUE);
		final Pattern otherPattern = new Pattern(ColorCode.BLUE, ColorCode.BLACK, ColorCode.BLUE);
		
		Assert.assertArrayEquals(expectedResult, pattern.isSamePattern(otherPattern));
	}
	
	@Test
	public void testIsSamePattern_TwoIncorrectCode(){
		final  ResultCode[] expectedResult = new ResultCode[]{ResultCode.CORRECT, ResultCode.CORRECT, ResultCode.INCORRECT, ResultCode.INCORRECT};
		final Pattern pattern = new Pattern(ColorCode.BLUE, ColorCode.BLUE, ColorCode.BLUE, ColorCode.BLUE);
		final Pattern otherPattern = new Pattern(ColorCode.BLUE, ColorCode.BLACK, ColorCode.BLACK, ColorCode.BLUE);
		
		Assert.assertArrayEquals(expectedResult, pattern.isSamePattern(otherPattern));
	}
	
	@Test
	public void testIsSamePattern_WithAllPossibleResult(){
		final  ResultCode[] expectedResult = new ResultCode[]{ResultCode.CORRECT, ResultCode.CORRECT, ResultCode.CORRECT_COLOR_ONLY, ResultCode.INCORRECT};
		final Pattern pattern = new Pattern(ColorCode.BLUE, ColorCode.GREEN, ColorCode.RED, ColorCode.GREEN);
		final Pattern otherPattern = new Pattern(ColorCode.BLUE, ColorCode.RED, ColorCode.BLACK, ColorCode.GREEN);
		
		Assert.assertArrayEquals(expectedResult, pattern.isSamePattern(otherPattern));
	}
}