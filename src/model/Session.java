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
	 * Session instantiates user object (Student/Admin),
	 * and calls initialiazeDatabaseData() if user is valid. 
	 * @param username : String
	 * @throws InvalidUserException : not a valid username
	 */
	public void login(String username, String password) throws InvalidUserException
	{
		this.user = this.db.getUsers().get(username);
		
		authenticateUser(password);
		if (this.user == null)
		{
			InvalidUserException e = new InvalidUserException("User not found");
			throw e;
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
            //Convert bytes from decimal to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
            if(this.user.checkHash(generatedPassword) != true) {
            	InvalidUserException incorrectPassword = new InvalidUserException("Invalid Password");
            	throw incorrectPassword;
            }
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        System.out.println(generatedPassword);
		
	}

	/*GETTERS & SETTERS*/

	public User getUser() {
		return this.user;
	}
	
	public Scholarship getScholarshipSelection() {
		return this.selectedScholarship;
	}
	
	public void setScholarshipSelection(Scholarship scholarship) {
		this.selectedScholarship = scholarship;
	}
	
	public Database getDatabase() {
		return this.db;
	}








}
