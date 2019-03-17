package model;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Application {
	//private int counter;
	StringProperty applicationID;
	StringProperty studentID;
	StringProperty scholarshipID;
	StringProperty dateSubmitted;
	StringProperty status;
	StringProperty scholarshipName;
	
	
	
	
	// new applications
	public Application() {
		this.setStatus("open");
	}
	
	// re-creating instances from a file
	//applicationID		studentID	scholarshipID datesubmitted	status
	public Application (String[] applicationData) {
		this.setApplicationId(applicationData[0]);
		this.setStudentId(applicationData[1]);
		this.setScholarshipId(applicationData[2]);
		this.setDateSubmitted(applicationData[3]); 
		this.setStatus(applicationData[4]);
		
		
	}
	/* String[] applicationData = [applicationID,	scholarshipID,	studentID,	datesubmitted]
	 * */
	public boolean saveApplication() {
		CsvReader c = new CsvReader();
		/* if this is a new application (does not yet have an ID)
		 * set the ID number
		 */
		if(getApplicationId() == null) {
			int counter = ApplicationFactory.getCounter();
			ApplicationFactory.incrementCounter();
			this.applicationID.set(String.valueOf(counter));
		}
		String[] applicationData = {this.applicationID.get(), studentID.get(), scholarshipID.get(), dateSubmitted.get(), status.get()};
		boolean success = c.addApplicationEntry(applicationData);
		return success;
	}


	public void setApplicationId(String value) { applicationIDProperty().set(value); }
    public String getApplicationId() { return applicationIDProperty().get(); }
    public StringProperty applicationIDProperty() { 
        if (applicationID == null) applicationID = new SimpleStringProperty(this, "applciationID");
        return applicationID; 
    } 
    public void setStudentId(String value) { studentIdProperty().set(value); }
    public String getStudentId() { return studentIdProperty().get(); }
    public StringProperty studentIdProperty() { 
        if (studentID == null) studentID = new SimpleStringProperty(this, "studentID");
        return studentID; 
    } 
    public void setScholarshipId(String value) { scholarshipIdProperty().set(value); }
    public String getScholarshipId() { return scholarshipIdProperty().get(); }
    public StringProperty scholarshipIdProperty() { 
        if (scholarshipID == null) scholarshipID = new SimpleStringProperty(this, "scholarshipID");
        return scholarshipID; 
    } 
    public void setDateSubmitted(String value) { dateSubmittedProperty().set(value); }
    public String getDateSubmitted() { return dateSubmittedProperty().get(); }
    public StringProperty dateSubmittedProperty() { 
        if (dateSubmitted == null) dateSubmitted = new SimpleStringProperty(this, "dateSubmitted");
        return dateSubmitted; 
    } 
    public void setStatus(String value) { statusProperty().set(value); }
    public String getStatusProperty() { return statusProperty().get(); }
    public StringProperty statusProperty() { 
        if (status == null) status = new SimpleStringProperty(this, "status");
        return status; 
    } 
    
    public void setScholarshipName(String value) { scholarshipNameProperty().set(value); }
    public String getScholarshipNameProperty() { return scholarshipNameProperty().get(); }
    public StringProperty scholarshipNameProperty() { 
        if (scholarshipName == null) scholarshipName = new SimpleStringProperty(this, "scholarshipName");
        return scholarshipName; 
    } 


}
