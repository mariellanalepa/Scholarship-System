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

public class CsvReader {
	final String adminDatabase = "res/adminDatabase.csv";
	final String studentDatabase = "res/studentDatabase.csv";
	final String scholarshipDatabase = "res/scholarshipDatabase.csv";
	final String applicationDatabase = "res/applicationDatabase.csv";
	private String[] data;
	private List<String[]> databaseData;
	private int databaseCounter;
	

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
				/* first line of application and student database are not data
				 * line 1 is header data */
				if(i == 0) {
					i++;
					continue;
				}
				list.add(line.split(delimiter));
				i++;
			}
			this.databaseData = list;
			this.databaseCounter = list.size();

			
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
	
	private void deleteDatabaseEntry(String databaseName, int index) throws IOException {
		File f = new File(databaseName);
		if (f.exists() && f.isFile()) {
			f.delete();
		}
		f.createNewFile();
		for (int i = 0; i < databaseData.size(); i++) {
			if (i != index) {
				String[] d = databaseData.get(i);
				addDatabaseEntry(scholarshipDatabase, d);
			}
		}
		
	}

	public void getDatabaseForDelete(String databaseName) {
		List<String[]> list = new ArrayList<String[]>();
		BufferedReader buffread = null;
		String line = "";
		String delimiter = ",";
		try {
			File f = new File(databaseName);
			buffread = new BufferedReader(new FileReader(f));
			while ((line = buffread.readLine()) != null) {
				list.add(line.split(delimiter));
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
	private void addDatabaseEntry(String databaseName, String[] data) {
		BufferedWriter bw = null;
		String line = String.join(",", data);
		line += "\n";
		try {
			File f = new File(databaseName);
			bw = new BufferedWriter(new FileWriter(f, true));
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
		ScholarshipFactory.setCounter(this.databaseCounter);
		return this.databaseData;
	}
	public String getScholarshipName(int scholarshipID){
		String sName = "null";
		getDatabase(scholarshipDatabase);
		//ScholarshipFactory.setCounter(this.databaseCounter);
		for(int i = 0; i < this.databaseData.size(); i++) {
			if(Integer.valueOf(this.databaseData.get(i)[0]) == scholarshipID) {
				sName = this.databaseData.get(i)[1];
				
			}
		}
		return sName;
	}
	public List<String[]> getApplicationData(){
		getDatabase(applicationDatabase);
		System.out.println("@ getApplicationData(): "+this.databaseCounter);
		ApplicationFactory.setCounter(this.databaseCounter);
		return this.databaseData;
	}
	public List<String[]> getApplicationData(int studentID){
		getDatabase(applicationDatabase);
		List<String[]> l = new ArrayList<String[]>();
		for(int i = 0; i < this.databaseData.size(); i++) {
			if(this.databaseData.get(i)[1].equals(String.valueOf(studentID))) {
				l.add(this.databaseData.get(i));
			}		
		}
		return l;
	}
	

	
	public boolean addScholarshipEntry(String[] scholarshipData) { 
		addDatabaseEntry(scholarshipDatabase, scholarshipData);
		boolean success = true;
		return success;
	}
	
	public boolean deleteScholarshipEntry(int index) throws Exception {
		this.deleteDatabaseEntry(scholarshipDatabase, index);
		boolean success = true;
		return success;
	}
	
	public boolean addApplicationEntry(String[] applicationData) {
		addDatabaseEntry(applicationDatabase, applicationData);
		boolean success = true;
		return success;
	}


}
	
