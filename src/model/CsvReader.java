package model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class CsvReader {
	private String[] studentData; 
	private String[] adminData; 
	
	CsvReader(int idNumber, int userType) {
		if(userType == 0) {
			this.readAdminCSV(idNumber);
		} else {
			this.readStudentCSV(idNumber);
		}
		
	}
	
	
	private void readAdminCSV(int adminID) {
		BufferedReader buffread = null;
		String line = "";
		String delimiter = ",";
		try {
			File f = new File("res/adminDatabase.csv");
			buffread = new BufferedReader(new FileReader(f));
			while ((line = buffread.readLine()) != null) {
				//System.out.println(line);
				if(line.startsWith(Integer.toString(adminID))) {
					this.adminData = line.split(delimiter);
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


	private void readStudentCSV(int studentID) {
		BufferedReader buffread = null;
		String line = "";
		String delimiter = ",";
		try {
			File f = new File("res/studentDatabase.csv");
			buffread = new BufferedReader(new FileReader(f));
			while ((line = buffread.readLine()) != null) {
				if(line.startsWith(Integer.toString(studentID))) {
					this.studentData = line.split(delimiter);
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
	
	public String[] getStudentData() {
		return this.studentData;
	}
	public String[] getAdminData() {
		return this.adminData;
	}

}
	
