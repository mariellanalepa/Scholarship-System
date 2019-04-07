/**
 * Controller to handle viewing, accepting, and declining awards.
 * Student is shown a drop down menu of awards based on open offers they have.
 * Student can accept or decline the award. A confirmation message will be printed,
 * and updates to the database are made. 
 * @author Jasmine Roebuck
 */

package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import model.Application;
import model.Award;
import model.Offer;
import model.Scholarship;
import model.Session;
import model.Student;

public class ViewAwardsController implements Initializable {
	
	private Main main;
	private Session session;
	private Offer offerOld;
	private String awardName;
	@FXML protected Button saveAndExitButton, submitButton; 
	@FXML private Label welcomeLabel, awardMessage;
	@FXML private ChoiceBox<String> awardDrop;
	private ObservableList<String> list; // List that populates the dropdown
	ArrayList<String> awardArray = new ArrayList<String>();

	//CSS styling
	String HOVERING_SIGNOUT_STYLE = "-fx-background-color: #cf0722; -fx-opacity: 70%; -fx-underline: true;";
	String NORMAL_SIGNOUT_STYLE = "-fx-background-color: #cf0722; -fx-text-fill: white;";
		
	public ViewAwardsController(Main main, Session session) {
		this.main = main;
		this.session = session;
	}
	
	/**
	 * Set up the FXML window. Populate the dropdown list and add set up a listener
	 * that will detect when changes are made to the dropdown selection.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// Populate the drop down list of scholarships with the ones that are offered to this user
		for (Offer o : session.getDatabase().getOffersByStudentID(session.getUser().getID())) {
			if (o.getStatus().equals("open")) {
				awardArray.add(o.getScholarshipName());				
			}
		}
		// Create a fixed observable list that feeds the data to the dropdown
			// Changes to this list will update what is displayed in the dropdown
		list = FXCollections.observableArrayList(awardArray);
		awardDrop.setItems(list);
		
		// Set a listener to capture the dropdown menu selection
		awardDrop.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) ->			{
			if (awardDrop.getValue() != null) {
				awardName = awardDrop.getValue().toString();
			} else {
				awardName = "";
			}
			});
		}
	
	/**
	 * If a student accepts an award offer, update the status of that offer. Update the status
	 * of the application for that award, and decrement the number of awards available for
	 * the scholarship. If the number of awards hits 0, close the scholarship. Recalculate
	 * the top candidates for the award. Finally, print a confirmation message to the screen.
	 * @param event
	 * @throws Exception
	 */
	@FXML
	protected void handleAcceptAwardButtonAction(ActionEvent event) throws Exception {
		// Only do something if a selection has been made in the dropdown menu
		if (!awardDrop.getSelectionModel().isEmpty()) {
			for (Offer o : session.getDatabase().getOffersByStudentID(session.getUser().getID())) {
				if (o.getScholarshipName().equals(awardName)) {
					offerOld = o; // Get the offer the user has selected
				}
			}
			Student student = this.session.getDatabase().getStudents().get(offerOld.getStudentID());
			Scholarship scholarship = this.session.getDatabase().getScholarshipsByName().get(offerOld.getScholarshipName());
			Offer offerNew = new Offer(scholarship, student, "accepted");
			student.addOffer(offerNew); // Add an offer with the edited status
				// This is done on the student object because, when the database write to
				// the offerDatabase.csv, it pulls offers from all student objects and writes those to the file
			student.getOffers().remove(offerOld); // Remove offer from the student
			// Update the award status
			for (Award a : student.getAwards()) {
				if (a.getScholarshipID() == scholarship.getId()) {
					a.setStatus("awarded");
				}
			}		
			
			list.remove(offerOld.getScholarshipName()); // Refresh the dropdown list
			
			// Find the application object for this student and this award
			for (Application a : scholarship.getApplicationsSubmittedOnly()) {
				if (a.getStudentId() == student.getID()) {
					a.setStatus("accepted"); // Edit application status
				}
			}
			
			int num = scholarship.getNumber(); // Decrement the number of awards available
			num--;
			scholarship.setNumber(num);
			if (num <= 0) {	// If number of awards hits 0...
				scholarship.setStatus("Closed"); // Close the scholarship
			} else {
				scholarship.recalculateTopCandidates(); // Reset the list of top candidates
			}
			
			awardMessage.setText("Congratulations on your award! You will be notified when your award is disbursed.");
			awardMessage.setVisible(true);
		}
	}
	
	/**
	 * If a student declines an award offer, update the status of that offer. Update the status
	 * of the application for that award. Recalculates the top candidates for this scholarship and
	 * sends an offer to the next candidate on the list. Finally, a confirmation message is
	 * printed to the screen.
	 * @param event
	 * @throws Exception
	 */
	@FXML
	protected void handleDeclineAwardButtonAction(ActionEvent event) throws Exception {
		// Only do something if a selection has been made in the dropdown menu
		if (!awardDrop.getSelectionModel().isEmpty()) {
			for (Offer o : session.getDatabase().getOffersByStudentID(session.getUser().getID())) {
				if (o.getScholarshipName().equals(awardName)) {
					offerOld = o; // Get the offer the user has selected
				}
			}
			Student student = this.session.getDatabase().getStudents().get(offerOld.getStudentID());
			Scholarship scholarship = this.session.getDatabase().getScholarshipsByName().get(offerOld.getScholarshipName());
			Offer offerNew = new Offer(this.session.getDatabase().getScholarshipsByName().get(offerOld.getScholarshipName()), student, "declined");
			student.addOffer(offerNew); // Add an offer with the edited status
			// This is done on the student object because, when the database write to
			// the offerDatabase.csv, it pulls offers from all student objects and writes those to the file
			for (Award a : student.getAwards()) {
				if (a.getScholarshipID() == scholarship.getId()) {
					a.setStatus("declined");
				}
			}
			
			student.getOffers().remove(offerOld); // Remove old offer from Student
			list.remove(offerOld.getScholarshipName()); // Refresh the dropdown list
			
			// Find the application object for this student and this award
			for (Application a : scholarship.getApplicationsSubmittedOnly()) {
				if (a.getStudentId() == student.getID()) {
					a.setStatus("declined"); // Edit application status
				}
			}
			
			scholarship.recalculateTopCandidates(); // Reset the list of top candidates
			// Find the next top candidate who does not yet have an offer for this award
			for (Student s : scholarship.getTopCandidates()) {
				boolean hasOffer = false;
				String name = scholarship.getName();
				if (s != null) {
					ArrayList<Offer> offers = s.getOffers();
					for (Offer o : offers) {
						if (o.getScholarshipName().equals(name)) {
							hasOffer = true; // Student has an offer for this scholarship
						}
					} 
				
					if (!hasOffer) { // If student does not have an offer for this award
						Offer newOffer = new Offer(scholarship, s, "open");
						s.addOffer(newOffer); // Give them an offer
					}
				}
			}
			
			awardMessage.setText("Thank you for your consideration. You have successfully declined this award.");
			awardMessage.setVisible(true);
		}
	}

}
