package model;

class LoginSession {
	Student s;
	
	
	LoginSession(int studentID){
		this.s = new Student(studentID);
	}

	public static void main(String[] args) {
		LoginSession l = new LoginSession(12345678);
		l.s.printS();
		
	}
}
