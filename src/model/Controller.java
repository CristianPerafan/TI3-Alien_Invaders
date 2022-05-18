package model;

public class Controller {
	
	private static final int WIDTHGAME = 750;  
	private static final int HEIGHTGAME = 550;	
	private static final int MOVEMENT = 2;

	private Player player;
	private Shot shot;
	
	
	public Controller() {
		super();
		

		
		int posX = (WIDTHGAME /2)-20;
		int posY = HEIGHTGAME-70;
		
		player = new Player(posX,posY);
		
		player.setDeltaX(MOVEMENT);
		
		shot = new Shot(posX,posY);
		
		shot.setDeltaY(MOVEMENT);

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

	public boolean shotingGun() {
	
		
		if(shot.getPosY()<0) {
			return false;
		}
		else {
			shot.move();
			return true;
		}
		
		
	}

	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Shot getShot() {
		return shot;
	}
	
	public void setShot(Player p) {
		shot.setPosX(p.getPosX());
		shot.setPosY(p.getPoxY());
	}
	
	
	
	
	
	
		

}
