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

class CsvReader {
	final static String adminDatabase = "res/adminDatabase.csv";
	final static String studentDatabase = "res/studentDatabase.csv";
	final static String scholarshipDatabase = "res/scholarshipDatabase.csv";
	final static String applicationDatabase = "res/applicationDatabase.csv";
	private int databaseCounter;
	
	private static String applicationDatabaseHeader ="applicationID,studentID,scholarshipID,dateAdded,status\n";
	private static String scholarshipDatabaseHeader = "IDNumber,Name,Donor,Deadline(dd/MM/yyyy HH:mm:ss),Amount,Number,ReqFac,ReqDept,RecType,ReqGPA,ReqYear,Status,DatePosted(dd/MM/yyyy HH:mm:ss)\n";
	
	
	/**
	 * Opens specified CSVFile, reads each line splitting on comma, 
	 * and returns the result as a List<String[]>
	 * @param databaseName
	 * @return List<String[]> Database
	 */
	List<String[]> getDatabase(String databaseName) {
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
		// initialize counters in DataManager
		if(databaseName == CsvReader.scholarshipDatabase) {
			DataManager.scholarshipCounter = list.size();
		} else if(databaseName == CsvReader.applicationDatabase) {
			DataManager.applicationCounter = list.size();
		}
		return list;
	}
	
	
	/**
	 * Fetches database data from database specified by databaseName whose
	 * ID number matches the given entryIdentifier
	 * @param userID
	 * @param databaseName
	 * @return String[] containing data from csv
	 * @throws InvalidUserException 
	 */
	String[] getUserDatabaseEntry(String userID, String databaseName) throws InvalidUserException {
		BufferedReader buffread = null;
		String[] data = null;
		String line = "";
		String delimiter = ",";
		try {
			File f = new File(databaseName);
			buffread = new BufferedReader(new FileReader(f));
			while ((line = buffread.readLine()) != null) {
				data = line.split(delimiter);
				if(data[0].equalsIgnoreCase(userID)) {
					return(data);
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
		// If none of the database entries matched, throw exception
		InvalidUserException e = new InvalidUserException();
		throw e;
	}
	
	
	/**
	 * Writes updated database to file before closing
	 * @param databaseName : String
	 * @param data : List<String[]>
	 */
	void writeDatabase(String databaseName, List<String[]> data) {
		BufferedWriter bw = null;
		try {
			File f = new File(databaseName);
			
			// filewriter in *overwrite mode*
			bw = new BufferedWriter(new FileWriter(f, false)); 
			
			// write appropriate header for database
			if(databaseName == CsvReader.scholarshipDatabase) {
				bw.write(scholarshipDatabaseHeader);
			} else if(databaseName == CsvReader.applicationDatabase) {
				bw.write(applicationDatabaseHeader);
			}
			//write contents of data array
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


//	public int getDatabaseCounter() {
//		return databaseCounter;
//	}
//
//
//	public void setDatabaseCounter(int databaseCounter) {
//		this.databaseCounter = databaseCounter;
//	}
//	
	

	
}
	
