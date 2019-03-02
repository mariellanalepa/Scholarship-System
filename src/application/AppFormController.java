package application;

import java.net.URL;
import java.util.ResourceBundle;

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

public class AppFormController implements Initializable
{	
	@FXML protected Label welcomeLabel, FNAME_FIELD, LNAME_FIELD, ID_FIELD, YEAR_FIELD, DEPT_FIELD, FACULTY_FIELD, GPA_FIELD, TYPE_FIELD;
	@FXML protected Button signOut, confirmButton, submitButton; 
	@FXML protected ChoiceBox<String> scholarshipSelectDropDown; 
	
	
	//CSS styling
		String HOVERING_SIGNOUT_STYLE = "-fx-background-color: #cf0722; -fx-opacity: 70%; -fx-underline: true;";
		String NORMAL_SIGNOUT_STYLE = "-fx-background-color: #cf0722; -fx-text-fill: white;";

	public static Scene getScene() throws Exception 
	{
		//getClass().getResource(path) loads resource from classpath
		FXMLLoader loader = new FXMLLoader(AppFormController.class.getResource("/view/AppForm.fxml"));
		Parent root = (Parent) loader.load();
		Scene newScene = new Scene(root);
		return newScene;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//event styling - uses lambda expressions
		signOut.setOnMouseEntered(e -> signOut.setStyle(HOVERING_SIGNOUT_STYLE));
		signOut.setOnMouseExited(e -> signOut.setStyle(NORMAL_SIGNOUT_STYLE));
		
		scholarshipSelectDropDown.setItems(FXCollections.observableArrayList("Scholarship A", "Scholarship B", "Scholarship C"));
		
		

	}
	
	@FXML
	private void handleComboBoxAction() {
			// Get student data & fill forms after selection
			  	FNAME_FIELD.setText(LoginController.studentFirstName);
				LNAME_FIELD.setText(LoginController.studentLastName);
				ID_FIELD.setText(LoginController.studentIDString);
				YEAR_FIELD.setText(LoginController.studentYearString);
				DEPT_FIELD.setText(LoginController.studentDept);
				FACULTY_FIELD.setText(LoginController.studentFaculty);
				GPA_FIELD.setText(LoginController.studentGPAString);
				TYPE_FIELD.setText(LoginController.studentType);
		  
	}
	
	@FXML
	protected void handleSignOutButtonAction(ActionEvent event) throws Exception
	{
		//Get the primary stage of our App
		Stage stage = (Stage) signOut.getScene().getWindow();
		//Set new scene
		stage.setScene(LoginController.getScene());			
		stage.show();
	}

	protected void handleConfirmButtonAction(ActionEvent event) throws Exception
	{
		//Get the primary stage of our App
		Stage stage = (Stage) signOut.getScene().getWindow();
		//Set new scene
		stage.setScene(LoginController.getScene());			
		stage.show();
	}
}
