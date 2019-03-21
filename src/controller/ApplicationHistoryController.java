package controller;
import model.Application;
import model.ApplicationFactory;
import model.Session;
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
	@FXML private Button signOut, mainMenuButton;
	@FXML private Label welcomeLabel;
	@FXML private TableColumn<Application,String> applicationIdCol, scholarshipIdCol, scholarshipNameCol, dateSubmittedCol, deadlineCol, status;
	@FXML private TableView<Application> table;
	
	public ApplicationHistoryController(Main main, Session session) {
		this.main = main;
		this.session = session;
	}
	
	@FXML
	protected void handleSignOutButtonAction(ActionEvent event) throws Exception {
		main.setScene("/view/Login.fxml");
	}
	
	@FXML 
	protected void handleMainMenuButtonAction(ActionEvent event) throws Exception {
		main.setScene("/view/StudentMain.fxml");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		welcomeLabel.setText(welcomeLabel.getText() + " " + session.getUser().getName());
		System.out.println(session.getUser().getID());
		ApplicationFactory af = new ApplicationFactory(session.getUser().getID());
		ObservableList<Application> data = FXCollections.observableArrayList(af.getApplicationArray());

		table.setItems(data);
		applicationIdCol.setCellValueFactory(f->f.getValue().applicationIDProperty());
		//scholarshipIdCol.setCellValueFactory(f->f.getValue().studentIdProperty());
		scholarshipNameCol.setCellValueFactory(f->f.getValue().scholarshipNameProperty());
		deadlineCol.setCellValueFactory(f->f.getValue().scholarshipDeadlineProperty());
		status.setCellValueFactory(f->f.getValue().statusProperty());
		table.getColumns().setAll(applicationIdCol, scholarshipNameCol, deadlineCol, status);
	}

}
