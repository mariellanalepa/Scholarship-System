package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Session;

public class AdminHeaderController implements Initializable {
	
	private Main main;
	private Session session;
	@FXML private Button signOut;
	@FXML private Label welcomeLabel;

	public AdminHeaderController(Main main, Session session)
	{
		this.main = main;
		this.session = session;
	}
	
	@FXML
	protected void handleSignOutButtonAction(ActionEvent event) throws Exception {	
		main.setScene("/view/Login.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Generate welcome message based on user's name
		welcomeLabel.setText(welcomeLabel.getText() + " " + session.getUser().getName());
		
	}

}
