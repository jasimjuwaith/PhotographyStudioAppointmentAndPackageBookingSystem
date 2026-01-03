package com.studio.util;

public class ActiveBookingException extends Exception{
	
private static final long serialVersionUID = 1L;
	
    @Override
    public String toString() {
        return "ActiveBookingException: " + getMessage();
    }
}
