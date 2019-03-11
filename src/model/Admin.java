package model;

public class Admin {
	private int adminID;
	private String adminFirstName;
	private String adminLastName;
	
	public Admin(int idNumber) {
		CsvReader c = new CsvReader(idNumber, 0);
		String[] data = c.getAdminData();
		this.adminID = Integer.valueOf(data[0]);
		this.adminFirstName = data[1];
		this.adminLastName = data[2];
	}

	public String getFirstName() {
		return this.adminFirstName;
	}
	public String getLastName() {
		return this.adminLastName;
	}

}
