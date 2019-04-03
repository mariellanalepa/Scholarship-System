package controller;

import java.util.function.Predicate;
import javafx.collections.transformation.FilteredList;
import model.Scholarship;
import model.Student;

/**
 * Custom ChangeListener for Scholarship table filtering
 * Listens for changes to the filter field 
 *
 */
public class ScholarshipCurator {

	FilteredList<Scholarship> curatedData;
	Student student;
	/**
	 * Constructor for the ScholarshipCurator
	 * @param curatedData - the filtered list whose data we are curating for the current student
	 */
	public ScholarshipCurator(FilteredList<Scholarship> curatedData, Student student) {
		this.student = student;
		this.curatedData = curatedData;
		this.curatedData.setPredicate(new CuratorPredicate(student));
	}

	/**
	 * Private Predicate class that defines the predicate that must be satisfied
	 * by Scholarship & Student elements if they are to appear in the filtered list. 
	 *
	 */
	private class CuratorPredicate implements Predicate<Scholarship> {

		Student student;
		
		private CuratorPredicate(Student student) {
			this.student = student;
		}
		
		@Override
		public boolean test(Scholarship scholarship) {
			
			//Compare lowercase versions of scholarship fields to student fields
			if (((scholarship.getFaculty().toLowerCase().contentEquals(student.getStudentFaculty().toLowerCase())) || (scholarship.getFaculty().toLowerCase().contentEquals("any")))
			&& ((scholarship.getDepartment().toLowerCase().contains(student.getStudentDepartment().toLowerCase())) || (scholarship.getDepartment().toLowerCase().contentEquals("any")))
			&& ((scholarship.getType().toLowerCase().contentEquals(student.getStudentType().toLowerCase())) || (scholarship.getType().toLowerCase().contentEquals("any")))
			&& (scholarship.getGpa() <= student.getGPA()) && (scholarship.getStatus().toLowerCase().contentEquals("open"))) 
			{
				int minYear;
				int studentYear = Integer.parseInt(student.getStudentYearString());
				if (scholarship.getYear().length() == 2){
					minYear = Integer.parseInt(scholarship.getYear().substring(0, 1));
					if (minYear <= studentYear){
						return true;
					}
				}
				else if (scholarship.getYear().toLowerCase().contains("any")){
					return true;
				}
				else{
					minYear = Integer.parseInt(scholarship.getYear());
					if (minYear == studentYear){
						return true;
					}
				}
			}
			//No match
			return false;
		}
		
	}

}
