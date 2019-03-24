package model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


// APPLICATION CSV : applicationID	studentID	scholarshipID	dateAdded	status
// SCHOLARSHIP CSV: IDNumber	Name	Donor	Deadline(dd/MM/yyyy HH:mm:ss)	Amount	Number	ReqFac	ReqDept	RecType	ReqGPA	ReqYear	Status	DatePosted(dd/MM/yyyy HH:mm:ss)
/**
 * CsvReader class. Contains all functions that perform I/O on the csv files
 * @author Natalie
 */

public class CsvReader {
	final String adminDatabase = "res/adminDatabase.csv";
	final String studentDatabase = "res/studentDatabase.csv";
	final String scholarshipDatabase = "res/scholarshipDatabase.csv";
	final String applicationDatabase = "res/applicationDatabase.csv";
	private String[] data;
	private List<String[]> databaseData;
	private int databaseCounter;
	
	
	/**
	 * Fetches student information from the student CSVFile by username
	 * @param username : String
	 * @return data : String[] of data from the CSVFile 
	 */
	public String[] getStudentData(String username) {
		getDatabaseEntry(username, studentDatabase);
		return this.data;
	}
	
	/**
	 * Fetches admin information from the admin CSVFile by username
	 * @param username : String
	 * @return data : String[] of data from the CSVFile 
	 */
	public String[] getAdminData(String username) {
		getDatabaseEntry(username, adminDatabase);
		return this.data;
	}
	
	
	/**
	 * Loads the entire scholarship database into memory,
	 * updates the databaseCounter, and
	 * returns data as List<String[]>
	 * @return databaseData : List<String[]> of database data
	 */
	public List<String[]> getScholarshipData(){
		getDatabase(scholarshipDatabase);
		ScholarshipFactory.setCounter(this.databaseCounter);
		return this.databaseData;
	}
	
	/**
	 *  DAVID CURATED FUNCTION HERE
	 * Loads the entire scholarship database into memory,
	 * updates the databaseCounter, and
	 * returns data as List<String[]>
	 * @return databaseData : List<String[]> of database data
	 */
	public List<String[]> getScholarshipData(int studentID){
		getDatabase(scholarshipDatabase);
		ScholarshipFactory.setCounter(this.databaseCounter);
		// CURATING CODE HERE //
		return this.databaseData;
	}
	
	/**
	 * Loads the entire application database into memory,
	 * updates the databaseCounter, and
	 * returns data as List<String[]>
	 * @return databaseData : List<String[]> of database data
	 */
	public List<String[]> getApplicationData(){
		getDatabase(applicationDatabase);
		System.out.println("@ getApplicationData(): "+this.databaseCounter);
		ApplicationFactory.setCounter(this.databaseCounter);
		return this.databaseData;
	}
	
	/**
	 * Reads the entire admin database,
	 * filters the data keeping only the entries which have a studentID field 
	 * equal to the supplied argument.
	 * Updates the databaseCounter, and returns data as List<String[]>
	 * @param studentID : int
	 * @return databaseData : List<String[]> of database data
	 */

	public List<String[]> getApplicationData(int studentID){
		getDatabase(applicationDatabase);
		List<String[]> dataList = new ArrayList<String[]>();
		for(int i = 0; i < this.databaseData.size(); i++) {
			if(this.databaseData.get(i)[1].equals(String.valueOf(studentID))) {
				dataList.add(this.databaseData.get(i));
			}		
		}
		return dataList;
	}
	
	/**
	 * Returns a List<String[]> of application data from applicationDatabase CSVFile
	 * whose status matches that provided as an argument
	 * @param status : String 
	 * @return databaseData : List<String[]> of database data
	 */

	public List<String[]> getApplicationData(String status){
		getDatabase(applicationDatabase);
		List<String[]> dataList = new ArrayList<String[]>();
		for(int i = 0; i < this.databaseData.size(); i++) {
			if(this.databaseData.get(i)[4].equals(status)) {
				dataList.add(this.databaseData.get(i));
			}		
		}
		return dataList;
	}
	
	/**
	 * Opens specified CSVFile, reads each line splitting on comma, 
	 * and stores the result into a List<String[]>
	 * which is set as the databaseData attribute
	 * @param databaseName
	 */
	private void getDatabase(String databaseName) {
		List<String[]> list = new ArrayList<String[]>();
		BufferedReader buffread = null;
		String line = "";
		String delimiter = ",";
		try {
			File f = new File(databaseName);
			buffread = new BufferedReader(new FileReader(f));
			int i = 0;
			while ((line = buffread.readLine()) != null) {
				/* first line of application and student database are not data
				 * line 1 is header data */
				if(i == 0) {
					i++;
					continue;
				}
				list.add(line.split(delimiter));
				i++;
			}
			this.databaseData = list;
			this.databaseCounter = list.size();

			
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
	
	
	private void getDatabaseEntry(String entryIdentifier, String databaseName) {
		BufferedReader buffread = null;
		String line = "";
		String delimiter = ",";
		try {
			File f = new File(databaseName);
			buffread = new BufferedReader(new FileReader(f));
			while ((line = buffread.readLine()) != null) {
				String[] data = line.split(delimiter);
				if(data[0].equalsIgnoreCase(entryIdentifier)) {
					this.data = data;
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
	
	private void deleteDatabaseEntry(String databaseName, int index) throws IOException {
		File f = new File(databaseName);
		if (f.exists() && f.isFile()) {
			f.delete();
		}
		f.createNewFile();
		for (int i = 0; i < databaseData.size(); i++) {
			if (i != index) {
				String[] d = databaseData.get(i);
				addDatabaseEntry(scholarshipDatabase, d);
			}
		}
		
	}
	
	/* THIS FUNCTION DUPLICATES FUNCTIONALITY 
	 * UPDATE CALLING CODE TO CALL getDatabase(String databaseName) INSTEAD 
	 * REMOVE FUNCTION AFTER UPDATED*/
	public void getDatabaseForDelete(String databaseName) {
		List<String[]> list = new ArrayList<String[]>();
		BufferedReader buffread = null;
		String line = "";
		String delimiter = ",";
		try {
			File f = new File(databaseName);
			buffread = new BufferedReader(new FileReader(f));
			while ((line = buffread.readLine()) != null) {
				list.add(line.split(delimiter));
			}
			this.databaseData = list;
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
	
	private void addDatabaseEntry(String databaseName, String[] data) {
		BufferedWriter bw = null;
		String line = String.join(",", data);
		line += "\n";
		try {
			File f = new File(databaseName);
			bw = new BufferedWriter(new FileWriter(f, true));
			bw.write(line);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	public String getScholarshipName(int scholarshipID){
		String sName = "null";
		getDatabase(scholarshipDatabase);
		for(int i = 0; i < this.databaseData.size(); i++) {
			if(Integer.valueOf(this.databaseData.get(i)[0]) == scholarshipID) {
				sName = this.databaseData.get(i)[1];
				
			}
		}
		return sName;
	}
	
	

	
	public boolean addScholarshipEntry(String[] scholarshipData) { 
		addDatabaseEntry(scholarshipDatabase, scholarshipData);
		boolean success = true;
		return success;
	}
	
	public boolean deleteScholarshipEntry(int index) throws Exception {
		this.deleteDatabaseEntry(scholarshipDatabase, index);
		boolean success = true;
		return success;
	}
	
	public boolean addApplicationEntry(String[] applicationData) {
		addDatabaseEntry(applicationDatabase, applicationData);
		boolean success = true;
		return success;
	}

	/**
	 * Writes updated database to file before closing
	 * @param databaseName : String
	 * @param databaseData : List<String[]>
	 */
	public void saveDatabaseOnExit(String databaseName, List<String[]> databaseData) {
		BufferedWriter bw = null;
		List<String[]> data = updateData(databaseName, databaseData);
		try {
			File f = new File(databaseName);
			bw = new BufferedWriter(new FileWriter(f, false));
			
			for(int i = 0; i < data.size(); i++) {
				String line = String.join(",", data.get(i));
				line += "\n";
				bw.write(line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Checks and fixes coherency between data in saved file and data from current Session
	 * @param databaseName : String
	 * @param databaseData : List<String[]>
	 */
	private List<String[]> updateData(String databaseName, List<String[]> freshDatabase) {
		List<String[]> data = new ArrayList<String[]>();
		
		// get database from file for comparison
		getDatabase(databaseName);
		List<String[]> staleDatabase = this.databaseData;
		
		int newLen = freshDatabase.size();
		int oldLen = staleDatabase.size();
		int i = 0;
		//int m = java.lang.Math.min(newLen, oldLen)
		int j = 0;
			
		
		while((i < newLen) & (j < oldLen)) {
			String[] newLine = freshDatabase.get(i);
			String[] oldLine = staleDatabase.get(j);
				
			// get integer values of id numbers
			int newID = Integer.valueOf(newLine[0]);
			int oldID = Integer.valueOf(oldLine[0]);
				
			if(newID == oldID) {
				// case 1: same ID number
				// replace with new data
				data.add(newLine);
				i++;
				j++;

			} else if((newID > oldID) & (j < oldLen)) {
				// case 2: ID number of new > old
				// add data from old until oldID >= newID
				while(newID > oldID) {
					oldLine = staleDatabase.get(j);
					oldID = Integer.valueOf(oldLine[0]);
					data.add(oldLine);
					j++;
				}

			} else {
				// case 3: ID number of new < old
				// add data from new until newID <= oldID
				while((newID < oldID) & (i < newLen)) {
					newLine = freshDatabase.get(i);
					newID = Integer.valueOf(newLine[0]);
					data.add(newLine);
					i++;
				}
			}
		
		}
		
		// if list lengths are not equal
		// add all remaining data from the longer list 
		if(newLen > oldLen) {
			while(i < newLen) {
				String[] newLine = freshDatabase.get(i);
				data.add(newLine);
				i++;
			}
		} else if(newLen < oldLen) {
			while(j < oldLen) {
				String[] oldLine = staleDatabase.get(j);
				data.add(oldLine);
				j++;
			}
			
		}
			
		return data;
	}
	
	
}
	
