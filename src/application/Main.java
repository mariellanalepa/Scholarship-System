package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.CsvReader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Student Scholarship Management System");
		
		//Set the scene to login UI 
		primaryStage.setScene(LoginController.getScene());
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void init() 
	{
		//Implement reading and initialization of Scholarships, Students in "database" here
		// Must create static (or otherwise make accessible) arrays for scholarships, students in "database"
		CsvReader c = new CsvReader();
		c.getApplicationData();
		c.getScholarshipData();
	}
	
	
}
