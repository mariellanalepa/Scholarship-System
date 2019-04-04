package controller;

import java.net.URL;
import java.util.ResourceBundle;
import application.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import model.Session;
import model.Scholarship;
import java.util.ArrayList;

/**
 * Controller for an admin to delete a selected scholarship from the system
 * @author luclegere
 *
 */
public class DeleteScholarshipController implements Initializable {
	
	private Main main;
	private Session session;
	@FXML protected Label deleteLabel;
	@FXML protected Button deleteButton;
	@FXML protected ChoiceBox<String> scholarshipSelectDropDown; 
	ArrayList<String> nameArray = new ArrayList<String>();	//array of scholarship names
	private Scholarship scholarship;	//scholarship to be deleted
	
	
	public DeleteScholarshipController(Main main, Session session) {
		this.main = main;
		this.session = session;
	}
	
	@FXML
	protected void handleDeleteButtonAction(ActionEvent event) throws Exception
	{
		String name = scholarshipSelectDropDown.getValue(); 
		System.out.println(name);
		//Find scholarship in database
		this.scholarship = this.session.getDatabase().getScholarshipsByName().get(name);
		//Remove scholarship from database
		this.session.getDatabase().deleteScholarship(this.scholarship);
		
		//Return to Admin "Main Page" by injecting into Scene
		main.injectPaneIntoScene("/view/AdminWelcomeText.fxml");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//set drop down menu with scholarships
		for (Scholarship scholarship : this.session.getDatabase().getScholarshipsById().values())
		{
			nameArray.add(scholarship.getName());
			scholarshipSelectDropDown.setItems(FXCollections.observableArrayList(nameArray));
		}	
	}
}
