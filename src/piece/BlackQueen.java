package piece;

public class BlackQueen extends Queen {

	public BlackQueen(int rank, int file) {
		super(rank, file);
		this.setColor(true);
	}
	
	public String toString() {
		return "BQ";
	}
	
}
