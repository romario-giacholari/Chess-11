package com.mygdx.game.models;

import com.mygdx.game.rules.*;

public class chessGame {
	
	Board board;
	
	public chessGame (boolean pawns, boolean queens, boolean knights, boolean rooks, boolean bishops) {
		board = new Board( pawns,  queens,  knights,  rooks,  bishops);
		board.setUp();
	}
	
	public  void boardState(Board board) {
		
		for (Piece[] x : board.getBoard()) {
			String out ="";
				for (int i=0;i<8;i++) {
					if(x[i] == null) {
						out = out + "-";
					}
					else {
						out = out + x[i].toString();
					}
				}
				System.out.println(out);
			}
		System.out.println("_____________________");
		}
	
	public  void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Board getBoard() {
		return board;
	}

}