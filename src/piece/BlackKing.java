package piece;

import java.util.Set;

import org.apache.commons.lang3.tuple.ImmutablePair;

import mechanics.MoveObject;
import mechanics.MoveType;
import utils.MoveCode;
import board.ChessBoard;

public class BlackKing extends King {

	public BlackKing(int rank, int file) {
		super(rank, file);
		this.setColor(true);
	}
	
	public void setFutureStates() {
		super.setFutureStates();
		int castleSquare;
		if(this.canKingSideCastle()) {
			castleSquare = ChessBoard.getIndexLocation(7, 6);
			
			this.stateSpace.add(new ImmutablePair(castleSquare, MoveCode.KINGSIDE_CASTLE));
		}
		
		if(this.canQueenSideCastle()) {
			castleSquare = ChessBoard.getIndexLocation(7, 1);
			this.stateSpace.add(new ImmutablePair(castleSquare, MoveCode.QUEENSIDE_CASTLE));
			
		}
	}
	
	@Override
	public boolean canKingSideCastle() {
		if(this.check) {
			return false;
		}
		
		Integer indexLocation = ChessBoard.getIndexLocation(7, 7);
		ChessPiece rook = this.getBoard().pieceAt(indexLocation);
		
		if(rook == null) {
			return false;
		}
		
		if (!this.hasMoved() && !rook.hasMoved()) {
			Integer indexLocation1 = ChessBoard.getIndexLocation(7, 5);
			Integer indexLocation2 = ChessBoard.getIndexLocation(7, 6);
			
			// Check that no opposing pieces are attacking the intermediate squares.
			Set<ChessPiece> whitePieces = this.getGame().getWhitePlayer().getActivePieces();
			for(ChessPiece piece : whitePieces) {
				if(piece.isValidMove(7, 5)) {
					return false;
				}
				
				if(piece.isValidMove(7, 6)) {
					return false;
				}
			}
			
			ChessPiece piece1 = this.getBoard().pieceAt(indexLocation1);
			ChessPiece piece2 = this.getBoard().pieceAt(indexLocation2);
			
			if (piece1 == null && piece2 == null) {
				return true;
			}
			
			return false;
		}
		
		return false;
	}

	@Override
	public boolean canQueenSideCastle() {
		if(this.check) {
			return false;
		}
		
		Integer indexLocation = ChessBoard.getIndexLocation(7, 0);
		ChessPiece rook = this.getBoard().pieceAt(indexLocation);
		
		if(rook == null) {
			return false;
		}
		
		if (!this.hasMoved() && !rook.hasMoved()) {
			Integer indexLocation1 = ChessBoard.getIndexLocation(7, 3);
			Integer indexLocation2 = ChessBoard.getIndexLocation(7, 2);
			Integer indexLocation3 = ChessBoard.getIndexLocation(7, 1);
			
			// Check that no opposing pieces are attacking the intermediate squares.
			Set<ChessPiece> whitePieces = this.getGame().getWhitePlayer().getActivePieces();
			for(ChessPiece piece : whitePieces) {
				if(piece.isValidMove(7, 3)) {
					return false;
				}
				
				if(piece.isValidMove(7, 2)) {
					return false;
				}
				
				if(piece.isValidMove(7, 1)) {
					return false;
				}
			}
			
			ChessPiece piece1 = this.getBoard().pieceAt(indexLocation1);
			ChessPiece piece2 = this.getBoard().pieceAt(indexLocation2);
			ChessPiece piece3 = this.getBoard().pieceAt(indexLocation3);
	
			if (piece1 == null && piece2 == null && piece3 == null) {
				return true;
			}
			
			return false;
		}
		
		return false;
	}
	
	public String toString() {
		return "BK";
	}
}
