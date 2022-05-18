package model;

import javafx.scene.image.Image;

public class Shot {
	
	//Attributes
	private int posX;
	private int posY;
	private int deltaY;
	
	private Image img;

	public Shot(int posX, int posY) {
		super();
		img = new Image("/media/shot.png");
		this.posX = posX;
		this.posY = posY;
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
	
	
	
	
	
	

}
