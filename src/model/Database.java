package model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

// STUDENT CSV: username,StudentID,FName,LName,Type,Year,Faculty,Department,GPA
// APPLICATION CSV : applicationID	studentID	scholarshipID	dateAdded	status
// SCHOLARSHIP CSV: IDNumber	Name	Donor	Deadline(dd/MM/yyyy HH:mm:ss)	Amount	Number	ReqFac	ReqDept	RecType	ReqGPA	ReqYear	Status	DatePosted(dd/MM/yyyy HH:mm:ss)
/**
 * Database class. Contains all functions that perform I/O on the csv files, and is the abstract store
 * of objects corresponding to database entries (e.g., Applications, Scholarships, Users, etc.)
 * @author Natalie
 */

public class Database {
	private int scholarshipIdCounter = 0;
	private int applicationIdCounter = 0;
	
	final private String adminDatabase = "res/adminDatabase.csv";
	final private String studentDatabase = "res/studentDatabase.csv";
	final private String scholarshipDatabase = "res/scholarshipDatabase.csv";
	final private String applicationDatabase = "res/applicationDatabase.csv";
	final private String offerDatabase = "res/offersDatabase.csv";
	final private String applicationDatabaseHeader ="applicationID,studentID,scholarshipID,dateAdded,status\n";
	final private String scholarshipDatabaseHeader = "IDNumber,Name,Donor,Deadline(dd/MM/yyyy HH:mm:ss),Amount,Number,ReqFac,ReqDept,RecType,ReqGPA,ReqYear,Status,DatePosted(dd/MM/yyyy HH:mm:ss)\n";
	final private String offerDatabaseHeader = "StudentID,ScholarshipName,OfferStatus\n";
	//Maps to store DB objects
	private HashMap<Integer,Admin> admins;
	private HashMap<Integer,Student> students;
	private HashMap<String,User> users;	//users key is userName
	private HashMap<Integer,Scholarship> scholarshipsById;
	private HashMap<String,Scholarship> scholarshipsByName;
	private HashMap<Integer,Application> applications;
	private ArrayList<Offer> offers;
	
	
	
	public Database() {
		
		this.users = new HashMap<String,User>();
		//Initialization must be done in this order 
		initAdmins();
		initStudents();
		initScholarships();
		initApplications();
		initOffers();
	}
	
	/**
	 * Initialize database admin entries to Admin object  s
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
		
		String[] attributes = new String[3];
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
		scholarshipsById = new HashMap<Integer,Scholarship>();
		scholarshipsByName = new HashMap<String,Scholarship>();
		
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
				Scholarship scholarship = new Scholarship(this,attributes);
				scholarshipsById.put(scholarship.getId(),scholarship);
				scholarshipsByName.put(scholarship.getName(), scholarship);
				
				//Keep track of the highest numbered ID so always generate unique ID
				if (scholarship.getId() > this.scholarshipIdCounter)
				{
					this.scholarshipIdCounter = scholarship.getId() + 1;
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
				Scholarship scholarship = scholarshipsById.get(application.getScholarshipId());
				scholarship.addApplication(application);
				//Get Student that this Application is associated with,
				// and add Application to that Student's Application list
				Student student = students.get(application.getStudentId());
				student.addApplication(application);
				//Finally, add to central database
				this.applications.put(application.getApplicationId(),application);
				
				//Keep track of the highest numbered ID so always generate unique ID
				if (application.getApplicationId() > this.applicationIdCounter)
				{
					this.applicationIdCounter = application.getApplicationId() + 1;
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
	
	private void initOffers() {
		//Initialize ArrayList of offers
			this.offers = new ArrayList<Offer>();
			
			String[] attributes = new String[3];
			BufferedReader buffread = null;	
			String line = "";
			String delimiter = ",";
			try {
				File f = new File(offerDatabase);
				buffread = new BufferedReader(new FileReader(f));
				/*Discard first line -- header data */
				buffread.readLine();
				while ((line = buffread.readLine()) != null) {
					attributes = line.split(delimiter);
					//Create Offer object		
					Offer offer = new Offer(this, attributes);
					
					Student student = students.get(offer.getStudentID());
					if (student != null) student.addOffer(offer);		 
				
					//Finally, add to central database
					this.offers.add(offer);
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
				/*
				 * filter through scholarships. If they are open and should be closed, their status is updated to
				 * closed and the top recipients at that moment are given offers
				 */
				//Go through scholarships compare their closing date/time with current date/time
				for (Scholarship scholarship : this.getScholarshipsById().values()) {
					try {
						Date deadline = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(scholarship.getDeadline());
						Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(dateTimeFormat(LocalDateTime.now()));
						if (deadline.compareTo(now) <= 0) {	//deadline has passed
							
							if (scholarship.getStatus() == "Open") {		//scholarship is still open
							for (Student student : scholarship.getTopCandidates()) {
								if (student != null) {	
									Offer offer = new Offer(scholarship, student, "open");	//create offer for each student in top candidates
									student.addOffer(offer);	 }
								}
							}
							try { scholarship.setStatus("Closed");	}	//set to closed
							catch (Exception e) { e.printStackTrace(); }
						}				
					} catch (ParseException e) {	e.printStackTrace(); }
				}
			}
	
	/*
	 * helper method to set current date and time into string format
	 * 		
	 */
	public String dateTimeFormat(LocalDateTime time) {
		String dateTime = time.toString();
		String year = dateTime.substring(0, 4)+ "-";
		String month = dateTime.substring(5, 7) + "-";
		String day = dateTime.substring(8, 10) + " ";
		String hm = dateTime.substring(11, 16);
		String dateTimeString = year + month + day + hm;
		return dateTimeString;
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
	public HashMap<Integer,Scholarship> getScholarshipsById() {
		return this.scholarshipsById;
	}
	
	/**
	 * Getter method for scholarships in database
	 * @return Hashmap of scholarships, where key is Scholarship name
	 */
	public HashMap<String,Scholarship> getScholarshipsByName() {
		return this.scholarshipsByName;
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
	 * Get the application ID counter to determine next ID number to assign
	 * @return int value of next application ID
	 */
	public int getApplicationIdCounter() {
		int id = this.applicationIdCounter;
		//Since this was called, means an application has taken this ID,
		//need to increment the counter
		this.applicationIdCounter++;
		return id;
	}
	
	/**
	 * Get the scholarship ID counter to determine next ID number to assign
	 * @return int value of next scholarship ID
	 */
	public int getScholarshipIdCounter() {
		int id = this.scholarshipIdCounter;
		//Since this was called, means a scholarship has taken this ID,
		//need to increment the counter
		this.scholarshipIdCounter++;
		return id;
		
	}
	
	/**
	 * Main entry point to write all database to file
	 */
	public void close() {
		this.writeApplicationsToDatabase();
		this.writeScholarshipsToDatabase();
		this.writeOffersToDatabase();
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
			for (Scholarship scholarship: scholarshipsById.values())
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
			for (Scholarship scholarship: scholarshipsById.values())
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
	 * Writes updated database to file before closing
	 */
	void writeOffersToDatabase() {
		BufferedWriter bw = null;
		try {
			File f = new File(offerDatabase);
			
			// filewriter in *overwrite mode*
			bw = new BufferedWriter(new FileWriter(f, false)); 
			
			// write appropriate header for database
			bw.write(offerDatabaseHeader);
			
			//write contents of map
			for (Student student: students.values())
			{
				for (Offer offer : student.getOffers())
				{
					String line = String.join(",",offer.toStringArray());
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
		this.scholarshipsById.put(scholarship.getId(),scholarship);
	}
	
	/**
	 * Method to delete Scholarship to database
	 * @param scholarship - Scholarship object
	 */
	public void deleteScholarship(Scholarship scholarship) {
		this.scholarshipsById.remove(scholarship.getId(),scholarship);
		this.scholarshipsByName.remove(scholarship.getName(),scholarship);
	}
	
	/**
	 * Method to add Application to database
	 * @param application - Application object
	 */
	public void addApplication(Application application) {
		this.applications.put(application.getApplicationId(),application);
		//Find associated student, add application to their list of applications
		int studentId = application.getStudentId();
		if (studentId != 0)
		{
			Student student = this.students.get(studentId);
			student.addApplication(application);
		}
		//Find associated scholarship, add application to its list of applications
		int scholarshipId = application.getScholarshipId();
		if (scholarshipId != 0)
		{
			Scholarship scholarship = this.scholarshipsById.get(scholarshipId);
			scholarship.addApplication(application);
		}		
	}
	
	public ArrayList<Offer> getOffersByStudentID(Integer id) {
		
		ArrayList<Offer> studentOffers = new ArrayList<Offer>();
		for (Offer offer : this.getOffers()) {
			if (offer.getStudentID() == id) {
				studentOffers.add(offer);
			}
		}
		return studentOffers;
		
		
	}
	
	public ArrayList<Offer> getOffers() {
		return this.offers;
	}
	
	/**
	 * Main method for testing this class
	 * @param args -- from command line (none used)
	 */
	public static void main(String[] args) {
		Database db = new Database();
		
		for (Student student : db.getStudents().values())
		{
			System.out.println(student.getFirstName());
			
			for (Application application : student.getApplications())
			{
				System.out.println(db.getScholarshipsById().get(application.getScholarshipId()).getName());
			}
		}
		
		db.close();
			
	}
	
	
}
	
