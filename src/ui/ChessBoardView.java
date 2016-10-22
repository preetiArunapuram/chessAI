package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import board.ChessBoard;

public class ChessBoardView {

	private HashMap<Integer, Image> pieceImgLoc = new HashMap<Integer, Image>();
	private Graphics graphics;
	
	private static final int START_X = 50;
	private static final int START_Y = 50;
	private static final int SQUARE_SIZE = 70;
	
	public ChessBoardView(Graphics g) {
		this.graphics = g;
	}
	
	public static Pair<Integer, Integer> getImgPosFromIndexLocation(int indexLocation) {
		Pair<Integer, Integer> rankAndFile = ChessBoard.getRankAndFileLocation(indexLocation);
		int rank = rankAndFile.getRight();
		int file = rankAndFile.getLeft();
		
		int xLoc = START_X + (SQUARE_SIZE * file);
		int yLoc = START_Y + (SQUARE_SIZE * (7 - rank));
		
		return new ImmutablePair<Integer, Integer>(xLoc, yLoc);
	}
	
	public void renderChessBoard() {
		
	}
	
}
