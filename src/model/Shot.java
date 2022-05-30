package model;

import enumerations.TypeOfShot;
import javafx.scene.image.Image;

public class Shot {
	
	
	
	static final int WIDTH = 15;  
	static final int HEIGHT = 15;	
	
	//Attributes
	private int posX;
	private int posY;
	private int deltaY;
	private int width;
	private int height;
	private Image img;
	@SuppressWarnings("unused")
	private TypeOfShot type;

	public Shot(int posX, int posY, int id) {
		super();
		
		this.posX = posX;
		this.posY = posY;
		width = WIDTH;
		height = HEIGHT;
		deltaY = 10;
		
		selectType(id);
		selectImage(id);
		
	}
	
	private void selectImage(int id) {
		if(id == 1) {
			img = new Image("/media/shot.png");
		}
		else {
			img = new Image("/media/evilProject.png");
		}
	}
	
	private void selectType(int id) {
		if(id == 1) {
			type = TypeOfShot.PLAYER;
		}
		else {
			type = TypeOfShot.ALIEN;
		}
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


	public void moveUp() {
		posY -= deltaY;
	}
	
	public void moveDown() {
		posY += deltaY;
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
