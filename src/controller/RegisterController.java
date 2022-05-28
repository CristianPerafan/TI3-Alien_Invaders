package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private TextField namePlayer;
    
    private Main main; 

    @FXML
    public void startTurn(ActionEvent event) {
    	
    	String name = namePlayer.getText();
    	
    	if(!name.equals("")) {
    		main.setNamePlayer(name);
    		main.gameView();
    	}
    	
    }
    
    public void setMain(Main main) {
    	this.main = main; 
    }

 
}
