/**
 * Controller to handle viewing, accepting, and declining awards.
 * Student is shown a drop down menu of awards based on open offers they have.
 * Student can accept or decline the award. A confirmation message will be printed,
 * and the status of the offer in the offerDatabase will be updated from 'open'
 * to either 'accepted' or 'declined'
 * @author Jasmine Roebuck
 */

package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import model.Offer;
import model.Session;
import model.Student;

public class ViewAwardsController implements Initializable {
	
	private Main main;
	private Session session;
	private Offer offerOld;
	private String awardName;
	@FXML protected Button signOut, saveAndExitButton, submitButton, mainMenuButton; 
	@FXML private Label welcomeLabel, awardMessage;
	@FXML private ChoiceBox<String> awardDrop;
	ArrayList<String> awardArray = new ArrayList<String>();

	//CSS styling
	String HOVERING_SIGNOUT_STYLE = "-fx-background-color: #cf0722; -fx-opacity: 70%; -fx-underline: true;";
	String NORMAL_SIGNOUT_STYLE = "-fx-background-color: #cf0722; -fx-text-fill: white;";
		
	public ViewAwardsController(Main main, Session session) {
		this.main = main;
		this.session = session;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// Populate the drop down list of scholarships with the ones that are offered to this user
		for (Offer o : session.getDatabase().getOffersByStudentID(session.getUser().getID())) {
			if (o.getStatus().equals("open")) {
				awardArray.add(o.getScholarshipName());				
			}
		}
		awardDrop.setItems(FXCollections.observableArrayList(awardArray));
		
		// Set a listener to capture the dropdown menu selection
		awardDrop.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) ->			{
			awardName = awardDrop.getValue().toString();								
			});
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
	
	@FXML
	protected void handleAcceptAwardButtonAction(ActionEvent event) throws Exception {
		// Only act if a selection has been made in the dropdown menu
		if (!awardDrop.getSelectionModel().isEmpty()) {
			for (Offer o : session.getDatabase().getOffersByStudentID(session.getUser().getID())) {
				if (o.getScholarshipName().equals(awardName)) {
					offerOld = o; // Get the offer the user has selected
				}
			}
			Student student = this.session.getDatabase().getStudents().get(offerOld.getStudentID());
			Offer offerNew = new Offer(this.session.getDatabase().getScholarshipsByName().get(offerOld.getScholarshipName()), student, "accepted");
			student.addOffer(offerNew); // Add an offer with the edited status
				// This is done on the student object because, when the database write to
				// the offerDatabase.csv, it pulls offers from all student objects and writes those to the file
			student.getOffers().remove(offerOld); 
			awardMessage.setText("Congratulations on your award! You will be notified when your award is disbursed.");
			awardMessage.setVisible(true);
		}
	}
	
	@FXML
	protected void handleDeclineAwardButtonAction(ActionEvent event) throws Exception {
		// Only act if a selection has been made in the dropdown menu
		if (!awardDrop.getSelectionModel().isEmpty()) {
			for (Offer o : session.getDatabase().getOffersByStudentID(session.getUser().getID())) {
				if (o.getScholarshipName().equals(awardName)) {
					offerOld = o; // Get the offer the user has selected
				}
			}
			Student student = this.session.getDatabase().getStudents().get(offerOld.getStudentID());
			Offer offerNew = new Offer(this.session.getDatabase().getScholarshipsByName().get(offerOld.getScholarshipName()), student, "declined");
			student.addOffer(offerNew); // Add an offer with the edited status
			// This is done on the student object because, when the database write to
			// the offerDatabase.csv, it pulls offers from all student objects and writes those to the file
			student.getOffers().remove(offerOld);
			awardMessage.setText("Thank you for your consideration. You have successfully declined this award.");
			awardMessage.setVisible(true);
		}
	}
	
	

}
