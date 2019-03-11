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
import javafx.stage.Stage;

public class AdminMainController implements Initializable {
	protected Parent root;
	@FXML private Button signOut, viewScholarshipsButton;
	@FXML private Label welcomeLabel;

	@FXML
	public static Scene getScene() throws Exception 
	{
		FXMLLoader loader = new FXMLLoader(AdminMainController.class.getResource("/view/AdminMain.fxml"));
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
	protected void handleScholarshipButtonAction(ActionEvent event) throws Exception
	{
		//Get the primary stage of our App
		Stage stage = (Stage) viewScholarshipsButton.getScene().getWindow();
		//Set new scene
		stage.setScene(AdminScholarshipController.getScene());			
		stage.show();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		welcomeLabel.setText(welcomeLabel.getText() + " " + LoginController.getAdminName());
	}


}
