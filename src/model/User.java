package model;

/**
 * Abstract class for users that can log in to system -- Students and Admins
 * @author Mariella, Natalie
 *
 */

public abstract class User {
	//Protected access modifiers to allow access by subclasses
	protected int userID;
	protected String userName;
	protected String firstName;
	protected String lastName;
	protected String passwordHash;
	
	/**
	 * Method for user validation.
	 * Checks the hash generated from the entered password
	 * with the hash stored for that user.
	 * returns boolean indicating success of match
	 * @param hash : hash generated from entered password
	 * @return boolean match : true if hashes match, false otherwise
	 */
	public boolean checkHash(String hash) {
		boolean match = false;
		if(hash.equalsIgnoreCase(this.passwordHash)) {
			match = true;
		}
		return match;
	}
	
	/*Getter Methods*/
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getName() {
		return this.firstName + " " + this.lastName;
	}
	
	public int getID() {
		return this.userID;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
}
