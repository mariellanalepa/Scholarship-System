package application;

import java.net.URL;
import java.time.LocalDateTime;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Application;
import model.ScholarshipFactory;
import model.Scholarship;
import java.util.ArrayList;
import java.util.List;

public class AdminEditController implements Initializable {
	
	protected Parent root;
	@FXML private Button signOut, submitButton, editScholarship;
	@FXML private TextField deadlineBox, yearBox, donorBox, nameBox, numberBox, amountBox, GPABox, typeBox, departmentBox, facultyBox; 
	@FXML private Label welcomeLabel, deadlineLabel, yearLabel, donorLabel, nameLabel, amountLabel, numberLabel, GPALabel, typeLabel, departmentLabel, facultyLabel;
	@FXML protected ChoiceBox<String> scholDrop; 
	ArrayList<String> nameArray = new ArrayList<String>();
	ScholarshipFactory sf = new ScholarshipFactory();
	List<Scholarship> scholArray = sf.getScholarshipArray();		
	private int i;

	@FXML
	public static Scene getScene() throws Exception 
	{
		FXMLLoader loader = new FXMLLoader(AdminEditController.class.getResource("/view/editScholarship.fxml"));
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
	protected void handleSubmitButtonAction(ActionEvent event) throws Exception 
	{
		String[] scholarshipData = new String[13];
		
		Scholarship s = scholArray.get(nameArray.indexOf(scholDrop.getValue().toString()));
		scholarshipData[0] = Integer.toString(s.getId());
		scholarshipData[1] = nameBox.getText();
		scholarshipData[2] = donorBox.getText();
		scholarshipData[3] = deadlineBox.getText();
		scholarshipData[4] = amountBox.getText();
		scholarshipData[5] = numberBox.getText();
		scholarshipData[6] = facultyBox.getText();
		scholarshipData[7] = departmentBox.getText();
		scholarshipData[8] = typeBox.getText();
		scholarshipData[9] = GPABox.getText();
		scholarshipData[10] = yearBox.getText();
		scholarshipData[11] = "Open";		
		scholarshipData[12] = dateTimeFormat(LocalDateTime.now());
		Scholarship scholarship = new Scholarship(scholarshipData);
		
		scholarship.deleteScholarship(i+1);
		scholarship.saveScholarship(scholarshipData);
		Stage stage = (Stage) submitButton.getScene().getWindow();
		stage.setScene(AdminMainController.getScene());
		stage.show();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		welcomeLabel.setText(welcomeLabel.getText() + " " + LoginController.getAdminName());
		ScholarshipFactory sf = new ScholarshipFactory();
		List<Scholarship> scholArray = sf.getScholarshipArray();		
		for (int i = 0; i < sf.getScholarshipListLength(); i++)
		{
			nameArray.add(scholArray.get(i).getName());
			scholDrop.setItems(FXCollections.observableArrayList(nameArray));

		}
		
	}

	@FXML
	public void handleEdit(ActionEvent event) throws Exception 
	{
		
		
		i = nameArray.indexOf(scholDrop.getValue().toString());
		Scholarship scholarship = scholArray.get(i);
		nameBox.setText(scholarship.getName());
		donorBox.setText(scholarship.getDonor());
		deadlineBox.setText(scholarship.getDeadline());
		amountBox.setText(Integer.toString(scholarship.getAmount()));
		numberBox.setText(Integer.toString(scholarship.getNumber()));
		facultyBox.setText(scholarship.getFaculty());
		departmentBox.setText(scholarship.getDepartment());
		typeBox.setText(scholarship.getType());
		GPABox.setText(Float.toString(scholarship.getGpa()));
		yearBox.setText(scholarship.getYear());
	 
	}
	
	public String dateTimeFormat(LocalDateTime time) {
		String dateTime = time.toString();
		String year = dateTime.substring(0, 4)+ " ";
		String month = dateTime.substring(5, 7) + "/";
		String day = dateTime.substring(8, 10) + "/";
		String hms = dateTime.substring(11, 19);
		String dateTimeString = year + month + day + hms;
		return dateTimeString;
				
	}
	
	
	
}
