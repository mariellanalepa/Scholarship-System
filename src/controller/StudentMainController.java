package controller;

import model.Session;
import java.net.URL;
import java.util.ResourceBundle;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StudentMainController implements Initializable {
	
	private Main main;
	private Session session;
	@FXML private Button newApplicationButton, signOut, viewScholarshipButton, reviewApplicationButton;
	@FXML private Label welcomeLabel;
	
	public StudentMainController(Main main, Session session) {
		this.main = main;
		this.session = session;
	}
	
	@FXML
	protected void handleNewApplicationButtonAction(ActionEvent event) throws Exception {	 
		main.setScene("/view/ApplicationForm.fxml");
	}
	
	@FXML
	protected void handleSignOutButtonAction(ActionEvent event) throws Exception {
		main.setScene("/view/Login.fxml");
	}
	
	@FXML
	protected void handleViewScholarshipButtonAction(ActionEvent event) throws Exception {
		main.setScene("/view/StudentScholarship.fxml");
	}
	
	@FXML
	protected void handleReviewApplicationsButtonAction(ActionEvent event) throws Exception {
		main.setScene("/view/ApplicationHistory.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		welcomeLabel.setText(welcomeLabel.getText() + " " + session.getUser().getName());
	}

}
