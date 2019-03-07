package application;

import model.LoginSession;
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
import model.LoginSession;

public class LoginController implements Initializable {
	
	protected Parent root;
	@FXML private Button signIn;
	@FXML private TextField idNumber;
	@FXML private Label errorLabel;
	
	//Store for static access
	static String studentIDString, adminIDString;
	static String studentFirstName, studentLastName, studentDept, studentFaculty, studentYearString, studentGPAString, studentType;
	static String adminFirstName, adminLastName;
	protected static String loginErrorMsg = "ERROR: invalid student ID number";
	
	@FXML
	protected void handleSignInButtonAction(ActionEvent event) throws Exception
	{	 
		//Get student ID from text field
		studentIDString = idNumber.getText();
		
		/* check string format: length */
		if(studentIDString.length() != 8) {
			errorLabel.setText(loginErrorMsg);
			return;
		} 
		
		
		/* Create new login session
		 * try to create new student, if input is not valid (integer) 
		 * or student ID not in database
		 * LoginSession instantiation will fail
		 */
		LoginSession login = null;
		try {
			login = new LoginSession(Integer.valueOf(studentIDString));
			
		} catch(NullPointerException | NumberFormatException invalidID){
			errorLabel.setText(loginErrorMsg);
				
			// Reset stage
			Stage stage = (Stage) errorLabel.getScene().getWindow();
			stage.setScene(LoginController.getScene());			
			stage.show();
			} 
		
		// Get student attributes and assign to the login session
		studentFirstName = login.getStudent().getFirstName();
		studentLastName = login.getStudent().getLastName();
		studentYearString = login.getStudent().getStudentYear();
		studentDept = login.getStudent().getStudentDepartment();
		studentFaculty = login.getStudent().getStudentFaculty();
		studentGPAString = login.getStudent().getStudentGPA();
		studentType = login.getStudent().getStudentType();
		
		//Get the primary stage of our App
		Stage stage = (Stage) signIn.getScene().getWindow();
		//Set new scene
		stage.setScene(StudentMainController.getScene());			
		stage.show();
	}
	
	public static Scene getScene() throws Exception
	{	
		//Inflate FXML and instantiate a LoginController object
		FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("/view/Login.fxml"));
		Parent root = (Parent) loader.load();
		//Create and return scene for root node
		return new Scene(root);
	}
	
	public static String getStudentID() 
	{
		//Retrieve the student number
		return studentIDString;
	}
	
	public static String getStudentName() 
	{
		//Retrieve the student name
		String name = studentFirstName + " " + studentLastName;
		return name;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}

	
}
