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
 * Controller class for the Admin Main Page. Handles navigation between pages when menu buttons are pressed
 * using the injectPaneIntoScene method from main
 * @author Natalie, Mariella
 *
 */
public class AdminMainController implements Initializable {
	private Main main;
	@SuppressWarnings("unused") //left in for convenient furture expansion
	private Session session;
	@FXML private Button mainMenuButton, viewScholarshipsButton, createScholarship, deleteButton, editScholarship, viewApplicationsButton, viewRecipientsButton;
	@FXML private Label lblInfo;

	public AdminMainController(Main main, Session session)
	{
		this.main = main;
		this.session = session;
	}
	
	@FXML 
	protected void handleMainMenuButtonAction(ActionEvent event) throws Exception {	
		main.injectPaneIntoScene("/view/AdminWelcomeText.fxml");
	}
	
	@FXML
	protected void handleScholarshipButtonAction  (ActionEvent event) throws Exception {
		main.injectPaneIntoScene("/view/AdminScholarship.fxml");
	}
	
	@FXML
	protected void handleCreateScholarshipButtonAction(ActionEvent event) throws Exception {
		main.injectPaneIntoScene("/view/AddScholarship.fxml");
	}

	@FXML
	protected void handleDeleteButtonAction(ActionEvent event) throws Exception {
		main.injectPaneIntoScene("/view/DeleteScholarship.fxml");
	}
	
	@FXML
	protected void handleEditScholarshipAction(ActionEvent event) throws Exception {
		main.injectPaneIntoScene("/view/EditScholarship.fxml");
	}
	
	@FXML
	protected void handleViewRecipientsButtonAction(ActionEvent event) throws Exception {
		main.injectPaneIntoScene("/view/AdminRecipient.fxml");
	}

	@FXML
	protected void handleViewApplicationsAction(ActionEvent event) throws Exception {
		main.injectPaneIntoScene("/view/AdminViewApplications.fxml");
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//empty method stub
	}
}
