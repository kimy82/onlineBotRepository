package com.online.exceptions;

public class UserExistException extends RuntimeException{

	private static final long	serialVersionUID	= 1L;

	public UserExistException() {

	}

	public UserExistException( String message ) {

		super(message);
	}

	public UserExistException( Exception e, String message ) {

		super(message, e);
	}
}