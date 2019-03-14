package application;
import model.Application;
import model.ApplicationFactory;
import model.LoginSession;
import model.Scholarship;
import model.ScholarshipFactory;
import model.Student;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.LoginSession;


public class StudentApplicationController implements Initializable {
	protected Parent root;
	@FXML private Button signOut, mainMenuButton;
	@FXML private Label welcomeLabel;
	@FXML private TableColumn<Application,String> applicationIdCol, scholarshipIdCol, scholarshipNameCol, dateStartedCol, deadlineCol, status;
	@FXML private TableView<Application> table;
	


	
	public static Scene getScene() throws Exception 
	{
		FXMLLoader loader = new FXMLLoader(StudentApplicationController.class.getResource("/view/ApplicationHistory.fxml"));
		Parent root = (Parent) loader.load();
		Scene newScene = new Scene(root);
		return newScene;
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
	protected void handleMainMenuButtonAction(ActionEvent event) throws Exception{
		Stage stage = (Stage) mainMenuButton.getScene().getWindow();
		stage.setScene(StudentMainController.getScene());			
		stage.show();
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		welcomeLabel.setText(welcomeLabel.getText() + " " + LoginController.getStudentName());
		String usr = LoginController.getUsername();
		ApplicationFactory af = new ApplicationFactory();
		ObservableList<Application> data = FXCollections.observableArrayList(af.getApplicationArray());

		table.setItems(data);
		applicationIdCol.setCellValueFactory(f->f.getValue().applicationIDProperty());
		scholarshipIdCol.setCellValueFactory(f->f.getValue().studentIdProperty());
		scholarshipNameCol.setCellValueFactory(f->f.getValue().scholarshipIdProperty());
		deadlineCol.setCellValueFactory(f->f.getValue().dateSubmittedProperty());
		status.setCellValueFactory(f->f.getValue().statusProperty());
		table.getColumns().setAll(applicationIdCol, scholarshipIdCol, scholarshipNameCol, deadlineCol, status);
	}

}
