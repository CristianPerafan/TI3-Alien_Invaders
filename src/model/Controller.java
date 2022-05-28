package model;

import java.util.ArrayList;

public class Controller {
	
	static final int WIDTHGAME = 750;  
	static final int HEIGHTGAME = 550;	
	static final int MOVEMENT = 8;
	static final int ENEMIES = 9; 

	
	//Attributes
	private Player player;
	private ArrayList<Shot> shotsList;
	
	private Alien [] enemiesList;
	
	
	public Controller() {
		super();
		
		shotsList = new ArrayList<Shot>();
		
		int posX = (WIDTHGAME /2)-20;
		int posY = HEIGHTGAME-70;
		
		player = new Player(posX,posY);
		
		player.setDeltaX(MOVEMENT);
		
		enemiesList = new Alien[ENEMIES];
		
		setUpEnemies();
		
	}
	
	
	
	private void setUpEnemies() {
		int posX = 30;
		int posY = 70;
		for(int i = 0;i<ENEMIES;i++) {
			enemiesList[i] = new Alien(posX,posY);
			posX += 80;
		}
	}
	
	public void startEnemies() {
		for(int i = 0;i<ENEMIES;i++) {
			enemiesList[i].start();
		}
	}
	
	
	public Alien[] getEnemiesList() {
		
		return enemiesList;
	}
	
	public void deleteAlienArray(int index) {
		
		Alien[] aux = new Alien[enemiesList.length-1]; 
		
		int j = 0; 
		for(int i = 0; i<enemiesList.length;i++) {
			if(i!=index) {
				aux[j] = enemiesList[i];
				j++;
			}
		}
		
		enemiesList = new Alien[aux.length];
		enemiesList = aux; 
	}

	public void addShot(int posX, int posY){
		
		Shot temp = new Shot(posX+15,posY-15);
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
			
			if(shotsList.get(i).getPosY()<0) {
				shotsList.remove(i);
			}
			else if(validate(shotsList.get(i))) {
				shotsList.remove(i);
			}
			else {
				shotsList.get(i).move();
			}
			
		}
	}
	
	private boolean validate(Shot s) {
		
		boolean out = false;
		
		boolean stop = false;
		
		for(int i = 0;i<enemiesList.length && !stop;i++) {
			
			if(s.getPosY() <= enemiesList[i].getPosY()+(enemiesList[i].getHeight()/2)) {
				if(s.getPosX()>=enemiesList[i].getPosX()-(enemiesList[i].getWidth()/2)) {
					//!
					if(s.getPosX()<=enemiesList[i].getPosX()+(enemiesList[i].getWidth())) {
						out = true;
						stop = true;
						enemiesList[i].switchImage();  
						enemiesList[i].sleepAlien(false);
					}
				}
				
			}
		}
		
		return out;
	}


	
	
	

	
	
	
	
	
	
	
	
	
	
		

}
