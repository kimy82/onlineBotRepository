package com.online.exceptions;

public class EmailException extends RuntimeException{

	private static final long	serialVersionUID	= 1L;

	public EmailException() {

	}

	public EmailException( String message ) {

		super(message);
	}

	public EmailException( Exception e, String message ) {

		super(message, e);
	}
}