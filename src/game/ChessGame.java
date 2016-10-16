package game;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import board.ChessBoard;
import piece.BlackBishop;
import piece.BlackKing;
import piece.BlackKnight;
import piece.BlackPawn;
import piece.BlackQueen;
import piece.BlackRook;
import piece.ChessPiece;
import piece.King;
import piece.WhiteBishop;
import piece.WhiteKing;
import piece.WhiteKnight;
import piece.WhitePawn;
import piece.WhiteQueen;
import piece.WhiteRook;
import player.Player;
import text.MoveTextConverter;
import utils.MoveCode;

public class ChessGame {

	private Player w_player;
	private Player b_player;
	private ChessBoard board;
	
	public ChessGame() {
		this.w_player = new Player(false);
		this.b_player = new Player(true);
		this.board = new ChessBoard();
		
		w_player.setActive(true);
		this.initializeBoard();
	}
	
	public void initializeBoard() {
		// White pieces
		ChessPiece[] whitePawns = new ChessPiece[8];
		for(int i = 0; i < whitePawns.length; i++) {
			whitePawns[i] = new WhitePawn(1, i);
			this.board.addPieceToRankAndFile(1, i, whitePawns[i]);
		}
		
		ChessPiece whiteRook1 = new WhiteRook(0, 0);	
		ChessPiece whiteRook2 = new WhiteRook(0, 7);
		
		this.board.addPieceToRankAndFile(0, 0, whiteRook1);
		this.board.addPieceToRankAndFile(0, 7, whiteRook2);
		
		ChessPiece whiteKnight1 = new WhiteKnight(0, 1);
		ChessPiece whiteKnight2 = new WhiteKnight(0, 6);
		
		this.board.addPieceToRankAndFile(0, 1, whiteKnight1);
		this.board.addPieceToRankAndFile(0, 6, whiteKnight2);
		
		ChessPiece whiteBishop1 = new WhiteBishop(0, 2);
		ChessPiece whiteBishop2 = new WhiteBishop(0, 5);
		
		this.board.addPieceToRankAndFile(0, 2, whiteBishop1);
		this.board.addPieceToRankAndFile(0, 5, whiteBishop2);
		
		ChessPiece whiteKing = new WhiteKing(0, 4);
		this.board.addPieceToRankAndFile(0, 4, whiteKing);
		
		ChessPiece whiteQueen = new WhiteQueen(0, 3);
		this.board.addPieceToRankAndFile(0, 3, whiteQueen);
		
		Set<ChessPiece> allWhitePieces = new HashSet<ChessPiece>();
		for(int i = 0; i < whitePawns.length; i++) {
			allWhitePieces.add(whitePawns[i]);
		}
		
		allWhitePieces.add(whiteRook1);
		allWhitePieces.add(whiteRook2);
		allWhitePieces.add(whiteKnight1);
		allWhitePieces.add(whiteKnight2);
		allWhitePieces.add(whiteBishop1);
		allWhitePieces.add(whiteBishop2);
		allWhitePieces.add(whiteQueen);
		allWhitePieces.add(whiteKing);
		
		w_player.setActivePieces(allWhitePieces);
		w_player.setKing((King)whiteKing);
		
		// Black pieces
		ChessPiece[] blackPawns = new ChessPiece[8];
		for(int i = 0; i < blackPawns.length; i++) {
			blackPawns[i] = new BlackPawn(6, i);
			this.board.addPieceToRankAndFile(6, i, blackPawns[i]);
		}
		
		ChessPiece blackRook1 = new BlackRook(7, 0);
		ChessPiece blackRook2 = new BlackRook(7, 7);
		
		this.board.addPieceToRankAndFile(7, 0, blackRook1);
		this.board.addPieceToRankAndFile(7, 7, blackRook2);
		
		ChessPiece blackKnight1 = new BlackKnight(7, 1);
		ChessPiece blackKnight2 = new BlackKnight(7, 6);
		
		this.board.addPieceToRankAndFile(7, 1, blackKnight1);
		this.board.addPieceToRankAndFile(7, 6, blackKnight2);
		
		ChessPiece blackBishop1 = new BlackBishop(7, 2);
		ChessPiece blackBishop2 = new BlackBishop(7, 5);
		
		this.board.addPieceToRankAndFile(7, 2, blackBishop1);
		this.board.addPieceToRankAndFile(7, 5, blackBishop2);
		
		ChessPiece blackKing = new BlackKing(7, 4);
		this.board.addPieceToRankAndFile(7, 4, blackKing);
		
		ChessPiece blackQueen = new BlackQueen(7, 3);
		this.board.addPieceToRankAndFile(7, 3, blackQueen);

		Set<ChessPiece> allBlackPieces = new HashSet<ChessPiece>();
		for(int i = 0; i < blackPawns.length; i++) {
			allBlackPieces.add(blackPawns[i]);
		}
		
		allBlackPieces.add(blackRook1);
		allBlackPieces.add(blackRook2);
		allBlackPieces.add(blackKnight1);
		allBlackPieces.add(blackKnight2);
		allBlackPieces.add(blackBishop1);
		allBlackPieces.add(blackBishop2);
		allBlackPieces.add(blackQueen);
		allBlackPieces.add(blackKing);
		
		b_player.setActivePieces(allBlackPieces);
		b_player.setKing((King)blackKing);
		
		for(ChessPiece piece : w_player.getActivePieces()) {
			piece.setBoard(this.board);
			piece.setGame(this);
			piece.setPlayer(w_player);
		}
		
		for(ChessPiece piece : b_player.getActivePieces()) {
			piece.setBoard(this.board);
			piece.setGame(this);
			piece.setPlayer(b_player);
		}
	}
	
	public Player getWhitePlayer() {
		return this.w_player;
	}
	
	public Player getBlackPlayer() {
		return this.b_player;
	}
	
	private ArrayList<Pair<ChessPiece, Pair<Integer, MoveCode>>> getAllowedMoves(Player activePlayer) {
		Player opposingPlayer = (activePlayer == this.w_player) ? this.b_player : this.w_player;
		ArrayList<Pair<ChessPiece, Pair<Integer, MoveCode>>> allowedMoves = new ArrayList<Pair<ChessPiece, Pair<Integer, MoveCode>>>();

		Set<ChessPiece> allActivePieces = new HashSet<ChessPiece>();
		for (ChessPiece piece : activePlayer.getActivePieces()) {
			allActivePieces.add(piece);
		}
		
		for(ChessPiece piece : allActivePieces) {
			piece.setFutureStates();
			Set<Pair<Integer, MoveCode>> futureLocations = piece.getFutureStates();
			if(futureLocations.isEmpty()) continue;
			
			int originalRank = piece.getRank();
			int originalFile = piece.getFile();

			for(Pair<Integer, MoveCode> move : futureLocations) {
				int loc = move.getLeft();
				MoveCode moveType = move.getRight();
				
				Pair<Integer, Integer> rankAndFile = ChessBoard.getRankAndFileLocation(loc);
				int rank = rankAndFile.getLeft();
				int file = rankAndFile.getRight();
				
				ChessPiece displacedPiece = piece.unofficialMove(rank, file, moveType);
				if(displacedPiece != null) {
					opposingPlayer.removePiece(displacedPiece);
				}
				
				boolean kingIsAttacked = this.isKingInCheck(activePlayer);
				if(!kingIsAttacked) {
					allowedMoves.add(
						new ImmutablePair<ChessPiece, Pair<Integer, MoveCode>>(
								piece, 
								move
						)
					);
				}
				
				piece.undoMove(originalRank, originalFile, moveType);
				if(displacedPiece != null) {
					this.board.addPieceToRankAndFile(rank, file, displacedPiece);
					opposingPlayer.addPiece(displacedPiece);
				}
			}
		}
		
		return allowedMoves;
	}
	
	public boolean isKingInCheck(Player activePlayer) {
		King kingPiece = activePlayer.getKing();
		//System.out.println(kingPiece);
		Player opposingPlayer = (activePlayer == this.w_player) ? this.b_player : this.w_player;
		for(ChessPiece opposingPiece : opposingPlayer.getActivePieces()) {
			//System.out.println(opposingPiece);
			if(opposingPiece.isValidMove(kingPiece.getRank(), kingPiece.getFile())) {
				//System.out.println("King on " + kingRank + ", " + kingFile + " is attacked by " + opposingPiece.toString() + " on " + opposingPiece.getRank() + ", " + opposingPiece.getFile());
				return true;
			}
		}
		
		return false;
	}
	
	public boolean playOneRound(Player activePlayer) {
		ArrayList<Pair<ChessPiece, Pair<Integer, MoveCode>>> movesArray = this.getAllowedMoves(activePlayer);
		int moveCount = movesArray.size();
		
		Player opposingPlayer = activePlayer == this.w_player
			? this.b_player
			: this.w_player;
		
		if(moveCount == 0) {
			String finalStatus;
			if (isKingInCheck(activePlayer)) {
				finalStatus = activePlayer == this.w_player
					? "0-1"
					: "1-0";
			} else {
				finalStatus = "0-0";
			}
			System.out.println(finalStatus);
			return false;
		} else if (activePlayer.getActivePieces().size() == 1 && activePlayer.getKing() != null && opposingPlayer.getActivePieces().size() == 1 && opposingPlayer.getKing() != null) {
			System.out.println("0-0");
			return false;
		}
		
		Random rng = new Random();

		int ind = rng.nextInt(movesArray.size());
		Pair<ChessPiece, Pair<Integer, MoveCode>> nextMove = movesArray.get(ind);
		ChessPiece piece = nextMove.getLeft();
		Pair<Integer, MoveCode> move = nextMove.getRight();
		
		int indexLocation = move.getLeft();
		MoveCode moveType = move.getRight();
		
		Pair<Integer, Integer> rankAndFile = ChessBoard.getRankAndFileLocation(indexLocation);
		
		int oldRank = piece.getRank();
		int oldFile = piece.getFile();
		int newRank = rankAndFile.getLeft();
		int newFile = rankAndFile.getRight();
		
		ChessPiece capturedPiece = piece.officialMove(newRank, newFile , moveType);
		String moveText = MoveTextConverter.moveToText(piece, oldRank, oldFile, newRank, newFile, moveType, (capturedPiece != null));
		System.out.print(moveText);
		
		if(capturedPiece != null) {
			capturedPiece.getPlayer().removePiece(capturedPiece);
		}
		
		return true;
	}
	
	public void playSomeRounds(int n) {
		for(int i = 0; i < n; i++) {
			//System.out.println("White playing: ");
			boolean whiteStillPlayed = this.playOneRound(this.w_player);
			if(!whiteStillPlayed) {
				return;
			}
			
			System.out.print('\t');
			//this.printCurrentBoard();
			//System.out.println("--------------------------------------------------------------------");
			
			//System.out.println("Black playing: ");
			boolean blackStillPlayed = this.playOneRound(this.b_player);
			if(!blackStillPlayed) {
				return;
			}
			
			System.out.println();
			//this.printCurrentBoard();
			//System.out.println("--------------------------------------------------------------------");
		}
	}
	
	public void printCurrentBoard() {
		System.out.println(this.board);
	}
}
