package model;

public class Admin {
	private int adminID;
	private String username;
	private String adminFirstName;
	private String adminLastName;
	
	public Admin(String usr) {
		CsvReader c = new CsvReader(usr, 0);
		String[] data = c.getAdminData();
		this.username = data[0];
		this.adminID = Integer.valueOf(data[1]);
		this.adminFirstName = data[2];
		this.adminLastName = data[3];
	}

	public String getFirstName() {
		return this.adminFirstName;
	}
	public String getLastName() {
		return this.adminLastName;
	}

}
