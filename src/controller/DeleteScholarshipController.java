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
import model.ScholarshipFactory;
import model.Session;
import model.Scholarship;
import java.util.ArrayList;
import java.util.List;

public class DeleteScholarshipController implements Initializable {
	
	private Main main;
	private Session session;
	@FXML protected Label welcomeLabel, deleteLabel;
	@FXML protected Button signOut, deleteButton, mainMenuButton;
	@FXML protected ChoiceBox<String> scholarshipSelectDropDown; 
	ArrayList<String> nameArray = new ArrayList<String>();
	private Scholarship scholarship;
	
	
	public DeleteScholarshipController(Main main, Session session) {
		this.main = main;
		this.session = session;
	}
	
	@FXML
	protected void handleSignOutButtonAction(ActionEvent event) throws Exception {
		main.setScene("/view/Login.fxml");
	}
	
	
	@FXML
	protected void handleDeleteButtonAction(ActionEvent event) throws Exception
	{
		ScholarshipFactory sf = new ScholarshipFactory();
		List<Scholarship> scholArray = sf.getScholarshipArray();
		int i = nameArray.indexOf(scholarshipSelectDropDown.getValue().toString())+1; //nameArray is 1 shorter than scholArray
		scholarship = scholArray.get(i-1); 				
		scholarship.deleteScholarship(i); 
		//Set scene to Admin Main 
		main.setScene("/view/AdminMain.fxml");
	}
	
	@FXML
	protected void handleMainMenuButtonAction(ActionEvent event) throws Exception {
		main.setScene("/view/AdminMain.fxml");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ScholarshipFactory sf = new ScholarshipFactory();
		List<Scholarship> scholArray = sf.getScholarshipArray();		
		for (int i = 0; i < sf.getScholarshipListLength(); i++)
		{
			nameArray.add(scholArray.get(i).getName());
			scholarshipSelectDropDown.setItems(FXCollections.observableArrayList(nameArray));
		}	
	}
}
