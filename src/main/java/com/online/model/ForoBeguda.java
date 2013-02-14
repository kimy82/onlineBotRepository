package com.online.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ForoBeguda implements Serializable{

	


	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	
	private Long				id;	
	
	private String				comment;
	
	private Beguda				beguda;

	
	// CONSTRUCTORS	 
	public ForoBeguda() {

		super();

	}

	public ForoBeguda( Long id ) {

		super();
		this.id = id;
	}

	// GETTERS i SETTERS
	public Long getId(){

		return id;
	}

	public void setId( Long id ){

		this.id = id;
	}

	public String getComment(){
	
		return comment;
	}

	public void setComment( String comment ){
	
		this.comment = comment;
	}

	public Beguda getBeguda(){
	
		return beguda;
	}

	public void setBeguda( Beguda beguda ){
	
		this.beguda = beguda;
	}
}
