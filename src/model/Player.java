package model;

import javafx.scene.image.Image;

public class Player {
	
	//Attributes
	private Image imgPlayer;
	private int posX;
	private int poxY;
	private int width;
	private int height;
	
	private int deltaX;
	
	public Player(int posX, int poxY) {
		super();
		
		imgPlayer = new Image("/media/nave.png");
		
		this.posX = posX;
		this.poxY = poxY;
		
		width = 50;
		height = 50;
		
	}
	
	//
	// === GETTERS AND SETTERS
	//
	
	public Image getImgPlayer() {
		return imgPlayer;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPoxY() {
		return poxY;
	}

	public void setPoxY(int poxY) {
		this.poxY = poxY;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	
	public int getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}

	public void moveRight() {
		
		posX += deltaX;
	}
	
	public void moveLeft() {
		posX -= deltaX;
	}
	
	
	

	@Override
	public String toString() {
		return "Player [posX=" + posX + ", poxY=" + poxY + "]";
	}
	
	
	
	
	
	
	
	

}
