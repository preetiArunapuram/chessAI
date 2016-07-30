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
		return;
	}
}
