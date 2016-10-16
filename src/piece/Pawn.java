package piece;

import java.util.HashSet;
import java.util.Set;

import board.ChessBoard;
import utils.MoveCode;

public abstract class Pawn extends Piece {

	public Pawn(int rank, int file) {
		super(rank, file);
		this.setVal(1);
	}
	
	@Override
	public abstract void setFutureStates();
	
	@Override
	public ChessPiece unofficialMove(int rank, int file, MoveCode moveType) {
		if (moveType == MoveCode.NORMAL) {
			return super.unofficialMove(rank, file, moveType);
		}
		
		ChessPiece piece = null;
		if (moveType == MoveCode.PROMOTE_KNIGHT) {
			if (this instanceof WhitePawn) {
				piece = new WhiteKnight(rank, file);
			} else {
				piece = new BlackKnight(rank, file);
			}	
		} else if (moveType == MoveCode.PROMOTE_BISHOP) {
			if (this instanceof WhitePawn) {
				piece = new WhiteBishop(rank, file);
			} else {
				piece = new BlackBishop(rank, file);
			}
		} else if (moveType == MoveCode.PROMOTE_ROOK) {
			if (this instanceof WhitePawn) {
				piece = new WhiteRook(rank, file);
			} else {
				piece = new BlackRook(rank, file);
			}
		} else if (moveType == MoveCode.PROMOTE_QUEEN) {
			if (this instanceof WhitePawn) {
				piece = new WhiteQueen(rank, file);
			} else {
				piece = new BlackQueen(rank, file);
			}
		}
		
		int indexLocation = ChessBoard.getIndexLocation(rank, file);
		ChessPiece capturedPiece = this.getBoard().pieceAt(indexLocation);
		this.getBoard().addPieceToRankAndFile(rank, file, piece);
		this.getPlayer().addPiece(piece);
		piece.setPlayer(this.getPlayer());
		piece.setBoard(this.getBoard());
		piece.setGame(this.getGame());
		
		this.getBoard().removePieceFromRankAndFile(this.getRank(), this.getFile());
		this.getPlayer().removePiece(this);
		
		setRank(rank);
		setFile(file);
		
		return capturedPiece;
	}
	
	@Override
	public void undoMove(int prevRank, int prevFile, MoveCode moveType) {;
		if (moveType == MoveCode.NORMAL) {
			super.undoMove(prevRank, prevFile, moveType);
			return;	
		}
		
		int indexLocation = ChessBoard.getIndexLocation(this.getRank(), this.getFile());
		ChessPiece promotedPiece = this.getBoard().pieceAt(indexLocation);
		if (moveType == MoveCode.PROMOTE_KNIGHT) {
			assert(promotedPiece instanceof Knight);
		} else if (moveType == MoveCode.PROMOTE_BISHOP) {
			assert(promotedPiece instanceof Bishop);
		} else if (moveType == MoveCode.PROMOTE_ROOK) {
			assert(promotedPiece instanceof Rook);
		} else if (moveType == MoveCode.PROMOTE_QUEEN) {
			assert(promotedPiece instanceof Queen);
		}
		
		this.getBoard().removePieceFromRankAndFile(this.getRank(), this.getFile());
		this.getPlayer().removePiece(promotedPiece);
		
		this.getBoard().addPieceToRankAndFile(prevRank, prevFile, this);
		this.getPlayer().addPiece(this);
		
		this.setRank(prevRank);
		this.setFile(prevFile);
	}
}
