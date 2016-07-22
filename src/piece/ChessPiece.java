package piece;

import game.ChessGame;

import java.util.Set;

import board.ChessBoard;

public interface ChessPiece {
	
	public int getRank();
	public int getFile();
	public boolean getColor();
	public void setColor(boolean color);
	public ChessBoard getBoard();
	public void setBoard(ChessBoard board);
	public ChessPiece officialMove(int rank, int file);
	public int getVal();
	public void setVal(int value);
	public boolean hasMoved();
	public void setHasMoved(boolean moved);
	public boolean isValidMove(int newRank, int newFile);
	public void setFutureStates();
	public Set<Integer> getFutureStates();
	
	public void setGame(ChessGame game);
	public ChessGame getGame();
	public void setRank(int i);
	public void setFile(int i);
	public void unofficialMove(int rank, int file);
}
