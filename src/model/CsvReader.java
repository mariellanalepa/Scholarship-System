package model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class CsvReader {
	private String[] studentData; 
	
	CsvReader(int studentID) {
		System.out.println(studentID);
		this.readCSV(studentID);
	}
	
	private void readCSV(int studentID) {
		System.out.println(studentID);
		BufferedReader buffread = null;
		String line = "";
		String delimiter = ",";
		try {
			File f = new File("res/studentDatabase.csv");
			buffread = new BufferedReader(new FileReader(f));
			while ((line = buffread.readLine()) != null) {
				System.out.println(line);
				System.out.println(Integer.toString(studentID));
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

}
	
