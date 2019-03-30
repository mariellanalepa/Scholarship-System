/**
 * Controller to handle viewing, accepting, and declining awards.
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

public class ViewAwardsController implements Initializable {
	
	private Main main;
	private Session session;
	@FXML protected Button signOut, saveAndExitButton, submitButton, mainMenuButton; 
	@FXML private Label welcomeLabel;
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
		for (Offer offer : session.getDatabase().getOffersByStudentID(session.getUser().getID())) {
			if (offer.getStatus().equals("open")) {
				awardArray.add(offer.getScholarshipName());				
			}
		}
		awardDrop.setItems(FXCollections.observableArrayList(awardArray));
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
		// Print out message to a label instead of the console
		System.out.println("Congratulations on your award! You will be notified when your award is disbursed.");
		// Change offer status in database to 'accepted'
	}
	
	@FXML
	protected void handleDeclineAwardButtonAction(ActionEvent event) throws Exception {
		// Print out message to a label instead of the console
		System.out.println("Thank you for your consideration. You have successfully declined this award.");
		// Change offer status in database to 'declined'
	}
	
	

}
