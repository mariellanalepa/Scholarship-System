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
		List<Application> ls = new ArrayList<Application>();
		for(int i = 0; i < applicationData.size(); i++) {
				Application s = new Application(applicationData.get(i));
				ls.add(s);
			
		}
		this.applicationArray = ls;
		this.appArraySize = applicationArray.size();
	}
	public ApplicationFactory(int studentID){
		CsvReader c = new CsvReader();
		this.applicationData = c.getApplicationData(studentID);
		List<Application> ls = new ArrayList<Application>();
		for(int i = 0; i < applicationData.size(); i++) {
				Application s = new Application(applicationData.get(i));
				ls.add(s);
			
		}
		this.applicationArray = ls;
		this.appArraySize = applicationArray.size();
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
