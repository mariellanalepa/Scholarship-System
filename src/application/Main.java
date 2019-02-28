package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
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
	
	/*private void initLoginLayout() 
	{
		LoginController controller = new LoginController();
		
		//Inflate FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginLayout.fxml"));
		loader.setController(controller);
		try {
			controller.root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Create scene from scene graph node
		Scene scene = new Scene((Parent) controller.root);
		
		
		try 
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/LoginLayout.fxml"));
			VBox rootLayout = (VBox) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}*/
}
