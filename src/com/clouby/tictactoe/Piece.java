package com.clouby.tictactoe;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum Piece {
	CROSS('X',"x.png",Color.BLUE),
	CIRCLE('O',"o.png", Color.RED),
	EMPTY(' ',null,Color.white);
	
	private char c;
	private Image img;
	private Color color;
	Piece(char c,String imageName, Color color){
		this.c = c;
		this.color = color;
		if(imageName == null)
			return;
		 File imgFile = new File("assets/" + imageName);
		 try {
			img = ImageIO.read(imgFile );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	char getChar(){
		return c;
	}
	Image getImage(){
		return img;
	}
	Color getColor(){
		return color;
	}

}
