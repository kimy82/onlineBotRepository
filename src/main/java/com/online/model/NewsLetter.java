package com.online.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class NewsLetter implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	@Expose
	private String email;
	
	@Expose
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