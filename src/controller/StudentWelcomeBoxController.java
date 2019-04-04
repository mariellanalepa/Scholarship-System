package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Offer;
import model.Session;
/**
 * Controller for the Information Displayed on the Student Main Page
 * Includes populating the "Deadlines" and "Awards" fields.
 * @author Natalie
 *
 */
public class StudentWelcomeBoxController implements Initializable {
	@SuppressWarnings("unused")
	private Main main;
	private Session session;
	// FXML objects for displayed messages
	@FXML private Label lblMessage, lblAnnouncement, lblInfo;

	public StudentWelcomeBoxController(Main main, Session session) {
		this.main = main;
		this.session = session;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/* Populating the "Award offers" field*/
		ArrayList<Offer> studentOffers = new ArrayList<Offer>();
		/* finding award offers for the indicated student with status "open"*/
		for (Offer offer : session.getDatabase().getOffersByStudentID(session.getUser().getID())) {
			if (offer.getStatus().equals("open")) {
				studentOffers.add(offer);				
			}
		}
		/* displaying award notifications on main page*/
		if (studentOffers.size() >= 1) {
			lblMessage.setText("Congratulations, you have been selected for the following awards: \n");
			for (Offer o : studentOffers) {
				lblMessage.setText(lblMessage.getText() + " " + o.getScholarshipName() + "\n"); 
		    }
			
		} else {
			lblMessage.setText("You currently have no award offers\n");
		}
	
		/* Populating the "Announcements" field*/
		lblInfo.setText("No announcements, check back later!");
	}

}
