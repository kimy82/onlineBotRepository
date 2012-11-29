package com.online.exceptions;

public class BOException extends RuntimeException{

	private static final long	serialVersionUID	= 1L;

	public BOException() {

	}

	public BOException( String message ) {

		super(message);
	}

	public BOException( Exception e, String message ) {

		super(message, e);
	}
}