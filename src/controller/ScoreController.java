package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Player;

public class ScoreController implements Initializable{

    @FXML
    private TableView<Player> tablePlayer;

    @FXML
    private TableColumn<Player,String> columnName;

    @FXML
    private TableColumn<Player,Integer> columnScore;

    @FXML
    private ObservableList<Player> observablePlayers;
    
    private Main main; 
    
   
    
    
    @FXML
    public void returnLoginView(ActionEvent event) {
    	main.showMenuView();
    }
    
    public void setMain(Main main) {
    	this.main = main;
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		columnScore.setCellValueFactory(new PropertyValueFactory<>("score"));
		observablePlayers = FXCollections.observableArrayList();
	}
	
	
	
	 @FXML
	  void refreshData(ActionEvent event) {
		 main.sortListPlayers();
		 observablePlayers.addAll(main.listRegisterPlayer());
		 tablePlayer.setItems(observablePlayers);

	  }
	 
	
}
