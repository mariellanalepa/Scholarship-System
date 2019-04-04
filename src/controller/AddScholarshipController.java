package controller;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import application.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Scholarship;
import model.Session;
/**
 * Controller class for an admin to add a scholarship to the system
 * @author luclegere
 *
 */
public class AddScholarshipController implements Initializable
{
	private Main main;
	private Session session;
	@FXML private DatePicker deadlinePicker;
	@FXML private TextField yearBox, donorBox, nameBox, numberBox, amountBox, GPABox, typeBox, departmentBox, facultyBox; 
	@FXML private Label welcomeLabel, errorLabel, deadlineLabel, yearLabel, donorLabel, nameLabel, amountLabel, numberLabel, GPALabel, typeLabel, departmentLabel, facultyLabel;
	@FXML private ChoiceBox<String> facDrop, stuDrop, depDrop;
	private boolean empty = false;			//to detect if at least one box is empty when submit is pressed


	public AddScholarshipController(Main main, Session session) {
		this.main = main;
		this.session = session;
	}
	
	/**
	 * extract input from form when submit button is pressed
	 * error checking for empty values
	 * @param event Submit Button pressed
	 * @throws Exception	
	 */
	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) throws Exception
	{
		String[] scholarshipData = new String[13];
		
		empty = false;
		
		//Formatter for date and time string: dd-MM-yyyy HH:mm:ss
		//To be used to format date entered in DatePicker 
		DateTimeFormatter deadlineFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		
		//Create scholarship ID based on how many items are presently in Scholarships
		scholarshipData[0] = Integer.toString(this.session.getDatabase().getScholarshipIdCounter());

		/*
		 * Check if any of the boxes are empty while extracting the data from them
		 */
		if (!nameBox.getText().isEmpty()) { scholarshipData[1] = nameBox.getText();}
		else { empty = true;}
		if (!donorBox.getText().isEmpty()) { scholarshipData[2] = donorBox.getText(); }
		else { 			empty = true; }
		//Make every deadline at 11:59 PM of the day
		if (deadlinePicker.getValue() != null) { scholarshipData[3] = deadlinePicker.getValue().format(deadlineFormatter) + " 23:59"; }
		else { 	empty = true;	}
		if (!amountBox.getText().isEmpty()) { scholarshipData[4] = amountBox.getText(); }
		else { 			empty = true;}
		if (!numberBox.getText().isEmpty()) { scholarshipData[5] = numberBox.getText(); }
		else {			empty = true; }
		if (!facDrop.getValue().isEmpty()) { scholarshipData[6] =  facDrop.getValue(); }
		else { 			empty = true;	}	
		if (!depDrop.getValue().isEmpty()) { scholarshipData[7] = depDrop.getValue(); }
		else { 			empty = true; }
		if (!stuDrop.getValue().isEmpty()) { scholarshipData[8] = stuDrop.getValue(); }
		else { 			empty = true; }
		if (!GPABox.getText().isEmpty()) { scholarshipData[9] = GPABox.getText(); }
		else {			empty = true; }
		if (!yearBox.getText().isEmpty()) { scholarshipData[10] = yearBox.getText();}
		else {			empty = true; }
		System.out.println(empty);
		scholarshipData[11] = "Open";
		
		//Get current system time, and format to follow yyyy-MM-dd HH:mm:ss format
		DateTimeFormatter postedFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		scholarshipData[12] = LocalDateTime.now().format(postedFormatter);
		
		if (empty == false) {	//there are no empty fields
			Scholarship scholarship = new Scholarship(this.session.getDatabase(),scholarshipData);
			//Add scholarship to database
			this.session.getDatabase().addScholarship(scholarship);
			
			//Return to Admin "Main Page", i.e.,
			//Inject new Pane into Scene 
			main.injectPaneIntoScene("/view/AdminWelcomeText.fxml");
		}
		else {
			errorLabel.setText("Error! Not all boxes contain a value");
		}
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
	//	signOut.setOnMouseEntered(e -> signOut.setStyle(HOVERING_SIGNOUT_STYLE));
	//	signOut.setOnMouseExited(e -> signOut.setStyle(NORMAL_SIGNOUT_STYLE));
		
		//initialize drop downs with values
		facDrop.setItems(FXCollections.observableArrayList("ANY", "SC", "H", "AR", "EN", "ED"));	
		facDrop.setValue("ANY");
		depDrop.setItems(FXCollections.observableArrayList("ANY"));
		depDrop.setValue("ANY");
		stuDrop.setItems(FXCollections.observableArrayList("ANY", "UGRAD", "GRAD", "CS"));
		stuDrop.setValue("ANY");
		
		//listener for facDrop to detect change; so depDrop changes based on the faculty
		facDrop.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) ->
		{
				if (facDrop.getValue() == "H") {
					depDrop.setItems(FXCollections.observableArrayList("ANY", "ACCT", "FNCE", "HR", "ENTI", "MKTG"));           
				}
				else if (facDrop.getValue() == "SC") {
					depDrop.setItems(FXCollections.observableArrayList("ANY", "CPSC", "BIOL", "CHEM", "PHYS", "MATH", "GEOL"));
				}		
				else if (facDrop.getValue() == "AR") {
					depDrop.setItems(FXCollections.observableArrayList("ANY", "ECON", "ENGL", "PSYC", "PHIL"));
				}
				else if (facDrop.getValue() == "EN") {
					depDrop.setItems(FXCollections.observableArrayList("ANY", "CIVL", "MECH", "SENG", "CHEM"));
				}
				else if (facDrop.getValue() == "ED") {
					depDrop.setItems(FXCollections.observableArrayList("ANY"));
				}
				else if (facDrop.getValue() == "ANY") {
					depDrop.setItems(FXCollections.observableArrayList("ANY"));
				}
		});
	}
	
}

