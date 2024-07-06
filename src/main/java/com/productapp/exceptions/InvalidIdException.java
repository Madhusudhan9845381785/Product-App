package com.productapp.exceptions;

public class InvalidIdException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidIdException() {
		super();
	}

	public InvalidIdException(String message) {
		super(message);
	}

}
