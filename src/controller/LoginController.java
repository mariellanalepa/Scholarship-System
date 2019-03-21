package controller;

import model.Admin;
import model.InvalidUserException;
import model.Session;
import model.Student;
import java.net.URL;
import java.util.ResourceBundle;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	
	private Main main;
	private Session session;
	protected static String invalidUname = "Invalid username or password, please try again";
	@FXML private Button signIn;
	@FXML private TextField usernameField;
	@FXML private Label errorLabel;
	
	
	public LoginController(Main main, Session session) {
		this.main = main;
		this.session = session;	
	}
	
	@FXML
	protected void handleSignInButtonAction(ActionEvent event) throws Exception
	{	 
		//Get student ID from text field
		String userName = usernameField.getText().toLowerCase();
	
		/* Call login from session
		 * try to create new student, if input is not valid (integer) 
		 * or student ID not in database, login will throw InvalidUserException
		 */
		try {
			session.login(userName);

		} catch(InvalidUserException notValidUser) {
			errorLabel.setText(invalidUname);
			System.out.println(notValidUser);
			// Reset stage
			Stage stage = (Stage) errorLabel.getScene().getWindow();		
			stage.show();
			return;
		} 

			if (session.getUser() instanceof Admin) {
				//Set scene to Admin Main Page
				main.setScene("/view/AdminMain.fxml");
				
			} else if (session.getUser() instanceof Student) {
				//Set scene to Student Main Page
				main.setScene("/view/StudentMain.fxml");
			}		
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
