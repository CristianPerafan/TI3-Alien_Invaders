package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Alien;
import model.Player;
import model.Shot;


public class GameViewController implements Initializable {
	
	//Attributes
	private Main main;
	private Player tempPlayer;

	@FXML
	private Canvas gameCanvas;
	@FXML
	private Button startButton;
	
	private GraphicsContext gc;
	private GraphicsContext gcShot;
	private GraphicsContext gcAlien;
	
	private Media sound;
	
	
	@SuppressWarnings("unused")
	private boolean left;
	@SuppressWarnings("unused")
	private boolean right;
	@SuppressWarnings("unused")
	private boolean space;
	
	private volatile boolean stopAll = false;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		String relativePath = "/media/spaceGun.mp3";
		
		sound = new Media(getClass().getResource(relativePath).toExternalForm());

		gameCanvas.setFocusTraversable(true);
		
		gc = gameCanvas.getGraphicsContext2D();
		
		gcShot = gameCanvas.getGraphicsContext2D();
		
		gcAlien = gameCanvas.getGraphicsContext2D();
		
	}

	
	@FXML
	void startGame(ActionEvent e) {
		
		tempPlayer = main.getPlayerInformation();
		
		startButton.setDisable(true);
		setUpGame();
		
		shot();
		
		main.startEnemies();
		enemies();
		
		
	}
	

	
	@FXML
	void keyPressed(KeyEvent e) {
		
		if(e.getCode() == KeyCode.LEFT) {
		
			moveLeft();
		}
		else if(e.getCode() == KeyCode.RIGHT) {
	
			moveRight();
		}
		else if(e.getCode() == KeyCode.SPACE) {
	
			main.addShot(tempPlayer.getPosX(),tempPlayer.getPoxY());
			soundShot();


		}
	
		
	}
	
	
	
	private void moveRight() {
		new Thread(()->{
			Platform.runLater(() ->{
				
				
				
				tempPlayer = main.getPlayerInformation();
				
				clear();
				
				//Move left
				uptadePlayer(1);
				
				paint();

			});
		}).start();
	}
	
	private void moveLeft() {
	
		new Thread(()->{

			Platform.runLater(() ->{
				
				
		
				tempPlayer = main.getPlayerInformation();
				
				clear();
				
				//Move left
				uptadePlayer(2);
				
				paint();

			});


			
		}).start();;
		
	}
	
	private void uptadePlayer(int id) {
		main.setPlayer(id);
	}
	
	public void setUpGame() {
		
		Player p = main.getPlayerInformation();
		
		Image img = p.getImgPlayer();
		
		
		
		gc.drawImage(img, p.getPosX(), p.getPoxY(), p.getWidth(), p.getHeight());
	
		
	}
	

	
	private void paintShot(ArrayList<Shot> s){
		
		for(int i = 0;i<s.size();i++) {
			
			Shot aux = s.get(i);
			
			Platform.runLater(() ->{
				gcShot.drawImage(aux.getImg(), aux.getPosX(), aux.getPosY(), 
						aux.getWidth(), aux.getHeight());
			});
		}

		

	}
	
	private void paint() {
	
		Platform.runLater(()->{
			
		
			
			Player p = main.getPlayerInformation();
			
			Image img = p.getImgPlayer();
			
			
			
			gc.drawImage(img, p.getPosX(), p.getPoxY(), p.getWidth(), p.getHeight());
			
	
		});
	}
	
	private void clear() {

		gc.clearRect(tempPlayer.getPosX(), tempPlayer.getPoxY(), 
					tempPlayer.getWidth(),tempPlayer.getHeight());

	}
	
	private void clearShot(ArrayList<Shot> s) {
		
		for(int i = 0;i<s.size();i++) {
			
			Shot aux = s.get(i);
			
			gcShot.clearRect(aux.getPosX(),aux.getPosY(),aux.getWidth(),aux.getHeight());
			
		}
		
		
	}
	
	private void enemies() {
		new Thread(()-> {
			while(stopAll == false ) {

				
				Alien[] enemies = main.getEnemies();
				
				//System.out.println("index: " + enemies.length);
				
				paintEnemies(enemies);
				

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				clearEnemies(enemies);

			}
		}).start();;
	}
	
	private void clearEnemies(Alien[] e) {
		
		for (int i = 0; i < e.length; i++) {
			Alien temp = e[i];
	
			Platform.runLater(() ->{
				gcAlien.clearRect(temp.getPosX(), temp.getPosY()-temp.getHeight()+10, temp.getWidth(),temp.getHeight());
			});
		
		}
	}
	
	private void deleteEnemies(ArrayList<Alien> enemies) {
		new Thread(() ->{
			try {
				
				for(int i = 0; i<enemies.size();i++) {
					
					paintEnemies(enemies.get(i));
					Thread.sleep(1000);
					clearEnemies(enemies.get(i));
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}).start();
	}
	
	private void paintEnemies(Alien e) {
		
		Platform.runLater(() ->{
			gcAlien.drawImage(e.getImg(),e.getPosX(),e.getPosY(),e.getWidth(),
					e.getHeight());
		});
	}
	private void clearEnemies(Alien e) {
	
		Platform.runLater(() ->{
			gcAlien.clearRect(e.getPosX(), e.getPosY()-e.getHeight()+10, e.getWidth(),e.getHeight());
		});
		
		
	}
	private void paintEnemies(Alien[] e) {
		
		for (int i = 0; i < e.length; i++) {
			
			Alien temp = e[i];
			
			if(main.getDeletedAlien().size()>0) {
				//System.out.println("Hole " + main.getDeletedAlien().size());
				//main.deleteAlien(i);
				deleteEnemies(main.getDeletedAlien());
			}
			Platform.runLater(() ->{
				gcAlien.drawImage(temp.getImg(),temp.getPosX(),temp.getPosY(),temp.getWidth(),
						temp.getHeight());
			});
			
			
		}

	}


	private void shot() {
		new Thread(() ->{

				while(stopAll == false) {
					
					ArrayList<Shot> temp = main.getShotList();
					
					if(temp.size()>0) {
						
						paintShot(temp);
						
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						clearShot(temp);
						
						main.updateShots();
						
						
						
						temp = main.getShotList();
						
					}

				}

			
		}).start();
	}
	

	private void soundShot() {
		
		MediaPlayer player = new MediaPlayer(sound);
		
		player.play();
		
	
	}
	
	public void setMain(Main main) {
		this.main = main;
	}

}