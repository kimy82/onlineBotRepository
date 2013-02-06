package com.online.model;

import com.google.gson.annotations.Expose;
import com.online.model.Plat;

public class PlatComanda{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Expose
	private long				id;
	
	@Expose
	private Plat				plat;
	
	@Expose
	private Integer				numPlats;

	// CONSTRUCTORS

	public PlatComanda() {

		super();

	}

	// GETTERS i SETTERS
	public Plat getPlat(){
	
		return plat;
	}

	public void setPlat( Plat plat ){
	
		this.plat = plat;
	}

	public Integer getNumPlats(){
	
		return numPlats;
	}

	public void setNumPlats( Integer numPlats ){
	
		this.numPlats = numPlats;
	}

	public long getId(){
	
		return id;
	}

	public void setId( long id ){
	
		this.id = id;
	}
	
}
