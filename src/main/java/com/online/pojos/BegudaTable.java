package com.online.pojos;

import com.google.gson.annotations.Expose;
import com.online.model.Beguda;

public class BegudaTable extends Beguda{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Expose
	private String				accio				= "";

	// CONSTRUCTORS

	public BegudaTable() {

		super();

	}

	public BegudaTable( Long id, String nom, Double preu ) {

		super(id, nom, preu);

	}

	// GETTERS i SETTERS

	public String getAccio(){

		return accio;
	}

	public void setAccio( String accio ){

		this.accio = accio;
	}

}
