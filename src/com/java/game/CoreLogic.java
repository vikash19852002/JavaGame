package com.java.game;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class is responsible to perform all core game logic
 * @author Administrator
 *
 */
public class CoreLogic {

	public static final String PLAYER_1 = "R";
	public static final String PLAYER_2 = "G";
	protected static final int ROW = 6;
	protected static final int COLUMN = 7;
	protected static final int WINNING_MOVE = 4;
	protected final String[][] blocks = new String[ROW][COLUMN];
	protected final Map<String, Set<Integer>> playerMoves = new HashMap<>();

	/**
	 * This method checks the draw status
	 * 
	 * @return
	 * 			{@link Boolean} return draw status
	 */
	public boolean checkDraw() {
		return ROW*COLUMN == playerMoves.values().stream().flatMap(Collection::stream).count();
	}
	/**
	 * This method is responsible to perform check for all direction and return status
	 * 
	 * @param player
	 * 			{@link String} contains player
	 * @return
	 * 			{@link Boolean} returns true if user wins
	 */
	public boolean validateWinner(String player) {
		boolean result = false;
		Set<Integer> moveList = playerMoves.get(player);
		for(int move : moveList) {
			//Do not calculate diagonal and vertical moves if box is not half filled
			if(move <= ROW*COLUMN/2) {
				if(move%COLUMN == 1) {
					//no left
					result = checkWinPosition(moveList, move, COLUMN+1, WINNING_MOVE, false);
				} else if(move % COLUMN == 0) {
					//no right
					result = checkWinPosition(moveList, move, COLUMN-1, WINNING_MOVE, false);
				} else {
					//all search
					result = checkWinPosition(moveList, move, COLUMN-1, WINNING_MOVE, false);
					result = result ? result : checkWinPosition(moveList, move, COLUMN+1, WINNING_MOVE, false);
				}
				//Check straight vertical row
				result = result ? result :checkWinPosition(moveList, move, COLUMN, WINNING_MOVE, false);
			}
			//Check horizontal row everytime
			result = result ? result :checkWinPosition(moveList, move, 1, WINNING_MOVE, true);
			if(result) {
				break;
			}
		}
		return result;
	}

	/**
	 * This method simply add moves into 2-dimensional array for user
	 * 
	 * @param column
	 * 			{@link Integer} contains user current move
	 * @param player
	 * 			{@link String} contains user
	 * @return
	 * 			{@link Set} of Integer: contains all moves of user
	 */
	public Set<Integer> addMoves(int column, String player) {
		for(int i = ROW -1; i >= 0; i--) {
			if(blocks[i][column -1] == null) {
				blocks[i][column -1] = player;
				return addMovesToList(i, column, player);
			}
		}
		return null;
	}

	/**
	 * This method calculate exact move position for User
	 * @param row
	 * 			{@link Integer} contains row value
	 * @param column
	 * 			{@link Integer} contains user move in column
	 * @param player
	 * 			{@link String} contains player name 
	 * @return
	 * 			{@link Set} of Integer: contains all user moves
	 */
	public Set<Integer> addMovesToList(int row, int column, String player) {
		int index = row == 0 ? column :(row * COLUMN) + column;
		if(playerMoves.get(player) == null) {
			playerMoves.put(player, new TreeSet<>());
		}
		Set<Integer> moveList = playerMoves.get(player);
		moveList.add(index);
		return moveList;
	}

	/**
	 * This method simply print all move status to Users
	 */
	public void printMoveStatus() {
		for(int i = 0; i < ROW; i++) {
			System.out.print("|");
			for(int j = 0; j < COLUMN; j++) {
				String value = blocks[i][j] == null ? " " : blocks[i][j];
				System.out.print(value + "|");
			}
			System.out.println();
		}
	}
	
	/**
	 * This method is responsible to check winning moves in all direction
	 * 
	 * @param moveList 
	 * 			{@link Set} of Integers: contains Player moves position
	 * @param move
	 * 			{@link Integer} contains current move
	 * @param increment
	 * 			{@link Integer} contains move increment to search
	 * @param position
	 * 			{@link Integer} contains winning move required
	 * @param horizontal
	 * 			{@link Boolean} contains flag to search horizontally 
	 * @return
	 */
	public boolean checkWinPosition(Set<Integer> moveList, int move, int increment, int position, boolean horizontal) {
		if(position == 0) {
			return true;
		}
		if(horizontal && move % COLUMN == 1 && position < WINNING_MOVE) {
			return false;
		}
		if(moveList.contains(move)) {
			return checkWinPosition(moveList, move+increment, increment, --position, horizontal);
		}
		return false;
	}
}
