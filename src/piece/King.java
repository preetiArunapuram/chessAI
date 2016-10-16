package piece;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import board.ChessBoard;
import utils.MoveCode;

public abstract class King extends Piece {

	boolean canKingSideCastle = false;
	boolean canQueenSideCastle = false;
	boolean check = false;
	
	public King(int rank, int file) {
		super(rank, file);
		this.setVal(12);
	}

	@Override
	public boolean isValidMove(int newRank, int newFile) {
		// TODO Auto-generated method stub
		if (newRank < 0 || newRank > 7) return false;
		if (newFile < 0 || newFile > 7) return false;
		
		int rank = this.getRank();
		int file = this.getFile();
		
		// Ensure that a piece of the same color is not on the new rank and file
		int indexLocation = ChessBoard.getIndexLocation(newRank, newFile);
		ChessPiece piece = this.getBoard().pieceAt(indexLocation);
		if(piece != null && piece.getColor() == this.getColor()) {
			return false;
		}
		
		// New location cannot be the same as the old location
		if (newRank == rank && newFile == file) {
			return false;
		}
		
		if (Math.abs(newRank - rank) <= 1 && Math.abs(newFile - file) <= 1) {
			return true;
		}
		
		return false;
	}
	
	@Override
	// The tricky part here is to make sure that the King doesn't move into a position of Check.
	public void setFutureStates() {
		// TODO Auto-generated method stub
		this.stateSpace.clear();
		
		int rank = this.getRank();
		int file = this.getFile();
		
		// Basic moves
		boolean addState = futureStatesHelper(rank + 1, file + 1);
		addState = futureStatesHelper(rank, file + 1);
		addState = futureStatesHelper(rank - 1, file + 1);
		addState = futureStatesHelper(rank + 1, file);
		addState = futureStatesHelper(rank - 1, file);
		addState = futureStatesHelper(rank + 1, file - 1);
		addState = futureStatesHelper(rank, file - 1);
		addState = futureStatesHelper(rank - 1, file - 1);
	}
	
	public Set<Integer> getAttackingStates() {
		Set<Integer> attackingSquares = new HashSet<Integer>();
		for (Pair<Integer, MoveCode> move : this.stateSpace) {
			attackingSquares.add(move.getLeft());
		}
		
		return attackingSquares;
	}
	
	public abstract boolean canKingSideCastle();
	public abstract boolean canQueenSideCastle();
	
	@Override
	public ChessPiece unofficialMove(int rank, int file, MoveCode moveType) {
		//return super.unofficialMove(rank, file, moveType);
		int indexLocation = ChessBoard.getIndexLocation(rank, file);
		ChessPiece piece = this.getBoard().pieceAt(indexLocation);
		
		this.getBoard().movePieceToRankAndFile(this.getRank(), this.getFile(), rank, file);
		setRank(rank);
		setFile(file);
		
		if (moveType == MoveCode.NORMAL) {
			return piece;
		}
		
		if (moveType == MoveCode.KINGSIDE_CASTLE) {
			int rookLocation = ChessBoard.getIndexLocation(this.getRank(), 7);
			
			ChessPiece rook1 = this.getBoard().pieceAt(rookLocation);
			assert(rook1 instanceof Rook);
			
			this.getBoard().movePieceToRankAndFile(rook1.getRank(), rook1.getFile(), rank, 5);
			rook1.setRank(rank);
			rook1.setFile(5);
		} else if (moveType == MoveCode.QUEENSIDE_CASTLE) {
			int rookLocation = ChessBoard.getIndexLocation(this.getRank(), 0);
			
			ChessPiece rook2 = this.getBoard().pieceAt(rookLocation);
			assert(rook2 instanceof Rook);
			
			this.getBoard().movePieceToRankAndFile(rook2.getRank(), rook2.getFile(), rank, 3);
			rook2.setRank(rank);
			rook2.setFile(1);
		} 
		
		return null;
	}
	
	@Override
	public void undoMove(int prevRank, int prevFile, MoveCode moveType) {
		if (moveType == MoveCode.NORMAL) {
			super.undoMove(prevRank, prevFile, moveType);
			return;
		}
		
		this.getBoard().movePieceToRankAndFile(this.getRank(), this.getFile(), prevRank, prevFile);
		setRank(prevRank);
		setFile(prevFile);
		if (moveType == MoveCode.KINGSIDE_CASTLE) {
			int rookLocation = this.getBoard().getIndexLocation(this.getRank(), 5);
			ChessPiece rook1 = this.getBoard().pieceAt(rookLocation);
			assert(rook1 instanceof Rook);
			
			this.getBoard().movePieceToRankAndFile(rook1.getRank(), rook1.getFile(), prevRank, 7);
			rook1.setRank(prevRank);
			rook1.setFile(7);
		} else if (moveType == MoveCode.QUEENSIDE_CASTLE) {
			int rookLocation = this.getBoard().getIndexLocation(this.getRank(), 3);
			ChessPiece rook2 = this.getBoard().pieceAt(rookLocation);
			assert(rook2 instanceof Rook);
			
			this.getBoard().movePieceToRankAndFile(rook2.getRank(), rook2.getFile(), prevRank, 0);
		}
	}
	
}
