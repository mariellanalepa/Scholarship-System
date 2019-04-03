package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Application;
import model.Award;
import model.Scholarship;
import model.Session;
import model.Student;

public class AwardHistoryController implements Initializable {
	private Main main;
	private Session session;
	private Student student;
	@FXML private TableColumn<Award,String> applicationIdCol, scholarshipIdCol, scholarshipNameCol, status;
	@FXML private TableView<Award> table;
	
	public AwardHistoryController(Main main, Session session) {
		this.main = main;
		this.session = session;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.student = (Student) this.session.getUser();
		ObservableList<Award> data = FXCollections.observableArrayList(this.student.getAwards());
		FilteredList<Award> filteredData = new FilteredList<>(data, p -> true);
		table.setItems(filteredData);
		//applicationIdCol.setCellValueFactory(f->f.getValue().getA());
		scholarshipIdCol.setCellValueFactory(f->f.getValue().scholarshipIDProperty());
		scholarshipNameCol.setCellValueFactory(f->f.getValue().scholarshipNameProperty());
		status.setCellValueFactory(f->f.getValue().statusProperty());

		
		
	}
	/*
	 *  csv to hold the award history, along with award objects that get initialized on startup and added to each student object. 
	 *  You can access that award list to display the award history,
	 *   it gives you the scholarship ID of awards that student has won.
	 */
	
	
	 
	

}
