package model;

public class Student {
	private String username;
	private int studentID;
	private String studentFirstName;
	private String studentLastName;
	private int studentYear;
	private String studentType;
	private String studentDepartment;
	private String studentFaculty;
	private float studentGPA;
	
	public Student(String usr) {
		CsvReader c = new CsvReader();
		String[] data = c.getStudentData(usr);
		this.username = data[0];
		this.studentID = Integer.valueOf(data[1]);
		this.studentFirstName = data[2];
		this.studentLastName = data[3];
		this.studentType = data[4];
		this.studentYear = Integer.valueOf(data[5]);
		this.studentFaculty = data[6];
		this.studentDepartment = data[7];
		this.studentGPA = Float.valueOf(data[8]);
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
		String type = "";
		if(this.studentType.startsWith("U")) { type = "Undergraduate";}
		return type;
	}
	public String getStudentGPA() {
		return Float.toString(studentGPA);
	}
	
}
