package com.java.game.test;

import static org.junit.jupiter.api.Assertions.*;
import static com.java.game.CoreLogic.*;

import org.junit.jupiter.api.Test;

import com.java.game.CoreLogic;

/**
 * This class is used to test all test cases of Game
 * @author Administrator
 *
 */
class GameTest {

	int[] winHorizontalMove = {1, 1, 2, 2, 3, 3, 4};
	int[] winVerticalMove = {1, 2, 1, 2, 1, 2, 1};
	int[] winLeftMove = {1, 2, 2, 3, 3, 4, 3, 4, 5, 4, 4};
	int[] winRightMove = {7, 6, 6, 5, 4, 5, 5, 4, 3, 3, 4, 2, 4};
	int[] drawMove = {1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 1, 
			2, 3, 4, 5, 6, 7, 2, 1, 4, 5, 6, 7, 1, 3, 2, 4, 3, 5, 5, 6, 7, 1, 7, 2, 4, 6, 3};
	
	private String player;
	
	private String getPlayer() {
		player = player != null && PLAYER_1.equals(player)? PLAYER_2: PLAYER_1;
		return player;
	}

	@Test
	void testHorizontal() {
		CoreLogic coreLogic =  new CoreLogic();
		for(int move : winHorizontalMove) {
			coreLogic.addMoves(move, getPlayer());
		}
		assertTrue(coreLogic.validateWinner(player));
	}
	
	@Test
	void testVertical() {
		CoreLogic coreLogic =  new CoreLogic();
		for(int move : winVerticalMove) {
			coreLogic.addMoves(move, getPlayer());
		}
		assertTrue(coreLogic.validateWinner(player));
	}
	
	@Test
	void testLeftDiagonal() {
		CoreLogic coreLogic =  new CoreLogic();
		for(int move : winLeftMove) {
			coreLogic.addMoves(move, getPlayer());
		}
		assertTrue(coreLogic.validateWinner(player));
	}
	
	@Test
	void testRightDiagonal() {
		CoreLogic coreLogic =  new CoreLogic();
		for(int move : winRightMove) {
			coreLogic.addMoves(move, getPlayer());
		}
		assertTrue(coreLogic.validateWinner(player));
	}
	
	@Test
	void testDraw() {
		CoreLogic coreLogic =  new CoreLogic();
		for(int move : drawMove) {
			coreLogic.addMoves(move, getPlayer());
		}
		assertFalse(coreLogic.validateWinner(player));
	}

}
