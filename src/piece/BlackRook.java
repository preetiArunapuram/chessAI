package piece;

public class BlackRook extends Rook {

	public BlackRook(int rank, int file) {
		super(rank, file);
		this.setColor(true);
	}
	
	public String toString() {
		return "BR";
	}
}
