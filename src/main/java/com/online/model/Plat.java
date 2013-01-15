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
	
	private Integer				prioritat;

	protected Image				foto;

	protected String			tipus;	

	protected Set<Restaurant>	restaurants			= new HashSet<Restaurant>(0);

	protected Set<Foro>			comments			= new HashSet<Foro>(0);
	
	protected Votacio			votacio;

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

	public String getTipus(){

		return tipus;
	}

	public void setTipus( String tipus ){

		this.tipus = tipus;
	}

	public Set<Foro> getComments(){
	
		return comments;
	}

	public void setComments( Set<Foro> comments ){
	
		this.comments = comments;
	}
	
	public Votacio getVotacio() {
		return votacio;
	}

	public void setVotacio(Votacio votacio) {
		this.votacio = votacio;
	}

	public Integer getPrioritat(){
	
		return prioritat;
	}

	public void setPrioritat( Integer prioritat ){
	
		this.prioritat = prioritat;
	}
	
	

}
