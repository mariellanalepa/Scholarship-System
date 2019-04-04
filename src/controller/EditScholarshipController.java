package controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import application.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Session;
import model.Scholarship;
import java.util.ArrayList;
/**
 * Controller class for an admin to select a scholarship and edit its properties
 */
public class EditScholarshipController implements Initializable {
	
	private Main main;
	private Session session;
	@FXML private Button submitButton, editScholarship;
	@FXML private DatePicker deadlinePicker;
	@FXML private TextField yearBox, donorBox, nameBox, numberBox, amountBox, GPABox; 
	@FXML private Label welcomeLabel, errorLabel, editLabel, deadlineLabel, yearLabel, donorLabel, nameLabel, amountLabel, numberLabel, GPALabel, typeLabel, departmentLabel, facultyLabel;
	@FXML protected ChoiceBox<String> scholDrop, stuDrop, facDrop, depDrop; 
	ArrayList<String> nameArray = new ArrayList<String>();	//array list of scholarship names
	private boolean empty = false;	//boolean storing whether the submission contains an empty field

	public EditScholarshipController(Main main, Session session) {
		this.main = main;
		this.session = session;
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
		
		//Get scholarship name
		String scholarshipName = scholDrop.getValue();
		Scholarship scholarshipOld = this.session.getDatabase().getScholarshipsByName().get(scholarshipName);
		
		scholarshipData[0] = Integer.toString(scholarshipOld.getId());
		
		//Formatter for date and time string: dd-MM-yyyy HH:mm:ss
		//To be used to format date entered in DatePicker 
		DateTimeFormatter deadlineFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		
		/*
		 * check if boxes are empty before extracting data
		 */
		if (!nameBox.getText().isEmpty()) { scholarshipData[1] = nameBox.getText();}
		else { empty = true;}
		if (!donorBox.getText().isEmpty()) { scholarshipData[2] = donorBox.getText(); }
		else { 			empty = true; }
		if (deadlinePicker.getValue() != null) { scholarshipData[3] = deadlinePicker.getValue().format(deadlineFormatter) + " 23:59"; }
		else { 	scholarshipData[3] = deadlinePicker.getPromptText();	}
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
		
		if (empty == false) {	//there are no empty fields
			//Delete "old version" of scholarship
			this.session.getDatabase().deleteScholarship(scholarshipOld);
			//Add "new version" of scholarship
			this.session.getDatabase().addScholarship(new Scholarship(this.session.getDatabase(),scholarshipData));
			
			//Set scene to Admin Main Page
			main.injectPaneIntoScene("/view/AdminWelcomeText.fxml");
		}
		else {	//there is an empty field
			errorLabel.setText("Error! Not all boxes contain a value");
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		for (Scholarship scholarship : this.session.getDatabase().getScholarshipsById().values())	//add all scholarships' names to nameArray
		{
			nameArray.add(scholarship.getName());	
			scholDrop.setItems(FXCollections.observableArrayList(nameArray));	//set scholarship names into drop down menu
		}
		/*
		 * Set drop down menus with intitial values
		 */
		facDrop.setItems(FXCollections.observableArrayList("ANY", "SC", "H", "AR", "EN", "ED"));	
		depDrop.setItems(FXCollections.observableArrayList("ANY"));
		stuDrop.setItems(FXCollections.observableArrayList("ANY", "UGRAD", "GRAD", "CS"));
		
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
	/**
	 * Fields are filled with the scholarship's current information
	 * @param event – Edit button is pressed
	 * @throws Exception
	 */
	@FXML
	public void handleEdit(ActionEvent event) throws Exception 
	{
		String scholarshipName = scholDrop.getValue();
		Scholarship scholarship = this.session.getDatabase().getScholarshipsByName().get(scholarshipName);
		nameBox.setText(scholarship.getName());
		donorBox.setText(scholarship.getDonor());
		deadlinePicker.setPromptText(scholarship.getDeadline());
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
