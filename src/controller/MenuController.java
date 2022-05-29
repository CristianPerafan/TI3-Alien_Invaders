package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController {
	
	
	private Main main;
	
	@FXML
    public void startGame(ActionEvent event) {
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
