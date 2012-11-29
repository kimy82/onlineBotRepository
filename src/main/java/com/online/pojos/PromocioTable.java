package com.online.pojos;

import com.google.gson.annotations.Expose;
import com.online.model.Promocio;

public class PromocioTable extends Promocio{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Expose
	private String				accio				= "";

	// CONSTRUCTORS
	public PromocioTable() {

		super();
	}

	// GETTERS i SETTERS
	public String getAccio(){

		return accio;
	}

	public void setAccio( String accio ){

		this.accio = accio;
	}

}
