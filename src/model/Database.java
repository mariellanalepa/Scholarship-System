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

/************************ CSV formats ****************************/
// STUDENT CSV: username,StudentID,FName,LName,Type,Year,Faculty,Department,GPA
// APPLICATION CSV : applicationID,studentID,scholarshipID,dateAdded,status
// SCHOLARSHIP CSV: IDNumber,Name,Donor,Deadline(yyyy-MM-dd HH:mm:ss),Amount,Number,ReqFac,ReqDept	RecType,ReqGPA,ReqYear,Status,DatePosted(yyyy-MM-dd HH:mm:ss)
// AWARD HISTORY CSV: StudentID,ScholarshipID

/**
 * Database class. Contains all functions that perform I/O on the csv files, and is the abstract store
 * of objects corresponding to database entries (e.g., Applications, Scholarships, Users, etc.)
 * @author Natalie, Mariella
 */
public class Database {
	// Counters to use for assigning unique database IDs to scholarships
	// and applications
	private int scholarshipIdCounter = 0;
	private int applicationIdCounter = 0;
	
	// CSV file names (our "database")
	final private String adminDatabase = "res/adminDatabase.csv";
	final private String studentDatabase = "res/studentDatabase.csv";
	final private String scholarshipDatabase = "res/scholarshipDatabase.csv";
	final private String applicationDatabase = "res/applicationDatabase.csv";
	final private String offerDatabase = "res/offerDatabase.csv";
	final private String awardHistoryDatabase = "res/awardHistoryDatabase.csv";
	final private String applicationDatabaseHeader ="applicationID,studentID,scholarshipID,dateAdded,status\n";
	final private String scholarshipDatabaseHeader = "IDNumber,Name,Donor,Deadline(dd/MM/yyyy HH:mm:ss),Amount,Number,ReqFac,ReqDept,RecType,ReqGPA,ReqYear,Status,DatePosted(dd/MM/yyyy HH:mm:ss)\n";
	final private String offerDatabaseHeader = "StudentID,ScholarshipName,OfferStatus\n";
	final private String awardHistoryHeader = "StudentID,ScholarshipID,Status\n";
	
	// Maps to store Database instance variables (User, Scholarship, 
	// Application, and Award objects)
	private HashMap<Integer,Admin> admins;
	private HashMap<Integer,Student> students;
	private HashMap<String,User> users;	//users key is userName
	private HashMap<Integer,Scholarship> scholarshipsById;
	private HashMap<String,Scholarship> scholarshipsByName;
	private HashMap<Integer,Application> applicationsById;
	private ArrayList<Offer> offers;
	private ArrayList<Award> awards;
	
	
	/**
	 * Database class to create our "virtual	object database" with
	 * which we can interact in Object-Oriented manner. Creates 
	 * objects for all Database entries, including Users (Admins and
	 * Students), Applications, Scholarships, and Awards.
	 */
	public Database() {
		
		// Initialize User map
		this.users = new HashMap<String,User>();
		
		// Initialization must be done in this order so that objects
		// instantiated later can receive references of earlier
		// instantiated objects
		initAdmins();
		initStudents();
		initScholarships();
		initApplications();
		initOffers();
		initAwardHistory();
	}
	
	/**
	 * Initialize database admin entries to Admin objects
	 */
	private void initAdmins() 
	{
		// Initialize hashmap
		admins = new HashMap<Integer,Admin>();
		
		// Read the CSV file to extract attributes for
		// Admin object, and create object
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
		
		// Read the CSV file to extract attributes for
		// Student object, and create object
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
		
		// Read the CSV file to extract attributes for
		// Scholarship object, and create object
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
				
				if (attributes.length != 0) {
					//Create Scholarship object
					Scholarship scholarship = new Scholarship(this,attributes);
					scholarshipsById.put(scholarship.getId(),scholarship);
					scholarshipsByName.put(scholarship.getName(), scholarship);
					
					//Keep track of the highest numbered ID so always generate unique ID
					if (scholarship.getId() >= this.scholarshipIdCounter) {
						this.scholarshipIdCounter = scholarship.getId() + 1;
					}
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
		this.applicationsById = new HashMap<Integer,Application>();
		
		// Read the CSV file to extract attributes for
		// Application object, and create object
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
				
				if (attributes.length != 0) {
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
					this.applicationsById.put(application.getApplicationId(),application);
					
					//Keep track of the highest numbered ID so always generate unique ID
					if (application.getApplicationId() >= this.applicationIdCounter)	{
						this.applicationIdCounter = application.getApplicationId() + 1;
					}
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
	
	private void initOffers() 
	{
		//Initialize ArrayList of offers
		this.offers = new ArrayList<Offer>();

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
					if (scholarship.getStatus().compareTo("Open") == 0) {		//scholarship is still open
						for (Student student : scholarship.getTopCandidates()) {
							if (student != null) {	
								Offer offer = new Offer(scholarship, student, "open");	//create offer for each student in top candidates
								student.addOffer(offer);	 
								this.offers.add(offer);	//add the offers to the list of offers
								for (Award award : student.getAwards()) {
									if (offer.getScholarshipName().compareTo(award.getScholarship().getName()) == 0) { 
										award.setStatus("Offered");	//change status to "Offered" 
									}
								}
							 
							}
						
						}
					}
					try { 
						scholarship.setStatus("Closed");	
					} catch (Exception e) { 
						e.printStackTrace(); 
					}
				}				
			} catch (ParseException e) {	e.printStackTrace(); }
		}
		
		// Now Read the CSV file to extract attributes for
		// Offers that have just been assigned, and create 
		// Offer object
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
		}//
		
	}
	
	private void initAwardHistory() 
	{
		//Initialize ArrayList of awards
		this.awards = new ArrayList<Award>();
					
		// Read the CSV file to extract attributes for
		// Award object, and create object
		String[] attributes = new String[3];
		BufferedReader buffread = null;	
		String line = "";
		String delimiter = ",";
		try {
			File f = new File(awardHistoryDatabase);
			buffread = new BufferedReader(new FileReader(f));
			/*Discard first line -- header data */
			buffread.readLine();
			while ((line = buffread.readLine()) != null) {
				attributes = line.split(delimiter);
				//Create Award object		
				Award award = new Award(this, attributes);
				Student student = students.get(award.getStudentID());
				award.setStudent(student);
				Scholarship scholarship = scholarshipsById.get(award.getScholarshipID());
				award.setScholarship(scholarship);
				String status = award.getStatus();
				award.setStatus(status);
				student.addAward(award); // Add award to the list maintained by student object
				this.awards.add(award); // Add award to central database
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
		// once a student accepts an offer, the award needs to be added to the award list
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
	public HashMap<Integer,Application> getApplicationsById() {
		return this.applicationsById;
	}
	
	/**
	 * Get the application ID counter to determine next ID number to assign
	 * @return int value of next application ID
	 */
	public int getApplicationIdCounter() {
		return this.applicationIdCounter;
	}
	
	/**
	 * Get the scholarship ID counter to determine next ID number to assign
	 * @return int value of next scholarship ID
	 */
	public int getScholarshipIdCounter() {
		return this.scholarshipIdCounter;
	}
	
	/**
	 * Main entry point to write all database to file
	 */
	public void close() {
		this.writeApplicationsToDatabase();
		this.writeScholarshipsToDatabase();
		this.writeOffersToDatabase();
		this.writeAwardHistoryToDatabase();
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
			for (Application application : applicationsById.values())
			{
				String line = String.join(",",application.toStringArray());
				line += "\n";
				bw.write(line);
			}
			/*for (Scholarship scholarship: scholarshipsById.values())
			{
				for (Application application: scholarship.getApplications())
				{
					String line = String.join(",",application.toStringArray());
					line += "\n";
					bw.write(line);
				}
				
			}*/

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
	

	void writeAwardHistoryToDatabase() {
		BufferedWriter bw = null;
		try {
			File f = new File(awardHistoryDatabase);
			
			// filewriter in *overwrite mode*
			bw = new BufferedWriter(new FileWriter(f, false)); 
			
			// write appropriate header for database
			bw.write(awardHistoryHeader);
			
		
			for (Student student: students.values()) {
				if (student.getAwards() != null) {

					for (Award award : student.getAwards())	{
						String line = String.join(",",award.toStringArray());
						line += "\n";
						bw.write(line);
					}
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
		
		// Increment scholarship counter 
		this.scholarshipIdCounter++;
		this.scholarshipsById.put(scholarship.getId(),scholarship);
		this.scholarshipsByName.put(scholarship.getName(),scholarship);
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
		//Increment application id counter
		this.applicationIdCounter++;
		
		this.applicationsById.put(application.getApplicationId(),application);
		//Find associated student, add application to their list of applications
		int studentId = application.getStudentId();
		
		//Add to student's list of applications
		Student student = this.students.get(studentId);
		student.addApplication(application);
		
		//Find associated scholarship, add application to its list of applications
		int scholarshipId = application.getScholarshipId();
		//Add to scholarship's list of applications
		Scholarship scholarship = this.scholarshipsById.get(scholarshipId);
		scholarship.addApplication(application);
	}
	
	/**
	 * Method to retrieve award offers based on Student id
	 * @param id - Student id 
	 * @return List of offers (empty if none)
	 */
	public ArrayList<Offer> getOffersByStudentID(Integer id) {
		
		ArrayList<Offer> studentOffers = new ArrayList<Offer>();
		for (Offer offer : this.getOffers()) {
			if (offer.getStudentID() == id) {
				studentOffers.add(offer);
			}
		}
		return studentOffers;
		
		
	}
	
	/**
	 * Method to retrieve all award offers
	 * @return List of all offers
	 */
	public ArrayList<Offer> getOffers() {
		return this.offers;
	}
	
}
	
