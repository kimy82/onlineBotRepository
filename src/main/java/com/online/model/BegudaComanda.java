package com.online.model;

import com.google.gson.annotations.Expose;

public class BegudaComanda{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Expose
	private long				id;
	
	@Expose
	private Beguda				beguda;
	
	@Expose
	private Integer				numBegudes;

	// CONSTRUCTORS

	public BegudaComanda() {

		super();

	}

	// GETTERS i SETTERS
	public long getId(){
	
		return id;
	}

	public void setId( long id ){
	
		this.id = id;
	}

	public Beguda getBeguda(){
	
		return beguda;
	}

	public void setBeguda( Beguda beguda ){
	
		this.beguda = beguda;
	}

	public Integer getNumBegudes(){
	
		return numBegudes;
	}

	public void setNumBegudes( Integer numBegudes ){
	
		this.numBegudes = numBegudes;
	}

	


	
}
