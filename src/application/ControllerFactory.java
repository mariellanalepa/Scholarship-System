package application;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javafx.util.Callback;
import model.SessionDataModel;

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
	
	SessionDataModel session; 

	public ControllerFactory(SessionDataModel session)
	{
		this.session = session;
	}

	@Override
	public Object call(Class<?> param) 
	{
		try 
		{
			//For list of constructors for passed class 'param'
			for (Constructor<?> constructor: param.getConstructors()) 
			{
			/*If the constructor takes one parameter, and that parameter is the same as, or a superclass or superinterface of,
			the class specified by Class 'param' ("is assignable from"), generate a new instance of that class 
			*/
				if (constructor.getParameterCount() == 1 && SessionDataModel.class.isAssignableFrom(constructor.getParameterTypes()[0]))
					return constructor.newInstance(session);
			}
			//Only reach here if we were unable to construct passed Class by providing SessionDataModel object as parameter 
			return param.newInstance();
		} catch (Exception e) 
		{
			throw new RuntimeException(e);
		}	
	}
	
	
}
