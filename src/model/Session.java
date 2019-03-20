package model;

import java.util.List;

public class Session {
	private Student student;
	private Admin admin;
	private List<String[]> applicationDatabase;
	private List<String[]> scholarshipDatabase;
	private int userType;	// 0 is admin, 1 is student
	public static int userID;
	
	/**
	 * Session constructor, creates a session for the user specified by username.
	 * Session instantiates user object (Student/Admin), and
	 * updates the applicationDatabase and scholarshipDatabase attributes depending on user type
	 * 		if user is Admin, the complete application and scholarship database will be loaded
	 *  	if user is Student, 
	 *  		applicationDatabase contains applications with the students ID number
	 *  	 	scholarshipDatabase contains the curated list of scholarships for that student
	 * @param username : String
	 * @throws InvalidUserException : not a valid username
	 */
	public Session(String username) throws InvalidUserException{
		try {
			this.admin= new Admin(username);
			this.userType = 0;
		} catch(NullPointerException notAdmin) {
			try {
			this.student = new Student(username);
			this.userType = 1;
			userID = Integer.valueOf(student.getStudentID());
			System.out.println(userID);
			} catch(NullPointerException notStudent) {
				InvalidUserException e = new InvalidUserException("User not found");
				throw e;
			}
		}
	}

	public int getUserType(){
		return this.userType;
	}
	
	public Admin getAdmin() {
		return this.admin;
	}
	public Student getStudent() {
		return this.student;
	}
	
}
