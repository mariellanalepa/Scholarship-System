package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javafx.collections.ObservableList;


/**
 * Class in which we contain the summary of application data that constitutes the MODEL in the MVC design.
 * Since the data model will depend on which user is logged in (i.e., the user session), we call this 
 * class Session. 
 * @author Natalie, Mariella
 *
 */
public class Session {
	private User user;
	private Database db;
	
	public ObservableList<Application> applications;
	public ObservableList<Scholarship> scholarships;
	
	//If a scholarship has been selected via GUI table, this variable will be set
	private Scholarship selectedScholarship = null;

	/**
	 * Session constructor. Creates a session and initializes all the databases
	 */
	public Session() {
		this.db = new Database();
	}

	/**
	 * Session checks login information, authenticates the user and
	 * instantiates user object (Student/Admin),
	 * and calls initialiazeDatabaseData() if user is valid. 
	 * @param username : String
	 * @throws InvalidUserException : not a valid username
	 */
	public void login(String username, String password) throws InvalidUserException
	{
		this.user = this.db.getUsers().get(username);
		
		if (this.user == null)
		{
			InvalidUserException e = new InvalidUserException("User not found");
			throw e;
		}
		
		if (password == null)
		{
			InvalidUserException e = new InvalidUserException("Invalid Password");
			throw e;
		} else {
			authenticateUser(password);
		}
		
	
		
	}

/**
 * MD5 hashing algorithm. Takes in user password, generates the hash, 
 * and compares it to the stored hash.
 * @param password
 * @throws InvalidUserException 
 */
	private void authenticateUser(String password) throws InvalidUserException {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            //Convert bytes from decimal to hexadecimal format
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
            // call validation method to compare hash values
            if(this.user.checkHash(generatedPassword) != true) {
            	InvalidUserException incorrectPassword = new InvalidUserException("Invalid Password");
            	throw incorrectPassword;
            }
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
		
	}

	/*GETTERS & SETTERS*/

	public User getUser() {
		return this.user;
	}
	
	/**
	 * Method to retrieve the scholarship which has been selected in this session via
	 * GUI (can be used for edit, deletion, or application submission)
	 * @return Scholarship that user has selected
	 */
	public Scholarship getScholarshipSelection() {
		return this.selectedScholarship;
	}
	
	/**
	 * Method to set Scholarship visually selected via GUI so that is it "globally" 
	 * accessible (at least, accessible to classes which have reference of Session
	 * via dependency injection) 
	 * @param Scholarship selected by user
	 */
	public void setScholarshipSelection(Scholarship scholarship) {
		this.selectedScholarship = scholarship;
	}
	
	/**
	 * Return the database instance associated with this program Session
	 * @return Database associated with this session
	 */
	public Database getDatabase() {
		return this.db;
	}








}
