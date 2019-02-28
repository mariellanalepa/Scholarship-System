package model;

public class LoginSession {
	private Student s;
	
	
	public LoginSession(int studentID){
		System.out.println("New sess");
		this.s = new Student(studentID);
	}
	
	public Student getStudent()
	{
		return s;
		
	}
}
