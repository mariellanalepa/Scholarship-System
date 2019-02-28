package model;

public class LoginSession {
	private Student s;
	
	
	public LoginSession(int studentID){
		this.s = new Student(studentID);
	}

	public Student getStudent() {
		return this.s;
	}
	
//	public static void main(String[] args) {
//		LoginSession l = new LoginSession(12345678);
//		
//	}
}
