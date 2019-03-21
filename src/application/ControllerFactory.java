package application;

import java.lang.reflect.Constructor;
import javafx.util.Callback;
import model.Session;

/**
 * Factory to abstract the construction of controllers for our application scenes. 
 * Each controller constructor takes the session data model as an input parameter. 
 * This factory ensures that every controller is getting the same session data model. 
 * This class implements the JavaFX Callback interface, and returns an instance of 
 * a controller (which will ultimately be determined by the FXML). 
 * @author Mariella
 *
 */
public class ControllerFactory implements Callback<Class<?>, Object> {
	
	Session session; 
	Main main;

	public ControllerFactory(Main main, Session session)
	{
		this.main = main;
		this.session = session;
	}

	@Override
	public Object call(Class<?> param) 
	{
		try 
		{
			//For list of constructors for (controller) class 'param'
			for (Constructor<?> constructor: param.getConstructors()) 
			{
			/*If the constructor takes two parameters, and the parameter are instances of (or instances of a superclass or 
			 * superinterface of) Main and Session, respectively, then generate a new instance of controller class 'param' 
			*/
				if (constructor.getParameterCount() == 2 
					&& Main.class.isAssignableFrom(constructor.getParameterTypes()[0])
					&& Session.class.isAssignableFrom(constructor.getParameterTypes()[1]))
					
					return constructor.newInstance(main, session);
			}
			//Only reach here if we were unable to construct controller by passing Main and Session objects as parameters
			return param.newInstance();
		} catch (Exception e) 
		{
			throw new RuntimeException(e);
		}	
	}
	
	
}
