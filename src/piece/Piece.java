package piece;

import game.ChessGame;
import player.Player;
import utils.MoveCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import board.ChessBoard;

public abstract class Piece implements ChessPiece {
	private int rank;
	private int file;
	private int value;
	private boolean color;
	private ChessBoard board;
	private ChessGame game;
	private Player player;
	

	protected Set<Pair<Integer, MoveCode>> stateSpace = new HashSet<Pair<Integer, MoveCode>>();
	protected Set<Integer> attackingSquares = new HashSet<Integer>();
	private boolean hasMoved = false;
	
	public Piece(int rank, int file) {
		this.rank = rank;
		this.file = file;
	}
	
	public int getRank() {
		return rank;
	}
	
	public int getFile() {
		return file;
	}
	
	public boolean getColor() {
		return color;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setColor(boolean color) {
		this.color = color;
	}
	
	public ChessBoard getBoard() {
		return board;
	}
	
	public void setGame(ChessGame game) {
		this.game = game;
	}
	
	public ChessGame getGame() {
		return game;
	}
	
	public void setBoard(ChessBoard board) {
		this.board = board;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void setRank(int rank) {
		if (rank < 0 || rank > 7) {
			String errorString = "Setting illegal rank value " + rank;
			System.out.println(errorString);
			return;
		}
		
		this.rank = rank;
	}
	
	public void setFile(int file) {
		if (file < 0 || file > 7) {
			String errorString = "Setting illegal file value" + file;
			System.out.println(errorString);
			return;
		}
		
		this.file = file;	
	}
	
	public abstract boolean isValidMove(int newRank, int newFile);
	
	// To find eligible moves and ensure that King is not in check.
	public ChessPiece unofficialMove(int rank, int file, MoveCode moveType) {
		int newIndexLocation = ChessBoard.getIndexLocation(rank, file);
		ChessPiece piece = this.getBoard().pieceAt(newIndexLocation);

		this.getBoard().movePieceToRankAndFile(this.rank, this.file, rank, file);
		
		setRank(rank);
		setFile(file);
		
		return piece;
	}
	
	// Need to override in child classes to handle special moves
	public void undoMove(int prevRank, int prevFile, MoveCode moveType) {
		this.getBoard().movePieceToRankAndFile(this.getRank(), this.getFile(), prevRank, prevFile);
		setRank(prevRank);
		setFile(prevFile);
	}
	
	public ChessPiece officialMove(int rank, int file, MoveCode moveType) {
		ChessPiece capturedPiece = this.unofficialMove(rank, file, moveType);
		this.hasMoved = true;
		return capturedPiece;
	}
	
	public boolean capture(int newRank, int newFile) {
		int indexLocation = ChessBoard.getIndexLocation(newRank, newFile);
		ChessPiece piece = this.getBoard().pieceAt(indexLocation);
		if (piece.getColor() != this.color) {
			return true;
		}
		
		return false;
	}
	
	public void setVal(int value) {
		this.value = value;
	}
	
	public int getVal() {
		return value;
	}
	
	public void setHasMoved(boolean moved) {
		this.hasMoved = moved;
	}
	
	public boolean hasMoved() {
		return this.hasMoved;
	}
	
	public Set<Pair<Integer, MoveCode>> getFutureStates() {
		return this.stateSpace;
	}

	public abstract void setFutureStates();
	
	public abstract Set<Integer> getAttackingStates();
	
	// For queen, rook, and bishop, this function will help to find free squares along a straight or diagonal path
	protected boolean futureStatesHelper(int rank, int file) {
		if(rank < 0 || rank > 7 || file < 0 || file > 7) {
			return false;
		}
		
		Integer indexLocation = ChessBoard.getIndexLocation(rank, file);

		if (indexLocation == null) {return false;}
		ChessPiece piece = this.getBoard().pieceAt(indexLocation);
		if (piece != null) {
			if (piece.getColor() != this.getColor()) {
				this.stateSpace.add(new ImmutablePair(indexLocation, MoveCode.NORMAL));
			}
			
			return false;
		}
		
		else {
			this.stateSpace.add(new ImmutablePair(indexLocation, MoveCode.NORMAL));
		}
		
		return true;
	}
	
	protected boolean canMoveHelper(int rank, int file) {
		if(rank < 0 || rank > 7 || file < 0 || file > 7) {
			return false;
		}
		
		Integer indexLocation = ChessBoard.getIndexLocation(rank, file);
		ChessPiece piece = this.getBoard().pieceAt(indexLocation);
		if (piece != null) {
			return false;
		}
		
		return true;
	}
	
	public abstract String toString();
	
}
