package application;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Application;
import model.ScholarshipFactory;
import model.Scholarship;
import java.util.ArrayList;
import java.util.List;

public class AdminDeleteController implements Initializable {
	protected Parent root;
	@FXML protected Label welcomeLabel, deleteLabel;
	@FXML protected Button signOut, deleteButton; 
	@FXML protected ChoiceBox<String> scholarshipSelectDropDown; 
	ArrayList<String> nameArray = new ArrayList<String>();
	private Scholarship scholarship;
	
	@FXML
	public static Scene getScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader(AdminDeleteController.class.getResource("/view/deleteScholarship.fxml"));
		Parent root = (Parent) loader.load();
		Scene newScene = new Scene(root);
		return newScene;
	}
	@FXML
	protected void handleSignOutButtonAction(ActionEvent event) throws Exception
	{
		//Get the primary stage of our App
		Stage stage = (Stage) signOut.getScene().getWindow();
		//Set new scene
		stage.setScene(LoginController.getScene());			
		stage.show();
	}
	
	
	@FXML
	protected void handleDeleteButtonAction(ActionEvent event) throws Exception
	{
		ScholarshipFactory sf = new ScholarshipFactory();
		List<Scholarship> scholArray = sf.getScholarshipArray();
		int i = nameArray.indexOf(scholarshipSelectDropDown.getValue().toString())+1; //nameArray is 1 shorter than scholArray
		scholarship = scholArray.get(i-1); 				
		scholarship.deleteScholarship(i); 
		Stage stage = (Stage) deleteButton.getScene().getWindow();
		stage.setScene(AdminMainController.getScene());
		stage.show();

		
		
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
