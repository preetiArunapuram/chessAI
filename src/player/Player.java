package player;

import java.util.Map;
import java.util.Set;

import piece.ChessPiece;
import piece.King;

public class Player {
	private boolean inCheck = false;
	private boolean color;
	private Set<ChessPiece> activePieces;
	private boolean isActive = false;
	
	private King king;
	
	public Player(boolean color) {
		this.color = color;
	}
	
	public King getKing() {
		return this.king;
	}
	
	public void setKing(King kingPiece) {
		this.king = kingPiece;
	}
	
	public boolean getColor() {
		return this.color;
	}
	
	public boolean isActive() {
		return this.isActive;
	}
	
	public void setInCheck(boolean check) {
		this.inCheck = check;
	}
	
	public boolean inCheck() {
		return this.inCheck;
	}
	
	public void setActivePieces(Set<ChessPiece> pieces) {
		this.activePieces = pieces;
	}
	
	public Set<ChessPiece> getActivePieces() {
		return activePieces;
	}
	
	public void removePiece(ChessPiece capturedPiece) {
		boolean removed = this.activePieces.remove(capturedPiece);
	}
	
	public void addPiece(ChessPiece piece) {
		this.activePieces.add(piece);
	}
	
	public void setActive(boolean active) {
		this.isActive = active;
	}
}
