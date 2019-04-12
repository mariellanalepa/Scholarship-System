package controller;

import java.util.function.Predicate;
import javafx.collections.transformation.FilteredList;
import model.Scholarship;
import model.Student;

/**
 * Curator to filter scholarships students can see so that only scholarships
 * they are eligible for appear. 
 * @author David
 *
 */
public class ScholarshipCurator {

	FilteredList<Scholarship> curatedData;
	Student student;
	/**
	 * Constructor for the ScholarshipCurator
	 * @param curatedData - the filtered list whose data we are curating for the current student
	 * @param student - the current student object to be curated for
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
				// Three different cases for contents for the year field in the scholarship
				// Year is "X+" where X is an integer value
				if (scholarship.getYear().length() == 2){
					minYear = Integer.parseInt(scholarship.getYear().substring(0, 1));
					if (minYear <= studentYear){
						// Fields all match
						return true;
					}
				}
				// Year is "Any"
				else if (scholarship.getYear().toLowerCase().contains("any")){
					// Fields all match
					return true;
				}
				// Year is "X" where X is an integer value
				else{
					minYear = Integer.parseInt(scholarship.getYear());
					if (minYear == studentYear){
						// Fields all match
						return true;
					}
				}
			}
			//No match
			return false;
		}
		
	}

}
