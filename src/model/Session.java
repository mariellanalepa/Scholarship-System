package model;

import java.util.List;


/**
 * Class in which we contain the summary of application data that constitutes the MODEL in the MVC design.
 * Since the data model will depend on which user is logged in (i.e., the user session), we call this 
 * class Session. 
 * @author Natalie, Mariella
 *
 */
public class Session {
	private Student student;
	private Admin admin;
	private List<String[]> applicationDatabase;
	private List<String[]> scholarshipDatabase;
	private int userType;	// 0 is admin, 1 is student
	public static int userID;
	
	/**
	 * Session constructor. Creates a session that is 
	 * customized to user upon call to login(String username).
	 */
	public Session() {}
	
	/**
	 * Session instantiates user object (Student/Admin),
	 * and calls initialiazeDatabaseData() if user is valid. 
	 * @param username : String
	 * @throws InvalidUserException : not a valid username
	 */
	public void login(String username) throws InvalidUserException
	{
		try {
			this.admin= new Admin(username);
			this.userType = 0;
			this.initializeDabaseData();
		} catch(NullPointerException notAdmin) {
			try {
			this.student = new Student(username);
			this.userType = 1;
			userID = Integer.valueOf(student.getStudentID());
			this.initializeDabaseData();
			} catch(NullPointerException notStudent) {
				InvalidUserException e = new InvalidUserException("User not found");
				throw e;
			}
		}
	}
	
	
	/**
	 * Initializes the applicationDatabase and scholarshipDatabase attributes depending on user type:
	 * 		if user is Admin, 
	 * 			applicationDatabase contains all the SUBMITTED-status applications
	 * 			scholarshipDatabase contains all scholarships
	 * 
	 *  	if user is Student, 
	 *  		applicationDatabase contains applications with the students ID number
	 *  	 	scholarshipDatabase contains the curated list of scholarships for that student
	 */
	private void initializeDabaseData() {
		CsvReader c = new CsvReader();
		if(this.userType == 0) {
			this.setApplicationDatabase(c.getApplicationData("submitted"));
			this.setScholarshipDatabase(c.getScholarshipData());
		} else if(this.userType == 1) {
			this.setApplicationDatabase(c.getApplicationData(Integer.valueOf(student.getStudentID())));
			this.setScholarshipDatabase(c.getScholarshipData(Integer.valueOf(student.getStudentID())));
		} 
		
	}
	
	/* THIS FUNCTION WILL BE RENDERED VOID - REPLACING WITH ABSTRACT*/
	public int getUserType(){
		return this.userType;
	}
	
	public Admin getAdmin() {
		return this.admin;
	}
	public Student getStudent() {
		return this.student;
	}

	public List<String[]> getApplicationDatabase() {
		return applicationDatabase;
	}

	public void setApplicationDatabase(List<String[]> applicationDatabase) {
		this.applicationDatabase = applicationDatabase;
	}

	public List<String[]> getScholarshipDatabase() {
		return scholarshipDatabase;
	}

	public void setScholarshipDatabase(List<String[]> scholarshipDatabase) {
		this.scholarshipDatabase = scholarshipDatabase;
	}
	
}
