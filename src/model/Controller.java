package model;

import java.util.ArrayList;

public class Controller {
	
	static final int WIDTHGAME = 750;  
	static final int HEIGHTGAME = 550;	
	static final int MOVEMENT = 8;
	static final int ENEMIES = 9; 
	static final int SCOREBYENEMY = 200;
	
	//Attributes
	private Player player;
	private ArrayList<Shot> shotsList;
	private Alien [] enemiesList;
	private boolean allEnemiesDead;
	private ArrayList<Player> registerPlayers;
 
	
	
	public Controller() {
		super();
		
		shotsList = new ArrayList<Shot>();
		registerPlayers = new ArrayList<Player>();
		
		int posX = (WIDTHGAME /2)-20;
		int posY = HEIGHTGAME-70;
		
		player = new Player(posX,posY);
		
		player.setDeltaX(MOVEMENT);
		
		enemiesList = new Alien[ENEMIES];
		
		setUpEnemies();
		
		allEnemiesDead = false;
		
	}
	
	
	
	public ArrayList<Player> getRegisterPlayers() {
		return registerPlayers;
	}



	public void setRegisterPlayers(ArrayList<Player> registerPlayers) {
		this.registerPlayers = registerPlayers;
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
	
	
	private boolean validateCoalitions(Shot s) {
		
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
						player.setScore(SCOREBYENEMY+player.getScore());
					}
				}
				
			}
		}
		
		return out;
	}

	
	public void updateShots() {
		for(int i = 0;i<shotsList.size();i++) {
			
			if(shotsList.get(i).getPosY()<0) {
				shotsList.remove(i);
			}
			else if(validateCoalitions(shotsList.get(i))) {
				shotsList.remove(i);
			}
			else {
				shotsList.get(i).move();
			}
			
		}
	}
	
	public void validatePlayerIsAlive() {
		
		for(int i = 0;i<enemiesList.length;i++) {

			if(enemiesList[i].getPosY()+(enemiesList[i].getHeight()/2)+10 >= player.getPoxY()) {
			
				if(enemiesList[i].getPosX() >= (player.getPosX()-(player.getWidth()/2))) {
					
					if(enemiesList[i].getPosX()<= (player.getPosX()+(player.getWidth()/2))) {
						
						allEnemiesDead = true;
						stopEnemies();
						player.setAlive(false);
						player.switchImage();
						
					}
				}
			}
			
		}
	}
	
	
	
	private void stopEnemies() {
		
		for(int i = 0;i<enemiesList.length;i++) {
			enemiesList[i].sleepAlien(false);
			
		}
	}
	
	//
	// === GETTERS AND SETTERS
	//
	
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
	
	public void setPlayerName(String name) {
		player.setName(name);
	}
	
	public Alien[] getEnemiesList() {
		
		return enemiesList;
	}

	public boolean isAllEnemiesDead() {
		allEnemiesDead = validateEnemiesAreDead();
		return allEnemiesDead;
	}
	
	private boolean validateEnemiesAreDead() {
		
		boolean out = true;
		boolean stop = false;
		for(int i = 0;i<enemiesList.length && !stop;i++) {
			if(enemiesList[i].getIsALive() == true) {
				out = false;
				stop = true;
			}
		}
		
		return out;
	}

	public void setAllEnemiesDead(boolean allEnemiesDead) {
		this.allEnemiesDead = allEnemiesDead;
	}
	
	private int calcuateScore(int seconds, Player player) {
		
		return player.getScore()-(seconds*10);
	}
	
	public void addWinPlayer(int seconds, Player player) {
		
		int finalScore = calcuateScore(seconds,player); 
		Player obj = new Player(player.getName(),finalScore);
		
		registerPlayers.add(obj);
		System.out.println(obj.toString());
	}
	
	

	
	
	

	
	
	
	
	
	
	
	
	
	
		

}
