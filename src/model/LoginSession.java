package model;

public class LoginSession {
	private static Student s;
	private static Admin a;
	private int userType;	// 0 is admin, 1 is student
	private int sessionID;
	private static int idCounter = 0;
	public static int userID;
	
	
	public LoginSession(String username) throws InvalidUserException{
		//System.out.println(username);
		try {
			Admin u = new Admin(username);
			a = u;
			this.userType = 0;
			this.sessionID = idCounter;
		} catch(NullPointerException notAdmin) {
			try {
			Student u = new Student(username);
			s = u;
			this.userType = 1;
			this.sessionID = idCounter;
			userID = Integer.valueOf(s.getStudentID());
			System.out.println(userID);
			} catch(NullPointerException notStudent) {
				InvalidUserException e = new InvalidUserException("User not found");
				throw e;
			}
		}finally {
			idCounter++;
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
