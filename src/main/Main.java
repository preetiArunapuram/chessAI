package main;

import game.ChessGame;

public class Main {

	public static void main(String[] args) {
		ChessGame game = new ChessGame();
		//game.printCurrentBoard();
		game.playSomeRounds(30);
	}
	
}
