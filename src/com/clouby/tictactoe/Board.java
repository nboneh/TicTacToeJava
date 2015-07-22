package com.clouby.tictactoe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;



public class Board {
	private Piece[][] board;
	private int width;
	private int height;
	
	private int grWidth;
	private int grHeight;
	private int xPos;
	private int yPos;
	
	

	public Board(){
		this(3,3);
	}

	public Board(int width, int height){
		board = new Piece[height][width];
		this.width = width;
		this.height = height;
		this.reset();
	}

	public void reset(){
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				board[i][j] = Piece.EMPTY;
			}
		}
	}
	
	public boolean insertPieceBasedOnCoordinate(int clickPosX, int clickPosY,Piece piece ){
		if(grWidth == 0)
			return false;
		float relX = clickPosX - xPos;
		float relY = clickPosY - yPos;
		int i =(int) ((relY/grHeight) * height);
		int j =(int) ((relX/grWidth) * width);
		return insertPiece(i, j,piece);
		
	}

	public boolean insertPiece( int i, int j, Piece piece){
		if(piece == Piece.EMPTY || 
				i >= height ||
				j >= width ||
				board[i][j] != Piece.EMPTY)
			return false;
		board[i][j] = piece;
		return true;
	}
	
	public boolean checkForTie(){
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				if(board[i][j]== Piece.EMPTY)
					return false;
			}
		}
		return true;
	}

	public  Piece checkForWin(){
		//Checking rows
		for(int i = 0; i <height; i++){
			Piece testPiece = Piece.EMPTY;
			for(int j = 0; j < width; j++){
				if(j==0){
					testPiece = board[i][j];
					if(testPiece == Piece.EMPTY)
						break;
				}
				else if(testPiece != board[i][j])
					break;

				else if(j == (width -1))
					return testPiece;

			}
		}

		//Checking columns
		for(int j = 0; j <width; j++){
			Piece testPiece = Piece.EMPTY;
			for(int i = 0; i < height; i++){
				if(i==0){
					testPiece = board[i][j];
					if(testPiece == Piece.EMPTY)
						break;
				}
				else if(testPiece != board[i][j])
					break;

				else if(i == (height -1))
					return testPiece;

			}
		}


		if(width != height){
			return Piece.EMPTY;
		}

		//Checking crosses
		Piece testPiece = Piece.EMPTY;
		for(int i =0; i < width; i++){
			if(i==0){
				testPiece = board[i][i];
				if(testPiece == Piece.EMPTY)
					break;
			}
			else if(testPiece != board[i][i])
				break;

			else if(i == (width -1))
				return testPiece;

		}

		testPiece = Piece.EMPTY;
		for(int i =0; i < width; i++){
			if(i==0)
				testPiece = board[i][width -i -1];
			else if(testPiece != board[i][width -i -1])
				break;

			else if(i == (width -1))
				return testPiece;

		}

		return Piece.EMPTY;
	}
	

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				if(j > 0)
					sb.append("|");
				sb.append(board[i][j].getChar());
			}
			sb.append("\n");
			if(i < (height -1)){
				for(int j = 0; j < width; j++){
					sb.append("--");
				}
			}

			sb.append("\n");
		}
		return sb.toString();

	}
	
	public void paint(Graphics g, int grWidth, int grHeight, int xPos, int yPos){
		g.setColor(Color.WHITE);
		
		this.grWidth = grWidth;
		this.grHeight = grHeight;
		this.xPos =xPos;
		this.yPos =yPos;
		//Drawing grid
		for(int i = 1; i < height; i++){
			g.fillRect(xPos, (int)(yPos + grHeight * (i/(height + 0.0))), grWidth, (int)(grHeight/60.0));
		}
		
		for(int j = 1; j < width; j++){
			g.fillRect((int)(xPos+ grWidth * (j/(width + 0.0))), yPos, (int)(grWidth/60.0), grHeight);
		}
		
		float ratio = .75f;
		int pieceWidth = (int) ((grWidth/width + 0.0)*ratio);
		int pieceHeight = (int) ((grHeight/height+ 0.0)*ratio);
		
		int xAdd = (int) (xPos + ((1-ratio)/2)*(grWidth/width + 0.0));
		int yAdd = (int) (yPos + ((1 -ratio)/2)*(grHeight/height+ 0.0)) ; 
		for(int i =0; i< height; i++){
			for(int j =0; j < width; j++){
				Image img = board[i][j].getImage();
		
				g.drawImage(img, (int)(xAdd + j * (grWidth/width + 0.0)), (int)(yAdd + grHeight * (i/(height + 0.0))), pieceWidth, pieceHeight, null);
			}
		}
		
		
		
	}
	

}
