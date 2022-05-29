package controller;

import java.net.URL;
import java.time.Duration;
import java.time.LocalTime;
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
	
	private LocalTime first;
	private LocalTime last; 
	
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
		
		first = LocalTime.now();
		
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
			
			clear(1);
			
			tempPlayer = main.getPlayerInformation();
			
			//Move right
			uptadePlayer(1);
				
			paintPlayer();
			

			
		}).start();
		
	}
	
	private void moveLeft() {
	
		new Thread(()->{

			clear(2);
				
			tempPlayer = main.getPlayerInformation();
			
			//Move left
			uptadePlayer(2);
				
			paintPlayer();

			
		}).start();
		
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
	
	private void paintPlayer() {
	
		Platform.runLater(()->{
			
			Player p = main.getPlayerInformation();
			
			Image img = p.getImgPlayer();
			
			gc.drawImage(img, p.getPosX(), p.getPoxY(), p.getWidth(), p.getHeight());
			
		});
	}
	
	private void clear(int id) {
		
		//id = 1 -> right
		//id = 2 -> left
	
		
		Platform.runLater(() ->{
			
			int delX = tempPlayer.getDeltaX();
		
			if(id == 2) {
				gc.clearRect(tempPlayer.getPosX()+(delX), tempPlayer.getPoxY(), 
						tempPlayer.getWidth(),tempPlayer.getHeight());
			}
			else {
				gc.clearRect(tempPlayer.getPosX()-(delX), tempPlayer.getPoxY(), 
						tempPlayer.getWidth(),tempPlayer.getHeight());
			}
			
		});
		

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
				
				
				
				validatePlayerIsAlive();
				
					
				if(main.validaEnemiesAreDead() == false) {
					
					paintEnemies(enemies);
					

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
					clearEnemies(enemies);
				}
				else {
					
					if(tempPlayer.isAlive()==true) {
						
						last = LocalTime.now();
						
						Duration period = Duration.between(first,last);
						int seconds = (int) period.getSeconds();
						
						main.addWinPlayer(tempPlayer, seconds);
						
						//main.showMenuView();
					}
					System.out.println("Stop");
					stopAll = true;
					clearEnemies(enemies);
				}
			
			
			}
		}).start();
		
		if(stopAll==true) {
			main.showMenuView();
		}
		
	}
	
	private void validatePlayerIsAlive() {
		main.validatePlayerIsAlive();
	}
	
	
	
	private void clearEnemies(Alien[] e) {
		
		for (int i = 0; i < e.length; i++) {
			Alien temp = e[i];
	
			Platform.runLater(() ->{
				gcAlien.clearRect(temp.getPosX(), temp.getPosY()-temp.getDeltaY(), 
						temp.getWidth(),temp.getHeight());
			});
		
		}
	}
	
	private void showBurst(Alien e) {
		new Thread(() ->{
			
			Platform.runLater(() ->{
				gcAlien.drawImage(e.getImg(),e.getPosX(),e.getPosY(),e.getWidth(),
						e.getHeight());
			});
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Platform.runLater(() ->{
				gcAlien.clearRect(e.getPosX(),e.getPosY(),e.getWidth(),
						e.getHeight());
			});
			
			
		
		}).start();
		
	}
	

	private void paintEnemies(Alien[] e) {
		
		for (int i = 0; i < e.length; i++) {
			
			Alien temp = e[i];
			
			if(temp.getIsALive() == false) {
				showBurst(temp);
				main.deleteAlien(i);
			}
			else {
				Platform.runLater(() ->{
					gcAlien.drawImage(temp.getImg(),temp.getPosX(),temp.getPosY(),temp.getWidth(),
							temp.getHeight());
				});
			}
		
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
	
	public void calculateScore() {
		
		
		
		
	}

}