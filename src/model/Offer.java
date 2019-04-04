package model;
/**
 * Offer object class representing an offer for a scholarship that has been sent to a student
 */
public class Offer {

	private Scholarship scholarship;
	private Student student;	
	private String status;	//should be 'sent', 'accepted', or 'declined'
	private int studentID;
	private String scholarshipName;
	
	/*
	 * Constructors to create new offers
	 */
	public Offer(Scholarship scholarship, Student student, String status) {
		this.scholarship = scholarship;
		this.student = student;
		this.status = status;	
		this.scholarshipName = scholarship.getName();
		this.studentID = student.getID();
	}
	
	
	public Offer(Database database, String[] attributes) {
		//attributes contains student ID, scholarship name and offer status
		this.setStudentID(Integer.parseInt(attributes[0]));	
		this.setScholarshipName(attributes[1]);
		this.setStatus(attributes[2]);		
	}
	/*
	 * Getters and setters for offer properties
	 */
	public void setScholarshipName(String name) {
		this.scholarshipName = name;
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
	
	public String getScholarshipName() {
		return this.scholarshipName;
	}
	
	public String getStatus() {
		return this.status;
	} 
	
	public int getStudentID() {
		return this.studentID;
	}
	
	public Student getStudent() {
		return this.student;
	}
    /**
     * Method to aid in writing application data to file upon program termination.
     */
    public String[] toStringArray() {
    		String[] offerString = new String[3];
    		offerString[0] = Integer.toString(this.getStudentID());
    		offerString[1] = this.getScholarshipName();
    		offerString[2] = this.getStatus();    		
    		return offerString;
    } 
}
