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
	@FXML private Button newApplicationButton, signOut, viewScholarshipButton, reviewApplicationButton;
	@FXML private Label welcomeLabel, lblMessage;
	
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
	
		ArrayList<Offer> studentOffers = new ArrayList<Offer>();
		studentOffers = session.getDatabase().getOffersByStudentID(session.getUser().getID());
		
		if (studentOffers.size() >= 1) lblMessage.setText("Congratulations, you have been selected for ");
		boolean first = true;
		int i = 0;
		for (Offer o : studentOffers) {
			if (first = true) {
			lblMessage.setText(lblMessage.getText() + " " + o.getScholarshipName() + "\n"); }
			else {
			lblMessage.setText(lblMessage.getText() + " and " + o.getScholarshipName());
			}
		first = false;
		i++;
		
		}
		System.out.println(i);
		
		
	}

}
