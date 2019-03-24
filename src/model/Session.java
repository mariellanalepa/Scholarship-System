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
	private User user;
	private List<String[]> applicationDatabase;	// specific to user 
	private List<String[]> scholarshipDatabase; // specific to user 

	
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
			this.user = new Admin(username);
			initializeDabaseData();
			
		} catch(NullPointerException notAdmin) {
			try {
			this.user = new Student(username);
			initializeDabaseData();
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
		if(this.user instanceof Admin) {
			this.setApplicationDatabase(c.getApplicationData("submitted"));
			this.setScholarshipDatabase(c.getScholarshipData());
		} else if(this.user instanceof Student) {
			this.setApplicationDatabase(c.getApplicationData(Integer.valueOf(user.getID())));
			this.setScholarshipDatabase(c.getScholarshipData(Integer.valueOf(user.getID())));
		} 
		
	}

	public void saveDatabases() {
		CsvReader c = new CsvReader();
		c.saveDatabaseOnExit(c.scholarshipDatabase, this.scholarshipDatabase);
		c.saveDatabaseOnExit(c.applicationDatabase, this.applicationDatabase);
	}
	
	/*GETTERS & SETTERS*/
	
	public User getUser() {
		return this.user;
	}
	public List<String[]> getApplicationDatabase() {
		return applicationDatabase;
	}
	public List<String[]> getScholarshipDatabase() {
		return scholarshipDatabase;
	}
	
	private void setApplicationDatabase(List<String[]> applicationDatabase) {
		this.applicationDatabase = applicationDatabase;
	}
	private void setScholarshipDatabase(List<String[]> scholarshipDatabase) {
		this.scholarshipDatabase = scholarshipDatabase;
	}
	
}
