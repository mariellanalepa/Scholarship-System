package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.ScholarshipFactory;
import model.Session;
import model.Student;
import model.Scholarship;

public class StudentScholarshipController implements Initializable { 
	
	private Main main;
	private Session session;
	
	protected Parent root;
	@FXML private Button signOut, mainMenuButton;
	@FXML private Label welcomeLabel;
	@FXML private TableColumn<Scholarship,String> nameCol, donorCol, deadlineCol, facCol, deptCol, typeCol, yearCol;
	@FXML private TableColumn<Scholarship,Number> idCol, amtCol, numCol, gpaCol;
	@FXML private TableView<Scholarship> table;
	
	public StudentScholarshipController(Main main, Session session) 
	{
		this.main = main;
		this.session = session;
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//User will be student if they have access to the page (scene) to which this controller is bound
		Student student = (Student) session.getUser();
		
		welcomeLabel.setText(welcomeLabel.getText() + " " + student.getName());
		
		ScholarshipFactory s = new ScholarshipFactory();
		
		ObservableList<Scholarship> data = FXCollections.observableArrayList(s.getScholarshipArray());
		FilteredList<Scholarship> filteredData = new FilteredList<>(data, p -> true);
		
		table.setItems(filteredData);
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
		//table.getColumns().setAll(idCol, nameCol, donorCol, deadlineCol,amtCol, numCol, facCol, deptCol, typeCol, gpaCol, yearCol);

		filteredData.setPredicate(scholarship -> {
			float studentGpa = Float.parseFloat(student.getStudentGPAString());
			float requiredGpa = scholarship.getGpa();
					
			if ((scholarship.getFaculty().contains(student.getStudentFaculty()) || scholarship.getFaculty().toLowerCase().contains("any"))
			&& (scholarship.getDepartment().contains(student.getStudentDepartment()) || scholarship.getDepartment().toLowerCase().contains("any"))
			/*&& (scholarship.getType().contains(LoginController.studentType) || scholarship.getType().toLowerCase().contains("any"))*/
			&& (scholarship.getYear().contains(student.getStudentYearString()) || scholarship.getYear().toLowerCase().contentEquals("any"))
			&& (studentGpa >= requiredGpa) && (scholarship.getStatus().toLowerCase().contains("open")) ) {
				return true;
			}
			else {
				return false;
			}
		});
	}
	
	@FXML
	protected void handleSignOutButtonAction(ActionEvent event) throws Exception {
		main.setScene("/view/Login.fxml");
	}
	
	@FXML 
	protected void handleMainMenuButtonAction(ActionEvent event) throws Exception {
		main.setScene("/view/StudentMain.fxml");
	}
}
