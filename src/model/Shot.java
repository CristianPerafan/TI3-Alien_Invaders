package model;

import javafx.scene.image.Image;

public class Shot {
	
	static final int WIDTH = 20;  
	static final int HEIGHT = 20;	
	
	//Attributes
	private int posX;
	private int posY;
	private int deltaY;
	private int width;
	private int height;
	private Image img;

	public Shot(int posX, int posY) {
		super();
		img = new Image("/media/shot.png");
		this.posX = posX;
		this.posY = posY;
		width = WIDTH;
		height = HEIGHT;
		deltaY = 10;
	}
	
	

	public int getPosX() {
		return posX;
	}



	public void setPosX(int posX) {
		this.posX = posX;
	}



	public int getPosY() {
		return posY;
	}



	public void setPosY(int posY) {
		this.posY = posY;
	}



	public int getDeltaY() {
		return deltaY;
	}
	

	public void setDeltaY(int deltaY) {
		this.deltaY = deltaY;
	}
	
	
	
	public Image getImg() {
		return img;
	}


	public void move() {
		posY -= deltaY;
	}



	public int getWidth() {
		return width;
	}



	public void setWidth(int width) {
		this.width = width;
	}



	public int getHeight() {
		return height;
	}



	public void setHeight(int height) {
		this.height = height;
	}



	@Override
	public String toString() {
		return "Shot [posX=" + posX + ", posY=" + posY+"]";
	}
	
	




	
	
	
	
	
	
	
	

}
