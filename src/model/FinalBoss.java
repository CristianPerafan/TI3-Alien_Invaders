package model;

import javafx.scene.image.Image;

public class FinalBoss extends Thread {
	
	private volatile boolean stop = false;
	
	static final int WIDTH = 80;  
	static final int HEIGHT = 80;	
	
	//Attribute
	private Image img;
	private int posX;
	private int posY;
	private int width;
	private int height;
	private int deltaY;
	private boolean isAlive;
	
	
	
	public FinalBoss(int posX, int posY) {
		
		img = new Image("/media/boss.png");
		
		this.posX = posX;
		this.posY = posY;
		
		width = WIDTH;
		height = HEIGHT;
		
		deltaY = 10;
		
		isAlive = true;
		
	}
	
	@Override
	public void run() {
		while(stop == false) {
			posY += deltaY;
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	
	//
	// GETTERS AND SETTERS
	//
	

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
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

	public int getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(int deltaY) {
		this.deltaY = deltaY;
	}

	public boolean getIsAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	

	
	
	
}
