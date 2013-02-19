package com.online.pojos;

import com.google.gson.annotations.Expose;
import com.online.model.Plat;

public class PlatTable extends Plat{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Expose
	private String				accio				= "";

	@Expose
	private String				prioritatPlat		= "";
	
	@Expose
	private String 			actiuPlat;

	// CONSTRUCTORS

	public PlatTable() {

		super();

	}

	public PlatTable( Long id, String nom, Double preu ) {

		super(id, nom, preu);

	}

	// GETTERS i SETTERS

	public String getAccio(){

		return accio;
	}

	public void setAccio( String accio ){

		this.accio = accio;
	}

	public String getPrioritatPlat(){
	
		return prioritatPlat;
	}

	public void setPrioritatPlat( String prioritatPlat ){
	
		this.prioritatPlat = prioritatPlat;
	}

	public String isActiuPlat(){
	
		return actiuPlat;
	}

	public void setActiuPlat( String actiuPlat ){
	
		this.actiuPlat = actiuPlat;
	}
	
}
