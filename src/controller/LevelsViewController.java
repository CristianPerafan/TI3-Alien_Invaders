package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class LevelsViewController implements Initializable{
	
	//Attributes
	private Main main;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	void level_one(ActionEvent e) {
		main.toInitalizeController(1);
		main.showRegisterView();
	}
	
	@FXML
	void level_two(ActionEvent e) {
		main.toInitalizeController(2);
		main.showRegisterView();
	}
	
	@FXML
	void level_three(ActionEvent e) {
		main.toInitalizeController(3);
		main.showRegisterView();
	}
	
	public void setMain(Main main) {
		this.main = main;
	}

}
