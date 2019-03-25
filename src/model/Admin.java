package model;

public class Admin extends User {
	
	public Admin(String usr) throws InvalidUserException {
		DataManager m = new DataManager();
		String[] data = m.getAdminData(usr);
		this.userName = data[0];
		this.userID = Integer.valueOf(data[1]);
		this.firstName = data[2];
		this.lastName = data[3];
	}

}
