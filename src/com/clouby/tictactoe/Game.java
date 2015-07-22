package com.clouby.tictactoe;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;


public class Game extends Applet implements MouseListener{     
	/**
	 * 
	 */
	private static final long serialVersionUID = -5502078364530436195L;
	private Board board;
	boolean gameOver;
	Piece currentPiece;

	public void init(){
		Frame c = (Frame)this.getParent().getParent();
		c.setTitle("Tic Tac Toe");
		board = new Board();
		addMouseListener( this );
		currentPiece = Piece.CROSS;
		gameOver = false; 
	}

	public void paint(Graphics g) {   
		setBackground(Color.black);
		
		int hyp = (int) Math.sqrt(this.getWidth() * this.getWidth() + this.getHeight() * this.getHeight());

		//Draw title
		int size = hyp/20;
		int yPush = hyp/20;
		g.setColor(Color.blue);    
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, size)); 
		String title = "Tic Tac Toe";
		FontMetrics fm = g2d.getFontMetrics();
		Rectangle2D r = fm.getStringBounds(title, g2d);
		int x = (this.getWidth() - (int) r.getWidth()) / 2;
		g2d.drawString(title, x,yPush);   


		int boardHeight = (int)(this.getHeight() * .75);
		board.paint(g2d, (int)(this.getWidth() *.75), boardHeight, (int)(this.getWidth() *.125), (int)(yPush *1.5));

		if(board.checkForTie()){
			currentPiece = Piece.EMPTY;
			gameOver = true; 
		}
		
		Piece checkPiece = board.checkForWin();
		if(checkPiece != Piece.EMPTY){
			currentPiece = checkPiece;
			gameOver = true;
		}
		


		String message; 
		 size = hyp/35;
		if(!gameOver)
			message = "It is " + currentPiece.getChar() + "'s turn";
		else {
			if(currentPiece != Piece.EMPTY)
				message = "The Winner is " + currentPiece.getChar() + "! Click to play again!";
			else 
				message = "It is a tie! Click to play again!";
		}

		int y = (int) (boardHeight + 2.5*yPush);
		g.setColor(currentPiece.getColor());    
		g2d.setFont(new Font("TimesRoman", Font.PLAIN,size)); 
		fm = g2d.getFontMetrics();
		r = fm.getStringBounds(message, g2d);
		x = (this.getWidth() - (int) r.getWidth()) / 2;
		g2d.drawString(message, x,y);   


	}

	@Override
	public void mouseClicked(MouseEvent eve) {
		if(!gameOver){
			if(board.insertPieceBasedOnCoordinate(eve.getPoint().x, eve.getPoint().y, currentPiece)){
				if(currentPiece == Piece.CROSS)
					currentPiece = Piece.CIRCLE;
				else 
					currentPiece = Piece.CROSS;
			}
		} else {
			currentPiece = Piece.CROSS;
			gameOver = false;
			board.reset();
		}

		this.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}
}