package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for handling data flow and synchronizing data for databases.
 * 
 * @author Natalie
 *
 */
public class DataManager {
	public static int scholarshipCounter;
	public static int applicationCounter;
	private static List<String[]> masterApplicationDatabase;
	private static List<String[]> masterScholarshipDatabase;
	private static CsvReader c = new CsvReader();
	
	/**
	 * Constructor for DataManager class.
	 */
	public DataManager() {}
	
	
	/*******************************************************************
	 * 
	 * 	Methods for User Databases
	 * 
	 *******************************************************************/
	
	/**
	 * Fetches student information from the student CSVFile by username
	 * @param username : String
	 * @return data : String[] of data from the CSVFile 
	 * @throws InvalidUserException 
	 */
	public String[] getStudentData(String username) throws InvalidUserException {
		return (c.getUserDatabaseEntry(username, CsvReader.studentDatabase));
	}
	
	/**
	 * Fetches admin information from the admin CSVFile by username
	 * @param username : String
	 * @return data : String[] of data from the CSVFile 
	 * @throws InvalidUserException 
	 */
	public String[] getAdminData(String username) throws InvalidUserException {
		return (c.getUserDatabaseEntry(username, CsvReader.adminDatabase));
	}
	
	
	/*******************************************************************
	 * 
	 *  Methods for Scholarship Database
	 * 
	 *******************************************************************/
	
	/**
	 * Loads the entire scholarship database into memory,
	 * updates the databaseCounter
	 */
	public static void loadScholarshipData(){
		masterScholarshipDatabase = c.getDatabase(CsvReader.scholarshipDatabase);
		scholarshipCounter = c.getDatabaseCounter();

	}
	/**
	 * returns master scholarship database as List<String[]>
	 * @return masterScholarshipDatabase : List<String[]> of scholarship data
	 */
	public static List<String[]> getScholarshipData(){
		return(masterScholarshipDatabase);
		
	}
	
	/**
	 *  ************DAVID CURATED FUNCTION HERE
	 * Loads the entire scholarship database into memory,
	 * updates the databaseCounter, and
	 * returns data as List<String[]>
	 * @return databaseData : List<String[]> of database data
	 */
	public List<String[]> getScholarshipDataByID(int studentID){
		List<String[]> curatedList = masterScholarshipDatabase;

		// CURATING CODE HERE //
		return curatedList;
	}
	
	
	public String getScholarshipName(int scholarshipID){
		String sName = "";
		for(int i = 0; i < masterScholarshipDatabase.size(); i++) {
			if(Integer.valueOf(masterScholarshipDatabase.get(i)[0]) == scholarshipID) {
				sName = masterScholarshipDatabase.get(i)[1];
				
			}
		}
		return sName;
	}
	
	public static int getScholarshipCounter() {
		return(scholarshipCounter);
	}
	
	public void addScholarshipEntry(String[] scholarshipData) {
		masterScholarshipDatabase.add(scholarshipData);
	}
	
	public void updateScholarshipEntry(int scholarshipID, String[] scholarshipData) {
		for(int i = 0; i < masterScholarshipDatabase.size(); i++) {
			if(masterScholarshipDatabase.get(i)[0] == String.valueOf(scholarshipID)) {
				masterScholarshipDatabase.set(i, scholarshipData);
			}
		}
		
	}
	/*******************************************************************
	 * 
	 *  Methods for Application Database
	 * 
	 *******************************************************************/
	/**
	 * Loads the entire application database and saves into masterApplicationDatabase attribute,
	 * sets the ApplicationDatabaseCounter
	 */
	public static void loadApplicationData(){
		masterApplicationDatabase = c.getDatabase(CsvReader.applicationDatabase);
		applicationCounter = c.getDatabaseCounter();
	}
	

	/**
	 * Returns the masterApplicationDatabase
	 * @return masterApplicationDatabase : List<String[]>
	 */
	public static List<String[]> getApplicationData(){
		return(masterApplicationDatabase);
	}
	
	/**
	 * Takes a studentID and the masterApplicationDatabase in List<String[]> form
	 * and returns a List<String[]> of application data specific to that StudentID 
	 * @param studentID
	 * @return List<String[]> of filtered application data
	 */
	public List<String[]> getApplicationDataByID(int studentID){
		List<String[]> dataList = new ArrayList<String[]>();
		
		for(int i = 0; i < masterApplicationDatabase.size(); i++) {
			String[] l = masterApplicationDatabase.get(i);
			System.out.println(String.join(",", l));
			if(masterApplicationDatabase.get(i)[1].equals(String.valueOf(studentID))) {
				dataList.add(masterApplicationDatabase.get(i));
			}		
		}
		return dataList;
	}
	
	/**
	 * Takes a status and the masterApplicationDatabase in List<String[]> form
	 * and returns a List<String[]> of application data specific to that status 
	 * @param status : String 
	 * @return List<String[]> of filtered application data
	 */
	public List<String[]> getApplicationDataByStatus(String status){
		List<String[]> dataList = new ArrayList<String[]>();
		for(int i = 0; i < masterApplicationDatabase.size(); i++) {
			if(masterApplicationDatabase.get(i)[4].equals(status)) {
				dataList.add(masterApplicationDatabase.get(i));
			}		
		}
		return dataList;
	}
	
	public static int getApplicationCounter() {
		return(applicationCounter);
	}

	public void addApplicationEntry(String[] applicationData) {
		masterApplicationDatabase.add(applicationData);
	}
	public void updateApplicationEntry(int applicationID, String[] applicationData) {
		for(int i = 0; i < masterApplicationDatabase.size(); i++) {
			if(masterApplicationDatabase.get(i)[0] == String.valueOf(applicationData)) {
				masterApplicationDatabase.set(i, applicationData);
			}
		}
		
	}
	/*********************************************************************************
	 * 
	 * Methods for CsvReader Functionality
	 * 
	 *********************************************************************************/
	
	/**
	 * Handles data synchronization on program exit 
	 * Calls functions to update data for specified database and invokes CsvReader to write the file
	 * @param databaseName
	 * @param databaseData
	 */
//	public void saveDatabaseOnExit(String databaseName, List<String[]> databaseData) {
//		List<String[]> updatedDatabase = updateData(databaseName, databaseData);
//		c.writeDatabase(databaseName, updatedDatabase);	
//	}
	public void saveDatabaseOnExit(String databaseName) {
		List<String[]> databaseData = null;
		// write appropriate header for database
		if(databaseName == CsvReader.scholarshipDatabase) {
			databaseData = masterScholarshipDatabase;
		} else if(databaseName == CsvReader.applicationDatabase) {
			databaseData = masterScholarshipDatabase;
		}
		
		// if null (never initialized) throw error
		if(databaseData == null) {
			NullPointerException e = new NullPointerException();
			throw e;
		}
		
		List<String[]> updatedDatabase = updateData(databaseName, databaseData);
		c.writeDatabase(databaseName, updatedDatabase);	
	}
	
	
	/**
	 * updateData
	 * 
	 * Checks and fixes coherency between data in saved file and data from current Session
	 * 
	 * @param databaseName : String
	 * @param databaseData : List<String[]>
	 */
	private List<String[]> updateData(String databaseName, List<String[]> freshDatabase) {
		List<String[]> data = new ArrayList<String[]>();
		
		// get database from file for comparison
		List<String[]> staleDatabase = c.getDatabase(databaseName);
		
		int newLen = freshDatabase.size();
		int oldLen = staleDatabase.size();
		int i = 0;
		int j = 0;
		
		while((i < newLen) & (j < oldLen)) {
			String[] newLine = freshDatabase.get(i);
			String[] oldLine = staleDatabase.get(j);
			
			// check for empty lines, skip if found
			if(newLine[0] == "") {
				i++;
				continue;
			} else if (oldLine[0] == "") {
				j++;
				continue;
			}
			
			// get integer values of id numbers
				int newID = Integer.valueOf(newLine[0]);
				int oldID = Integer.valueOf(oldLine[0]);
				
			if(newID == oldID) {
				// case 1: same ID number
				// add data from new, increment both i and j
				data.add(newLine);
				i++;
				j++;
				continue;

			} else if((newID > oldID) & (j < oldLen)) {
				// case 2: ID number of new > old
				// add data from old, increment j
					data.add(oldLine);
					j++;
					continue;
	
			} else {
				// case 3: ID number of new < old
				// add data from new, increment i
					data.add(newLine);
					i++;
					continue;
			}
		}
		
		// if list lengths are not equal
		// add all remaining data from the longer list 
		if(i > j) {
			while(i < newLen) {
				String[] newLine = freshDatabase.get(i);
				data.add(newLine);
				i++;
			}
		} else if(i < j) {
			while(j < oldLen) {
				String[] oldLine = staleDatabase.get(j);
				data.add(oldLine);
				j++;
			}
			
		}
			
		return data;
	}



	
	
}
