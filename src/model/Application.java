package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Application class
 * @author Natalie
 *
 */
public class Application {
	
	//Must have database access to get name of scholarship, rather than ID
	//Might be able to bypass this later
	Database db;
	
	StringProperty applicationID;
	StringProperty studentID;
	StringProperty scholarshipID;
	StringProperty dateAdded;
	StringProperty status;
	StringProperty scholarshipName;
	StringProperty scholarshipDeadline;
	StringProperty dateSubmitted;
		
	
	
	/**
	 * Constructor for a new Application object
	 */
	public Application(Database database) {
		this.db = database;
		this.setStatus("saved");
	}
	
	/**
	 * Constructor for re-creating an Application object from CSV file data
	 * @param applicationData : String[] application data from application database
	 */
	//applicationID		studentID	scholarshipID datesubmitted	status
	public Application(Database database, String[] applicationData) {
		
			this.db = database;
		
			this.setApplicationId(Integer.valueOf(applicationData[0]));
			this.setStudentId(Integer.valueOf(applicationData[1]));
			this.setScholarshipId(Integer.valueOf(applicationData[2]));
			this.setDateSubmitted(applicationData[3]); 
			this.setStatus(applicationData[4]);
			
			//associated scholarship object
			Scholarship scholarship = db.getScholarshipsById().get(this.getScholarshipId());
			//and now we can add these scholarship-specific attributes
			this.setScholarshipName(scholarship.getName());
			this.setScholarshipDeadline(scholarship.getDeadline());
		
	}
	
	
	/**
	 * Constructor for Application that should only be called from Submit Application interface
	 * @param id
	 */
	public Application(Database database, int id) {
		//Id should come from session.getDatabase().getApplications().size()
		this.setApplicationId(id);
		//associated scholarship object
		this.db = database;
		Scholarship scholarship = db.getScholarshipsById().get(this.getScholarshipId());
		//and now we can add these scholarship-specific attributes
		this.setScholarshipName(scholarship.getName());
		this.setScholarshipDeadline(scholarship.getDeadline());
	}
	
	
	/*GETTERS & SETTERS*/
	
	/**
	 * Set the application id to the input value
	 * @param value - int representing the application id
	 */
	public void setApplicationId(int value) { applicationIDProperty().set(Integer.toString(value)); }
    
	public int getApplicationId() { return Integer.valueOf(applicationIDProperty().get()); }
    
	public StringProperty applicationIDProperty() { 
        if (applicationID == null) applicationID = new SimpleStringProperty(this, "applciationID");
        return applicationID; 
    } 
    public void setStudentId(int value) { studentIdProperty().set(Integer.toString(value)); }
    
    public int getStudentId() { return Integer.valueOf(studentIdProperty().get()); }
    
   
    public StringProperty studentIdProperty() { 
        if (studentID == null) studentID = new SimpleStringProperty(this, "studentID");
        return studentID; 
    } 
    public void setScholarshipId(int value) { scholarshipIdProperty().set(Integer.toString(value)); }
    
    public int getScholarshipId() { return Integer.valueOf(scholarshipIdProperty().get()); }
    
    public StringProperty scholarshipIdProperty() { 
        if (scholarshipID == null) scholarshipID = new SimpleStringProperty(this, "scholarshipID");
        return scholarshipID; 
    } 
    public void setDateAdded(String value) { dateAddedProperty().set(value); }
    
    public String getDateAdded() { return dateAddedProperty().get(); }
    
    public StringProperty dateAddedProperty() { 
        if (dateAdded == null) dateAdded = new SimpleStringProperty(this, "dateAdded");
        return dateAdded; 
    } 
    public void setStatus(String value) 
    { 
    		statusProperty().set(value);
    		//Ensure that if status changes to submitted, Application is now considered
    		//for award
    		if (value.equals("submitted"))
    		{
    			//Get relevant scholarship
    			Scholarship scholarship = this.db.getScholarshipsById().get(this.getScholarshipId());
    			//Recalculate top candidates 
    			scholarship.findTopCandidates();
    		}
    }
    
    public String getStatus() { return statusProperty().get(); }
    
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
    public void setScholarshipDeadline(String value) { scholarshipDeadlineProperty().set(value); }
    
    public String getScholarshipDeadlineProperty() { return scholarshipDeadlineProperty().get(); }
    
    public StringProperty scholarshipDeadlineProperty() { 
        if (scholarshipDeadline == null) scholarshipDeadline = new SimpleStringProperty(this, "scholarshipDeadline");
        return scholarshipDeadline; 
    } 
    public void setDateSubmitted(String value) { dateSubmittedProperty().set(value); }
    
    public String getDateSubmitted() { return dateSubmittedProperty().get(); }
    
    public StringProperty dateSubmittedProperty() { 
        if (dateSubmitted == null) dateSubmitted = new SimpleStringProperty(this, "dateSubmitted");
        return dateSubmitted; 
    } 

    /**
     * Method to aid in writing application data to file upon program termination.
     */
    public String[] toStringArray() {
    		String[] applicationString = new String[5];
    		applicationString[0] = Integer.toString(this.getApplicationId());
    		applicationString[1] = Integer.toString(this.getStudentId());
    		applicationString[2] = Integer.toString(this.getScholarshipId());
    		applicationString[3] = this.getDateSubmitted();
    		applicationString[4] = this.getStatus();
    		
    		return applicationString;
    }
    
	
  }
