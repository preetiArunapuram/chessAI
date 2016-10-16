package piece;

import game.ChessGame;
import player.Player;
import utils.MoveCode;

import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import board.ChessBoard;

public interface ChessPiece {
	
	public int getRank();
	public int getFile();
	public boolean getColor();
	public void setColor(boolean color);
	public ChessBoard getBoard();
	public void setBoard(ChessBoard board);
	public ChessPiece officialMove(int rank, int file, MoveCode moveType);
	public int getVal();
	public void setVal(int value);
	public boolean hasMoved();
	public void setHasMoved(boolean moved);
	public boolean isValidMove(int newRank, int newFile);
	public void setFutureStates();
	public Set<Pair<Integer, MoveCode>> getFutureStates();
	
	public void setGame(ChessGame game);
	public ChessGame getGame();
	public void setRank(int i);
	public void setFile(int i);
	public ChessPiece unofficialMove(int rank, int file, MoveCode moveType);
	public void undoMove(int prevRank, int prevFile, MoveCode moveType);
	public void setPlayer(Player player);
	public Player getPlayer();
}
