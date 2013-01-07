package com.online.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Foro implements Serializable{

	


	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	
	private Long				id;	
	
	private String				comment;
	
	private Plat				plat;

	
	// CONSTRUCTORS	 
	public Foro() {

		super();

	}

	public Foro( Long id ) {

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

	public Plat getPlat(){
	
		return plat;
	}

	public void setPlat( Plat plat ){
	
		this.plat = plat;
	}	
	
	
}
