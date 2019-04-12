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
/**
 * Controller class for the Header pane used throughout the project.
 * Customized welcome message with the user's name.
 * Handles the redirection when the sign out button is pushed.
 *
 */
public class HeaderController implements Initializable {
	private Main main;
	private Session session;
	@FXML private Button signOut;
	@FXML private Label welcomeLabel;
	
	//CSS styling
	String HOVERING_SIGNOUT_STYLE = "-fx-background-color: #cf0722; -fx-opacity: 70%; -fx-underline: true;";
	String NORMAL_SIGNOUT_STYLE = "-fx-background-color: #cf0722; -fx-text-fill: white;";

	public HeaderController(Main main, Session session)
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
		
		//event styling - uses lambda expressions
		signOut.setOnMouseEntered(e -> signOut.setStyle(HOVERING_SIGNOUT_STYLE));
		signOut.setOnMouseExited(e -> signOut.setStyle(NORMAL_SIGNOUT_STYLE));
		
	}

}
