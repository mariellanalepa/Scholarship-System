package model;

public class Application {
	private static int counter = 0;
	private final int applicationID;
	private int studentID;
	private int scholarshipID;
	private String dateSubmitted;
	
	
	//applicationID	scholarshipID	studentID	datesubmitted

	public Application() {
		this.applicationID = counter++;	
	}
	
	/* String[] applicationData = [applicationID,	scholarshipID,	studentID,	datesubmitted]
	 * */
	public boolean saveApplication() {
		String[] applicationData = {String.valueOf(this.applicationID), String.valueOf(this.studentID), String.valueOf(this.scholarshipID), this.dateSubmitted};
		CsvReader c = new CsvReader();
		boolean success = c.addApplicationEntry(applicationData);
		return success;
	}
	int getApplicationID(){
		return applicationID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
		
	}

	public void setScholarshipID(int scholarshipID) {
		this.scholarshipID = scholarshipID;
		
	}
	public void setDateSubmitted(String dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
		
	}
	

}
