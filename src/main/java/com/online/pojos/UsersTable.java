package com.online.pojos;

import com.google.gson.annotations.Expose;
import com.online.model.Users;

public class UsersTable extends Users{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Expose
	private String				accio				= "";

	// CONSTRUCTORS

	public UsersTable() {

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
