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


public class StudentMainController implements Initializable {
	protected Parent root;
	@FXML private Button newApplicationButton, signOut;
	@FXML private Label welcomeLabel;
	
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
		stage.setScene(AppFormController.getScene());			
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
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		welcomeLabel.setText(welcomeLabel.getText() + " " + LoginController.getStudentName());
	}

}
