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
	private DataManager m;
	
//	/* Application and Scholarship Databases specific to the logged-in user*/
//	private List<String[]> userApplicationDatabase;	// specific to user 
//	private List<String[]> userScholarshipDatabase; // specific to user 
	


	/**
	 * Session constructor. Creates a session that is 
	 * customized to user upon call to login(String username).
	 */
	public Session() {
		this.m = new DataManager();
		DataManager.loadApplicationData(); // loads application database
		DataManager.loadScholarshipData(); // loads scholarship database
	}

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

		} catch(InvalidUserException notAdmin) {
			try {
				this.user = new Student(username);
			} catch(InvalidUserException notStudent) {
				InvalidUserException e = new InvalidUserException("User not found");
				throw e;
			}
		}
		
		initializeDabaseData();
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
		if(this.user instanceof Admin) {
			DataManager.initAdminDatabases();
		} else if(this.user instanceof Student) {
			DataManager.initStudentDatabases(this.user.getID());
		} 

	}

	public void saveDatabases() {
		try {
			m.saveDatabaseOnExit(CsvReader.scholarshipDatabase);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			m.saveDatabaseOnExit(CsvReader.applicationDatabase);
		} catch(NullPointerException n) {
			System.out.println(n);
			return;
		}

	}


//	public void updateApplicationDatabase() {
//		this.userApplicationDatabase = DataManager.getApplicationData();	
//	}
//	public void updateScholarshipDatabase() {
//		this.userScholarshipDatabase = DataManager.getScholarshipData();	
//	}

	/*GETTERS & SETTERS*/

	public User getUser() {
		return this.user;
	}
	public List<String[]> getUserApplicationDatabase() {
		return userApplicationDatabase;
	}
	public List<String[]> getUserScholarshipDatabase() {
		return userScholarshipDatabase;
	}

	protected void setUserApplicationDatabase(List<String[]> applicationDatabase) {
		this.userApplicationDatabase = applicationDatabase;
	}
	protected void setUserScholarshipDatabase(List<String[]> scholarshipDatabase) {
		this.userScholarshipDatabase = scholarshipDatabase;
	}






}
