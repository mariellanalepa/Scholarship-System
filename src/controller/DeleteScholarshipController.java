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


public class DeleteScholarshipController implements Initializable {
	
	private Main main;
	private Session session;
	@FXML protected Label deleteLabel;
	@FXML protected Button deleteButton;
	@FXML protected ChoiceBox<String> scholarshipSelectDropDown; 
	ArrayList<String> nameArray = new ArrayList<String>();
	private Scholarship scholarship;
	
	
	public DeleteScholarshipController(Main main, Session session) {
		this.main = main;
		this.session = session;
	}
	
	@FXML
	protected void handleDeleteButtonAction(ActionEvent event) throws Exception
	{
		int i = nameArray.indexOf(scholarshipSelectDropDown.getValue().toString())+1; //nameArray is 1 shorter than scholArray
		//Find scholarship in database
		this.scholarship = this.session.getDatabase().getScholarshipsById().get(i);
		//Remove scholarship from database
		this.session.getDatabase().deleteScholarship(this.scholarship);
		
		//Return to Admin "Main Page" by injecting into Scene
		main.injectPaneIntoScene("/view/AdminWelcomeText.fxml");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
			
		for (Scholarship scholarship : this.session.getDatabase().getScholarshipsById().values())
		{
			nameArray.add(scholarship.getName());
			scholarshipSelectDropDown.setItems(FXCollections.observableArrayList(nameArray));
		}	
	}
}
