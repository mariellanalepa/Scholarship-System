package controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import application.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import model.Application;
import model.Award;
import model.Scholarship;
import model.Session;
import model.Student;
/**
 * Controller that allows students to view their application form
 *
 */
public class ApplicationFormController implements Initializable
{	
	private Main main;
	private Session session;
	@FXML protected Label confirmationLabel, SCHOLARSHIP_FIELD, FNAME_FIELD, LNAME_FIELD, ID_FIELD, YEAR_FIELD, DEPT_FIELD, FACULTY_FIELD, GPA_FIELD, TYPE_FIELD;
	@FXML protected Button saveAndExitButton, submitButton; 
	@FXML protected ChoiceBox<String> scholarshipSelectDropDown; 
	private Application application;
	private Scholarship scholarship;
	//Formatter for current system time: yyyy-MM-dd HH:mm:ss format
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 

	/**
	 * Constructor for ApplicationFormController
	 * @param main - reference to main
	 * @param session - reference to the current session
	 */
	public ApplicationFormController(Main main, Session session) {
		this.main = main;
		this.session = session;
	}
		

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Student student = (Student) session.getUser();
		
		//Check if a scholarship has been selected via table GUI
		this.scholarship = this.session.getScholarshipSelection();
		
		if (this.scholarship != null)
		{
			SCHOLARSHIP_FIELD.setText(this.scholarship.getName());
		}
		
		String[] applicationData = new String[5];
		applicationData[0] = Integer.toString(this.session.getDatabase().getApplicationIdCounter());
		applicationData[1] = Integer.toString(student.getID());
		applicationData[2] = Integer.toString(this.scholarship.getId());
		applicationData[3] = LocalDateTime.now().format(formatter);
		applicationData[4] = "saved";
		
		Application a = new Application(this.session.getDatabase(), applicationData);
		this.application = a;
		
		//Add application to database
		this.session.getDatabase().addApplication(application);
		
		//Auto-fill application with student data
		FNAME_FIELD.setText(student.getFirstName());
		LNAME_FIELD.setText(student.getLastName());
		ID_FIELD.setText(student.getStudentIDString());
		YEAR_FIELD.setText(student.getStudentYearString());
		DEPT_FIELD.setText(student.getStudentDepartment());
		FACULTY_FIELD.setText(student.getStudentFaculty());
		GPA_FIELD.setText(student.getStudentGPAString());
		TYPE_FIELD.setText(student.getStudentType());

	}
	
	@FXML
	protected void handleSaveAndExitButtonAction(ActionEvent event) throws Exception
	{
		main.injectPaneIntoScene("/view/StudentAwardsMessage.fxml");
	}
	
	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) throws Exception
	{
		this.application.setDateAdded(LocalDateTime.now().format(formatter));
		this.application.setStatus("submitted");
		Student student = (Student) this.session.getUser();
		Award award = new Award(student, scholarship, "application submitted");
		student.addAward(award);
		
		submitButton.setVisible(false);
		saveAndExitButton.setVisible(false);
		
		confirmationLabel.setVisible(true);
	}
}



