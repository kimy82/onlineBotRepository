package com.online.exceptions;

public class PaymentException extends RuntimeException{

	private static final long	serialVersionUID	= 1L;

	public PaymentException() {

	}

	public PaymentException( String message ) {

		super(message);
	}

	public PaymentException( Exception e, String message ) {

		super(message, e);
	}
}