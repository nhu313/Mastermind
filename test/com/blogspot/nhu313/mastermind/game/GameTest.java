package com.blogspot.nhu313.mastermind.game;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.blogspot.nhu313.mastermind.GameProperties;
import com.blogspot.nhu313.mastermind.colorcode.ColorCode;
import com.blogspot.nhu313.mastermind.colorcode.ColorCodeComparator;
import com.blogspot.nhu313.mastermind.result.ResultCode;

public class GameTest {
	
	private static final int GAME_SIZE = 2;

	private ColorCode[] secretCode = new ColorCode[]{ColorCode.BLUE, ColorCode.BLUE};
	private Game game;
	
	@Before
	public void setUp(){
		game = EasyMock.createMockBuilder(Game.class).addMockedMethod("getRandomNumber").createMock();
		EasyMock.expect(game.getRandomNumber(GAME_SIZE)).andReturn(0).anyTimes();
		EasyMock.replay(game);
		
		game.setColorCodeComparator(new ColorCodeComparator());
		game.newGame(GAME_SIZE);
	}
	
	@After
	public void tearDown(){
		EasyMock.verify(game);
		game = null;		
	}
	
	@Test
	public void testGuess_ExceedMaxGuess(){
		final int maxGuess = GameProperties.MAX_GUESS;
		
		for (int i = 0; i < maxGuess; i++){
			Assert.assertNotNull(game.submitGuess(secretCode));
		}
		
		Assert.assertNull(game.submitGuess(secretCode));
	}
	
	@Test
	public void testGuess_withCorrectResult(){
		List<ResultCode> expectedResult = new ArrayList<ResultCode>();
		expectedResult.add(ResultCode.CORRECT);
		expectedResult.add(ResultCode.CORRECT);
		Assert.assertEquals(expectedResult, game.submitGuess(secretCode));
	}
	
	@Test
	public void testGuess_withIncorrectResult(){
		List<ResultCode> expectedResult = new ArrayList<ResultCode>();
		expectedResult.add(ResultCode.INCORRECT);
		expectedResult.add(ResultCode.INCORRECT);
		
		ColorCode[] guess = new ColorCode[]{ColorCode.BLACK, ColorCode.BLACK};
		
		Assert.assertEquals(expectedResult, game.submitGuess(guess));
	}	
}