package model;

public class LoginSession {
	private Student s;
	private Admin a;
	private int userType;	// 0 is admin, 1 is student
	private int sessionID;	
	
	
	public LoginSession(String username) throws InvalidUserException{
		System.out.println(username);
		int tempSessionID = 1;
		try {
			Admin u = new Admin(username);
			this.a = u;
			this.userType = 0;
			this.sessionID = tempSessionID;
		} catch(NullPointerException notAdmin) {
			try {
			Student u = new Student(username);
			this.s = u;
			this.userType = 1;
			this.sessionID = tempSessionID;
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
	
}
