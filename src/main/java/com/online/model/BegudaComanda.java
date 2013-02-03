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
	private Integer				numBegudesPromo;
	
	@Expose
	private Integer				numBegudes;

	@Expose
	private boolean				promo				= false;

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

	public boolean isPromo(){
	
		return promo;
	}

	public void setPromo( boolean promo ){
	
		this.promo = promo;
	}
	
	public Integer getNumBegudesPromo() {
		return numBegudesPromo;
	}

	public void setNumBegudesPromo(Integer numBegudesPromo) {
		this.numBegudesPromo = numBegudesPromo;
	}
	
	

}
