package model;

public class Student {
	private int studentID;
	private String studentFirstName;
	private String studentLastName;
	private int studentYear;
	private String studentType;
	private String studentDepartment;
	private String studentFaculty;
	private float studentGPA;
	
	public Student(int studentID) {
		CsvReader c = new CsvReader(studentID);
		String[] data = c.data;
		this.studentID = Integer.valueOf(data[0]);
		this.studentFirstName = data[1];
		this.studentLastName = data[2];
		this.studentType = data[3];
		this.studentYear = Integer.valueOf(data[4]);
		this.studentFaculty = data[6];
		this.studentDepartment = data[5];
		this.studentGPA = Float.valueOf(data[7]);
	}
	

	public String getFirstName() 
	{
		return this.studentFirstName;
	}
	public String getLastName() {
		return this.studentLastName;
	}
	public String getStudentID() {
		return Integer.toString(this.studentID);
	}
	public String getStudentYear() {
		return Integer.toString(this.studentYear);
	}
	public String getStudentDepartment() {
		return this.studentDepartment;
	}
	public String getStudentFaculty() {
		return this.studentFaculty;
	}
	public String getStudentType() {
		String type = "Undergraduate";
		return type;
	}
	public String getStudentGPA() {
		return Float.toString(studentGPA);
	}
	
}
