package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {
	
	protected Parent root;
	@FXML 
	private Button signIn;
	
	@FXML
	protected void handleSignInButtonAction(ActionEvent event) throws Exception
	{	
		System.out.println("Got here");
		//getClass().getResource(path) loads resource from classpath
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ApplicationForm.fxml"));
		AnchorPane appForm = (AnchorPane) loader.load();
		Scene newScene = new Scene(appForm);

		//Get the primary stage of our App
		Stage stage = (Stage) signIn.getScene().getWindow();
		stage.setScene(newScene);			
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
	
}
