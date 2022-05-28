package application;
	
import java.io.IOException;
import java.util.ArrayList;

import controller.GameViewController;
import controller.MenuController;
import controller.RegisterController;
import controller.ScoreController;
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
			showMenuView();
			//gameView();
			
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
	
	public void deleteAlien(int index) {
		gameController.deleteAlienArray(index);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void showMenuView() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/MenuView.fxml"));
			BorderPane root;
			root = (BorderPane)loader.load();
			MenuController controller = loader.getController();
			controller.setMain(this);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../ui/application.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			currentStage = stage;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showRegisterView() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/RegisterView.fxml"));
			BorderPane root;
			root = (BorderPane)loader.load();
			RegisterController controller = loader.getController();
			controller.setMain(this);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../ui/application.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			currentStage.close();
			stage.show();
			currentStage = stage;
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public void showScoreView() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/ScoreView.fxml"));
			BorderPane root;
			root = (BorderPane)loader.load();
			ScoreController controller = loader.getController();
			controller.setMain(this);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../ui/application.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			currentStage = stage;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setNamePlayer(String name) {
		gameController.setPlayerName(name);
	}

	
	
	
	
	
	


	
}
