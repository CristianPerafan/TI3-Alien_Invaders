package controller;

import java.net.URL;
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
import javafx.scene.paint.Color;
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
	
	private Media sound;
	
	private Image img3 = new Image("media/shot.png");
	
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		String relativePath = "/media/spaceGun.mp3";
		
		sound = new Media(getClass().getResource(relativePath).toExternalForm());

		gameCanvas.setFocusTraversable(true);
		
		gc = gameCanvas.getGraphicsContext2D();
		
		
		
	}

	public void setUpGame() {
		
		Player p = main.getPlayerInformation();
		
		Image img = p.getImgPlayer();
		
		
		
		gc.drawImage(img, p.getPosX(), p.getPoxY(), p.getWidth(), p.getHeight());
		
		gc.drawImage(img3, 200, 200,35,35);
		
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
	@FXML
	void startGame(ActionEvent e) {
		
		tempPlayer = main.getPlayerInformation();
		
		startButton.setDisable(true);
		setUpGame();
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
			soundShot();
			shot();
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
		}).start();;
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
	
	private void shot() {
		new Thread(() ->{
			
			boolean stop = true;
			
			main.setShot(tempPlayer);
			
			while(stop == true) {
				
				try {
					Thread.sleep(1000/100);
					
					stop = main.shottingMain();
					painShot();
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
				
				
			
			}
			
			
		}).start();
	}
	
	private void painShot(){
		
		Platform.runLater(() ->{
			
			
			Shot s = main.getShot();
			
			Image img = s.getImg();
			
			
			
			gc.drawImage(img,s.getPosX(),s.getPosY(),25,25);
			

		});
		
		
		
	}
	
	
	

	private void soundShot() {
		
		
		new Thread(() ->{
			
			MediaPlayer player = new MediaPlayer(sound);
			
			player.play();
			

		}).start();;
	}
	
	public void setMain(Main main) {
		this.main = main;
	}

}
