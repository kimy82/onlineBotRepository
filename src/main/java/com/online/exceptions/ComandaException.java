package com.online.exceptions;

public class ComandaException extends RuntimeException{

	private static final long	serialVersionUID	= 1L;

	public ComandaException() {

	}

	public ComandaException( String message ) {

		super(message);
	}

	public ComandaException( Exception e, String message ) {

		super(message, e);
	}
}