package controller;
import model.Session;
import model.ScholarshipFactory;
import model.Student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
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


public class StudentMainController implements Initializable {
	
	private Main main;
	private Session session;
	
	protected Parent root;
	@FXML private Button newApplicationButton, signOut, viewScholarshipButton, reviewApplicationButton;
	@FXML private Label welcomeLabel;
	private Student student;
	
	public StudentMainController(Main main, Session session)
	{
		this.main = main;
		this.session = session;
	}
	
	@FXML
	protected void handleNewApplicationButtonAction(ActionEvent event) throws Exception
	{	 
		/*//Get the primary stage of our App
		Stage stage = (Stage) newApplicationButton.getScene().getWindow();
		//Set new scene
		stage.setScene(ApplicationFormController.getScene());			
		stage.show();*/
		main.setScene("/view/ApplicationForm.fxml");
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
	protected void handleViewScholarshipButtonAction(ActionEvent event) throws Exception
	{
		/*Stage stage = (Stage) viewScholarshipButton.getScene().getWindow();
		stage.setScene(StudentScholarshipController.getScene());			
		stage.show();*/
		main.setScene("/view/StudentScholarship.fxml");
		
	}
	
	@FXML
	protected void handleReviewApplicationsButtonAction(ActionEvent event) throws Exception
	{
		/*Stage stage = (Stage) reviewApplicationButton.getScene().getWindow();
		stage.setScene(ApplicationHistoryController.getScene());			
		stage.show();*/
		main.setScene("/view/ApplicationHistory.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		welcomeLabel.setText(welcomeLabel.getText() + " " + session.getUser().getName());
	}
	
	public void setStudent(Student s) {
		this.student = s;
	}

}
