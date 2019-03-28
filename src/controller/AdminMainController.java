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
import model.Scholarship;
import model.Session;

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
	protected void handleScholarshipButtonAction(ActionEvent event) throws Exception {
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
		
		//Go through scholarships compare their closing date/time with current date/time
		for (Scholarship scholarship : this.session.getDatabase().getScholarshipsById().values()) {
			try {
				Date deadline = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(scholarship.getDeadline());
				Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(dateTimeFormat(LocalDateTime.now()));
				if (deadline.compareTo(now) <= 0) {	//deadline has passed
		
					try { scholarship.setStatus("Closed");	}	//set to closed
					catch (Exception e) {e.printStackTrace();}
					
				}				
			} catch (ParseException e) {	e.printStackTrace(); }
	
		}
	
	}
	/*
	 * helper method to set current date and time into string format
	 */
	public String dateTimeFormat(LocalDateTime time) {
		String dateTime = time.toString();
		String year = dateTime.substring(0, 4)+ "-";
		String month = dateTime.substring(5, 7) + "-";
		String day = dateTime.substring(8, 10) + " ";
		String hm = dateTime.substring(11, 16);
		String dateTimeString = year + month + day + hm;
		return dateTimeString;
	}

}
