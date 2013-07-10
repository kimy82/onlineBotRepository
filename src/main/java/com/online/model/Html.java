package com.online.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class Html implements Serializable{

	


	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	
	@Expose
	private Long				id;
	
	@Expose
	private String				html;
	
	
	// CONSTRUCTORS
	 
	public Html() {

		super();

	}


	// GETTERS i SETTERS
	public Long getId(){
	
		return id;
	}


	public void setId( Long id ){
	
		this.id = id;
	}


	public String getHtml(){
	
		return html;
	}


	public void setHtml( String html ){
	
		this.html = html;
	}
		
}
