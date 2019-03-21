package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.ScholarshipFactory;
import model.Session;
import model.Scholarship;

public class AdminScholarshipController implements Initializable { 
	
	private Main main;
	private Session session;
	@FXML private Button signOut, mainMenuButton;
	@FXML private Label welcomeLabel;
	@FXML private TableColumn<Scholarship,String> nameCol, donorCol, deadlineCol, facCol, deptCol, typeCol, yearCol;
	@FXML private TableColumn<Scholarship,Number> idCol, amtCol, numCol, gpaCol;
	@FXML private TableView<Scholarship> table;
	@FXML private TextField filter;	
	
	
	public AdminScholarshipController(Main main, Session session) {
		this.main = main;
		this.session = session;
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		welcomeLabel.setText(welcomeLabel.getText() + " " + session.getUser().getName());
		
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

		filter.textProperty().addListener((observable, oldFilter, newFilter) -> {
			filteredData.setPredicate(scholarship -> {
				if (newFilter == oldFilter) {
					return true;
				}
				String lowerCaseFilter = newFilter.toString().toLowerCase();
				
				if (scholarship.getId().toString().toLowerCase().contains(lowerCaseFilter) ||
					scholarship.getName().toLowerCase().contains(lowerCaseFilter) ||
					scholarship.getDonor().toLowerCase().contains(lowerCaseFilter) ||
					scholarship.getDeadline().toLowerCase().contains(lowerCaseFilter) ||
					scholarship.getAmount().toString().toLowerCase().contains(lowerCaseFilter) ||
					scholarship.getFaculty().toLowerCase().contains(lowerCaseFilter) ||
					scholarship.getDepartment().toLowerCase().contains(lowerCaseFilter) ||
					scholarship.getType().toLowerCase().contains(lowerCaseFilter) ||
					scholarship.getGpa().toString().toLowerCase().contains(lowerCaseFilter) ||
					scholarship.getYear().toLowerCase().contains(lowerCaseFilter) ||
					scholarship.getStatus().toLowerCase().contains(lowerCaseFilter) ||
					scholarship.getPosted().toLowerCase().contains(lowerCaseFilter) ||
					scholarship.getNumber().toString().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}
				return false;
			});
		});
	
	}
	
	
	@FXML
	protected void handleSignOutButtonAction(ActionEvent event) throws Exception {
		main.setScene("/view/Login.fxml");
	}
	
	@FXML 
	protected void handleMainMenuButtonAction(ActionEvent event) throws Exception {	
		main.setScene("/view/AdminMain.fxml");
	}
}
