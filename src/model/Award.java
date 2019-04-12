package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Award object class
 * @author Jasmine Roebuck
 *
 */
public class Award {

	private Scholarship scholarship;
	private int scholarshipID;
	private Student student;
	private int studentID;
	private String status;
	
	/**
	 * Object constructor
	 * @param student - The student who is offered this award
	 * @param scholarship - The scholarship this award is from
	 * @param status - 'open', 'accepted', or 'declined'
	 */
	public Award(Student student, Scholarship scholarship, String status) {
		this.scholarship = scholarship;
		this.student = student;
		this.scholarshipID = scholarship.getId();
		this.studentID = student.getID();
		this.status = status;
	}
	
	/**
	 * Constructor to build award objects from the database file
	 * @param database - Database manager
	 * @param attributes - String array of award attributes
	 */
	public Award(Database database, String[] attributes) {
		this.setStudentID(Integer.parseInt(attributes[0]));	
		this.setScholarshipID(Integer.parseInt(attributes[1]));
		this.setStatus(attributes[2]);
	}
	
	// Setter & Getter methods
	public void setScholarship(Scholarship s) {
		this.scholarship = s;
	}
	
	public void setStudent(Student s) {
		this.student = s;
	}
	
	public void setScholarshipID(int id) {
		this.scholarshipID = id;
	}
	
	public void setStudentID(int id) {
		this.studentID = id;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public Scholarship getScholarship()  {
		return this.scholarship;
	}
	
	public int getScholarshipID() {
		return this.scholarshipID;
	}
	
	public Student getStudent() {
		return this.student;
	} 
	
	public int getStudentID() {
		return this.studentID;
	}
	
	public String getStatus() {
		return this.status;
	}
	
    /**
     * Helper method to help with print formatting
     * @return A string array of the object's attributes
     */
    public String[] toStringArray() {
    		String[] awardString = new String[3  ];
    		awardString[0] = Integer.toString(this.getStudentID());
    		awardString[1] = Integer.toString(this.getScholarshipID()); 
    		awardString[2] = this.getStatus();
    		return awardString;
    }
    
    /**
     * Helper methods to create StringProperties for display in TableViews
     * @return The appropriate StringProperty
     */
	public StringProperty scholarshipIDProperty() {
		
		if (Integer.toString(this.getScholarshipID()) != null) {
			StringProperty scholarshipID = new SimpleStringProperty(Integer.toString(this.getScholarshipID()));
			return scholarshipID; 
		}
		return new SimpleStringProperty(this, "ScholarshipID");
	}
	
	public StringProperty statusProperty() {
		if (this.getStatus() != null) {
			StringProperty status = new SimpleStringProperty(this.getStatus());
			return status;
		}
		return new SimpleStringProperty(this, "Status");	
	}
	
	public StringProperty scholarshipNameProperty() {
		if (this.getScholarship() != null && this.getScholarship().getName() != null) {
			StringProperty scholName = new SimpleStringProperty(this.getScholarship().getName());
			return scholName;
		}
		return new SimpleStringProperty(this, "ScholasrshipName");
	}
	
}
