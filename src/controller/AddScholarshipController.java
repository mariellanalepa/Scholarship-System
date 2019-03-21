package controller;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Scholarship;
import model.ScholarshipFactory;
import model.Session;

public class AddScholarshipController implements Initializable
{
	private Main main;
	private Session session;
	@FXML private Button signOut, submitButton, mainMenuButton;
	@FXML private TextField deadlineBox, yearBox, donorBox, nameBox, numberBox, amountBox, GPABox, typeBox, departmentBox, facultyBox; 
	@FXML private Label welcomeLabel, deadlineLabel, yearLabel, donorLabel, nameLabel, amountLabel, numberLabel, GPALabel, typeLabel, departmentLabel, facultyLabel;


	public AddScholarshipController(Main main, Session session) {
		this.main = main;
		this.session = session;
	}
	
	
	@FXML
	protected void handleSignOutButtonAction(ActionEvent event) throws Exception {
		main.setScene("/view/Login.fxml");
	}
	
	@FXML 
	protected void handleMainMenuButtonAction(ActionEvent event) throws Exception {
		main.setScene("/view/AdminMain.fxml");	
	}
	
	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) throws Exception
	{
		String[] scholarshipData = new String[13];
		ScholarshipFactory s = new ScholarshipFactory();
		
		scholarshipData[0] = Integer.toString(1 + s.getScholarshipListLength());
		scholarshipData[1] = nameBox.getText();
		scholarshipData[2] = donorBox.getText();
		scholarshipData[3] = deadlineBox.getText();
		scholarshipData[4] = amountBox.getText();
		scholarshipData[5] = numberBox.getText();
		scholarshipData[6] = facultyBox.getText();
		scholarshipData[7] = departmentBox.getText();
		scholarshipData[8] = typeBox.getText();
		scholarshipData[9] = GPABox.getText();
		scholarshipData[10] = yearBox.getText();
		scholarshipData[11] = "Open";		
		scholarshipData[12] = dateTimeFormat(LocalDateTime.now());
		
		Scholarship scholarship = new Scholarship(scholarshipData);
		scholarship.saveScholarship(scholarshipData);
		//Set scene to Admin Main Page
		main.setScene("/view/AdminMain.fxml");
	}
	
	
	public String dateTimeFormat(LocalDateTime time) {
		String dateTime = time.toString();
		String year = dateTime.substring(0, 4)+ " ";
		String month = dateTime.substring(5, 7) + "/";
		String day = dateTime.substring(8, 10) + "/";
		String hms = dateTime.substring(11, 19);
		String dateTimeString = year + month + day + hms;
		return dateTimeString;
				
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	
	//	signOut.setOnMouseEntered(e -> signOut.setStyle(HOVERING_SIGNOUT_STYLE));
	//	signOut.setOnMouseExited(e -> signOut.setStyle(NORMAL_SIGNOUT_STYLE));
		
		
		welcomeLabel.setText(welcomeLabel.getText() + " " + session.getUser().getName());
	}
	
}




