package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Session;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * Main for Scholarship Management System
 * @author Mariella, Natalie
 *
 */
public class Main extends Application {
	private Stage primaryStage;
	private Session session;  
	//Visual bounds determined from screen size
	private Rectangle2D visualBounds;
	
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
		//Session instance corresponding to this program run
		this.session = new Session();
		//Create controller factory that will be used to initialize all controllers with dependency
		//injection
		this.controllerFactory = new ControllerFactory(this, session);
		//Get visual bounds from screen size
		visualBounds = Screen.getPrimary().getVisualBounds();
		//visualBounds = new Rectangle2D(0, 0, 1400, 600);
		
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
		this.primaryStage.setScene(new Scene(root, visualBounds.getWidth(), visualBounds.getHeight()));
		this.primaryStage.show();
	}
	
	/**
	 * Method for injecting a Pane object into the center pane of a Scene object with root
	 * BorderPane. Used to keep base layout the same, but modify content of central pane.
	 * There is an internal call to Main.setScene(String url). 
	 * @param pane - Pane object that we wish to inject into the scene defined in the FXML
	 * file specified
	 * @param url - String specifying the URL of the FXML document corresponding to the BorderPane
	 * Scene into which we wish to inject
	 */
	public void injectPaneIntoScene(String paneUrl) throws Exception
	{
		//We make the assumption that this function will not be called unless 
		//the root is BorderPane
		BorderPane borderPane = (BorderPane) this.primaryStage.getScene().getRoot();
		//Create FXML Loader to get Pane to inject
		FXMLLoader loader = new FXMLLoader(Main.class.getResource(paneUrl));
		loader.setControllerFactory(controllerFactory);
		//Get node 'root' corresponding to FXML scene graph
		Pane pane = (Pane) loader.load();
		borderPane.setCenter(pane);
		
		
	}
	
	@Override
	/**
	 * The application stop method. Intercepts exit signal in order to save any changes to the database data
	 * 
	 */
	public void stop() {
		// check to see if user has logged in (ie. are there even any changes to save?)
		if(this.session.getUser() != null) {
			this.session.getDatabase().close();
		}
		System.out.println("Goodbye!\n");
	}
	
	
}
