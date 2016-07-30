package piece;

import java.util.HashSet;
import java.util.Set;

import mechanics.MoveObject;
import mechanics.MoveType;
import board.ChessBoard;

public abstract class Pawn extends Piece {

	public Pawn(int rank, int file) {
		super(rank, file);
		this.setVal(1);
	}
	
	@Override
	public void setFutureStates() {
		// TODO Auto-generated method stub
		this.stateSpace.clear();
		this.preCheckValidMoves.clear();
		
		int rank = this.getRank();
		int file = this.getFile();
		
		// Normal case
		boolean addState = false;
		
		Integer indexLocation = ChessBoard.getIndexLocation(rank + 1, file);
		ChessPiece piece = this.getBoard().pieceAt(indexLocation);
		if (piece == null) {
			this.stateSpace.add(indexLocation);
			
			if (rank + 1 == 7) {
				MoveObject bishop_promo = new MoveObject(this, rank + 1, file, MoveType.PAWN_CAPTURE_PROMOTION_BISHOP);
				MoveObject knight_promo = new MoveObject(this, rank + 1, file, MoveType.PAWN_CAPTURE_PROMOTION_KNIGHT);
				MoveObject rook_promo = new MoveObject(this, rank + 1, file, MoveType.PAWN_CAPTURE_PROMOTION_ROOK);
				MoveObject queen_promo = new MoveObject(this, rank + 1, file, MoveType.PAWN_CAPTURE_PROMOTION_QUEEN);
				
				this.preCheckValidMoves.add(bishop_promo);
				this.preCheckValidMoves.add(knight_promo);
				this.preCheckValidMoves.add(rook_promo);
				this.preCheckValidMoves.add(queen_promo);
			} else {
				MoveObject move = new MoveObject(this, rank + 1, file, MoveType.NORMAL);
				this.preCheckValidMoves.add(move);
				addState = true;
			}
		}
		
		// Initial jump
		if (rank == 1) {
			indexLocation = ChessBoard.getIndexLocation(rank + 2, file);
			piece = this.getBoard().pieceAt(indexLocation);
			if (addState && piece == null) {
				this.stateSpace.add(indexLocation);
				
				MoveObject move = new MoveObject(this, rank + 2, file, MoveType.NORMAL);
				this.preCheckValidMoves.add(move);
			}
		}
		
		// Diagonal attacks
		indexLocation = ChessBoard.getIndexLocation(rank + 1, file + 1);
		if (indexLocation != null) {
			piece = this.getBoard().pieceAt(indexLocation);
			if (piece != null && piece.getColor() != this.getColor()) {
				this.stateSpace.add(indexLocation);
			}
		}
		
		indexLocation = ChessBoard.getIndexLocation(rank + 1, file - 1);
		if (indexLocation != null) {
			piece = this.getBoard().pieceAt(indexLocation);
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
