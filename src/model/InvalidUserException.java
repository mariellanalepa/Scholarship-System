package model;

public class InvalidUserException extends Exception { 
	private static final long serialVersionUID = 1L;

	public InvalidUserException(String errorMessage) {
		super(errorMessage);
	}
}

