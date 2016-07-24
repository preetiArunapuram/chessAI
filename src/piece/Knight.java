package piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import board.ChessBoard;

public abstract class Knight extends Piece {

	public Knight() {
		this.setVal(3);
	}

	@Override
	public boolean isValidMove(int newRank, int newFile) {
		if (newRank < 0 || newRank > 7) return false;
		if (newFile < 0 || newRank > 7) return false;
		
		if(newRank == this.getRank() || newFile == this.getFile()) {
			return false;
		}
		
		int indexLocation = ChessBoard.getIndexLocation(newRank, newFile);
		ChessPiece piece = this.getBoard().pieceAt(indexLocation);
		
		if(piece != null && piece.getColor() == this.getColor()) {
			return false;
		}
		
		if((Math.abs(newRank - this.getRank()) == 2 && Math.abs(newFile - this.getFile()) == 1) ||
				(Math.abs(newRank - this.getRank()) == 1 && Math.abs(newFile - this.getFile()) == 2)) {
			return true;
		}
		
		return false;
	}
	
	// This function will get the possible future moves for the Rook
	public void setFutureStates() {
		this.stateSpace.clear();
		int rank = this.getRank();
		int file = this.getFile();
				
		boolean addState = futureStatesHelper(rank + 1, file + 2);
		addState = futureStatesHelper(rank + 2, file + 1);
		addState = futureStatesHelper(rank - 1, file + 2);
		addState = futureStatesHelper(rank - 2, file + 1);
		addState = futureStatesHelper(rank + 1, file - 2);
		addState = futureStatesHelper(rank + 2, file - 1);
		addState = futureStatesHelper(rank - 1, file - 2);
		addState = futureStatesHelper(rank - 2, file - 1);
	}

	public Set<Integer> getAttackingStates() {
		return this.stateSpace;
	}
	
		/*
		@Override
		public void setAttackingSquares() {
			// TODO Auto-generated method stub
			this.attackingSquares.clear();
			this.attackingSquares.addAll(this.stateSpace);
		}

		@Override
		public boolean capture(int newRank, int newFile) {
			// TODO Auto-generated method stub
			return false;
		}*/
}
