package model;


import java.util.List;

import javafx.scene.control.TableColumn;

public class Scholarship {
	private List<String[]> scholarshipData;
	private String donorCol;
	private String deadlineColumn; 
	private String amtCol; 
	private String numCol; 
	private String facCol;
	private String deptCol; 
	private String typeCol; 
	private String gpaCol; 
	private String yearCol;
	
	
	public Scholarship(){
		CsvReader c = new CsvReader();
		List<String[]> scholarshipData = c.getScholarshipData();
		for(int i = 0; i < scholarshipData.size(); i++) {
			for(int j = 0; j < scholarshipData.size(); j++) {
				System.out.println(scholarshipData.get(i)[j]);
			}
		}
	}
	
	

}
