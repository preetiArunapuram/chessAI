package piece;

import java.util.HashSet;
import java.util.Set;


import org.apache.commons.lang3.tuple.ImmutablePair;

import board.ChessBoard;
import utils.MoveCode;

public class WhitePawn extends Pawn {

	public WhitePawn(int rank, int file) {
		super(rank, file);
		this.setColor(false);
	}

	@Override
	public boolean isValidMove(int newRank, int newFile) {
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
		
		if(newRank - rank == 1 && newFile == file && piece == null) {
			return true;
		} else if(newRank - rank == 2 && !this.hasMoved()) {
			int nextRank = rank;
			boolean continueFindingStates;
			do {
				nextRank++;
				continueFindingStates = this.canMoveHelper(nextRank, file);
			} while(continueFindingStates && nextRank == newRank);
			
			if(nextRank == newRank) {
				return true;
			}
		} else if(newRank - rank == 1 && Math.abs(newFile - file) == 1 && piece != null) {
			return true;
		}
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFutureStates() {
		// TODO Auto-generated method stub
		this.stateSpace.clear();
		int rank = this.getRank();
		int file = this.getFile();
	
		boolean addState = false;
		Integer indexLocation;
		
		if(rank < 7) {
			// Normal case
			indexLocation = ChessBoard.getIndexLocation(rank + 1, file);
			if (indexLocation != null) {
				ChessPiece piece = this.getBoard().pieceAt(indexLocation);
				if (piece == null) {
					if (rank < 6) {
						this.stateSpace.add(new ImmutablePair(indexLocation, MoveCode.NORMAL));
					} else {
						this.stateSpace.add(new ImmutablePair(indexLocation, MoveCode.PROMOTE_KNIGHT));
						this.stateSpace.add(new ImmutablePair(indexLocation, MoveCode.PROMOTE_BISHOP));
						this.stateSpace.add(new ImmutablePair(indexLocation, MoveCode.PROMOTE_ROOK));
						this.stateSpace.add(new ImmutablePair(indexLocation, MoveCode.PROMOTE_QUEEN));
					}
					addState = true;
				}
			}
			
			// Diagonal attacks
			if(file < 7) {
				indexLocation = ChessBoard.getIndexLocation(rank + 1, file + 1);
				if (indexLocation != null) {
					ChessPiece piece = this.getBoard().pieceAt(indexLocation);
					if (piece != null && piece.getColor() != this.getColor()) {
						if (rank < 6) {
							this.stateSpace.add(new ImmutablePair(indexLocation, MoveCode.NORMAL));
						} else {
							this.stateSpace.add(new ImmutablePair(indexLocation, MoveCode.PROMOTE_KNIGHT));
							this.stateSpace.add(new ImmutablePair(indexLocation, MoveCode.PROMOTE_BISHOP));
							this.stateSpace.add(new ImmutablePair(indexLocation, MoveCode.PROMOTE_ROOK));
							this.stateSpace.add(new ImmutablePair(indexLocation, MoveCode.PROMOTE_QUEEN));
						}
					}
				}
			}
			
			if(file > 0) {
				indexLocation = ChessBoard.getIndexLocation(rank + 1, file - 1);
				if (indexLocation != null) {
					ChessPiece piece = this.getBoard().pieceAt(indexLocation);
					if (piece != null && piece.getColor() != this.getColor()) {
						if (rank < 6) {
							this.stateSpace.add(new ImmutablePair(indexLocation, MoveCode.NORMAL));
						} else {
							this.stateSpace.add(new ImmutablePair(indexLocation, MoveCode.PROMOTE_KNIGHT));
							this.stateSpace.add(new ImmutablePair(indexLocation, MoveCode.PROMOTE_BISHOP));
							this.stateSpace.add(new ImmutablePair(indexLocation, MoveCode.PROMOTE_ROOK));
							this.stateSpace.add(new ImmutablePair(indexLocation, MoveCode.PROMOTE_QUEEN));
						}
					}
				}
			}
		}
		
		else {
			return;
		}
		
		// Initial jump
		if (!this.hasMoved()) {
			indexLocation = ChessBoard.getIndexLocation(rank + 2, file);
			ChessPiece piece = this.getBoard().pieceAt(indexLocation);
			if (addState && piece == null) {
				this.stateSpace.add(new ImmutablePair(indexLocation, MoveCode.NORMAL));
			}
		}
		
		// TODO: Implement en passant check
	}
	
	public Set<Integer> getAttackingStates() {
		Set<Integer> attackingSquares = new HashSet<Integer>();
		int rank = this.getRank();
		int file = this.getFile();
		
		Integer indexLocation1 = ChessBoard.getIndexLocation(rank + 1, file + 1);
		if (indexLocation1 != null) {
			attackingSquares.add(indexLocation1);
		}
		
		Integer indexLocation2 = ChessBoard.getIndexLocation(rank + 1, file - 1);
		if (indexLocation2 != null) {
			attackingSquares.add(indexLocation2);
		}
		
		return attackingSquares;
	}
	
	public String toString() {
		return "WP";
	}
}
