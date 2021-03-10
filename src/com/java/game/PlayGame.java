package com.java.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class PlayGame extends CoreLogic {
	
	private static List<Integer> moves = new ArrayList<>();

	/**
	 * This method accepts input from user
	 * @param player
	 * 			{@link String} contains user name
	 * @return
	 * 			{@link String} contains move number
	 */
	public int acceptInput(String player) {
		String name = PLAYER_1.equals(player) ? "Player 1 [RED] " : "Player 2 [Green] ";
		System.out.print("\n" + name + "- choose column (1-7):");  
		Scanner sc = new Scanner(System.in);
		try {
			int a = sc.nextInt();
			if(a <= 0 || a > COLUMN) {
				throw new Exception("Input is outside Required range (1-7)");
			}
			return a;
		} catch (Exception e) {
			System.out.println("Error: "+ e);
		} 
		return -1;
	}

	/**
	 * This method is responsible to start game 
	 */
	public void startGame() {
		String player = PLAYER_1;
		int column;
		while(true) {
			column = acceptInput(player);
			if(column == -1) {
				continue;
			} else {
				moves.add(column);
				Set<Integer> moveList = addMoves(column, player);
				if(moveList == null) {
					System.out.println("This column is full, Please choose different move");
					continue;
				}
				System.out.println();
				printMoveStatus();
				System.out.println(moves);
				if(validateWinner(player)) {
					String name = PLAYER_1.equals(player) ? "Player 1 [RED] " : "Player 2 [Green] ";
					System.out.println(name + " wins the game");
					System.exit(0);
				} else if(checkDraw()) {
					System.out.println("*** Game Draw ***");
					System.exit(0);
				}
				player = PLAYER_1.equals(player)? PLAYER_2: PLAYER_1;

			}
		}
	}

	/**
	 ******Start the game here******
	 */
	public static void main(String[] args) {
		System.out.println("*****************************\n\tGame Started\n*****************************");
		new PlayGame().startGame();
	}
}
