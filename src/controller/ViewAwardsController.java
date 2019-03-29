package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.Session;

public class ViewAwardsController implements Initializable {
	
	private Main main;
	private Session session;
	@FXML protected Button signOut, saveAndExitButton, submitButton, mainMenuButton; 

	//CSS styling
	String HOVERING_SIGNOUT_STYLE = "-fx-background-color: #cf0722; -fx-opacity: 70%; -fx-underline: true;";
	String NORMAL_SIGNOUT_STYLE = "-fx-background-color: #cf0722; -fx-text-fill: white;";
		
	public ViewAwardsController(Main main, Session session) {
		this.main = main;
		this.session = session;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	
	@FXML 
	protected void handleMainMenuButtonAction(ActionEvent event) throws Exception{
		main.setScene("/view/StudentMain.fxml");
	}
	
	@FXML
	protected void handleSignOutButtonAction(ActionEvent event) throws Exception
	{
		main.setScene("/view/Login.fxml");
	}

}
