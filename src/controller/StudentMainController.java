package controller;

import model.Offer;
import model.Session;
import java.net.URL;
import java.util.ArrayList;
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
	@FXML private Button mainMenuButton, newApplicationButton, viewScholarshipButton, reviewApplicationButton, viewAwardsButton;
	
	public StudentMainController(Main main, Session session) {
		this.main = main;
		this.session = session;
	}
	
	@FXML 
	protected void handleMainMenuButtonAction(ActionEvent event) throws Exception {
		//Don't have anything to display in main right now
		main.injectPaneIntoScene("/view/StudentWelcomeMessage.fxml");
		//main.injectPaneIntoScene("/view/StudentAwardsMessage.fxml");
	}
	
	@FXML
	protected void handleNewApplicationButtonAction(ActionEvent event) throws Exception {	
		// redirects to scholarship view to choose a new scholarship
		main.injectPaneIntoScene("/view/StudentScholarship.fxml");
	}
	
	@FXML
	protected void handleViewScholarshipButtonAction(ActionEvent event) throws Exception {
		main.injectPaneIntoScene("/view/StudentScholarship.fxml");
	}
	
	@FXML
	protected void handleReviewApplicationsButtonAction(ActionEvent event) throws Exception {
		main.injectPaneIntoScene("/view/ApplicationHistory.fxml");
	}
	
	@FXML
	protected void handleViewAwardsButtonAction(ActionEvent event) throws Exception {
		main.injectPaneIntoScene("/view/Awards.fxml");
	}
	
	@FXML
	protected void handleAwardHistoryButtonAction(ActionEvent event) throws Exception {
		main.injectPaneIntoScene("/view/AwardHistory.fxml");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
