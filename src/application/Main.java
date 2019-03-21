package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.CsvReader;
import model.Session;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	private Stage primaryStage;
	private Session session;  //Do fields of session need to be observable?
	/*Controller Factory for ensuring controllers is facilitate construction of controllers
	  which require as parameter SessionDataModel object*/ 
	private ControllerFactory controllerFactory;
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("University of Calgary Scholarship Management System");
		this.setScene("/view/Login.fxml");
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	/**
	 * The application initialization method. This is the first method called after construction of the 
	 * Application object, and precedes call to start(javafx.stage.Stage). Here we place any objects that
	 * must be initialized prior to application start. Note that we should not Scene or Stage objects here. 
	 */
	public void init() 
	{
		//Implement reading and initialization of Scholarships, Students in "database" here
		// Must create static (or otherwise make accessible) arrays for scholarships, students in "database"
		CsvReader c = new CsvReader();
		c.getApplicationData();
		c.getScholarshipData();
		this.session = new Session();
		this.controllerFactory = new ControllerFactory(this, session);
	}
	
	public void setScene(String url) throws Exception 
	{
		//Inflate FXML and instantiate a LoginController object
		//Main.class.getResource() indicates url is relative to path of this class
		FXMLLoader loader = new FXMLLoader(Main.class.getResource(url));
		loader.setControllerFactory(controllerFactory);
		//Get node 'root' corresponding to FXML scene graph
		Parent root = (Parent) loader.load();
		//Create scene from root node and set scene to login UI 
		this.primaryStage.setScene(new Scene(root));
		this.primaryStage.show();
	}
	
	
}
