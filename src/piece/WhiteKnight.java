package piece;

public class WhiteKnight extends Knight {

	public WhiteKnight(int rank, int file) {
		super(rank, file);
		this.setColor(false);
	}
	
	public String toString() {
		return "WN";
	}
}
