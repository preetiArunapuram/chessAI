package piece;

public class BlackBishop extends Bishop {

	public BlackBishop(int rank, int file) {
		super(rank, file);
		this.setColor(true);
	}
	
	public String toString() {
		return "BB";
	}
}
