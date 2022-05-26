package model;

import java.util.ArrayList;

public class Controller {
	
	private static final int WIDTHGAME = 750;  
	private static final int HEIGHTGAME = 550;	
	private static final int MOVEMENT = 5;

	private Player player;
	private ArrayList<Shot> shotsList;
	
	
	public Controller() {
		super();
		
		shotsList = new ArrayList<Shot>();

		
		int posX = (WIDTHGAME /2)-20;
		int posY = HEIGHTGAME-70;
		
		player = new Player(posX,posY);
		
		player.setDeltaX(MOVEMENT);
		
	
		
	}
	
	public void addShot(int posX, int posY){
		
		Shot temp = new Shot(posX,posY-15);
		shotsList.add(temp);
	}	
	//
	// === GETTERS AND SETTERS
	//
	
	public void updatePlayer(int id ) {
		// id = 1 moveRight
		// id = 2 moveLeft
		
		if(id == 1) {
			
			if(!(player.getPosX()+player.getWidth()>WIDTHGAME )) {
				player.moveRight();
			}
			
		}
		else if(id == 2) {
			if(!(player.getPosX()<0 )) {
				player.moveLeft();
			}
		}
	}

	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Shot> getShotsList() {
		return shotsList;
	}

	public void setShotsList(ArrayList<Shot> shotsList) {
		this.shotsList = shotsList;
	}
	
	public int getShotsListSize() {
		return shotsList.size();
	}
	
	public void updateShots() {
		for(int i = 0;i<shotsList.size();i++) {
			if(shotsList.get(i).getPosY()==0) {
				shotsList.remove(i);
				
			}
			else {
				shotsList.get(i).move();
			}
			
		}
	}
	

	
	
	
	
	
	
	
	
	
	
		

}
