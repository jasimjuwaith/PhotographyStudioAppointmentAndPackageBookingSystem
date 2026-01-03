package com.studio.util;

public class SlotAlreadyBookedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SlotAlreadyBookedException(String string) {
		super(string);
	}

	@Override
	public String toString() {
		return "SlotAlreadyBookedException:" + getMessage();
	}
}
