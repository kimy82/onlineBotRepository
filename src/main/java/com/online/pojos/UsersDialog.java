package com.online.pojos;

import com.google.gson.annotations.Expose;
import com.online.model.Users;

public class UsersDialog extends Users{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Expose
	private Integer				numComandesRealitzades				= 0;

	
	@Expose
	private Integer				numComandesAmbTargeta				= 0;

	
	@Expose
	private Integer				numComandesSenseTargeta				= 0;

	// CONSTRUCTORS

	public UsersDialog() {

		super();

	}

	// GETTERS i SETTERS
	public Integer getNumComandesRealitzades(){
	
		return numComandesRealitzades;
	}

	public void setNumComandesRealitzades( Integer numComandesRealitzades ){
	
		this.numComandesRealitzades = numComandesRealitzades;
	}

	public Integer getNumComandesAmbTargeta(){
	
		return numComandesAmbTargeta;
	}

	public void setNumComandesAmbTargeta( Integer numComandesAmbTargeta ){
	
		this.numComandesAmbTargeta = numComandesAmbTargeta;
	}

	public Integer getNumComandesSenseTargeta(){
	
		return numComandesSenseTargeta;
	}

	public void setNumComandesSenseTargeta( Integer numComandesSenseTargeta ){
	
		this.numComandesSenseTargeta = numComandesSenseTargeta;
	}


	

}
