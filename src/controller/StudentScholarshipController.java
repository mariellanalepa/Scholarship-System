package controller;

import java.net.URL;
import java.util.ResourceBundle;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Session;
import model.Student;
import model.Scholarship;

public class StudentScholarshipController implements Initializable { 
	
	private Main main;
	private Session session;
	@FXML private Button startApplicationButton;
	@FXML private TableColumn<Scholarship,String> nameCol, donorCol, deadlineCol, facCol, deptCol, typeCol, yearCol;
	@FXML private TableColumn<Scholarship,Number> idCol, amtCol, numCol, gpaCol;
	@FXML private TableView<Scholarship> table;
	@FXML private TextField filterField;	
	
	public StudentScholarshipController(Main main, Session session) {
		this.main = main;
		this.session = session;
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//User will be student if they have access to the page (scene) to which this controller is bound
		Student student = (Student) session.getUser();
		
		ObservableList<Scholarship> data = FXCollections.observableArrayList(this.session.getDatabase().getScholarshipsById().values());
		
		//table.setItems(data);
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
		
		//Wrap observable list in filterable list for curation
		FilteredList<Scholarship> curatedData = new FilteredList<>(data, predicate -> true);	
		
		//Call for list to be curated
		ScholarshipCurator curator = new ScholarshipCurator(curatedData, student);
		
		//Wrap curated list in filterable list for search filtering
		FilteredList<Scholarship> filteredData = new FilteredList<>(curatedData, predicate -> true);
		
		//Add ChangeListener to filterField to see when its value changes
		//ChangeListener is notified whenever the value of filterField.textProperty() changes 	
		filterField.textProperty().addListener(new ScholarshipFilterListener(filteredData));
		
						
		//Now wrap the filtered list in a sorted list
		SortedList<Scholarship> sortedData = new SortedList<>(filteredData);
		//Bind the SortedList comparator to the TableView comparator
		//Comparator imposes total ordering on some collection of objects; 
		//denotes the order of the sorted list
		sortedData.comparatorProperty().bind(table.comparatorProperty());
				
		//Add sorted and/or filtered data to table
		table.setItems(sortedData);
	}
	
	@FXML 
	protected void handleStartApplicationButtonAction(ActionEvent event) throws Exception {
		//If there is a scholarship currently selected in the table
		//Should only ever be one selected
		if (table.getSelectionModel().getSelectedItem() != null)
		{
			Scholarship scholarship = table.getSelectionModel().getSelectedItem();
			session.setScholarshipSelection(scholarship);
			main.injectPaneIntoScene("/view/ApplicationForm.fxml");
		}
	}
}
