package com.online.model;

import java.io.Serializable;

public class NewsLetter implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	private String email;
	private Long id;


	// CONSTRUCTORS

	public NewsLetter() {

		super();

	}


	public String getEmail(){
	
		return email;
	}


	public void setEmail( String email ){
	
		this.email = email;
	}


	public Long getId(){
	
		return id;
	}


	public void setId( Long id ){
	
		this.id = id;
	}
}