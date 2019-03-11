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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Scholarship;

public class StudentScholarshipController implements Initializable { 
	protected Parent root;
	@FXML private Button newApplicationButton, signOut, viewScholarshipButton;
	@FXML private Label welcomeLabel;
	@FXML private TableColumn<Scholarship, String> donorCol, deadlineColumn, amtCol, numCol, facCol, deptCol, typeCol, gpaCol, yearCol;
	@FXML private TableView table = new TableView();

	
	public static Scene getScene() throws Exception 
	{
		//getClass().getResource(path) loads resource from classpath
		FXMLLoader loader = new FXMLLoader(ApplicationFormController.class.getResource("/view/StudentScholarship.fxml"));
		Parent root = (Parent) loader.load();
		Scene newScene = new Scene(root);
		return newScene;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		welcomeLabel.setText(welcomeLabel.getText() + " " + LoginController.getStudentName());
		Scholarship s = new Scholarship();
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
}
