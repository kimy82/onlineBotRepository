package com.online.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

public class Plat implements Serializable{

	


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
	
	protected Image				foto;
	

	protected Set<Restaurant>		restaurants			= new HashSet<Restaurant>(0);

	// CONSTRUCTORS
	 
	public Plat() {

		super();

	}

	public Plat( Long id, String nom, Double preu ) {

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

	public Image getFoto(){

		return foto;
	}

	public void setFoto( Image foto ){

		this.foto = foto;
	}

	public Set<Restaurant> getRestaurants(){
	
		return restaurants;
	}

	public void setRestaurants( Set<Restaurant> restaurants ){
	
		this.restaurants = restaurants;
	}

	
	
}
