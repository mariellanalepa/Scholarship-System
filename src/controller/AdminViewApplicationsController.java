package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

public class AdminViewApplicationsController implements Initializable {

	private Main main;
	private Session session;
	@FXML private Button viewScholarshipsButton, createScholarship, deleteButton, editScholarship;
	@FXML private ChoiceBox<String> scholDrop;
	@FXML private TableColumn<Application, String> stuIDCol, appIDCol, dateCol;
	@FXML private TableView<Application> table;
	ArrayList<String> nameArray = new ArrayList<String>();	
	
	public AdminViewApplicationsController(Main main, Session session)
	{
		this.main = main;
		this.session = session;
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
		scholDrop.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) ->
		{
			String scholarshipName = scholDrop.getValue().toString();	
			Scholarship current = this.session.getDatabase().getScholarshipsByName().get(scholarshipName);
			ObservableList<Application> data = FXCollections.observableArrayList(current.getApplications());
			FilteredList<Application> filteredData = new FilteredList<>(data, p -> true);
			table.setItems(filteredData);
			stuIDCol.setCellValueFactory(f->f.getValue().studentIdProperty()); 
			appIDCol.setCellValueFactory(f->f.getValue().applicationIDProperty());
			dateCol.setCellValueFactory(f->f.getValue().dateSubmittedProperty());		
			
		});
	
	}	
	
}
