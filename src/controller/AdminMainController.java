package controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Database;
import model.Offer;
import model.Scholarship;
import model.Session;
import model.Student;

public class AdminMainController implements Initializable {

	private Main main;
	private Session session;
	@FXML private Button signOut, viewScholarshipsButton, createScholarship, deleteButton, editScholarship, viewApplicationsButton, viewRecipientsButton;
	@FXML private Label welcomeLabel;

	public AdminMainController(Main main, Session session)
	{
		this.main = main;
		this.session = session;
	}
	
	@FXML
	protected void handleSignOutButtonAction(ActionEvent event) throws Exception {	
		main.setScene("/view/Login.fxml");
	}
	
	@FXML
	protected void handleScholarshipButtonAction  (ActionEvent event) throws Exception {
		main.setScene("/view/AdminScholarship.fxml");
	}
	
	@FXML
	protected void handleCreateScholarshipButtonAction(ActionEvent event) throws Exception {
		main.setScene("/view/AddScholarship.fxml");
	}

	@FXML
	protected void handleDeleteButtonAction(ActionEvent event) throws Exception {
		main.setScene("/view/DeleteScholarship.fxml");
	}
	
	@FXML
	protected void handleEditScholarshipAction(ActionEvent event) throws Exception {
		main.setScene("/view/EditScholarship.fxml");
	}
	
	@FXML
	protected void handleViewRecipientsButtonAction(ActionEvent event) throws Exception {
		main.setScene("/view/AdminRecipient.fxml");
	}

	@FXML
	protected void handleViewApplicationsAction(ActionEvent event) throws Exception {
		main.setScene("/view/AdminViewApplications.fxml");
	}

	/*
	 * Update scholarships to closed if deadline has passed. Placed in this class only temporarily
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		welcomeLabel.setText(welcomeLabel.getText() + " " + session.getUser().getName());
		
	}
}
