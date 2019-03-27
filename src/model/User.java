package model;

/**
 * Abstract class for users that can log in to system -- Students and Admins
 * @author Mariella
 *
 */
public abstract class User {
	
	//Protected access modifiers to allow access by subclasses
	protected int userID;
	protected String userName;
	protected String firstName;
	protected String lastName;
	
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
