package model;

/**
 * Admin object class
 * @author Natalie
 *
 */
public class Admin extends User {

	/**
	 * Constructor for a new Admin object
	 */
	public Admin(String[] data) {
		this.userName = data[0];
		this.userID = Integer.valueOf(data[1]);
		this.firstName = data[2];
		this.lastName = data[3];
		this.passwordHash = data[4];
	}

}
