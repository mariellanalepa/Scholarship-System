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

class CsvReader {
	private final String adminDatabase = "res/adminDatabase.csv";
	private final String studentDatabase = "res/studentDatabase.csv";
	private final String scholarshipDatabase = "res/scholarshipDatabase.csv";
	private final String applicationDatabase = "res/applicationDatabase.csv";
	private String[] data;
	private List<String[]> databaseData;
	

	private void getDatabase(String databaseName) {
		List<String[]> list = new ArrayList<String[]>();
		BufferedReader buffread = null;
		String line = "";
		String delimiter = ",";
		try {
			File f = new File(databaseName);
			buffread = new BufferedReader(new FileReader(f));
			int i = 0;
			while ((line = buffread.readLine()) != null) {
				if(i == 0) {
					i++;
					continue;
				}
				System.out.println(line);
				list.add(line.split(delimiter));
				i++;
			}
			this.databaseData = list;
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
	
	private void getDatabaseEntry(String entryIdentifier, String databaseName) {
		BufferedReader buffread = null;
		String line = "";
		String delimiter = ",";
		try {
			File f = new File(databaseName);
			buffread = new BufferedReader(new FileReader(f));
			while ((line = buffread.readLine()) != null) {
				String[] data = line.split(delimiter);
				if(data[0].equalsIgnoreCase(entryIdentifier)) {
					this.data = data;
					break;
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
	
	private void addDatabaseEntry(String databaseName, String[] data) {
		BufferedWriter bw = null;
		String line = String.join(",", data);
		line += "\n";
		
		System.out.println(line);
		FileWriter fw = null;
		try {
			File f = new File(databaseName);
			bw = new BufferedWriter(new FileWriter(f, true));
			//fw = new FileWriter(f, true);
			bw.write(line);
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
	
	
	public String[] getStudentData(String username) {
		getDatabaseEntry(username, studentDatabase);
		return this.data;
	}
	public String[] getAdminData(String username) {
		getDatabaseEntry(username, adminDatabase);
		return this.data;
	}
	public List<String[]> getScholarshipData(){
		getDatabase(scholarshipDatabase);
		return this.databaseData;
	}
	public List<String[]> getApplicationData(){
		getDatabase(applicationDatabase);
		return this.databaseData;
	}
	
	public boolean addScholarshipEntry(String[] scholarshipData) { 
		addDatabaseEntry(scholarshipDatabase, scholarshipData);
		boolean success = true;
		return success;
	}
	
	public boolean addApplicationEntry(String[] applicationData) {
		addDatabaseEntry(applicationDatabase, applicationData);
		boolean success = true;
		return success;
	}

}
	
