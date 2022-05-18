package application;
	
import java.io.IOException;

import controller.GameViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
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
	
	public void setShot(Player p) {
		gameController.setShot(p);
	}
	
	public boolean shottingMain() {
		
		return gameController.shotingGun();
	}
	
	public Shot getShot() {
		return gameController.getShot();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void setPlayer(int id) {
		gameController.updatePlayer(id);
	}
	
	


	
}
