package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Application;
import model.Scholarship;
import model.Session;
import model.Student;
/**
 * Class to view top applicants to a scholarship
 * Note: Names are still not working as of Mar.28/2019
 * @author luclegere
 *
 */
public class AdminRecipientController implements Initializable {

	private Main main;
	private Session session;
	@FXML private Button signOut, mainMenuButton;
	@FXML private Label welcomeLabel;
	@FXML private ChoiceBox<String> scholDrop;
	@FXML private TableColumn<Student, String> GPACol, stuNameCol, stuIDCol;
	@FXML private TableView<Student> table;
	ArrayList<String> nameArray = new ArrayList<String>();

	
	public AdminRecipientController(Main main, Session session) {
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		//write scholarships to the dropdown menu 
		for (Scholarship scholarship : this.session.getDatabase().getScholarshipsById().values())
		{
			nameArray.add(scholarship.getName());
			scholDrop.setItems(FXCollections.observableArrayList(nameArray));
		}

		//listener to detect change in dropdown menu and add applications to table accordingly
		scholDrop.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) ->			{
		String scholarshipName = scholDrop.getValue().toString();	
		Scholarship current = this.session.getDatabase().getScholarshipsByName().get(scholarshipName);
		ArrayList<Student> stuList = new ArrayList<Student>();

			
		
			for (Student student : current.getTopCandidates()) {
				if (student != null) stuList.add(student);
			}
		
			ObservableList<Student> data = FXCollections.observableArrayList(stuList);
			table.setItems(data);
			stuIDCol.setCellValueFactory(f->f.getValue().studentIDProperty()); 
			GPACol.setCellValueFactory(f->f.getValue().GPAProperty());	
		//	stuNameCol.setCellValueFactory(f->f.getValue().nameProperty());	
								
		});
		
	
	}
	
	
	
	
	
}
