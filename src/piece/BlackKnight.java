package piece;

public class BlackKnight extends Knight {
	
	public BlackKnight(int rank, int file) {
		super(rank, file);
		this.setColor(true);
	}

	public String toString() {
		return "BN";
	}
	
}
