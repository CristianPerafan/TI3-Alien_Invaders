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
    	System.out.println("Hola");
    }
    
    public void setMain(Main main) {
    	this.main = main; 
    }

}
