package model;

import java.util.ArrayList;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * 
 * @author Natalie
 *
 */
public class Scholarship {
	//List of applications related to this scholarships
	private ArrayList<Application> applications;
	//Top candidate(s) based on GPA of submitted scholarships
	private Student[] topCandidates;
	//Database
	private Database db;
	
	private IntegerProperty id;
	private StringProperty name;
	private StringProperty donor;
	private StringProperty deadline; 
	private IntegerProperty amount;
	private IntegerProperty number; 
	private StringProperty faculty;
	private StringProperty department; 
	private StringProperty type; 
	private FloatProperty gpa; 
	private StringProperty year;
	private StringProperty status;
	private StringProperty posted;

	public Scholarship (Database database, String[] scholarshipData) {
		this.db = database;
		this.applications = new ArrayList<Application>();
		this.setId(scholarshipData[0]);
		this.setName(scholarshipData[1]);
		this.setDonor(scholarshipData[2]);
		this.setDeadline(scholarshipData[3]); 
		this.setAmount(scholarshipData[4]);
		this.setNumber(scholarshipData[5]); 
		this.setFaculty(scholarshipData[6]);
		this.setDepartment(scholarshipData[7]); 
		this.setType(scholarshipData[8]); 
		this.setGpa(scholarshipData[9]); 
		this.setYear(scholarshipData[10]);
		this.setStatus(scholarshipData[11]);
		this.setPosted(scholarshipData[12]);
		
		//Make as many positions for top candidate as number of scholarships
		//to be awarded
		this.topCandidates = new Student[this.getNumber()];
	}
	

	
	
	public void addApplication(Application application)
	{
		this.applications.add(application);
		this.findTopCandidates(application);
	}
	
	/**
	 * Helper method to calculate top candidates with addition of new application
	 * or modification of application status to "submitted"
	 * @param application
	 */
	public void findTopCandidates(Application application) 
	{
		//Only consider application for award if status is "submitted"
		if (application.getStatus().equals("submitted"))
		{
			//Get associated student
			int studentID = application.getStudentId();
			Student student = this.db.getStudents().get(studentID);
					
			//Re-calculate top candidate
			for (int s = 0; s < this.topCandidates.length; s++)
			{
				//If topCandidates is not yet full
				if (this.topCandidates[s] == null)
				{
					topCandidates[s] = student;
					break;
				}
				//Otherwise check to see if new application's student GPA is higher
				else if (this.topCandidates[s].getGPA() < student.getGPA())
				{
					topCandidates[s] = student;
					break;
				}
			}
		}			
	}
	
	public Student[] getTopCandidates() {
		return this.topCandidates;
	}
	
	public ArrayList<Application> getApplications() 
	{
		return this.applications;
	}
	
	public ArrayList<Application> getApplicationsSubmittedOnly() {
		ArrayList<Application> submittedApps = new ArrayList<Application>();
		for (Application application : this.getApplications())
		{
			String status = application.getStatus();
			if (status.equals("submitted"))
			{
				submittedApps.add(application);
			}
		}
		return submittedApps;
	}
	/* GETTERS & SETTERS */
	/* following code adapted from Oracle Docs
	 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TableView.html
	 */
	public void setId(String value) { idProperty().set(Integer.parseInt(value)); }
	
    public Integer getId() { return idProperty().get(); }
    
    public IntegerProperty idProperty() { 
        if (id == null) id = new SimpleIntegerProperty(this, "id");
        return id; 
    } 
	public void setName(String value) { nameProperty().set(value); }
    public String getName() { return nameProperty().get(); }
    public StringProperty nameProperty() { 
        if (name == null) name = new SimpleStringProperty(this, "name");
        return name; 
    } 
    public void setDonor(String value) { donorProperty().set(value); }
    public String getDonor() { return donorProperty().get(); }
    public StringProperty donorProperty() { 
        if (donor == null) donor = new SimpleStringProperty(this, "donor");
        return donor; 
    } 
    public void setDeadline(String value) { deadlineProperty().set(value); }
    public String getDeadline() { return deadlineProperty().get(); }
    public StringProperty deadlineProperty() { 
        if (deadline == null) deadline = new SimpleStringProperty(this, "deadline");
        return deadline; 
    } 
    public void setAmount(String value) { amountProperty().set(Integer.parseInt(value)); }
    public Integer getAmount() { return amountProperty().get(); }
    public IntegerProperty amountProperty() { 
        if (amount == null) amount = new SimpleIntegerProperty(this, "amount");
        return amount; 
    }
    public void setNumber(String value) { numberProperty().set(Integer.parseInt(value));} 
    public void setNumber(int value) { numberProperty().set(value); }
    public Integer getNumber() {return numberProperty().get();}
	public IntegerProperty numberProperty() { 
        if (number == null) number = new SimpleIntegerProperty(this, "number");
        return number; 
    }
	public void setFaculty(String value) { facultyProperty().set(value); }
    public String getFaculty() { return facultyProperty().get(); }
    public StringProperty facultyProperty() { 
        if (faculty == null) faculty = new SimpleStringProperty(this, "faculty");
        return faculty; 
    } 
    public void setDepartment(String value) { departmentProperty().set(value); }
    public String getDepartment() { return departmentProperty().get(); }
    public StringProperty departmentProperty() { 
        if (department == null) department = new SimpleStringProperty(this, "department");
        return department; 
    } 
    public void setType(String value) { typeProperty().set(value); }
    public String getType() { return typeProperty().get(); }
    public StringProperty typeProperty() { 
        if (type == null) type = new SimpleStringProperty(this, "type");
        return type; 
    } 
    public void setGpa(String value) { gpaProperty().set(Float.parseFloat(value)); }
    public Float getGpa() { return gpaProperty().get(); }
    public FloatProperty gpaProperty() { 
        if (gpa == null) gpa = new SimpleFloatProperty(this, "gpa");
        return gpa; 
    } 
    public void setYear(String value) { yearProperty().set(value); }
    public String getYear() { return yearProperty().get(); }
    public StringProperty yearProperty() { 
        if (year == null) year = new SimpleStringProperty(this, "year");
        return year; 
    } 
    public void setStatus(String value) { statusProperty().set(value); }
    public String getStatus() { return statusProperty().get(); }
    public StringProperty statusProperty() { 
        if (status == null) status = new SimpleStringProperty(this, "status");
        return status; 
    } 
    public void setPosted(String value) { postedProperty().set(value); }
    public String getPosted() { return postedProperty().get(); }
    public StringProperty postedProperty() { 
        if (posted == null) posted = new SimpleStringProperty(this, "posted");
        return posted; 
    } 
    
    /**
     * Method to aid in writing scholarship data to file upon program termination.
     */
    public String[] toStringArray() {
    		String[] scholarshipString = new String[13];
    		scholarshipString[0] = Integer.toString(this.getId());
    		scholarshipString[1] = this.getName();
    		scholarshipString[2] = this.getDonor();
    		scholarshipString[3] = this.getDeadline();
    		scholarshipString[4] = Integer.toString(this.getAmount());
    		scholarshipString[5] = Integer.toString(this.getNumber());
    		scholarshipString[6] = this.getFaculty();
    		scholarshipString[7] = this.getDepartment();
    		scholarshipString[8] = this.getType();
    		scholarshipString[9] = Float.toString(this.getGpa());
    		scholarshipString[10] = this.getYear();
    		scholarshipString[11] = this.getStatus();
    		scholarshipString[12] = this.getPosted();
    		
    		return scholarshipString;
    }
    
}
