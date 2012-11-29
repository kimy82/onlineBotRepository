package com.online.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

public class ConfigRestaurant implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private Integer				id;

	private Integer				idRestaurant;

	@Expose
	private boolean				obert;

	private Date				data;
	
	protected Set<Restaurant>		restaurants			= new HashSet<Restaurant>(0);

	// CONSTRUCTORS

	public ConfigRestaurant() {

		super();

	}

	// GETTERS i SETTERS

	public Integer getId(){

		return id;
	}

	public void setId( Integer id ){

		this.id = id;
	}

	public Integer getIdRestaurant(){

		return idRestaurant;
	}

	public void setIdRestaurant( Integer idRestaurant ){

		this.idRestaurant = idRestaurant;
	}

	public boolean isObert(){

		return obert;
	}

	public void setObert( boolean obert ){

		this.obert = obert;
	}

	public Date getData(){

		return data;
	}

	public void setData( Date data ){

		this.data = data;
	}

	public Set<Restaurant> getRestaurants(){
	
		return restaurants;
	}

	public void setRestaurants( Set<Restaurant> restaurants ){
	
		this.restaurants = restaurants;
	}
	

}
