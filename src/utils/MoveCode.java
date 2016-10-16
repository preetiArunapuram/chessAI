package utils;

public enum MoveCode {
	KINGSIDE_CASTLE(0), 
	QUEENSIDE_CASTLE(1), 
	EN_PASSANT(2), 
	PROMOTE_QUEEN(3), 
	PROMOTE_ROOK(4), 
	PROMOTE_KNIGHT(5), 
	PROMOTE_BISHOP(6),
	NORMAL(7);
	
	private int value;
	
	private MoveCode(int value) {
		this.value = value;
	}
	
}
