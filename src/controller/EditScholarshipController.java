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
import model.ScholarshipFactory;
import model.Session;
import model.Scholarship;
import java.util.ArrayList;
import java.util.List;

public class EditScholarshipController implements Initializable {
	
	private Main main;
	private Session session;
	@FXML private Button signOut, submitButton, editScholarship, mainMenuButton;
	@FXML private TextField deadlineBox, yearBox, donorBox, nameBox, numberBox, amountBox, GPABox, typeBox, departmentBox, facultyBox; 
	@FXML private Label welcomeLabel, deadlineLabel, yearLabel, donorLabel, nameLabel, amountLabel, numberLabel, GPALabel, typeLabel, departmentLabel, facultyLabel;
	@FXML protected ChoiceBox<String> scholDrop; 
	ArrayList<String> nameArray = new ArrayList<String>();
	ScholarshipFactory sf = new ScholarshipFactory();
	List<Scholarship> scholArray = sf.getScholarshipArray();		
	private int i;

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
		//Set scene to Admin Main
		main.setScene("/view/AdminMain.fxml");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		welcomeLabel.setText(welcomeLabel.getText() + " " + session.getUser().getName());
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
