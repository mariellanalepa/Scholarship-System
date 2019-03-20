package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Class in which we contain the summary of application data that constitutes the MODEL in the MVC design.
 * Since the data model will depend on which user is logged in (i.e., the user session), we call this 
 * class the SessionDataModel. Uses JavaFX Beans for benefit of property wrapper classes whose instantiations
 * are observable.
 * @see <a href="https://dzone.com/articles/the-bean-class-for-javafx-programming">The Bean Class For JavaFX
 * Programming</a>
 * @author Mariella
 *
 */
public class SessionDataModel {

	private ObjectProperty<User> currentUser = new SimpleObjectProperty<>();
	
}
