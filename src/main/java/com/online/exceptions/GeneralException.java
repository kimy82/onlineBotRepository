package com.online.exceptions;

public class GeneralException extends RuntimeException{

	private static final long	serialVersionUID	= 1L;

	public GeneralException() {

	}

	public GeneralException( String message ) {

		super(message);
	}

	public GeneralException( Exception e, String message ) {

		super(message, e);
	}
}