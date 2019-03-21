package application;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Scholarship;
import model.ScholarshipFactory;
import model.Session;

public class AdminMainController implements Initializable {
	
	private Main main;
	private Session session;
	
	protected Parent root;
	@FXML private Button signOut, viewScholarshipsButton, createScholarship, deleteButton, editScholarship;
	@FXML private Label welcomeLabel;

	public AdminMainController(Main main, Session session)
	{
		this.main = main;
		this.session = session;
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
	protected void handleScholarshipButtonAction(ActionEvent event) throws Exception
	{
		/*//Get the primary stage of our App
		Stage stage = (Stage) viewScholarshipsButton.getScene().getWindow();
		//Set new scene
		stage.setScene(AdminScholarshipController.getScene());			
		stage.show();*/
		main.setScene("/view/AdminScholarship.fxml");
	}
	@FXML
	protected void handleCreateScholarshipButtonAction(ActionEvent event) throws Exception 
	{
		/*Stage stage = (Stage) createScholarship.getScene().getWindow();
		stage.setScene(AdminFormController.getScene());
		stage.show();*/
		main.setScene("/view/AddScholarship.fxml");
	}

	@FXML
	protected void handleDeleteButtonAction(ActionEvent event) throws Exception 
	{
		/*Stage stage = (Stage) deleteButton.getScene().getWindow();
		stage.setScene(DeleteScholarshipController.getScene());
		stage.show();*/
		main.setScene("/view/DeleteScholarship.fxml");
	}
	
	@FXML
	protected void handleEditScholarshipAction(ActionEvent event) throws Exception 
	{
		/*Stage stage = (Stage) editScholarship.getScene().getWindow();
		stage.setScene(AdminEditController.getScene());
		stage.show();*/
		main.setScene("/view/EditScholarship.fxml");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		welcomeLabel.setText(welcomeLabel.getText() + " " + LoginController.getAdminName());
	
	}

	

}
