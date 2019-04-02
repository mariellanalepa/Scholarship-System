package controller;
import model.Application;
import model.Scholarship;
import model.Session;
import model.Student;

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


public class ApplicationHistoryController implements Initializable {
	
	private Main main;
	private Session session;
	private Student student;
	@FXML private Button submitApplicationButton;
	@FXML private TableColumn<Application,String> applicationIdCol, scholarshipIdCol, scholarshipNameCol, dateSubmittedCol, deadlineCol, status;
	@FXML private TableView<Application> table;
	@FXML protected Label confirmationLabel;
	
	
	public ApplicationHistoryController(Main main, Session session) {
		this.main = main;
		this.session = session;
	}
	
	@FXML
	public void handleSubmitApplicationButtonAction() {
		
		//If an application has been selected from the table
		if (table.getSelectionModel().getSelectedItem() != null)
		{
			//Retrieve the object corresponding to the application
			Application application = table.getSelectionModel().getSelectedItem();
			//If its status is "saved", but not yet "submitted"
			if (application.getStatus().equals("saved"))
			{
				application.setStatus("submitted");
				confirmationLabel.setVisible(true);
			}
			
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//Get the Student corresponding to this Session instance
		//i.e., logged in User
		this.student = (Student) this.session.getUser();
		
		ObservableList<Application> data = FXCollections.observableArrayList(this.student.getApplications());

		table.setItems(data);
		applicationIdCol.setCellValueFactory(f->f.getValue().applicationIDProperty());
		//scholarshipIdCol.setCellValueFactory(f->f.getValue().studentIdProperty());
		scholarshipNameCol.setCellValueFactory(f->f.getValue().scholarshipNameProperty());
		deadlineCol.setCellValueFactory(f->f.getValue().scholarshipDeadlineProperty());
		status.setCellValueFactory(f->f.getValue().statusProperty());
		table.getColumns().setAll(applicationIdCol, scholarshipNameCol, deadlineCol, status);
		
		//Add listener to table selection
		table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (!newSelection.equals(oldSelection)) {
		        confirmationLabel.setVisible(false);
		    }
		});
	}

}
