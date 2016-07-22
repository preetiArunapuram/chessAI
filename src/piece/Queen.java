package piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import board.ChessBoard;

public abstract class Queen extends Piece {
	
	public Queen() {
		this.setVal(10);
	}
	
	@Override
	public boolean isValidMove(int newRank, int newFile) {
		if (newRank < 0 || newRank > 7) return false;
		if (newFile < 0 || newFile > 7) return false;
		
		int rank = this.getRank();
		int file = this.getFile();
		
		// Ensure that a piece of the same color is not on the new rank and file
		int indexLocation = ChessBoard.getIndexLocation(rank, file);
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
				nextRank = (newRank > rank) ? nextRank + 1 : nextRank - 1;
				continueFindingStates = this.futureStatesHelper(nextRank, file);
			} while(continueFindingStates && (nextRank != newRank));
			
			if(nextRank == newRank) {
				return true;
			}
		} else if(newFile == file) {
			do {
				nextFile = (newFile > file) ? nextFile + 1 : nextFile - 1;
				continueFindingStates = this.canMoveHelper(rank, nextFile);
			} while(continueFindingStates || (nextFile != newFile));
			
			if(nextFile == newFile) {
				return true;
			}
		}
		
		// Diagonal moves are also valid
		float ratio = Math.abs(newRank - rank) / Math.abs(newFile - file);	
		if (ratio == 1) {
			do {
				nextRank = (newRank > rank) ? nextRank + 1 : nextRank - 1;
				nextFile = (newFile > file) ? nextFile + 1 : nextFile - 1;
				continueFindingStates = this.canMoveHelper(nextRank, nextFile);
			} while(continueFindingStates && (nextRank != newRank) && (nextFile != newFile));

			if(nextRank == newRank && nextFile == newFile) {
				return true;
			}
		} 
		
		return false;
	}
	
	// This function will get the possible future moves for the Queen
	public void setFutureStates() {
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
			if (newFile < 0 || newFile > 7) break;
			
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
