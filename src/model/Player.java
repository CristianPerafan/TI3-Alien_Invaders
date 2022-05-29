package model;

import java.io.Serializable;

import javafx.scene.image.Image;

public class Player implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Attributes
	private Image imgPlayer;
	private int posX;
	private int poxY;
	private int width;
	private int height;
	private int deltaX;
	private String name; 
	private int score; 
	private boolean isAlive;
	
	
	public Player(int posX, int poxY) {
		super();
		
		imgPlayer = new Image("/media/s.png");
		
		this.posX = posX;
		this.poxY = poxY;
		
		width = 50;
		height = 50;
		
		score = 0;
		isAlive = true;
	}
	
	public Player(String name,int score) {
		this.name = name;
		this.score = score; 
	}
	
	public void switchImage() {
		imgPlayer = new Image("/media/fire.png");
	}
	
	//
	// === GETTERS AND SETTERS
	//
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	@Override
	public String toString() {
		return "Player [Name " + name + ", score" + score + "]";
	}
	
	
	
	
	
	
	
	

}
