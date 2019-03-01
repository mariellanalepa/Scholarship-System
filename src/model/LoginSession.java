package model;

public class LoginSession {
	private Student s;
	private int sessionID;
	
	
	public LoginSession(int studentID){
		this.s = new Student(studentID);
		this.sessionID = 1;
	}

	public Student getStudent() {
		return this.s;
	}
	
//	public static void main(String[] args) {
//		LoginSession l = new LoginSession(12345678);
//		
//	}
}
