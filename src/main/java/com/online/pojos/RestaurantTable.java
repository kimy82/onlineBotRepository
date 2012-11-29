package com.online.pojos;

import com.google.gson.annotations.Expose;
import com.online.model.Restaurant;

public class RestaurantTable extends Restaurant{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Expose
	private String				accio				= "";

	// CONSTRUCTORS
	public RestaurantTable() {

		super();

	}

	public RestaurantTable( Integer id, String nom ) {

		super(id, nom);

	}

	// GETTERS i SETTERS
	public String getAccio(){

		return accio;
	}

	public void setAccio( String accio ){

		this.accio = accio;
	}

}
