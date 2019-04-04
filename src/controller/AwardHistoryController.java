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

/**
 * Controller for a student to view their award history,
 * includes scholarships accepted, declined and applications submitted
 * @author luclegere
 *
 */
public class AwardHistoryController implements Initializable {
	private Main main;
	private Session session;
	private Student student;
	@FXML private TableColumn<Award,String> scholarshipIdCol, scholarshipNameCol, status;
	@FXML private TableView<Award> table;
	
	public AwardHistoryController(Main main, Session session) {
		this.main = main;
		this.session = session;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.student = (Student) this.session.getUser();	
		ObservableList<Award> data = FXCollections.observableArrayList(this.student.getAwards());	//get the award history of the student
		FilteredList<Award> filteredData = new FilteredList<>(data, p -> true);
		//set table with award history
		table.setItems(filteredData);
		scholarshipIdCol.setCellValueFactory(f->f.getValue().scholarshipIDProperty());	
		scholarshipNameCol.setCellValueFactory(f->f.getValue().scholarshipNameProperty());
		status.setCellValueFactory(f->f.getValue().statusProperty());

		
	}


}
