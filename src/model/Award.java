package model;

public class Award {

	private Scholarship scholarship;
	private int scholarshipID;
	private Student student;
	private int studentID;
	
	
	public Award(Student student, Scholarship scholarship) {
		this.scholarship = scholarship;
		this.student = student;
		this.scholarshipID = scholarship.getId();
		this.studentID = student.getID();
	}
	
	public Award(Database database, String[] attributes) {
		this.setStudentID(Integer.parseInt(attributes[0]));	
		this.setScholarshipID(Integer.parseInt(attributes[1]));
	}
	
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
	
    /**
     * Method to aid in writing application data to file upon program termination.
     */
    public String[] toStringArray() {
    		String[] awardString = new String[2];
    		awardString[0] = Integer.toString(this.getStudentID());
    		awardString[1] = Integer.toString(this.getScholarshipID()); 		
    		return awardString;
    } 
}
