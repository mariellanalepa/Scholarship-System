package application;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Application;
import model.ApplicationFactory;
import model.CsvReader;
import model.ScholarshipFactory;
import model.Session;

public class ApplicationFormController implements Initializable
{	
	private Main main;
	private Session session;
	
	protected Parent root;
	@FXML protected Label welcomeLabel, confirmationLabel, FNAME_FIELD, LNAME_FIELD, ID_FIELD, YEAR_FIELD, DEPT_FIELD, FACULTY_FIELD, GPA_FIELD, TYPE_FIELD;
	@FXML protected Button signOut, saveAndExitButton, submitButton, mainMenuButton; 
	@FXML protected ChoiceBox<String> scholarshipSelectDropDown; 
	private Application application;
	
	
	//CSS styling
		String HOVERING_SIGNOUT_STYLE = "-fx-background-color: #cf0722; -fx-opacity: 70%; -fx-underline: true;";
		String NORMAL_SIGNOUT_STYLE = "-fx-background-color: #cf0722; -fx-text-fill: white;";

	public ApplicationFormController(Main main, Session session)
	{
		this.main = main;
		this.session = session;
	}
		

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		welcomeLabel.setText(welcomeLabel.getText() + " " + LoginController.getStudentName());
		CsvReader s = new CsvReader();

		Application a = new Application();
		//event styling - uses lambda expressions
		signOut.setOnMouseEntered(e -> signOut.setStyle(HOVERING_SIGNOUT_STYLE));
		signOut.setOnMouseExited(e -> signOut.setStyle(NORMAL_SIGNOUT_STYLE));
		a.setStudentId((LoginController.studentID));
		a.setDateAdded(dateTimeFormat(LocalDateTime.now()));
		a.setScholarshipId("1"); //temp hardcode 
		a.setScholarshipName(s.getScholarshipName(Integer.valueOf(a.getScholarshipId())));
		//a.setScholarshipDeadline(s.getDeadline(Integer.valueOf(a.getScholarshipDeadlineProperty())));
		this.application = a;
		
		FNAME_FIELD.setText(LoginController.studentFirstName);
		LNAME_FIELD.setText(LoginController.studentLastName);
		ID_FIELD.setText(LoginController.studentID);
		YEAR_FIELD.setText(LoginController.studentYearString);
		DEPT_FIELD.setText(LoginController.studentDept);
		FACULTY_FIELD.setText(LoginController.studentFaculty);
		GPA_FIELD.setText(LoginController.studentGPAString);
		TYPE_FIELD.setText(LoginController.studentType);
		
		//scholarshipSelectDropDown.setItems(FXCollections.observableArrayList("Scholarship A", "Scholarship B", "Scholarship C"));
		
		

	}
	
	@FXML 
	protected void handleMainMenuButtonAction(ActionEvent event) throws Exception{
		/*Stage stage = (Stage) mainMenuButton.getScene().getWindow();
		stage.setScene(StudentMainController.getScene());			
		stage.show();*/
		main.setScene("/view/StudentMain.fxml");
		
	}
	@FXML
	protected void handleSignOutButtonAction(ActionEvent event) throws Exception
	{
		/*//Get the primary stage of our App
		Stage stage = (Stage) signOut.getScene().getWindow();
		//Set new scene
		stage.setScene(LoginController.getScene());			
		stage.show();*/
		main.setScene("/view/Login.fxml");
	}
	@FXML
	protected void handleSaveAndExitButtonAction(ActionEvent event) throws Exception
	{
		this.application.stashApplication();
		/*//Get the primary stage of our App
		Stage stage = (Stage) saveAndExitButton.getScene().getWindow();
		//Set new scene
		stage.setScene(StudentMainController.getScene());			
		stage.show();*/
		main.setScene("/view/StudentMain.fxml");
	}
	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) throws Exception
	{
		//Get the primary stage of our App
		this.application.setDateSubmitted(dateTimeFormat(LocalDateTime.now()));
		this.application.submitApplication();
		confirmationLabel.setVisible(true);
		//Stage stage = (Stage) submitButton.getScene().getWindow();
		//Set new scene
		//stage.setScene(StudentMainController.getScene());			
		//stage.show();
	}
	private String dateTimeFormat(LocalDateTime time) {
		String dateTime = time.toString();
		String year = dateTime.substring(0, 4)+ " ";
		String month = dateTime.substring(5, 7) + "/";
		String day = dateTime.substring(8, 10) + "/";
		String hms = dateTime.substring(11, 19);
		String dateTimeString = year + month + day + hms;
		return dateTimeString;
				
	}
}



