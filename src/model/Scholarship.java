package model;

import java.util.ArrayList;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Scholarship object class.
 * @author Natalie
 *
 */
public class Scholarship {
	private ArrayList<Application> applications;	//List of applications related to this scholarship
	private ArrayList<Application> removals; // List of closed applications related to this scholarship
	private Student[] topCandidates;	//Top candidate(s) based on GPA of submitted scholarships
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
		this.removals = new ArrayList<Application>();
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
	
	/**
	 * Add a submitted application to the list for this scholarship. Insert
	 * the new application in the sorted list by GPA. The top candidates are then
	 * recalculated.
	 * @param newApplication - The application object to be added
	 */
	public void addApplication(Application newApplication) {
		if (this.applications.isEmpty()) {
			this.applications.add(newApplication);
			System.out.println("Application has been added to Scholarship for " + db.getStudents().get(newApplication.getStudentId()).getName());
		} else {
			boolean insert = false;
			//Student associated with application that is being added
			Student s1 = this.db.getStudents().get(newApplication.getStudentId());
			for (int i = 0; i < this.applications.size(); i++) {
				//Student associated with application already in list	
				Student s2 = this.db.getStudents().get(this.applications.get(i).getStudentId());
				if (s1.getGPA() > s2.getGPA()) {
					insert = true;
					this.applications.add(i, newApplication); // Insert this application at i
					System.out.println("Application has been added to Scholarship for " + db.getStudents().get(newApplication.getStudentId()).getName());
					break;
				}
			}
			if (!insert) {
				// Add to the end of the list if this student has the lowest GPA
				this.applications.add(newApplication);
				System.out.println("Application has been added to Scholarship for " + db.getStudents().get(newApplication.getStudentId()).getName());
			}
		}
		
		this.findTopCandidates(); // Recalculate top candidates			
	}
	
	/**
	 * Reconstructs the list of top candidates for this award. First, closed applications
	 * are removed from the list. Then N candidates are pulled from the sorted list of
	 * applications, where N is the number of available awards.
	 */
	public void findTopCandidates() {
		removeClosedApplications();
		
		for (int i = 0; i < this.topCandidates.length; i++) {
			if (i < this.applications.size() && this.applications.get(i) != null) {
				Student student = this.db.getStudents().get(this.applications.get(i).getStudentId());
				this.topCandidates[i] = student;
			}
		}			
	}
	
	/**
	 * Reconstructs the list of top candidates for this award in the event the
	 * number of available awards changes (ex. if an award is accepted).
	 */
	public void recalculateTopCandidates() {
		// Re-instantiate the list of top candidates in case the number of available awards changes
		this.topCandidates = new Student[this.getNumber()];
		this.findTopCandidates();
	}
	
	/**
	 * If a student accepts or declines an award, their application is closed. 
	 * The application is removed from the sorted list of applicants to avoid
	 * being considered as a top candidate. The list of removals is maintained.
	 */
	public void removeClosedApplications() {		
		for (Application a : this.applications) {
			if (!a.getStatus().equals("submitted")) {
				this.removals.add(a);
			}
		}
		for (Application a : this.removals) {
			this.applications.remove(a);
		}
	}
	
	/**
	 * Helper method to turn attributes into a string array for printing.
	 * @return The String array of attributes
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
	
    
	/* Getters & Setters */
	public Student[] getTopCandidates() {
		return this.topCandidates;
	}
	
	public ArrayList<Application> getApplications() {
		ArrayList<Application> allSubmittedApplications = new ArrayList<Application>();
		allSubmittedApplications.addAll(this.applications);
		allSubmittedApplications.addAll(this.removals);
		return allSubmittedApplications;
	}
	
	public ArrayList<Application> getApplicationsSubmittedOnly() {
		ArrayList<Application> submittedApps = new ArrayList<Application>();
		for (Application application : this.getApplications()) {
			String status = application.getStatus();
			if (status.equals("submitted"))	{
				submittedApps.add(application);
			}
		}
		return submittedApps;
	}
	
	/* The following code adapted from Oracle Docs
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
    
}
