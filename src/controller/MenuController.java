package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MenuController implements Initializable {
	
	//Attributes
	MediaPlayer player;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		putSong();
	}
	
	private void putSong() {
		new Thread(()->{
		
			String relativePath = "/media/musica.mp3";
			Media song = new Media(getClass().getResource(relativePath).toExternalForm());
			
			player = new MediaPlayer(song);
			
			player.play();
			
		}).start();
		
	}
	
	private void stopSong() {
		player.stop();
	}

	
	private Main main;
	
	@FXML
    public void startGame(ActionEvent event) {
		stopSong();
		main.showRegisterView();
    }
	
	public void setMain(Main main) {
		this.main = main; 
	}
	
	@FXML
	public void lookScores(ActionEvent event) {
		main.showScoreView();
	}
	
	 @FXML
	 public void saveData(ActionEvent event) {
		main.toSerilize();
		main.closeProgram();
	 }

	
}
