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
import model.Scholarship;
/**
 * Controller class for an admin to create a new scholarship 
 * and add it to the system
 *
 */
public class AdminScholarshipController implements Initializable { 
	
	private Main main;
	private Session session;
	@FXML private TableColumn<Scholarship,String> nameCol, donorCol, deadlineCol, facCol, deptCol, typeCol, yearCol;
	@FXML private TableColumn<Scholarship,Number> idCol, amtCol, numCol, gpaCol;
	@FXML private TableView<Scholarship> table;
	@FXML private TextField filterField;	
	
	
	public AdminScholarshipController(Main main, Session session) {
		this.main = main;
		this.session = session;
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
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

		//Wrap observable list in filterable list
		FilteredList<Scholarship> filteredData = new FilteredList<>(data, predicate -> true);
		
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

}
