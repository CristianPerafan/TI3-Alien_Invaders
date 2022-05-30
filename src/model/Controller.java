package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Controller {
	
	static final int WIDTHGAME = 750;  
	static final int HEIGHTGAME = 550;	
	static final int MOVEMENT = 8;
	static final int SCOREBYENEMY = 200;
	static final int ALIEN_MOVEMENT_L1 = 5;
	static final int ALIEN_MOVEMENT_L2 = 8;
	static final int ALIEN_MOVEMENT_L3 = 10;
	
	//Attributes
	private int timeSecondsShotEnemies;
	private int numEnemies;
	private int numLevel;
	private boolean allEnemiesDead;
	private Player player;
	private ArrayList<Shot> shotsList;
	private ArrayList<Shot> shotsEnemiesList;
	private Alien [] enemiesList;
	private ArrayList<Player> registerPlayers;
	private File file; 
	
	
	public Controller(int level) {
		super();
		
		shotsList = new ArrayList<Shot>();
		shotsEnemiesList = new ArrayList<Shot>();
		
		registerPlayers = new ArrayList<Player>();
		file = new File(".\\files\\playersData.txt");
		int posX = (WIDTHGAME /2)-20;
		int posY = HEIGHTGAME-70;
		
		player = new Player(posX,posY);
		
		player.setDeltaX(MOVEMENT);
		
		if(level != 0) {
			setUpGameForLevel(level);
		}
		
		allEnemiesDead = false;
		

	}
	
	private void setUpGameForLevel(int level) {
		if(level == 1) {
			
			numLevel = 1;
			
			numEnemies = 3;

			enemiesList = new Alien[numEnemies];
			
			setUpEnemies(level);
			
		}
		else if(level == 2) {
			
			numLevel = 2;
			
			numEnemies = 9;
			
			enemiesList = new Alien[numEnemies];
			
			setUpEnemies(level);
			
		}
		else if(level == 3) {
			
			numLevel = 3;
			
			numEnemies = 9;
			
			enemiesList = new Alien[numEnemies];
			
			setUpEnemies(level);
		}
	}
	
	public ArrayList<Player> getRegisterPlayers() {
		return registerPlayers;
	}



	public void setRegisterPlayers(ArrayList<Player> registerPlayers) {
		this.registerPlayers = registerPlayers;
	}



	private void setUpEnemies(int level) {
		if(level == 1) {
			
			
			int posX = 100;
			int posY = 70;
			for(int i = 0;i<numEnemies;i++) {
				enemiesList[i] = new Alien(posX,posY,ALIEN_MOVEMENT_L1);
				posX += 260;
			}
			
		}
		else if(level == 2) {
			
			int posX = 30;
			int posY = 70;
			for(int i = 0;i<numEnemies;i++) {
				enemiesList[i] = new Alien(posX,posY,ALIEN_MOVEMENT_L2);
				posX += 80;
			}
			
		}
		else if(level == 3) {
			
			int posX = 30;
			int posY = 70;
			for(int i = 0;i<numEnemies;i++) {
				enemiesList[i] = new Alien(posX,posY,ALIEN_MOVEMENT_L3);
				posX += 80;
			}
			
		}

	}
	
	public void startEnemies() {
		for(int i = 0;i<numEnemies;i++) {
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

	public void addShotPlayer(int posX, int posY, int id){
		
		Shot temp = new Shot(posX+15,posY-15,id);
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
				shotsList.get(i).moveUp();
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
		
	}
	
	public void uptadeEnemyShot() {
		for(int i = 0;i<shotsEnemiesList.size();i++) {
			if(shotsEnemiesList.get(i).getPosY()>HEIGHTGAME) {
				shotsEnemiesList.remove(i);
			}
			else if( validateShotKillPlayer(shotsEnemiesList.get(i)) == true) {
				allEnemiesDead = true;
				stopEnemies();
				player.setAlive(false);
				player.switchImage();
			}
			else {
				shotsEnemiesList.get(i).moveDown();
			}
		}
	}
	
	private boolean validateShotKillPlayer(Shot s) {
		boolean out = false;
		
		if(s.getPosX() >= player.getPosX()-(player.getWidth()/2)) {
			if(s.getPosX() <= player.getPosX()+(player.getWidth()/2)) {
				if(s.getPosY() >= player.getPoxY()) {
					out = true;
				}
				
			}
		}
		
		return out;
	}
	
	public void addEnemyShot() {
		
		int index = randomNumber();
	
		 
		if(index < enemiesList.length) {
			
			
			int posX = enemiesList[index].getPosX();
			int posY = enemiesList[index].getPosY();
			
			
			Shot s = new Shot(posX,posY+10,2);
			
			shotsEnemiesList.add(s);
		}
			
	}
	
	
	
	private int randomNumber() {
		return (int) (Math.random()*(enemiesList.length));
	}
	
	public void serialize() throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(getRegisterPlayers());
		
		oos.close();
		fos.close();	
	}
	
	public void deserialize() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		@SuppressWarnings("unchecked")
		ArrayList<Player> obj = (ArrayList<Player>)ois.readObject();
		 registerPlayers.addAll(obj);
		ois.close();
		fis.close();
	}
	
	public boolean validatePlayersFile() {
		
		if(file.length()==0) {
			return false; 
		}else {
			return true; 
		}
	}
	
	public void sortScorePlayers() {
		
		Collections.sort(registerPlayers,
				
				new Comparator<Player>() {

					@Override
					public int compare(Player c1, Player c2) {
						
						int result = c1.getScore()-c2.getScore(); 
						
						result*=-1;
						return result;
					}
			
		});
	}
	
	//
	// === GETTERS AND SETTERS
	//
	
	public int getNumLevel() {
		return numLevel;
	}

	public void setNumLevel(int numLevel) {
		this.numLevel = numLevel;
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

	public int getTimeSecondsShotEnemies() {
		return timeSecondsShotEnemies;
	}

	public void setTimeSecondsShotEnemies(int timeSecondsShotEnemies) {
		this.timeSecondsShotEnemies = timeSecondsShotEnemies;
	}

	public ArrayList<Shot> getShotsEnemiesList() {
		return shotsEnemiesList;
	}

	public int getNumEnemies() {
		return numEnemies;
	}

	public void setNumEnemies(int numEnemies) {
		this.numEnemies = numEnemies;
	}
	
}
