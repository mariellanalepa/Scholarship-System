package model;

public class Application {
	
	private int studentID;
	private int awardID;
	private String status;
	private int priority;
	
	public Application(int studentID, int awardID)
	{
		this.studentID = studentID;
		this.awardID = awardID; 
		this.status = "active";
	}
	
	public void setPriority(int value) 
	{
		this.priority = value;
	}

}
