package com.online.pojos;

import java.util.Date;

import com.google.gson.annotations.Expose;

public class ComandesUserTable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Expose
	private Date				dia;

	@Expose
	private Double				preu;

	@Expose
	private String				platsString;
	
	@Expose
	private String		    	links;
	
	@Expose
	private String		    	observacions;

	@Expose
	private String				accio				= "";

	// CONSTRUCTORS

	public ComandesUserTable() {

		super();

	}

	
	// GETTERS i SETTERS

	public String getAccio(){

		return accio;
	}

	public void setAccio( String accio ){

		this.accio = accio;
	}


	public Date getDia(){
	
		return dia;
	}


	public void setDia( Date dia ){
	
		this.dia = dia;
	}


	public Double getPreu(){
	
		return preu;
	}


	public void setPreu( Double preu ){
	
		this.preu = preu;
	}

	public String getPlatsString() {
		return platsString;
	}


	public void setPlatsString(String platsString) {
		this.platsString = platsString;
	}


	public String getObservacions(){
	
		return observacions;
	}


	public void setObservacions( String observacions ){
	
		this.observacions = observacions;
	}


	public String getLinks(){
	
		return links;
	}


	public void setLinks( String links ){
	
		this.links = links;
	}
	
	

}
