package model;

public class Student {
	private int studentID;
	private String firstName;
	private String lastName;
	private int year;
	private String type;
	private String department;
	private String faculty;
	private float GPA;
	
	public Student(int studentID) {
		CsvReader c = new CsvReader(12345678);
		String[] data = c.data;
		this.studentID = Integer.valueOf(data[0]);
		this.firstName = data[1];
		this.lastName = data[2];
		this.type = data[3];
		this.year = Integer.valueOf(data[4]);
		this.faculty = data[6];
		this.department = data[5];
		this.GPA = Float.valueOf(data[7]);
		
		
	}
	public void printS() {
		System.out.println(this.studentID);
		System.out.println(this.firstName);
		System.out.println(this.lastName);
		System.out.println(this.year);
		System.out.println(this.type);
		System.out.println(this.department);
		System.out.println(this.faculty);
		System.out.println(this.GPA);
		
	}

	public String getFirstName() 
	{
		return this.firstName + " " + this.studentID;
	}
	
}
