package mechanics;

import piece.ChessPiece;

public class MoveObject {

	ChessPiece piece;
	Integer rank;
	Integer file;
	MoveType moveType;
	
	public MoveObject(ChessPiece piece, int rank, int file, MoveType moveType) {
		this.piece = piece;
		this.rank = rank;
		this.file = file;
		this.moveType = moveType;
	}
	
	public MoveObject(ChessPiece piece, MoveType moveType) {
		this.piece = piece;
		this.rank = null;
		this.file = null;
		this.moveType = moveType;
	}
}
