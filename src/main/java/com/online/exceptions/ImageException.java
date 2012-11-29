package com.online.exceptions;

public class ImageException extends RuntimeException{

	private static final long	serialVersionUID	= 1L;

	public ImageException() {

	}

	public ImageException( String message ) {

		super(message);
	}

	public ImageException( Exception e, String message ) {

		super(message, e);
	}
}