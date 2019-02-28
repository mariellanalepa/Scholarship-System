package model;

public class LoginSession {
	Student s;
	
	
	public LoginSession(int studentID){
		//this.s = new Student(studentID);
		System.out.println("Made login sess");
	}

	public static void main(String[] args) {
		LoginSession l = new LoginSession(12345678);
		l.s.printS();
		
	}
}
