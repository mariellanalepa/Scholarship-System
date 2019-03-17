package model;


import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.StringProperty;
import javafx.scene.control.TableColumn;

public class ApplicationFactory {
	private List<String[]> applicationData;
	private List<Application> applicationArray;
	private int appArraySize;
	public static int counter = 0;
	
	
	public ApplicationFactory(){
		CsvReader c = new CsvReader();
		this.applicationData = c.getApplicationData();
		List<Application> applicationList = new ArrayList<Application>();
		for(int i = 0; i < applicationData.size(); i++) {
				Application s = new Application(applicationData.get(i));
				applicationList.add(s);
			
		}
		this.applicationArray = applicationList;
		this.appArraySize = applicationArray.size();
		for(int i = 0; i < applicationArray.size(); i++) {
			Application a = applicationArray.get(i);
			System.out.println(c.getScholarshipName(Integer.valueOf(a.getScholarshipId())));
			a.setScholarshipName(c.getScholarshipName(Integer.valueOf(a.getScholarshipId())));
		}
	}
	
	public ApplicationFactory(int studentID){
		CsvReader c = new CsvReader();
		this.applicationData = c.getApplicationData(studentID);
		List<Application> applicationList = new ArrayList<Application>();
		for(int i = 0; i < applicationData.size(); i++) {
				Application s = new Application(applicationData.get(i));
				applicationList.add(s);
		}
		this.applicationArray = applicationList;
		this.appArraySize = applicationArray.size();
		System.out.println(this.appArraySize);
		CsvReader s = new CsvReader();
		for(int i = 0; i < applicationArray.size(); i++) {
			Application a = applicationArray.get(i);
			System.out.println(s.getScholarshipName(Integer.valueOf(a.getScholarshipId())));
			a.setScholarshipName(s.getScholarshipName(Integer.valueOf(a.getScholarshipId())));
		}
	}
	
	public int getApplicationListLength() {
		return this.appArraySize;
	}
	
	public List<Application> getApplicationArray(){
		return this.applicationArray;
	}
	
	public static int getCounter() {
		return counter;
	}
	public static void incrementCounter() {
		counter++;
	}
	static void setCounter(int counterValue) {
		counter = counterValue;
	}
	


	
	

}
