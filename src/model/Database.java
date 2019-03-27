package model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// STUDENT CSV: username,StudentID,FName,LName,Type,Year,Faculty,Department,GPA
// APPLICATION CSV : applicationID	studentID	scholarshipID	dateAdded	status
// SCHOLARSHIP CSV: IDNumber	Name	Donor	Deadline(dd/MM/yyyy HH:mm:ss)	Amount	Number	ReqFac	ReqDept	RecType	ReqGPA	ReqYear	Status	DatePosted(dd/MM/yyyy HH:mm:ss)
/**
 * Database class. Contains all functions that perform I/O on the csv files, and is the abstract store
 * of objects corresponding to database entries (e.g., Applications, Scholarships, Users, etc.)
 * @author Natalie
 */

public class Database {
	final static String adminDatabase = "res/adminDatabase.csv";
	final static String studentDatabase = "res/studentDatabase.csv";
	final static String scholarshipDatabase = "res/scholarshipDatabase.csv";
	final static String applicationDatabase = "res/applicationDatabase.csv";
	
	private static String applicationDatabaseHeader ="applicationID,studentID,scholarshipID,dateAdded,status\n";
	private static String scholarshipDatabaseHeader = "IDNumber,Name,Donor,Deadline(dd/MM/yyyy HH:mm:ss),Amount,Number,ReqFac,ReqDept,RecType,ReqGPA,ReqYear,Status,DatePosted(dd/MM/yyyy HH:mm:ss)\n";
	
	//Maps to store DB objects
	private HashMap<Integer,Admin> admins;
	private HashMap<Integer,Student> students;
	private HashMap<String,User> users;	//users key is userName
	private HashMap<Integer,Scholarship> scholarships;
	private HashMap<Integer,Application> applications;
	
	public Database() {
		
		this.users = new HashMap<String,User>();
		//Initialization must be done in this order 
		initAdmins();
		initStudents();
		initScholarships();
		initApplications();
	}
	
	/**
	 * Initialize database admin entries to Admin objects
	 */
	private void initAdmins() 
	{
		//Initialize hashmap
		admins = new HashMap<Integer,Admin>();
		
		String[] attributes = new String[8];
		BufferedReader buffread = null;
		String line = "";
		String delimiter = ",";
		try {
			File f = new File(adminDatabase);
			buffread = new BufferedReader(new FileReader(f));
			/*Discard first line -- header data */
			buffread.readLine();
			while ((line = buffread.readLine()) != null) {
				attributes = line.split(delimiter);
				
				//Create Admin object
				Admin admin = new Admin(attributes);
				admins.put(admin.getID(),admin);
				//Store Admin in users, with username as key
				users.put(admin.getUserName(), admin);
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
	
	/**
	 * Initialize database student entries to User objects
	 */
	private void initStudents() 
	{
		//Initialize hashmap
		students = new HashMap<Integer,Student>();
		
		String[] attributes = new String[8];
		BufferedReader buffread = null;
		String line = "";
		String delimiter = ",";
		try {
			File f = new File(studentDatabase);
			buffread = new BufferedReader(new FileReader(f));
			/*Discard first line -- header data */
			buffread.readLine();
			while ((line = buffread.readLine()) != null) {
				attributes = line.split(delimiter);
				
				//Create Student object
				Student student = new Student(attributes);
				students.put(student.getID(),student);	
				//Put student in the users list, with username as key
				users.put(student.getUserName(), student);
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
	
	/**
	 * Initialize database scholarship entries to Scholarship objects
	 */
	private void initScholarships() 
	{
		//Initialize hashmap
		scholarships = new HashMap<Integer,Scholarship>();
		
		String[] attributes = new String[8];
		BufferedReader buffread = null;
		String line = "";
		String delimiter = ",";
		try {
			File f = new File(scholarshipDatabase);
			buffread = new BufferedReader(new FileReader(f));
			/*Discard first line -- header data */
			buffread.readLine();
			while ((line = buffread.readLine()) != null) {
				attributes = line.split(delimiter);
				
				//Create Scholarship object
				Scholarship scholarship = new Scholarship(attributes);
				scholarships.put(scholarship.getId(),scholarship);
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
	
	/**
	 * Initialize database application entries to Application objects
	 */
	private void initApplications() 
	{	
		//Initialize hashmap
		this.applications = new HashMap<Integer,Application>();
		
		String[] attributes = new String[8];
		BufferedReader buffread = null;
		String line = "";
		String delimiter = ",";
		try {
			File f = new File(applicationDatabase);
			buffread = new BufferedReader(new FileReader(f));
			/*Discard first line -- header data */
			buffread.readLine();
			while ((line = buffread.readLine()) != null) {
				attributes = line.split(delimiter);
				
				//Create Application object
				Application application = new Application(this, attributes);
				
				//Get Scholarship that this Application is associated with,
				// and add Application to that Scholarship's Application list
				Scholarship scholarship = scholarships.get(application.getScholarshipId());
				scholarship.addApplication(application);
				//Get Student that this Application is associated with,
				// and add Application to that Student's Application list
				Student student = students.get(application.getStudentId());
				student.addApplication(application);
				//Finally, add to central database
				this.applications.put(application.getApplicationId(),application);
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
	
	/**
	 * Getter method for students in database
	 * @return Hashmap of students, where key is Student ID
	 */
	public HashMap<Integer,Student> getStudents() {
		return this.students;
	}
	
	/**
	 * Getter method for scholarships in database
	 * @return Hashmap of scholarships, where key is Scholarship ID
	 */
	public HashMap<Integer,Scholarship> getScholarships() {
		return this.scholarships;
	}
	
	/**
	 * Getter method for admins in database
	 * @return Hashmap of admins, where key is user ID
	 */
	public HashMap<Integer,Admin> getAdmins() {
		return this.admins;
	}
	
	/**
	 * Getter method for users in database
	 * @return Hashmap of users, where key is username
	 */
	public HashMap<String,User> getUsers() {
		return this.users;
	}
	
	/**
	 * Getter method for applications in database
	 * @return Hashmap of applications, where key is Application ID
	 */
	public HashMap<Integer,Application> getApplications() {
		return this.applications;
	}
	
	/**
	 * Main entry point to write all database to file
	 */
	public void close() {
		this.writeApplicationsToDatabase();
		this.writeScholarshipsToDatabase();
	}
	
	/**
	 * Writes updated database to file before closing
	 */
	void writeScholarshipsToDatabase() {
		BufferedWriter bw = null;
		try {
			File f = new File(scholarshipDatabase);
			
			// filewriter in *overwrite mode*
			bw = new BufferedWriter(new FileWriter(f, false)); 
			
			// write appropriate header for database
			bw.write(scholarshipDatabaseHeader);
			
			//write contents of map
			for (Scholarship scholarship: scholarships.values())
			{
				String line = String.join(",",scholarship.toStringArray());
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
	 * Writes updated database to file before closing
	 */
	void writeApplicationsToDatabase() {
		BufferedWriter bw = null;
		try {
			File f = new File(applicationDatabase);
			
			// filewriter in *overwrite mode*
			bw = new BufferedWriter(new FileWriter(f, false)); 
			
			// write appropriate header for database
			bw.write(applicationDatabaseHeader);
			
			//write contents of map
			for (Scholarship scholarship: scholarships.values())
			{
				for (Application application: scholarship.getApplications())
				{
					String line = String.join(",",application.toStringArray());
					line += "\n";
					bw.write(line);
				}
				
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
	 * Method to add Scholarship to database
	 * @param scholarship - Scholarship object
	 */
	public void addScholarship(Scholarship scholarship) {
		this.scholarships.put(scholarship.getId(),scholarship);
	}
	
	/**
	 * Method to delete Scholarship to database
	 * @param scholarship - Scholarship object
	 */
	public void deleteScholarship(Scholarship scholarship) {
		this.scholarships.remove(scholarship.getId(),scholarship);
	}
	
	/**
	 * Method to add Application to database
	 * @param application - Application object
	 */
	public void addApplication(Application application) {
		this.applications.put(application.getApplicationId(),application);
	}
	
	
	
	/**
	 * Opens specified CSVFile, reads each line splitting on comma, 
	 * and returns the result as a List<String[]>
	 * @param databaseName
	 * @return List<String[]> Database
	 *//*
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
				 first line of application and student database are not data
				 * line 1 is header data 
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
		if(databaseName == Database.scholarshipDatabase) {
			DataManager.scholarshipCounter = list.size();
		} else if(databaseName == Database.applicationDatabase) {
			DataManager.applicationCounter = list.size();
		}
		return list;
	}*/
	
	
	/**
	 * Fetches database data from database specified by databaseName whose
	 * ID number matches the given entryIdentifier
	 * @param userID
	 * @param databaseName
	 * @return String[] containing data from csv
	 * @throws InvalidUserException 
	 *//*
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
	}*/
	
	
	


//	public int getDatabaseCounter() {
//		return databaseCounter;
//	}
//
//
//	public void setDatabaseCounter(int databaseCounter) {
//		this.databaseCounter = databaseCounter;
//	}
//	
	
	public static void main(String[] args) {
		Database db = new Database();
		
		for (Student student : db.getStudents().values())
		{
			System.out.println(student.getFirstName());
			
			for (Application application : student.getApplications())
			{
				System.out.println(db.getScholarships().get(application.getScholarshipId()).getName());
			}
		}
		
		db.close();
			
	}
	
	
}
	
