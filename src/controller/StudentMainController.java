package controller;

import model.Session;
import java.net.URL;
import java.util.ResourceBundle;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * Controller class for the Student Main Page. Handles navigation between pages when menu buttons are pressed
 * using the injectPaneIntoScene method from main
 * @author Natalie, Mariella
 *
 */
public class StudentMainController implements Initializable {
	
	private Main main;
	@SuppressWarnings("unused") //left in for convenient furture expansion
	private Session session;
	@FXML private Button mainMenuButton, newApplicationButton, viewScholarshipButton, reviewApplicationButton, viewAwardsButton;
	
	public StudentMainController(Main main, Session session) {
		this.main = main;
		this.session = session;
	}
	
	@FXML 
	protected void handleMainMenuButtonAction(ActionEvent event) throws Exception {
		main.injectPaneIntoScene("/view/StudentWelcomeMessage.fxml");
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
		// empty method stub
	}

}
