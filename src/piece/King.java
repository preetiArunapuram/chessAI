package piece;

import java.util.Set;

import board.ChessBoard;

public abstract class King extends Piece {

	boolean canKingSideCastle = false;
	boolean canQueenSideCastle = false;
	boolean check = false;
	
	public King() {
		this.setVal(12);
	}

	@Override
	public boolean isValidMove(int newRank, int newFile) {
		// TODO Auto-generated method stub
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
		
		if (Math.abs(newRank - rank) == 1 && Math.abs(newRank - rank) == 1) {
			return true;
		}
		
		return false;
	}
	
	@Override
	// The tricky part here is to make sure that the King doesn't move into a position of Check.
	public void setFutureStates() {
		// TODO Auto-generated method stub
		this.stateSpace.clear();
		int rank = this.getRank();
		int file = this.getFile();
		
		// Basic moves
		boolean addState = futureStatesHelper(rank + 1, file + 1);
		addState = futureStatesHelper(rank, file + 1);
		addState = futureStatesHelper(rank - 1, file + 1);
		addState = futureStatesHelper(rank + 1, file);
		addState = futureStatesHelper(rank - 1, file);
		addState = futureStatesHelper(rank + 1, file - 1);
		addState = futureStatesHelper(rank, file - 1);
		addState = futureStatesHelper(rank - 1, file - 1);
		
		//System.out.println("We have the state space now!");
		
		//System.out.println(this.stateSpace);
		
		
		/*
		// King-side castling
		Integer indexLocation = ChessBoard.getIndexLocation(0, 0);
		ChessPiece piece = this.getBoard().pieceAt(indexLocation);
		
		if (!this.hasMoved() && !piece.hasMoved()) {
			this.canKingSideCastle = true;
		}
		
		// Queen-side castling
		indexLocation = ChessBoard.getIndexLocation(0, 0);
		piece = this.getBoard().pieceAt(indexLocation);*/
		
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
	
	public abstract boolean canKingSideCastle();
	public abstract boolean canQueenSideCastle();
	
}
