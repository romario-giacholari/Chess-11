package com.mygdx.game.models;

import java.util.ArrayList;

import com.mygdx.game.rules.*;



/**
 * A class to represent the board of a game of chess
 * @author Student
 *
 */
public class Board {
	
	private boolean turn = true;
	/**
	 * A 2d array to hold all the Piece objects in their positions on the chess board
	 */
	private Piece[][] board;
	
	private ArrayList<Position> positions;
	
	private Position pointer;
	
	private Position[] surrounding;
	
	private int pieceCount;
	/**
	 * A constructor for a Board object, it initialises the board array to be 8*8, the dimensions of a normal chess board
	 */
	public Board() {
		board = new Piece[8][8];
		positions = new ArrayList<Position>();
		pointer = new Position(0, 0);
		surrounding = new Position[8];
	}
	/**
	 * A method for setting up the pieces on the chess board
	 * Each Piece object is set to its defualt positions on a chess board within the array
	 */
	public void setUp() {
		board [0][0] = new Rook(0,0,true);
		board [0][1] = new Knight(0,1,true);
		board [0][2] = new Bishop(0,2,true);
		board [0][3] = new Queen(0,3,true);
		board [0][4] = new King(0,4,true);
		board [0][5] = new Bishop(0,5,true);
		board [0][6] = new Knight(0,6,true);
		board [0][7] = new Rook(0,7,true);
		board [1][0] = new Pawn(1,0,true);
		board [1][1] = new Pawn(1,1,true);
		board [1][2] = new Pawn(1,2,true);
		board [1][3] = new Pawn(1,3,true);
		board [1][4] = new Pawn(1,4,true);
		board [1][5] = new Pawn(1,5,true);
		board [1][6] = new Pawn(1,6,true);
		board [1][7] = new Pawn(1,7,true);
		
		board [7][0] = new Rook(7,0,false);
		board [7][1] = new Knight(7,1,false);
		board [7][2] = new Bishop(7,2,false);
		board [7][3] = new Queen(7,3,false);
		board [7][4] = new King(7,4,false);
		board [7][5] = new Bishop(7,5,false);
		board [7][6] = new Knight(7,6,false);
		board [7][7] = new Rook(7,7,false);
		board [6][0] = new Pawn(6,0,false);
		board [6][1] = new Pawn(6,1,false);
		board [6][2] = new Pawn(6,2,false);
		board [6][3] = new Pawn(6,3,false);
		board [6][4] = new Pawn(6,4,false);
		board [6][5] = new Pawn(6,5,false);
		board [6][6] = new Pawn(6,6,false);
		board [6][7] = new Pawn(6,7,false);
	}
	public Piece getSquare (Position position) {
		return board[position.getX()][position.getY()];
	}
	
	public void  promoteCheck() {
		for(int i =0; i<=7; i++) {
			if(board[0][i] instanceof Pawn && board[0][i] .getTeam() == false) {
				board[0][i] = null;
				board[0][i] = new Queen(0,i,false);
			}
			if(board[7][i] instanceof Pawn && board[7][i].getTeam()==true) {
				board[7][i] = null;
				board[7][i] = new Queen(7,i,true);
			}
		}
		
	}
	
	public void getPieceAtSquare(int x, int y,int oldX,int oldY){
		switch (x) {
			case 0:
				x = 7;
				break;
			case 1:
				x = 6;
				break;
			case 2:
				x = 5;
				break;
			case 3:
				x = 4;
				break;
			case 4:
				x = 3;
				break;
			case 5:
				x = 2;
				break;
			case 6:
				x = 1;
				break;
			case 7:
				x = 0;
				break;
				
		}
		
		switch (oldX) {
			case 0:
				oldX = 7;
				break;
			case 1:
				oldX = 6;
				break;
			case 2:
				oldX = 5;
				break;
			case 3:
				oldX = 4;
				break;
			case 4:
				oldX = 3;
				break;
			case 5:
				oldX = 2;
				break;
			case 6:
				oldX = 1;
				break;
			case 7:
				oldX = 0;
				break;
		}
		
		if(board[oldX][oldY] != null){
		System.out.println(board[oldX][oldY].printPieceType());
			
			if(board[oldX][oldY].getTeam() == turn) {
				if (board[oldX][oldY].move(x, y)) {
					if(checkMove(board[oldX][oldY], x, y) == true) {
					setPiece(board[oldX][oldY],new Position(x,y));
					turn = !turn;
					}
				}
			}
		} else System.out.println("This square is empty.");
	}
	
	
	private boolean checkRight(Piece piece,Position end) {
		Position pointer = piece.getPos();
		while(pointer.equals(end) == false) {
			pointer = new Position(pointer.getX(), pointer.getY() + 1);
			if (getSquare(pointer) != null) {
				if(getSquare(pointer).getTeam() != piece.getTeam() && pointer.equals(end)) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		return true;
	}
	
	
	private boolean checkLeft(Piece piece, Position end) {
		Position pointer = piece.getPos();
		while(pointer.equals(end) == false) {
			pointer = new Position(pointer.getX(),pointer.getY()-1);
			if (getSquare(pointer) != null) {
				if(getSquare(pointer).getTeam() != piece.getTeam() && pointer.equals(end)) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean checkUp(Piece piece, Position end) {
		Position pointer = piece.getPos();
		while(pointer.equals(end) == false) {
			pointer = new Position(pointer.getX() + 1,pointer.getY());
			if (getSquare(pointer) != null) {
				if(getSquare(pointer).getTeam() != piece.getTeam() && pointer.equals(end)) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean checkDown(Piece piece,Position end) {
		Position pointer = piece.getPos();
		while(pointer.equals(end) == false) {
			pointer = new Position(pointer.getX()-1,pointer.getY());
			if (getSquare(pointer) != null) {
				if(getSquare(pointer).getTeam() != piece.getTeam() && pointer.equals(end)) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean checkupRight(Piece piece, Position end) {
		Position pointer = piece.getPos();
		while(pointer.equals(end) == false) {
			pointer = new Position(pointer.getX()+1,pointer.getY()+1);
			if(getSquare(pointer)!= null) {
				if(getSquare(pointer).getTeam() != piece.getTeam() && pointer.equals(end)) {
					return true;
			}
				else {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean checkUpLeft(Piece piece, Position end) {
		Position pointer = piece.getPos();
		while(pointer.equals(end) == false) {
			pointer = new Position(pointer.getX()+1,pointer.getY()-1);
			if(getSquare(pointer)!= null) {
				if(getSquare(pointer).getTeam() != piece.getTeam() && pointer.equals(end)) {
					return true;
			}
				else {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean checkDownLeft (Piece piece, Position end) {
		Position pointer = piece.getPos();
		while (pointer.equals(end) == false) {
			pointer = new Position(pointer.getX() - 1, pointer.getY() - 1);
			if (getSquare(pointer)!= null) {
				if (getSquare(pointer).getTeam() != piece.getTeam() && pointer.equals(end)) return true;
				else return false;
			}
		}
		return true;
	}
	
	private boolean checkDownRight(Piece piece, Position end) {
		Position pointer = piece.getPos();
		while(pointer.equals(end) == false) {
			pointer = new Position(pointer.getX()-1,pointer.getY()+1);
			if(getSquare(pointer)!= null) {
				if(getSquare(pointer).getTeam() != piece.getTeam() && pointer.equals(end)) {
					return true;
			}
				else {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean check (Piece piece, Position end) {
		
		pointer = piece.getPos();
		
		while (pointer.equals(end) == false) {
			
		}
		return false;
	}
	/**
	 * Sets the new location to the piece
	 * Then sets the piece's original location to null
	 * then set the position to the new one
	 * @param piece
	 * @param end
	 */
	public void setPiece (Piece piece, Position end) {
		board[end.getX()][end.getY()] = piece;
		board[piece.getPos().getX()][piece.getPos().getY()] = null;
		piece.setPos(end);
	}
	
//	public void setPiece(Position originalPosition, Position newPosition) {
//		board[newPosition.getX()][newPosition.getY()] = board[originalPosition.getX()][originalPosition.getY()];
//		board[originalPosition.getX()][originalPosition.getY()] = null;
//		
//	}
	
	/**
	 * A method to move a piece to a new location. It takes a piece object and a new location and, 
	 * if the pieces move function returns true, will move the piece to its new location
	 * @param piece the Piece object to moved
	 * @param row the x coordinate of the new location
	 * @param col the y coordinate of the new location
	 */
	public boolean move (Piece piece, int row, int col)  {
		Position end = new Position(row,col);
		
		if (piece instanceof Knight) {
			if (getSquare(end) != null) {
				if (getSquare(end).getTeam() != piece.getTeam()) {
					setPiece(piece,end);
					return true;
				}
			}
			else {
				setPiece(piece,end);
				return true;
			}
			
		}
		else if(piece instanceof Pawn) {
			if (end.getY() == piece.getPos().getY()) {
				if (end.getX() > piece.getPos().getX()) {
					if(checkUp(piece,end)) {
						setPiece(piece,end);
						return true;

					}
				}
				else if (end.getX()<piece.getPos().getX()) {
					if (checkDown(piece,end)) {
						setPiece(piece,end);
						return true;
					}
				}
			}
			
			else if (end.getX()>piece.getPos().getX()) {
				if (end.getY()>piece.getPos().getY()) {
					if (checkupRight(piece,end) && ( (board [piece.getPos().getX()+1] [piece.getPos().getY()+1] != null && board [piece.getPos().getX()+1] [piece.getPos().getY()+1].getTeam() != piece.getTeam()))) {
						setPiece(piece,end);
						return true;
					}
				}
				else if (end.getY()<piece.getPos().getY()) {
					if(checkUpLeft(piece,end) && ( (board [piece.getPos().getX()+1] [piece.getPos().getY()-1] != null && board [piece.getPos().getX()+1] [piece.getPos().getY()-1].getTeam() != piece.getTeam()))) {
					{
						setPiece(piece,end);
						return true;

					}
				}
			}
			
		}
			else if (end.getX()< piece.getPos().getX()) {
				if (end.getY() > piece.getPos().getY()) {
					if (checkDownRight(piece,end) && ( (board [piece.getPos().getX()-1] [piece.getPos().getY()+1] != null && board [piece.getPos().getX()-1] [piece.getPos().getY()+1].getTeam() != piece.getTeam()))) {
						setPiece(piece,end);
						return true;

					}
				}
				else if (end.getY()<piece.getPos().getY()) {
					if(checkDownLeft(piece,end)&& ( (board [piece.getPos().getX()-1] [piece.getPos().getY()-1] != null && board [piece.getPos().getX()-1] [piece.getPos().getY()-1].getTeam() != piece.getTeam()))) {
						setPiece(piece,end);
						return true;

					}
				}
			}
		}
		
		else {
			if(end.getX() == piece.getPos().getX()) {
				if(end.getY() > piece.getPos().getY()) {
					if(checkRight(piece,end)) {
						setPiece(piece,end);
						return true;
					}
				}
				else {
					if(checkLeft(piece,end)) {
						setPiece(piece,end);
						return true;
					}
				}
			}
			else if (end.getY() == piece.getPos().getY()) {
				if (end.getX()>piece.getPos().getX()) {
					if(checkUp(piece,end)) {
						setPiece(piece,end);
						return true;

					}
				}
				else if (end.getX()<piece.getPos().getX()) {
					if (checkDown(piece,end)) {
						setPiece(piece,end);
						return true;
					}
				}
			}
			else if (end.getX()>piece.getPos().getX()) {
				if (end.getY()>piece.getPos().getY()) {
					if (checkupRight(piece,end)) {
						setPiece(piece,end);
						return true;
					}
				}
				else if (end.getY()<piece.getPos().getY()) {
					if(checkUpLeft(piece,end)) 
					{
						setPiece(piece,end);
						return true;

					}
				}
			}
			else if(end.getX() < piece.getPos().getX()) {
				if (end.getY() > piece.getPos().getY()) {
					if (checkDownRight(piece,end)) {
						setPiece(piece,end);
						return true;

					}
				}
				else if (end.getY() < piece.getPos().getY()) {
					if(checkDownLeft(piece,end)) {
						setPiece(piece,end);
						return true;

					}
				}
			}
		
		}
		return false;
		
	}

	
	/**
	 * A method to return a specic piece for a given location on the board
	 * @param row the x coordinate of the piece to be returned
	 * @param col the y coordinate of the piece to be returned
	 * @return the piece requested
	 */
	public Piece getSquare (int row, int col) {
		return board[row][col];
	}
	
	public Piece [][] getBoard () { return board; }
	
	public ArrayList<Position> allPositions () {
		positions.clear();
		for (int i = 0; i < getBoard().length; i++) {
			for (int n = 0; n < getBoard().length; n++) {
				if (getSquare(i, n) instanceof Piece) {
					positions.add(getBoard()[i][n].getPos());
				}
			}
		}
		return positions;
		
	}
	
	public int numberOfPieces () {
		for (int i = 0; i < board.length; i++) {
			for (int n = 0; n < board.length; n++) {
				if (board[i][n] != null) {
					pieceCount ++;
				}
			}
		}
		return pieceCount;
	}
	
	public int getNumberOfPieces () {
		return pieceCount;
	}
	
	private boolean checkMove(Piece piece,int x, int y) {
		Position pos = new Position(x,y);
		if (piece instanceof Knight) {
			if (getSquare(pos) != null) {
				if (getSquare(pos).getTeam() != piece.getTeam()) {
					return true;
				}
			}
			else {
				return true;
			}
	}
		else if(piece instanceof Pawn) {
			if (pos.getY() == piece.getPos().getY()) {
				if (pos.getX() > piece.getPos().getX()) {
					if(checkUp(piece,pos)) {
						return true;

					}
				}
				else if (pos.getX()<piece.getPos().getX()) {
					if (checkDown(piece,pos)) {
						return true;
					}
				}
			}
			
			else if (pos.getX()>piece.getPos().getX()) {
				if (pos.getY()>piece.getPos().getY()) {
					if (checkupRight(piece,pos) && ( (board [piece.getPos().getX()+1] [piece.getPos().getY()+1] != null && board [piece.getPos().getX()+1] [piece.getPos().getY()+1].getTeam() != piece.getTeam()))) {
						return true;
					}
				}
				else if (pos.getY()<piece.getPos().getY()) {
					if(checkUpLeft(piece,pos) && ( (board [piece.getPos().getX()+1] [piece.getPos().getY()-1] != null && board [piece.getPos().getX()+1] [piece.getPos().getY()-1].getTeam() != piece.getTeam()))) {
					{
						return true;

					}
				}
			}
			
		}
			else if (pos.getX()< piece.getPos().getX()) {
				if (pos.getY() > piece.getPos().getY()) {
					if (checkDownRight(piece,pos) && ( (board [piece.getPos().getX()-1] [piece.getPos().getY()+1] != null && board [piece.getPos().getX()-1] [piece.getPos().getY()+1].getTeam() != piece.getTeam()))) {
						return true;

					}
				}
				else if (pos.getY()<piece.getPos().getY()) {
					if(checkDownLeft(piece,pos)&& ( (board [piece.getPos().getX()-1] [piece.getPos().getY()-1] != null && board [piece.getPos().getX()-1] [piece.getPos().getY()-1].getTeam() != piece.getTeam()))) {
						return true;

					}
				}
			}
		}
		
		else {
			if(pos.getX() == piece.getPos().getX()) {
				if(pos.getY() > piece.getPos().getY()) {
					if(checkRight(piece,pos)) {
						return true;
					}
				}
				else {
					if(checkLeft(piece,pos)) {
						return true;
					}
				}
			}
			else if (pos.getY() == piece.getPos().getY()) {
				if (pos.getX()>piece.getPos().getX()) {
					if(checkUp(piece,pos)) {
						return true;

					}
				}
				else if (pos.getX()<piece.getPos().getX()) {
					if (checkDown(piece,pos)) {
						return true;
					}
				}
			}
			else if (pos.getX()>piece.getPos().getX()) {
				if (pos.getY()>piece.getPos().getY()) {
					if (checkupRight(piece,pos)) {
						return true;
					}
				}
				else if (pos.getY()<piece.getPos().getY()) {
					if(checkUpLeft(piece,pos)) 
					{
						return true;

					}
				}
			}
			else if(pos.getX() < piece.getPos().getX()) {
				if (pos.getY() > piece.getPos().getY()) {
					if (checkDownRight(piece,pos)) {
						return true;

					}
				}
				else if (pos.getY() < piece.getPos().getY()) {
					if(checkDownLeft(piece,pos)) {
						return true;

					}
				}
			}
		
		}
		return false;
		
	}
	
	
}