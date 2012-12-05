package com.online.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class Beguda implements Serializable{

	


	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	
	@Expose
	private Long				id;
	
	@Expose
	private String				nom;
	
	@Expose
	private String				descripcio;
	
	@Expose
	private Double				preu;	
	
	@Expose
	protected String			tipus;
	

	// CONSTRUCTORS
	 
	public Beguda() {

		super();

	}

	public Beguda( Long id, String nom, Double preu ) {

		super();
		this.id = id;
		this.nom = nom;
		this.preu = preu;
	}

	// GETTERS i SETTERS
	public Long getId(){

		return id;
	}

	public void setId( Long id ){

		this.id = id;
	}

	public String getNom(){

		return nom;
	}

	public void setNom( String nom ){

		this.nom = nom;
	}

	public String getDescripcio(){

		return descripcio;
	}

	public void setDescripcio( String descripcio ){

		this.descripcio = descripcio;
	}

	public Double getPreu(){

		return preu;
	}

	public void setPreu( Double preu ){

		this.preu = preu;
	}

	public String getTipus(){
	
		return tipus;
	}

	public void setTipus( String tipus ){
	
		this.tipus = tipus;
	}
	
}
