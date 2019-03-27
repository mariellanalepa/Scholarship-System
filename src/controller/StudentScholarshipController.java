package controller;

import java.net.URL;
import java.util.ResourceBundle;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.ScholarshipFactory;
import model.Session;
import model.Student;
import model.Scholarship;

public class StudentScholarshipController implements Initializable { 
	
	private Main main;
	private Session session;
	@FXML private Button signOut, mainMenuButton, startApplicationButton;
	@FXML private Label welcomeLabel;
	@FXML private TableColumn<Scholarship,String> nameCol, donorCol, deadlineCol, facCol, deptCol, typeCol, yearCol;
	@FXML private TableColumn<Scholarship,Number> idCol, amtCol, numCol, gpaCol;
	@FXML private TableView<Scholarship> table;
	
	public StudentScholarshipController(Main main, Session session) {
		this.main = main;
		this.session = session;
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//User will be student if they have access to the page (scene) to which this controller is bound
		Student student = (Student) session.getUser();
		
		welcomeLabel.setText(welcomeLabel.getText() + " " + session.getUser().getName());
		
		//ScholarshipFactory s = new ScholarshipFactory(session.getUser().getID());
		
		ObservableList<Scholarship> data = FXCollections.observableArrayList(this.session.getDatabase().getScholarships().values());
		
		table.setItems(data);
		idCol.setCellValueFactory(f->f.getValue().idProperty());
		nameCol.setCellValueFactory(f->f.getValue().nameProperty());
		donorCol.setCellValueFactory(f->f.getValue().donorProperty());
		deadlineCol.setCellValueFactory(f->f.getValue().deadlineProperty());
		amtCol.setCellValueFactory(f->f.getValue().amountProperty());
		numCol.setCellValueFactory(f->f.getValue().numberProperty());
		facCol.setCellValueFactory(f->f.getValue().facultyProperty());
		deptCol.setCellValueFactory(f->f.getValue().departmentProperty());
		typeCol.setCellValueFactory(f->f.getValue().typeProperty());
		gpaCol.setCellValueFactory(f->f.getValue().gpaProperty());
		yearCol.setCellValueFactory(f->f.getValue().yearProperty());
		table.getColumns().setAll(idCol, nameCol, donorCol, deadlineCol,amtCol, numCol, facCol, deptCol, typeCol, gpaCol, yearCol);		
	}
	
	@FXML
	protected void handleSignOutButtonAction(ActionEvent event) throws Exception {
		main.setScene("/view/Login.fxml");
	}
	
	@FXML 
	protected void handleMainMenuButtonAction(ActionEvent event) throws Exception {
		main.setScene("/view/StudentMain.fxml");
	}
	
	@FXML 
	protected void handleStartApplicationButtonAction(ActionEvent event) throws Exception {
		//If there is a scholarship currently selected in the table
		//Should only ever be one selected
		if (table.getSelectionModel().getSelectedItem() != null)
		{
			Scholarship scholarship = table.getSelectionModel().getSelectedItem();
			session.setScholarshipSelection(scholarship);
			main.setScene("/view/ApplicationForm.fxml");
		}
	}
}
