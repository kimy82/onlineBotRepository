package com.online.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

public class Moters implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private Integer				id;

	private Integer				idRestaurant;

	@Expose
	private Integer				numeroMoters;

	private Date				data;
	
	protected Set<Restaurant>	restaurants			= new HashSet<Restaurant>(0);

	// CONSTRUCTORS

	public Moters() {

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

	public Integer getNumeroMoters(){

		return numeroMoters;
	}

	public void setNumeroMoters( Integer numeroMoters ){

		this.numeroMoters = numeroMoters;
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
