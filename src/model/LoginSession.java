package model;

public class LoginSession {
	private Student s;
	private Admin a;
	private int userType;	// 0 is admin, 1 is student
	private int sessionID;	
	
	
	public LoginSession(int idNumber) throws InvalidUserException{
		System.out.println(idNumber);
		try {
			Admin u = new Admin(idNumber);
			this.a = u;
			this.userType = 0;
			this.sessionID = 1;
			System.out.println("Success");
			//return;
		} catch(NullPointerException notAdmin) {
			try {
			this.s = new Student(idNumber);
			this.userType = 1;
			this.sessionID = 1;
			System.out.println("Success");
			//return;
			} catch(NullPointerException notStudent) {
				String err = "User not found";
				InvalidUserException e = new InvalidUserException(err);
				throw e;
			}
		}
		
		
	}

	public int getUserType(){
		return this.userType;
	}
	public Admin getAdmin() {
		return this.a;
	}
	public Student getStudent() {
		return this.s;
	}
	
//	public static void main(String[] args) {
//		LoginSession l = new LoginSession(12345678);
//		
//	}
}
