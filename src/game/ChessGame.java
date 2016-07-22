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
			whitePawns[i] = new WhitePawn();
			whitePawns[i].setRank(1);
			whitePawns[i].setFile(i);
			this.board.addPieceToRankAndFile(1, i, whitePawns[i]);
		}
		
		ChessPiece whiteRook1 = new WhiteRook();
		whiteRook1.setRank(0);
		whiteRook1.setFile(0);
		
		ChessPiece whiteRook2 = new WhiteRook();
		whiteRook2.setRank(0);
		whiteRook2.setFile(7);
		
		this.board.addPieceToRankAndFile(0, 0, whiteRook1);
		this.board.addPieceToRankAndFile(0, 7, whiteRook2);
		
		ChessPiece whiteKnight1 = new WhiteKnight();
		whiteKnight1.setRank(0);
		whiteKnight1.setFile(1);
		
		ChessPiece whiteKnight2 = new WhiteKnight();
		whiteKnight2.setRank(0);
		whiteKnight2.setFile(6);
		
		this.board.addPieceToRankAndFile(0, 1, whiteKnight1);
		this.board.addPieceToRankAndFile(0, 6, whiteKnight2);
		
		ChessPiece whiteBishop1 = new WhiteBishop();
		whiteBishop1.setRank(0);
		whiteBishop1.setFile(2);
		
		ChessPiece whiteBishop2 = new WhiteBishop();
		whiteBishop2.setRank(0);
		whiteBishop2.setFile(5);
		
		this.board.addPieceToRankAndFile(0, 2, whiteBishop1);
		this.board.addPieceToRankAndFile(0, 5, whiteBishop2);
		
		ChessPiece whiteKing = new WhiteKing();
		whiteKing.setRank(0);
		whiteKing.setFile(4);
		
		this.board.addPieceToRankAndFile(0, 4, whiteKing);
		
		ChessPiece whiteQueen = new WhiteQueen();
		whiteQueen.setRank(0);
		whiteQueen.setFile(3);
		
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
			blackPawns[i] = new BlackPawn();
			blackPawns[i].setRank(6);
			blackPawns[i].setFile(i);
			this.board.addPieceToRankAndFile(6, i, blackPawns[i]);
		}
		
		ChessPiece blackRook1 = new BlackRook();
		blackRook1.setRank(7);
		blackRook1.setFile(0);
		
		ChessPiece blackRook2 = new BlackRook();
		blackRook2.setRank(7);
		blackRook2.setFile(7);
		
		this.board.addPieceToRankAndFile(7, 0, blackRook1);
		this.board.addPieceToRankAndFile(7, 7, blackRook2);
		
		ChessPiece blackKnight1 = new BlackKnight();
		blackKnight1.setRank(7);
		blackKnight1.setFile(1);
		
		ChessPiece blackKnight2 = new BlackKnight();
		blackKnight2.setRank(7);
		blackKnight2.setFile(6);
		
		this.board.addPieceToRankAndFile(7, 1, blackKnight1);
		this.board.addPieceToRankAndFile(7, 6, blackKnight2);
		
		ChessPiece blackBishop1 = new BlackBishop();
		blackBishop1.setRank(7);
		blackBishop1.setFile(2);
		
		ChessPiece blackBishop2 = new BlackBishop();
		blackBishop2.setRank(7);
		blackBishop2.setFile(5);
		
		this.board.addPieceToRankAndFile(7, 2, blackBishop1);
		this.board.addPieceToRankAndFile(7, 5, blackBishop2);
		
		ChessPiece blackKing = new BlackKing();
		blackKing.setRank(7);
		blackKing.setFile(4);
		
		this.board.addPieceToRankAndFile(7, 4, blackKing);
		
		ChessPiece blackQueen = new BlackQueen();
		blackQueen.setRank(7);
		blackQueen.setFile(3);
		
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
		}
		
		for(ChessPiece piece : b_player.getActivePieces()) {
			piece.setBoard(this.board);
			piece.setGame(this);
		}
	}
	
	public Player getWhitePlayer() {
		return this.w_player;
	}
	
	public Player getBlackPlayer() {
		return this.b_player;
	}
	
	private ArrayList<Pair<ChessPiece, Integer>> getAllowedMoves(Player activePlayer) {
		//System.out.println("Number of pieces before loop: " + activePlayer.getActivePieces().size());
		Player opposingPlayer = (activePlayer == this.w_player) ? this.b_player : this.w_player;
		King kingPiece = activePlayer.getKing();
		int kingRank = kingPiece.getRank();
		int kingFile = kingPiece.getFile();
		
		ArrayList<Pair<ChessPiece, Integer>> allowedMoves = new ArrayList<Pair<ChessPiece, Integer>>();

		Set<ChessPiece> allActivePieces = activePlayer.getActivePieces();
		for(ChessPiece piece : allActivePieces) {
			piece.setFutureStates();
			Set<Integer> futureLocations = piece.getFutureStates();
			//System.out.println(futureLocations);
			if(futureLocations.isEmpty()) continue;
			
			int originalRank = piece.getRank();
			int originalFile = piece.getFile();

			for(Integer loc : futureLocations) {
				//System.out.println("Exploring location: " + loc);
				Pair<Integer, Integer> rankAndFile = ChessBoard.getRankAndFileLocation(loc);
				int rank = rankAndFile.getLeft();
				int file = rankAndFile.getRight();
				
				ChessPiece displacedPiece = piece.officialMove(rank, file);
				if(displacedPiece != null) {
					//System.out.println("Displaced piece: " + displacedPiece.toString());
					opposingPlayer.removePiece(displacedPiece);
				}

				
				boolean kingIsAttacked = false;
				
				for(ChessPiece opposingPiece : opposingPlayer.getActivePieces()) {
					if(opposingPiece.isValidMove(kingRank, kingFile)) {
						kingIsAttacked = true;
						break;
					}
				}
				
				if(!kingIsAttacked) {
					allowedMoves.add(
						new ImmutablePair<ChessPiece, Integer>(piece, ChessBoard.getIndexLocation(rank, file))
					);
				}
				
				piece.officialMove(originalRank, originalFile);
				if(displacedPiece != null) {
					//System.out.println("Added back " + displacedPiece.toString());
					this.board.addPieceToRankAndFile(rank, file, displacedPiece);
					opposingPlayer.addPiece(displacedPiece);
					//System.out.println("Replaced piece: " + displacedPiece.toString());
				}
				
				//System.out.println("Number of pieces after replacement: " + opposingPlayer.getActivePieces().size());
				//System.out.println("Number of pieces: " + activePlayer.getActivePieces().size());
			}
			
			//piece.officialMove(originalRank, originalFile);
			//}
		}
		
		return allowedMoves;
	}
	
	public boolean playOneRound(Player activePlayer) {
		//System.out.println("Number of pieces beginning of play: " + activePlayer.getActivePieces().size());
		ArrayList<Pair<ChessPiece, Integer>> movesArray = this.getAllowedMoves(activePlayer);
		int moveCount = movesArray.size();
		
		if(moveCount == 0) {
			System.out.println("Game over!");
			return false;
		}
		
		//Pair<ChessPiece, Integer>[] movesArray = (Pair<ChessPiece, Integer>[]) moves.toArray();
		Random rng = new Random();

		int ind = rng.nextInt(movesArray.size());
		Pair<ChessPiece, Integer> whiteNextMove = movesArray.get(ind);
		ChessPiece piece = whiteNextMove.getLeft();
		Pair<Integer, Integer> rankAndFile = ChessBoard.getRankAndFileLocation(whiteNextMove.getRight());
		
		//System.out.println("Number of pieces after capture: " + activePlayer.getActivePieces().size());
		ChessPiece capturedPiece = piece.officialMove(rankAndFile.getLeft(), rankAndFile.getRight());
		if(capturedPiece != null) {
			//System.out.println("Captured " + capturedPiece.toString());
			Player opposingPlayer = (activePlayer == this.w_player) ? this.b_player : this.w_player;
			opposingPlayer.removePiece(capturedPiece);
		}
		
		//System.out.println("Number of pieces after capture: " + activePlayer.getActivePieces().size());
		return true;
	}
	
	public void playSomeRounds(int n) {
		for(int i = 0; i < n; i++) {
			System.out.println("White playing: ");
			//System.out.println("Number of white pieces before play: " + w_player.getActivePieces().size());
			//System.out.println("Number of pieces: " + this.w_player.getActivePieces().size());
			boolean whiteStillPlayed = this.playOneRound(this.w_player);
			if(!whiteStillPlayed) {
				return;
			}
			
			this.printCurrentBoard();
			//System.out.println("Number of white pieces after play: " + w_player.getActivePieces().size());
			System.out.println("--------------------------------------------------------------------");
			
			System.out.println("Black playing: ");
			//System.out.println("Number of black pieces before play: " + b_player.getActivePieces().size());
			//System.out.println("Number of pieces: " + this.b_player.getActivePieces().size());
			boolean blackStillPlayed = this.playOneRound(this.b_player);
			if(!blackStillPlayed) {
				return;
			}
			
			this.printCurrentBoard();
			//System.out.println("Number of black pieces after play: " + b_player.getActivePieces().size());
			System.out.println("--------------------------------------------------------------------");
		}
	}
	
	public void printCurrentBoard() {
		System.out.println(this.board);
	}
}
