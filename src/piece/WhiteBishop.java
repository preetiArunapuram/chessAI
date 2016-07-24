package piece;

public class WhiteBishop extends Bishop {

	public WhiteBishop(int rank, int file) {
		super(rank, file);
		this.setColor(false);
	}
	
	public String toString() {
		return "WB";
	}
}
