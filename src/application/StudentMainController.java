package application;
import model.LoginSession;
import model.ScholarshipFactory;
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


public class StudentMainController implements Initializable {
	protected Parent root;
	@FXML private Button newApplicationButton, signOut, viewScholarshipButton, reviewApplicationButton;
	@FXML private Label welcomeLabel;
	private Student student;

	
	public static Scene getScene() throws Exception 
	{
		FXMLLoader loader = new FXMLLoader(StudentMainController.class.getResource("/view/StudentMain.fxml"));
		Parent root = (Parent) loader.load();
		Scene newScene = new Scene(root);
		return newScene;
	}
	
	@FXML
	protected void handleNewApplicationButtonAction(ActionEvent event) throws Exception
	{	 
		//Get the primary stage of our App
		Stage stage = (Stage) newApplicationButton.getScene().getWindow();
		//Set new scene
		stage.setScene(ApplicationFormController.getScene());			
		stage.show();
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
	@FXML
	protected void handleViewScholarshipButtonAction(ActionEvent event) throws Exception
	{
		Stage stage = (Stage) viewScholarshipButton.getScene().getWindow();
		stage.setScene(StudentScholarshipController.getScene());			
		stage.show();
		
	}
	
	@FXML
	protected void handleReviewApplicationsButtonAction(ActionEvent event) throws Exception
	{
		Stage stage = (Stage) reviewApplicationButton.getScene().getWindow();
		stage.setScene(StudentApplicationController.getScene());			
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		welcomeLabel.setText(welcomeLabel.getText() + " " + LoginController.getStudentName());
	}
	
	public void setStudent(Student s) {
		this.student = s;
	}

}
