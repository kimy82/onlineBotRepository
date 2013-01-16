package com.online.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

public class Restaurant implements Serializable{

	/**
	 * 
	 */
	private static final long		serialVersionUID	= 1L;

	private Integer					id;

	@Expose
	private String					nom;

	@Expose
	private String					descripcio;

	protected Set<Plat>				plats				= new HashSet<Plat>(0);
	protected Set<ConfigRestaurant>	configRestaurants	= new HashSet<ConfigRestaurant>(0);

	private Image					foto;

	private String					codiMaquina;

	protected VotacioRestaurant		votacio;

	// CONSTRUCTORS
	public Restaurant() {

		super();

	}

	public Restaurant( Integer id, String nom ) {

		super();
		this.id = id;
		this.nom = nom;
	}

	// GETTERS i SETTERS
	public Integer getId(){

		return id;
	}

	public void setId( Integer id ){

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

	public Set<Plat> getPlats(){

		return plats;
	}

	public void setPlats( Set<Plat> plats ){

		this.plats = plats;
	}

	public Image getFoto(){

		return foto;
	}

	public void setFoto( Image foto ){

		this.foto = foto;
	}

	public Set<ConfigRestaurant> getConfigRestaurants(){

		return configRestaurants;
	}

	public void setConfigRestaurants( Set<ConfigRestaurant> configRestaurants ){

		this.configRestaurants = configRestaurants;
	}

	public String getCodiMaquina(){

		return codiMaquina;
	}

	public void setCodiMaquina( String codiMaquina ){

		this.codiMaquina = codiMaquina;
	}

	public VotacioRestaurant getVotacio(){
	
		return votacio;
	}

	public void setVotacio( VotacioRestaurant votacio ){
	
		this.votacio = votacio;
	}

	
}
