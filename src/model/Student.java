package model;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
	
	private int studentYear;
	private String studentType;
	private String studentDepartment;
	private String studentFaculty;
	private float studentGPA;
	private List<String[]> applications1;
	//List of applications related to this student
	private ArrayList<Application> applications;
	
	public Student(String[] data) {
		this.applications = new ArrayList<Application>();
		//DataManager m = new DataManager();
		//String[] data = m.getStudentData(username);
		this.userName = data[0];
		this.userID = Integer.valueOf(data[1]);
		this.firstName = data[2];
		this.lastName = data[3];
		this.studentType = data[4];
		this.studentYear = Integer.valueOf(data[5]);
		this.studentFaculty = data[6];
		this.studentDepartment = data[7];
		this.studentGPA = Float.valueOf(data[8]);
		//this.applications = m.getApplicationDataByID(this.userID);
	}
	
	public void addApplication(Application application)
	{
		this.applications.add(application);
	}
	
	public String getStudentIDString() {
		return Integer.toString(this.getID());
	}
	
	public String getStudentYearString() {
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
	public String getStudentGPAString() {
		return Float.toString(studentGPA);
	}
	
	public Float getGPA() {
		return this.studentGPA;
	}
	
	public ArrayList<Application> getApplications() {
		return this.applications;
	}

//	public List<String[]> getStudentApplicationList() {
//		CsvReader c = new CsvReader();
//		List<String[]> data = c.getApplicationData(this.studentID);
//		this.applications = data;
//		return this.applications;
//	}
	
}
