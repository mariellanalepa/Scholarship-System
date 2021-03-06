package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Offer;
import model.Scholarship;
import model.Session;
import model.Student;
/**
 * Class to view recipients of a scholarship
 * @author luclegere
 *
 */
public class AdminRecipientController implements Initializable {

	@SuppressWarnings("unused")
	private Main main;
	private Session session;
	@FXML private ChoiceBox<String> scholDrop;
	@FXML private TableColumn<Student, String> GPACol, nameCol, stuIDCol;
	@FXML private TableView<Student> table;
	ArrayList<String> nameArray = new ArrayList<String>();	//array of scholarship names

	
	public AdminRecipientController(Main main, Session session) {
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
		scholDrop.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) ->			{
		String scholarshipName = scholDrop.getValue().toString();	
		Scholarship current = this.session.getDatabase().getScholarshipsByName().get(scholarshipName);	
		ArrayList<Student> stuList = new ArrayList<Student>();	//list to hold top candidates for scholarship
			//add all top candidates for the current scholarship to stuList
		ArrayList<Offer> offers = this.session.getDatabase().getOffers();
		
		/*
		 * sort through all offers, adding their corresponding students to the list if they
		 * both match the selected scholarship and are accepted
		 */
		for (Offer o : offers) {	
			if (o.getScholarshipName().compareTo(current.getName()) == 0) {	//if the scholarship is the one selected
				if (o.getStatus().compareTo("accepted") == 0) {	//if the offer is accepted
					stuList.add(this.session.getDatabase().getStudents().get(o.getStudentID()));
				}
			}
		}
		
	
		
		//Set table
		ObservableList<Student> data = FXCollections.observableArrayList(stuList);
		table.setItems(data);
		stuIDCol.setCellValueFactory(f->f.getValue().studentIDProperty()); 
		GPACol.setCellValueFactory(f->f.getValue().GPAProperty());	
		nameCol.setCellValueFactory(f->f.getValue().nameProperty());	
								
		});
		
	
	}
	
	
	
	
	
}
