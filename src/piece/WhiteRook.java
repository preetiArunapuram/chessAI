package piece;

public class WhiteRook extends Rook {

	public WhiteRook(int rank, int file) {
		super(rank, file);
		this.setColor(false);
	}
	
	public String toString() {
		return "WR";
	}
	
}
