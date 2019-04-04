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
/**
 * Controller class for Login page. Gets the user input, 
 * performs user authentication, and initializes the appropriate user
 * @author Natalie
 *
 */
public class LoginController implements Initializable {
	private Main main;
	private Session session;
	protected static String invalidLogin = "Invalid username or password, please try again";
	@FXML private Button signIn;
	@FXML private TextField usernameField, passwordField;
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
		String password = passwordField.getText();
	
		/* Call login from session
		 * try to create new student, if input is not valid (integer) 
		 * or student ID not in database, login will throw InvalidUserException
		 */
		try {
			session.login(userName, password);

		} catch(InvalidUserException notValidUser) {
			errorLabel.setText(invalidLogin);
			
			// Reset stage
			Stage stage = (Stage) errorLabel.getScene().getWindow();		
			stage.show();
			return;
		} 
			System.out.println((session.getUser()));
			if (session.getUser() instanceof Admin) {
				//Set scene to Admin Main Page
				main.setScene("/view/AdminMain.fxml");
				//Inject pane into center of BorderPane that is root of Scene
				main.injectPaneIntoScene("/view/AdminWelcomeText.fxml");
				
			} else if (session.getUser() instanceof Student) {
				//Set scene to Student Main Page
				main.setScene("/view/StudentMain.fxml");
				//Inject pane into center of BorderPane that is root of Scene
				main.injectPaneIntoScene("/view/StudentWelcomeMessage.fxml");
				
			}		
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Bind the Enter key to the signIn button
		signIn.setDefaultButton(true);
	}

}
