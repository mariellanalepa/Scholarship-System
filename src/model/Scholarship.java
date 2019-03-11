package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Scholarship {
	private StringProperty id;
	private StringProperty name;
	private StringProperty donor;
	private StringProperty deadline; 
	private StringProperty amount; 
	private StringProperty number; 
	private StringProperty faculty;
	private StringProperty department; 
	private StringProperty type; 
	private StringProperty gpa; 
	private StringProperty year;
	private StringProperty status;
	private StringProperty posted;
	
	//IDNumber	Name	Donor	Deadline(dd/MM/yyyy HH:mm:ss)	Amount	Number	ReqFac	ReqDept	RecType	ReqGPA	ReqYear	Status	DatePosted(dd/MM/yyyy HH:mm:ss)
	public Scholarship (String[] scholarshipData) {
		this.setId(scholarshipData[0]);
		this.setName(scholarshipData[1]);
		this.setDonor(scholarshipData[2]);
		this.setDeadline(scholarshipData[3]); 
		this.setAmount(scholarshipData[4]); 
		this.setNumber(scholarshipData[5]); 
		this.setFaculty(scholarshipData[6]);
		this.setDepartment(scholarshipData[7]); 
		this.setType(scholarshipData[8]); 
		this.setGpa(scholarshipData[9]); 
		this.setYear(scholarshipData[10]);
		this.setStatus(scholarshipData[11]);
		this.setPosted(scholarshipData[12]);
	}

	/* following code adapted from Oracle Docs
	 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TableView.html
	 */
	public void setId(String value) { idProperty().set(value); }
    public String getId() { return idProperty().get(); }
    public StringProperty idProperty() { 
        if (id == null) id = new SimpleStringProperty(this, "id");
        return id; 
    } 
	public void setName(String value) { nameProperty().set(value); }
    public String getName() { return nameProperty().get(); }
    public StringProperty nameProperty() { 
        if (name == null) name = new SimpleStringProperty(this, "name");
        return name; 
    } 
    public void setDonor(String value) { donorProperty().set(value); }
    public String getDonor() { return donorProperty().get(); }
    public StringProperty donorProperty() { 
        if (donor == null) donor = new SimpleStringProperty(this, "donor");
        return donor; 
    } 
    public void setDeadline(String value) { deadlineProperty().set(value); }
    public String getDeadline() { return deadlineProperty().get(); }
    public StringProperty deadlineProperty() { 
        if (deadline == null) deadline = new SimpleStringProperty(this, "deadline");
        return deadline; 
    } 
    public void setAmount(String value) { amountProperty().set(value); }
    public String getAmount() { return amountProperty().get(); }
    public StringProperty amountProperty() { 
        if (amount == null) amount = new SimpleStringProperty(this, "amount");
        return amount; 
    }

    public void setNumber(String value) { numberProperty().set(value);} 
    public String getNumber() {return numberProperty().get();}
	public StringProperty numberProperty() { 
        if (number == null) number = new SimpleStringProperty(this, "number");
        return number; 
    }
	public void setFaculty(String value) { facultyProperty().set(value); }
    public String getFaculty() { return facultyProperty().get(); }
    public StringProperty facultyProperty() { 
        if (faculty == null) faculty = new SimpleStringProperty(this, "faculty");
        return faculty; 
    } 
    public void setDepartment(String value) { departmentProperty().set(value); }
    public String getDepartment() { return departmentProperty().get(); }
    public StringProperty departmentProperty() { 
        if (department == null) department = new SimpleStringProperty(this, "department");
        return department; 
    } 
    public void setType(String value) { typeProperty().set(value); }
    public String getType() { return typeProperty().get(); }
    public StringProperty typeProperty() { 
        if (type == null) type = new SimpleStringProperty(this, "type");
        return type; 
    } 
    public void setGpa(String value) { gpaProperty().set(value); }
    public String getGpa() { return gpaProperty().get(); }
    public StringProperty gpaProperty() { 
        if (gpa == null) gpa = new SimpleStringProperty(this, "gpa");
        return gpa; 
    } 
    public void setYear(String value) { yearProperty().set(value); }
    public String getYear() { return yearProperty().get(); }
    public StringProperty yearProperty() { 
        if (year == null) year = new SimpleStringProperty(this, "year");
        return year; 
    } 
    public void setStatus(String value) { statusProperty().set(value); }
    public String getStatus() { return statusProperty().get(); }
    public StringProperty statusProperty() { 
        if (status == null) status = new SimpleStringProperty(this, "status");
        return status; 
    } 
    public void setPosted(String value) { postedProperty().set(value); }
    public String getPosted() { return postedProperty().get(); }
    public StringProperty postedProperty() { 
        if (posted == null) posted = new SimpleStringProperty(this, "posted");
        return posted; 
    } 
    
}
