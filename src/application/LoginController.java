package application;

import model.Admin;
import model.InvalidUserException;
import model.Session;
import model.Student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Session;

public class LoginController implements Initializable {
	
	protected Parent root;
	@FXML private Button signIn;
	@FXML private TextField usernameField;
	@FXML private Label errorLabel;
	
	//Store for static access
	static String username;
	static String studentID, studentFirstName, studentLastName, studentDept, studentFaculty, studentYearString, studentGPAString, studentType;
	static String adminID, adminFirstName, adminLastName;
	protected static String invalidUname = "Invalid username or password, please try again";

	
	
	@FXML
	protected void handleSignInButtonAction(ActionEvent event) throws Exception
	{	 
		//Get student ID from text field
		username = usernameField.getText().toLowerCase();
	
		/* Create new login session
		 * try to create new student, if input is not valid (integer) 
		 * or student ID not in database
		 * LoginSession instantiation will fail
		 */
		Session login = null;
		try {
			login = new Session(username);

		} catch(InvalidUserException notValidUser) {
			errorLabel.setText(invalidUname);
			System.out.println(notValidUser);
			// Reset stage
			Stage stage = (Stage) errorLabel.getScene().getWindow();		
			stage.show();
			return;
		} 

			//Get the primary stage of our App
			Stage stage = (Stage) signIn.getScene().getWindow();
			
			int userType = login.getUserType();
			/* admin user */
			if (userType == 0) {
				Admin a = login.getAdmin();
				adminFirstName = a.getFirstName();
				adminLastName = a.getLastName();
	
				//Set new scene
				stage.setScene(AdminMainController.getScene());			
				stage.show();
				
			/* student user */	
			} else if (userType == 1) {
				Student s = login.getStudent();
				// Get student attributes and assign to the login session
				studentID = s.getStudentID();
				studentFirstName = s.getFirstName();
				studentLastName = s.getLastName();
				studentYearString = s.getStudentYear();
				studentDept = s.getStudentDepartment();
				studentFaculty = s.getStudentFaculty();
				studentGPAString = s.getStudentGPA();
				studentType = s.getStudentType();
				//Set new scene
				stage.setScene(StudentMainController.getScene());			
				stage.show();
			}
		
		
	}
	
	
	public static Scene getScene() throws Exception
	{	
		//Inflate FXML and instantiate a LoginController object
		FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("/view/Login.fxml"));
		Parent root = (Parent) loader.load();
		//Create and return scene for root node
		return new Scene(root);
	}
	
	public static String getUsername() 
	{
		//Retrieve the student number
		return username;
	}
	
	public static String getStudentName() 
	{
		//Retrieve the student name
		String name = studentFirstName + " " + studentLastName;
		return name;
	}
	
	public static String getAdminName() 
	{
		//Retrieve the student name
		String name = adminFirstName + " " + adminLastName;
		return name;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public static int getStudentID() {
		return Integer.valueOf(studentID);
	}
	public static int getAdminID() {
		return Integer.valueOf(adminID);
	}


	
}
