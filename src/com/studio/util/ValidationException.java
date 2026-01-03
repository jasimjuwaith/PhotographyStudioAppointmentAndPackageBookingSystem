package com.studio.util;

public class ValidationException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ValidationException(String message) {
		super(message);
	}
	
    @Override
    public String toString() {
        return "ValidationException: " + getMessage();
    }
}