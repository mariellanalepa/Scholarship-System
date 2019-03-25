package model;


import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TableColumn;

public class ScholarshipFactory {
	private static int counter;
	private List<String[]> scholarshipData;
	private List<Scholarship> scholarshipArray;
	private int scholArraySize;
	
	
	public ScholarshipFactory() {
		counter = DataManager.getScholarshipCounter();
		this.scholarshipData = DataManager.getScholarshipData();
		List<Scholarship> ls = new ArrayList<Scholarship>();
		for(int i = 0; i < scholarshipData.size(); i++) {
				Scholarship s = new Scholarship(scholarshipData.get(i));
				ls.add(s);
			
		}
		this.scholarshipArray = ls;
		this.scholArraySize = scholarshipArray.size();
	}
	
	public int getScholarshipListLength() {
		return this.scholArraySize;
	}
	
	public List<Scholarship> getScholarshipArray(){
		return this.scholarshipArray;
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
