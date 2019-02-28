package model;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class CsvReader {
	String[] data; 
	
	CsvReader(int studentID) {
		this.readCSV(studentID);
	}
	
	private void readCSV(int studentID) {
		String csvFile = "C:\\Users\\nd34n\\OneDrive\\300\\eclipse-workspace\\ScholarshipSoftware\\bin\\Scholarship\\studentDatabase.csv";
		BufferedReader buffread = null;
		String line = "";
		String delimiter = ",";
		try {
			buffread = new BufferedReader(new FileReader(csvFile));
			while ((line = buffread.readLine()) != null) {
				if(line.startsWith(Integer.toString(studentID))) {
					this.data = line.split(delimiter);
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

	}
	
