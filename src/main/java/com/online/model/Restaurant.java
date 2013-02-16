package com.online.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.google.gson.annotations.Expose;
import com.online.utils.Constants;

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
	
	@Expose
	private String					descripcioES;

	protected Set<Plat>				plats				= new HashSet<Plat>(0);
	protected Set<ConfigRestaurant>	configRestaurants	= new HashSet<ConfigRestaurant>(0);

	private Image					foto;

	@Expose
	private String					codiMaquina;

	@Expose
	private String					hores;
	
	private String 					address;

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

	public String getHores(){
	
		return hores;
	}

	public void setHores( String hores ){
	
		this.hores = hores;
	}
	
	public String getDescripcioES(){
		
		return descripcioES;
	}

	public void setDescripcioES( String descripcioES ){
	
		this.descripcioES = descripcioES;
	}		
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public boolean equals( Object obj ){
		Restaurant restaurant = (Restaurant)obj;
		if(restaurant.getId() == this.id) return true;
		return false;
	}

	public Set<Plat> getPlats(String tipus){
		
		if(tipus.equals(Constants.TIPUS_PLAT_ANY)) return this.plats;
		
		Iterator<Plat> itera = this.plats.iterator();
		Set<Plat> platList = new HashSet<Plat>();
		while(itera.hasNext()){
			Plat plat = (Plat)itera.next();
			if(plat.getTipus().equals(tipus)){
				platList.add(plat);
			}
		}
		return platList;
	}

}
