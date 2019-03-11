package model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class CsvReader {
	private String[] studentData; 
	private String[] adminData; 
	private List<String[]> scholarshipData;
	
	/* constructor
	 * use when pulling user data
	 * userType 0 = admin, 1 = student
	 */
	CsvReader(String username, int userType) {
		if(userType == 0) {
			this.readAdminCSV(username);
		} else {
			this.readStudentCSV(username);
		}
		
	}
	
	/* constructor
	 * use when pulling scholarship data
	 */
	CsvReader() {
		this.readScholarshipCSV();
	}
	
	
	private void readAdminCSV(String username) {
		BufferedReader buffread = null;
		String line = "";
		String delimiter = ",";
		try {
			File f = new File("res/adminDatabase.csv");
			buffread = new BufferedReader(new FileReader(f));
			while ((line = buffread.readLine()) != null) {
				String[] data = line.split(delimiter);
				if(data[0].equalsIgnoreCase(username)) {
					this.adminData = data;
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (buffread != null) {
				try {
					buffread.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}


	private void readStudentCSV(String username) {
		BufferedReader buffread = null;
		String line = "";
		String delimiter = ",";
		try {
			File f = new File("res/studentDatabase.csv");
			buffread = new BufferedReader(new FileReader(f));
			while ((line = buffread.readLine()) != null) {
				String[] data = line.split(delimiter);
				if(data[0].equalsIgnoreCase(username)) {
					this.studentData = data;
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (buffread != null) {
				try {
					buffread.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	 }
	
	private void readScholarshipCSV() {
		List<String[]> list = new ArrayList<String[]>();
		BufferedReader buffread = null;
		String line = "";
		String delimiter = ",";
		try {
			File f = new File("res/scholarshipDatabase.csv");
			buffread = new BufferedReader(new FileReader(f));
			int i = 0;
			while ((line = buffread.readLine()) != null) {
				if(i == 0) {
					i++;
					continue;
				}
				System.out.println(line);
				list.add(line.split(delimiter));
				i++;
			}
			this.scholarshipData = list;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (buffread != null) {
				try {
					buffread.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	 }
	
	public String[] getStudentData() {
		return this.studentData;
	}
	public String[] getAdminData() {
		return this.adminData;
	}
	public List<String[]> getScholarshipData(){
		return this.scholarshipData;
	}

}
	
