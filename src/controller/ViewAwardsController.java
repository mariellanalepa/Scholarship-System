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
import model.Offer;
import model.Scholarship;
import model.Session;

public class ViewAwardsController implements Initializable {
	
	private Main main;
	private Session session;
	@FXML protected Button signOut, saveAndExitButton, submitButton, mainMenuButton; 
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
		
		for (Offer offer : this.session.getDatabase().getOffers())
		{
			if (offer.getStudentID() == session.getUser().getID()) {
				awardArray.add(offer.getScholarshipName());
			}
		}
		awardDrop.setItems(FXCollections.observableArrayList(awardArray));
		// display confirmation messages for accept/decline?
		// What happens behind the scenes?

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

}
