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

public class StudentWelcomeBoxController implements Initializable {
	
	private Main main;
	private Session session;
	@FXML private Label lblMessage, lblDeadlines;

	public StudentWelcomeBoxController(Main main, Session session) {
		this.main = main;
		this.session = session;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<Offer> studentOffers = new ArrayList<Offer>();
		
		for (Offer offer : session.getDatabase().getOffersByStudentID(session.getUser().getID())) {
			if (offer.getStatus().equals("open")) {
				studentOffers.add(offer);				
			}
		}
		
		if (studentOffers.size() >= 1) {
			lblMessage.setText("Congratulations, you have been selected for the following awards: \n");
			for (Offer o : studentOffers) {
				lblMessage.setText(lblMessage.getText() + " " + o.getScholarshipName() + "\n"); 
		    }
			
		} else {
			lblMessage.setText("You currently have no award offers\n");
		}
	
		
	}

}
