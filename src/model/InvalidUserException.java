package model;
/**
 * Custom exception class for InvalidUserExceptions
 * @author Natalie
 *
 */
public class InvalidUserException extends Exception { 
	private static final long serialVersionUID = 1L;

	public InvalidUserException() {
		super();
	}
	public InvalidUserException(String errorMessage) {
		super(errorMessage);
	}
}

