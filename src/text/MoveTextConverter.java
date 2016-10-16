package text;

import board.ChessBoard;
import game.ChessGame;
import piece.Bishop;
import piece.ChessPiece;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Queen;
import piece.Rook;
import player.Player;
import utils.MoveCode;

public class MoveTextConverter {

	public static String moveToText(
			ChessPiece piece, 
			int oldRank, 
			int oldFile, 
			int newRank, 
			int newFile, 
			MoveCode moveType, 
			boolean captured
	) {
		char oldRankText = (char) (oldRank + 49);
		char oldFileText = (char) (oldFile + 'a');
		
		char newRankText = (char) (newRank + 49);
		char newFileText = (char) (newFile + 'a');
		
		StringBuilder sb = new StringBuilder();
		switch(moveType) {
			case KINGSIDE_CASTLE:
				return "O-O";
			case QUEENSIDE_CASTLE:
				return "O-O-O";
			case PROMOTE_KNIGHT:
				sb.append(new char[] {newFileText, newRankText, '=', 'N'});
				break;
			case PROMOTE_BISHOP:
				sb.append(new char[] {newFileText, newRankText, '=', 'B'});
				break;
			case PROMOTE_ROOK:
				sb.append(new char[] {newFileText, newRankText, '=', 'R'});
				break;
			case PROMOTE_QUEEN:
				sb.append(new char[] {newFileText, newRankText, '=', 'Q'});
				break;
			case NORMAL:
				if (piece instanceof Pawn) {
					if (captured) {
						sb.append(new char[] {oldFileText, 'x', newFileText, newRankText});
					} else {
						sb.append(new char[] {newFileText, newRankText});
					}
				} else {
					if (piece instanceof Knight) {
						sb.append('N');
					} else if (piece instanceof Bishop) {
						sb.append('B');
					} else if (piece instanceof Rook) {
						sb.append('R');
					} else if (piece instanceof Queen) {
						sb.append('Q');
					} else if (piece instanceof King) {
						sb.append('K');
					}
					
					if (captured) {
						sb.append(new char[] {'x', newFileText, newRankText});
					} else {
						sb.append(new char[] {newFileText, newRankText});
					}
				}
				
				break;
			default:
				return "";
		}
		
		Player currentPlayer = piece.getPlayer();
		ChessGame game = piece.getGame();
		
		Player opposingPlayer = (currentPlayer == game.getWhitePlayer()) 
			? game.getBlackPlayer() 
			: game.getWhitePlayer();
			
		if (game.isKingInCheck(opposingPlayer)) {
			sb.append('+');
		}
			
		return new String(sb);
	}
	
}
