package piece;

public class WhiteQueen extends Queen {

	public WhiteQueen(int rank, int file) {
		super(rank, file);
		this.setColor(false);
	}

	public String toString() {
		return "WQ";
	}
}
