package com.blogspot.nhu313.mastermind;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.blogspot.nhu313.mastermind.pattern.Pattern;
import com.blogspot.nhu313.mastermind.pattern.ResultCode;
import com.blogspot.nhu313.mastermind.ui.ColorCode;

public class GameTest {
	
	private static final int GAME_SIZE = 2;

	private Pattern pattern = new Pattern(ColorCode.BLUE, ColorCode.BLUE);
	private Game game;
	
	@Before
	public void setUp(){
		game = EasyMock.createMockBuilder(Game.class).addMockedMethod("getRandomNumber").createMock();
		EasyMock.expect(game.getRandomNumber(GAME_SIZE)).andReturn(0).anyTimes();
		EasyMock.replay(game);
		
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
			Assert.assertNotNull(game.submitGuess(pattern));
		}
		
		Assert.assertNull(game.submitGuess(pattern));
	}
	
	@Test
	public void testGuess_withCorrectResult(){
		List<ResultCode> expectedResult = new ArrayList<ResultCode>();
		expectedResult.add(ResultCode.CORRECT);
		expectedResult.add(ResultCode.CORRECT);
		Assert.assertEquals(expectedResult, game.submitGuess(pattern));
	}
	
	@Test
	public void testGuess_withIncorrectResult(){
		List<ResultCode> expectedResult = new ArrayList<ResultCode>();
		expectedResult.add(ResultCode.INCORRECT);
		expectedResult.add(ResultCode.INCORRECT);
		Pattern pattern = new Pattern(ColorCode.BLACK, ColorCode.BLACK);
		Assert.assertEquals(expectedResult, game.submitGuess(pattern));
	}	
}