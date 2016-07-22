package piece;

import java.util.HashSet;
import java.util.Set;

import board.ChessBoard;

public abstract class Pawn extends Piece {

	public Pawn() {
		this.setVal(1);
	}
	
	@Override
	public void setFutureStates() {
		// TODO Auto-generated method stub
		this.stateSpace.clear();
		
		int rank = this.getRank();
		int file = this.getFile();
		
		// Normal case
		boolean addState = false;
		
		Integer indexLocation = ChessBoard.getIndexLocation(rank + 1, file);
		if (indexLocation != null) {
			ChessPiece piece = this.getBoard().pieceAt(indexLocation);
			if (piece == null) {
				this.stateSpace.add(indexLocation);
				addState = true;
			}
		}
		
		else {
			return;
		}
		
		// Initial jump
		if (rank == 1) {
			indexLocation = ChessBoard.getIndexLocation(rank + 2, file);
			ChessPiece piece = this.getBoard().pieceAt(indexLocation);
			if (addState && piece == null) {
				this.stateSpace.add(indexLocation);
			}
		}
		
		// Diagonal attacks
		indexLocation = ChessBoard.getIndexLocation(rank + 1, file + 1);
		if (indexLocation != null) {
			ChessPiece piece = this.getBoard().pieceAt(indexLocation);
			if (piece != null && piece.getColor() != this.getColor()) {
				this.stateSpace.add(indexLocation);
			}
		}
		
		indexLocation = ChessBoard.getIndexLocation(rank + 1, file - 1);
		if (indexLocation != null) {
			ChessPiece piece = this.getBoard().pieceAt(indexLocation);
			if (piece != null && piece.getColor() != this.getColor()) {
				this.stateSpace.add(indexLocation);
			}
		}
		
		// TODO: Implement en passant check
	}
	
	/*public void setAttackingSquares() {
		// TODO Auto-generated method stub
		this.attackingSquares.clear();
		
		int rank = this.getRank();
		int file = this.getFile();
		
		Integer indexLocation1 = ChessBoard.getIndexLocation(rank + 1, file + 1);
		if (indexLocation1 != null) {
			this.attackingSquares.add(indexLocation1);
		}
		
		Integer indexLocation2 = ChessBoard.getIndexLocation(rank + 1, file - 1);
		if (indexLocation2 != null) {
			this.attackingSquares.add(indexLocation2);
		}
		
		// TODO: Implement en passant check
	}*/
	
}
