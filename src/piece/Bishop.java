package piece;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import board.ChessBoard;
import utils.MoveCode;

public abstract class Bishop extends Piece {

	public Bishop(int rank, int file) {
		super(rank, file);
		this.setVal(3);
	}

	@Override
	// For now, we will assume that no piece is obstructing the move. We need to fix this to
	// handle the case in which there is a piece in the way.
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
		if (newRank == rank || newFile == file) {
			return false;
		}
		
		boolean continueFindingStates;
		int nextRank = rank;
		int nextFile = file;
		
		// Diagonal moves are also valid
		float ratio = Math.abs(newRank - rank) / Math.abs(newFile - file);	
		if (ratio == 1) {
			do {
				nextRank = (newRank > rank) ? nextRank + 1 : nextRank - 1;
				nextFile = (newFile > file) ? nextFile + 1 : nextFile - 1;
				if((nextRank == newRank) && (nextFile == newFile)) {
					return true;
				}
				
				continueFindingStates = this.canMoveHelper(nextRank, nextFile);
			} while(continueFindingStates);
		} 
		
		return false;
	}
		
	// This function will get the possible future moves for the Bishop
	public void setFutureStates() {
		this.stateSpace.clear();
		
		int rank = this.getRank();
		int file = this.getFile();
		
		// Diagonal directions
		int deltaRank = 1;
		int deltaFile = 1;
		while(true) {
			int newRank = rank + deltaRank;
			int newFile = file + deltaFile;
			
			if (newRank < 0 || newRank > 7) break;
			if (newFile < 0 || newRank > 7) break;
			
			boolean continueFindingStates = futureStatesHelper(rank + deltaRank, file + deltaFile);
			if (!continueFindingStates) {
				break;
			}
			
			deltaRank += 1;
			deltaFile += 1;
		}
		
		deltaRank = 1;
		deltaFile = -1;
		while(true) {
			int newRank = rank + deltaRank;
			int newFile = file + deltaFile;
			
			if (newRank < 0 || newRank > 7) break;
			if (newFile < 0 || newRank > 7) break;
			
			boolean continueFindingStates = futureStatesHelper(rank + deltaRank, file + deltaFile);
			if (!continueFindingStates) {
				break;
			}
			
			deltaRank += 1;
			deltaFile -= 1;
		}
		
		deltaRank = -1;
		deltaFile = 1;
		while(true) {
			int newRank = rank + deltaRank;
			int newFile = file + deltaFile;
			
			if (newRank < 0 || newRank > 7) break;
			if (newFile < 0 || newRank > 7) break;
			
			boolean continueFindingStates = futureStatesHelper(rank + deltaRank, file + deltaFile);
			if (!continueFindingStates) {
				break;
			}
			
			deltaRank -= 1;
			deltaFile += 1;
		}
		
		deltaRank = -1;
		deltaFile = -1;
		while(true) {
			int newRank = rank + deltaRank;
			int newFile = file + deltaFile;
			
			if (newRank < 0 || newRank > 7) break;
			if (newFile < 0 || newRank > 7) break;
			
			boolean continueFindingStates = futureStatesHelper(rank + deltaRank, file + deltaFile);
			if (!continueFindingStates) {
				break;
			}
			
			deltaRank -= 1;
			deltaFile -= 1;
		}
	}

	public Set<Integer> getAttackingStates() {
		Set<Integer> attackingSquares = new HashSet<Integer>();
		for (Pair<Integer, MoveCode> move : this.stateSpace) {
			attackingSquares.add(move.getLeft());
		}
		
		return attackingSquares;
	}
	
	@Override
	public void undoMove(int prevRank, int prevFile, MoveCode moveType) {
		super.undoMove(prevRank, prevFile, moveType);
	}
}
