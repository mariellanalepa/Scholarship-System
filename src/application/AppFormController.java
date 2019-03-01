package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class AppFormController implements Initializable
{	
	@FXML protected Label welcomeLabel;
	@FXML protected Label FNAME_FIELD, LNAME_FIELD, ID_FIELD, YEAR_FIELD, DEPT_FIELD, FACULTY_FIELD, GPA_FIELD, TYPE_FIELD;


	public static Scene getScene() throws Exception 
	{
		//getClass().getResource(path) loads resource from classpath
		FXMLLoader loader = new FXMLLoader(AppFormController.class.getResource("/view/ApplicationForm.fxml"));
		Parent root = (Parent) loader.load();
		Scene newScene = new Scene(root);
		return newScene;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//First get name and last name for student
		welcomeLabel.setText(welcomeLabel.getText() + " " + LoginController.getStudentName());
		FNAME_FIELD.setText(LoginController.studentFirstName);
		LNAME_FIELD.setText(LoginController.studentLastName);
		ID_FIELD.setText(LoginController.studentIDString);
		YEAR_FIELD.setText(LoginController.studentYearString);
		DEPT_FIELD.setText(LoginController.studentDept);
		FACULTY_FIELD.setText(LoginController.studentFaculty);
		GPA_FIELD.setText(LoginController.studentGPAString);
		TYPE_FIELD.setText(LoginController.studentType);

	}

}
