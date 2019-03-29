package controller;

import java.util.function.Predicate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import model.Scholarship;

/**
 * Custom ChangeListener for Scholarship table filtering
 * Listens for changes to the filter field 
 * @author Mariella
 *
 */
public class ScholarshipFilterListener implements ChangeListener<String> {

	FilteredList<Scholarship> filteredData;
	
	/**
	 * Constructor for the ChangeListener.
	 * @param filteredData - the filtered list that whose filterField we are listened to
	 */
	public ScholarshipFilterListener(FilteredList<Scholarship> filteredData) {
		
		this.filteredData = filteredData;
	}


	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		//set predicate that must be satisfied by elements in filtered list
		filteredData.setPredicate(new FilterPredicate(newValue));
	}
	
	/**
	 * Private Predicate class that defines the predicate that must be satisfied
	 * by Scholarship elements if they are to appear in the filtered list.
	 * Defined such that if filterField is empty, we return all elements (true 
	 * for all elements); if the filterField is not empty, filter list such that
	 * only those elements having text fields whose values contain the filterField
	 * appear in the sorted list.
	 * @author Mariella
	 *
	 */
	private class FilterPredicate implements Predicate<Scholarship> {

		String newValue;
		
		private FilterPredicate(String newValue) {
			this.newValue = newValue;
		}
		
		@Override
		public boolean test(Scholarship scholarship) {
			
			if (newValue == null || newValue.isEmpty()) {
				return true;
			}
			
			//Else compare lowercase versions of fields to filter
			String lowerCaseFilterField = newValue.toLowerCase();
			
			for (String attribute : scholarship.toStringArray())
			if (attribute.toLowerCase().contains(lowerCaseFilterField))
			{
				//Field matchs filter
				return true;
			}
			//No match
			return false;
		}
		
	}

}
