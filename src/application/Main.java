package application;
	
import java.io.IOException;
import java.util.ArrayList;

import controller.GameViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Alien;
import model.Controller;
import model.Player;
import model.Shot;



public class Main extends Application {
	//Attributes
	@SuppressWarnings("unused")
	private Stage currentStage;
	private Controller gameController;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			gameController = new Controller();
			
			gameView();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void gameView() {
	
		try {
			
			FXMLLoader loader_GV = new FXMLLoader(getClass().getResource("../ui/GameView.fxml"));
			
			BorderPane root_GV = (BorderPane)loader_GV.load();
			
			
			
			GameViewController controller_OB = loader_GV.getController();
			
		
			controller_OB.setMain(this);
			
			
			Scene scene = new Scene(root_GV);
			Stage stage = new Stage();
			
			stage.setScene(scene);
			
			currentStage = stage;
			
			stage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	public Player getPlayerInformation() {
		
		return gameController.getPlayer();
		
	}
	
	public void setPlayer(int id) {
		gameController.updatePlayer(id);
	}
	
	public void addShot(int posX, int posY) {
		gameController.addShot(posX, posY);
	}
	
	public void updateShots() {
		gameController.updateShots();
	}
	
	public void startEnemies() {
		gameController.startEnemies();
	}
	


	
	
	public ArrayList<Shot> getShotList(){
		return gameController.getShotsList();
	}
	
	
	public Alien[] getEnemies() {
		return gameController.getEnemiesList();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void deleteAlien(int index) {
		gameController.deleteAlienArray(index);
	}
	
	public ArrayList<Alien> getDeletedAlien(){
		return gameController.getDeletedAlien();
	}
	
	
	


	
	
	
	
	
	


	
}
