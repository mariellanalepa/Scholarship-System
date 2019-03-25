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
	private List<String[]> masterApplicationDatabase;	
	private List<String[]> masterScholarshipDatabase;  
	
	
	/**
	 * Session constructor. Creates a session that is 
	 * customized to user upon call to login(String username).
	 */
	public Session() {
		DataManager m = new DataManager();
		m.loadApplicationData(); // loads application database
		m.loadScholarshipData(); // loads scholarship database
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
			initializeDabaseData();
			
		} catch(InvalidUserException notAdmin) {
			try {
			this.user = new Student(username);
			initializeDabaseData();
			} catch(InvalidUserException notStudent) {
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
		DataManager m = new DataManager();
//		m.loadApplicationData(); // loads application database
//		m.loadScholarshipData(); // loads scholarship database

		
		if(this.user instanceof Admin) {
			this.setApplicationDatabase(m.getApplicationDataByStatus("submitted"));
			this.setScholarshipDatabase(DataManager.getScholarshipData());
		} else if(this.user instanceof Student) {
			this.setApplicationDatabase(m.getApplicationDataByID(Integer.valueOf(user.getID())));
			this.setScholarshipDatabase(m.getScholarshipDataByID(Integer.valueOf(user.getID())));
		} 
		
	}

	public void saveDatabases() {
		DataManager m = new DataManager();
		m.saveDatabaseOnExit(CsvReader.scholarshipDatabase, this.scholarshipDatabase);
		m.saveDatabaseOnExit(CsvReader.applicationDatabase, this.applicationDatabase);
	}
	
	
	public void updateApplicationDatabase() {
		this.applicationDatabase = DataManager.getApplicationData();	
	}
	public void updateScholarshipDatabase() {
		this.scholarshipDatabase = DataManager.getScholarshipData();	
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
	
	protected void setApplicationDatabase(List<String[]> applicationDatabase) {
		this.applicationDatabase = applicationDatabase;
	}
	protected void setScholarshipDatabase(List<String[]> scholarshipDatabase) {
		this.scholarshipDatabase = scholarshipDatabase;
	}

	public List<String[]> getMasterApplicationDatabase() {
		return masterApplicationDatabase;
	}

	public void setMasterApplicationDatabase(List<String[]> masterApplicationDatabase) {
		this.masterApplicationDatabase = masterApplicationDatabase;
	}

	public List<String[]> getMasterScholarshipDatabase() {
		return masterScholarshipDatabase;
	}

	public void setMasterScholarshipDatabase(List<String[]> masterScholarshipDatabase) {
		this.masterScholarshipDatabase = masterScholarshipDatabase;
	}



	
}
