package application;

import model.LoginSession;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {
	
	protected Parent root;
	@FXML 
	private Button signIn;
	@FXML
	private TextField studentID;
	
	//Store for static access
	public static String studentIDString;
	public static String studentName;
	
	@FXML
	protected void handleSignInButtonAction(ActionEvent event) throws Exception
	{	 
		//Get student ID
		studentIDString = studentID.getText();
		// Create new login session
		LoginSession login = new LoginSession(Integer.valueOf(studentIDString));
		studentName = login.getStudent().getFirstName();
		
		//Get the primary stage of our App
		Stage stage = (Stage) signIn.getScene().getWindow();
		//Set new scene
		stage.setScene(AppFormController.getScene());			
		stage.show();
	}
	
	public static Scene getScene() throws Exception
	{	
		//Inflate FXML and instantiate a LoginController object
		FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("/view/LoginLayout.fxml"));
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
		//Retrieve the student number
		return studentName;
	}

	
}
