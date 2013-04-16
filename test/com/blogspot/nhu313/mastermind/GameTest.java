package com.blogspot.nhu313.mastermind;

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
	public void testGuess(){
		ResultCode[] expectedResult = new ResultCode[]{ResultCode.CORRECT, ResultCode.CORRECT};
		Assert.assertArrayEquals(expectedResult, game.submitGuess(pattern));
	}
	
	@Test
	public void testGuess_Incorrect(){
		Pattern pattern = new Pattern(ColorCode.BLACK, ColorCode.BLACK);
		ResultCode[] expectedResult = new ResultCode[]{ResultCode.INCORRECT, ResultCode.INCORRECT};
		Assert.assertArrayEquals(expectedResult, game.submitGuess(pattern));
	}
	
//	@Test
	public void testGuess_withMixResult(){
		final int gameSize = 3;
		game = EasyMock.createMockBuilder(Game.class).addMockedMethod("getRandomNumber").createStrictMock();
		game.newGame(gameSize);
		for (int i = 0; i < gameSize; i++){
			EasyMock.expect(game.getRandomNumber(gameSize)).andReturn(i).once();
		}
		EasyMock.replay(game);
		
		//TODO
//		EasyMock.verify(game);
	}
}