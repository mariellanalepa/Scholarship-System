package controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import application.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Session;
import model.Scholarship;
import java.util.ArrayList;

public class EditScholarshipController implements Initializable {
	
	private Main main;
	private Session session;
	@FXML private Button signOut, submitButton, editScholarship, mainMenuButton;
	@FXML private TextField deadlineBox, yearBox, donorBox, nameBox, numberBox, amountBox, GPABox; 
	@FXML private Label welcomeLabel, errorLabel, editLabel, deadlineLabel, yearLabel, donorLabel, nameLabel, amountLabel, numberLabel, GPALabel, typeLabel, departmentLabel, facultyLabel;
	@FXML protected ChoiceBox<String> scholDrop, stuDrop, facDrop, depDrop; 
	ArrayList<String> nameArray = new ArrayList<String>();	
	private boolean empty = false;

	public EditScholarshipController(Main main, Session session) {
		this.main = main;
		this.session = session;
	}
	
	@FXML
	protected void handleSignOutButtonAction(ActionEvent event) throws Exception {
		main.setScene("/view/Login.fxml");
	}
	
	@FXML
	protected void handleMainMenuButtonAction(ActionEvent event) throws Exception {
		main.setScene("/view/AdminMain.fxml");
	}

	/**
	 * extract input from form when submit button is pressed
	 * error checking for empty values
	 * @param event Submit Button pressed
	 * @throws Exception	
	 */
	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) throws Exception 
	{
		String[] scholarshipData = new String[13];
	
		empty = false; 
		
		//Get scholarship ID
		scholarshipData[0] = scholDrop.getValue().toString();
		
		if (!nameBox.getText().isEmpty()) { scholarshipData[1] = nameBox.getText();}
		else { empty = true;}
		if (!donorBox.getText().isEmpty()) { scholarshipData[2] = donorBox.getText(); }
		else { 			empty = true; }
		if (!deadlineBox.getText().isEmpty()) { scholarshipData[3] = deadlineBox.getText(); }
		else { 	empty = true;	}
		if (!amountBox.getText().isEmpty()) { scholarshipData[4] = amountBox.getText(); }
		else { 			empty = true;}
		if (!numberBox.getText().isEmpty()) { scholarshipData[5] = numberBox.getText(); }
		else {			empty = true; }
		if (!facDrop.getValue().isEmpty()) { scholarshipData[6] =  facDrop.getValue(); }
		else { 			empty = true;	}	
		if (!depDrop.getValue().isEmpty()) { scholarshipData[7] = depDrop.getValue(); }
		else { 			empty = true; }
		if (!stuDrop.getValue().isEmpty()) { scholarshipData[8] = stuDrop.getValue(); }
		else { 			empty = true; }
		if (!GPABox.getText().isEmpty()) { scholarshipData[9] = GPABox.getText(); }
		else {			empty = true; }
		if (!yearBox.getText().isEmpty()) { scholarshipData[10] = yearBox.getText();}
		else {			empty = true; }
		scholarshipData[11] = "Open";		
		scholarshipData[12] = dateTimeFormat(LocalDateTime.now());
		
		if (empty == false) {
			//Retrieve "old version" of scholarship
			Scholarship scholarship = this.session.getDatabase().getScholarships().get(Integer.valueOf(scholarshipData[0]));
			//Delete "old version" of scholarship
			this.session.getDatabase().deleteScholarship(scholarship);
			//Add "new version" of scholarship
			this.session.getDatabase().addScholarship(new Scholarship(this.session.getDatabase(),scholarshipData));
			
			//Set scene to Admin Main Page
			main.setScene("/view/AdminMain.fxml");
		}
		else {
			errorLabel.setText("Error! Not all boxes contain a value");
			
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Welcome message customized to user's name
		welcomeLabel.setText(welcomeLabel.getText() + " " + session.getUser().getName());
		
		for (Scholarship scholarship : this.session.getDatabase().getScholarships().values())
		{
			nameArray.add(scholarship.getName());
			scholDrop.setItems(FXCollections.observableArrayList(nameArray));
		}
	
		facDrop.setItems(FXCollections.observableArrayList("ANY", "SC", "H", "AR", "EN", "ED"));	
		depDrop.setItems(FXCollections.observableArrayList("ANY"));
		stuDrop.setItems(FXCollections.observableArrayList("ANY", "U", "G", "CS"));
		
		//listener for facDrop to detect change; so depDrop changes based on the faculty
		facDrop.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) ->
		{
				if (facDrop.getValue() == "H") {
					depDrop.setItems(FXCollections.observableArrayList("ANY", "ACCT", "FNCE", "HR", "ENTI", "MKTG"));
					depDrop.setValue("ANY");
           
				}
				else if (facDrop.getValue() == "SC") {
					depDrop.setItems(FXCollections.observableArrayList("ANY", "CPSC", "BIOL", "CHEM", "PHYS", "MATH", "GEOL"));
					depDrop.setValue("ANY");

				}		
				else if (facDrop.getValue() == "AR") {
					depDrop.setItems(FXCollections.observableArrayList("ANY", "ECON", "ENGL", "PSYC", "PHIL"));
					depDrop.setValue("ANY");

				}
				else if (facDrop.getValue() == "EN") {
					depDrop.setItems(FXCollections.observableArrayList("ANY", "CIVL", "MECH", "SENG", "CHEM"));
					depDrop.setValue("ANY");

				}
				else if (facDrop.getValue() == "ED") {
					depDrop.setItems(FXCollections.observableArrayList("ANY"));
					depDrop.setValue("ANY");

				}
				else if (facDrop.getValue() == "ANY") {
					depDrop.setItems(FXCollections.observableArrayList("ANY"));
					depDrop.setValue("ANY");

				}
		});
	}

	@FXML
	public void handleEdit(ActionEvent event) throws Exception 
	{
		int scholarshipId = Integer.valueOf(scholDrop.getValue().toString());
		Scholarship scholarship = this.session.getDatabase().getScholarships().get(scholarshipId);
		nameBox.setText(scholarship.getName());
		donorBox.setText(scholarship.getDonor());
		deadlineBox.setText(scholarship.getDeadline());
		amountBox.setText(Integer.toString(scholarship.getAmount()));
		numberBox.setText(Integer.toString(scholarship.getNumber()));
		facDrop.setValue(scholarship.getFaculty());
		depDrop.setValue(scholarship.getDepartment());
		stuDrop.setValue(scholarship.getType());
		GPABox.setText(Float.toString(scholarship.getGpa()));
		yearBox.setText(scholarship.getYear());
	 
	}
	/**
	 * takes in current local date and time and formats it into the desired string format
	 */
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
