package piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import board.ChessBoard;

public abstract class Rook extends Piece {

	public Rook(int rank, int file) {
		super(rank, file);
		this.setVal(5);
	}

	@Override
	public boolean isValidMove(int newRank, int newFile) {
		if (newRank < 0 || newRank > 7) return false;
		if (newFile < 0 || newFile > 7) return false;
		
		int rank = this.getRank();
		int file = this.getFile();
		
		// Ensure that a piece of the same color is not on the new rank and file
		int indexLocation = ChessBoard.getIndexLocation(newRank, newFile);
		ChessPiece piece = this.getBoard().pieceAt(indexLocation);
		if(piece != null && piece.getColor() == this.getColor()) {
			return false;
		}
		
		// New location cannot be the same as the old location
		if (newRank == rank && newFile == file) {
			return false;
		}
		
		boolean continueFindingStates;
		int nextRank = rank;
		int nextFile = file;
		
		// A horizontal or vertical move is valid 
		if (newRank == rank) {
			do {
				nextFile = (newFile > file) ? nextFile + 1 : nextFile - 1;
				if(nextFile == newFile) {
					return true;
				}
				
				continueFindingStates = this.canMoveHelper(rank, nextFile);
			} while(continueFindingStates);
		} else if(newFile == file) {
			do {
				nextRank = (newRank > rank) ? nextRank + 1 : nextRank - 1;
				if(nextRank == newRank) {
					return true;
				}
				continueFindingStates = this.canMoveHelper(nextRank, file);
			} while(continueFindingStates);
		}
		
		return false;
	}
	
	// This function will get the possible future moves for the Queen
	public void setFutureStates() {
		this.stateSpace.clear();
		int rank = this.getRank();
		int file = this.getFile();
		
		// Vertical directions
		for(int i = this.getRank() + 1; i < 8; i++) {
			boolean continueFindingStates = futureStatesHelper(i, file);
			if (!continueFindingStates) {
				break;
			}
		}
		
		for(int i = this.getRank() - 1; i >= 0; i--) {
			boolean continueFindingStates = futureStatesHelper(i, file);
			if (!continueFindingStates) {
				break;
			}
		}
		
		// Horizontal directions
		for(int i = this.getFile() + 1; i < 8; i++) {
			boolean continueFindingStates = futureStatesHelper(rank, i);
			if (!continueFindingStates) {
				break;
			}
		}
		
		for(int i = this.getFile() - 1; i >= 0; i--) {
			boolean continueFindingStates = futureStatesHelper(rank, i);
			if (!continueFindingStates) {
				break;
			}
		}
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
		
	}*/
	
}
