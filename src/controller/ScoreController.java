package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ScoreController {

    @FXML
    private TableView<?> tablePlayer;

    @FXML
    private TableColumn<?, ?> columnName;

    @FXML
    private TableColumn<?, ?> columnScore;

    
    private Main main; 
    @FXML
    public void returnLoginView(ActionEvent event) {

    }
    
    public void setMain(Main main) {
    	
    }

}
