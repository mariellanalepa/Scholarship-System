package model;

public class Admin extends User {
	
	public Admin(String[] data) {
		this.userName = data[0];
		this.userID = Integer.valueOf(data[1]);
		this.firstName = data[2];
		this.lastName = data[3];
	}

}
