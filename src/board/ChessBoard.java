package board;

import java.util.HashMap;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import piece.ChessPiece;

public class ChessBoard {

	private HashMap<Integer, ChessPiece> locationToMap;
	
	public ChessBoard() {
		locationToMap = new HashMap<Integer, ChessPiece>();
	}
	
	
	public static Pair<Integer, Integer> getRankAndFileLocation(int indexLocation) {
		int rank = indexLocation / 8;
		int file = indexLocation % 8;
		
		return new MutablePair<Integer, Integer>(rank, file);
	}
	
	public static int getIndexLocation(int rank, int file) {
		return 8*rank + file;
	}
	
	public ChessPiece pieceAt(int indexLocation) {
		return locationToMap.get(indexLocation);
	}
	
	public void addPieceToRankAndFile(int rank, int file, ChessPiece piece) {
		int indexLocation = ChessBoard.getIndexLocation(rank, file);
		this.locationToMap.put(indexLocation, piece);
	}
	
	public void movePiece(int oldIndexLocation, int newIndexLocation) {
		ChessPiece movingPiece = pieceAt(oldIndexLocation);
		this.locationToMap.remove(oldIndexLocation);
		this.locationToMap.put(newIndexLocation, movingPiece);
		
	}
	
	public void movePieceToRankAndFile(int oldRank, int oldFile, int newRank, int newFile) {
		int oldIndexLocation = ChessBoard.getIndexLocation(oldRank, oldFile);
		int newIndexLocation = ChessBoard.getIndexLocation(newRank, newFile);
		this.movePiece(oldIndexLocation, newIndexLocation);
	}
	
	public String toString() {
		StringBuilder rankBuilder = new StringBuilder();
		for(int i = 7; i >= 0; i--) {
			StringBuilder fileBuilder = new StringBuilder();
			for(int j = 0; j < 8; j++) {
				int indexLocation = ChessBoard.getIndexLocation(i, j);
				ChessPiece piece = this.pieceAt(indexLocation);
				
				if (piece == null) {
					fileBuilder.append("*\t");
				} else {
					fileBuilder.append(piece.toString());
					fileBuilder.append('\t');
				}
			}
			
			rankBuilder.append(fileBuilder);
			rankBuilder.append('\n');
		}
		
		return rankBuilder.toString();
	}
}
