package com.online.exceptions;

public class WrongParamException extends RuntimeException{

	private static final long	serialVersionUID	= 1L;

	public WrongParamException() {

	}

	public WrongParamException( String message ) {

		super(message);
	}

	public WrongParamException( Exception e, String message ) {

		super(message, e);
	}
}