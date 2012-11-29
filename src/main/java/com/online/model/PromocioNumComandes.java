package com.online.model;


public class PromocioNumComandes extends Promocio{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private Integer				numComandes;
	private Integer				temps;
	
	// CONSTRUCTORS
	public PromocioNumComandes() {

		super();
	}

	// GETTERS i SETTERS
	public Integer getNumComandes(){
	
		return numComandes;
	}

	public void setNumComandes( Integer numComandes ){
	
		this.numComandes = numComandes;
	}

	public Integer getTemps(){
	
		return temps;
	}

	public void setTemps( Integer temps ){
	
		this.temps = temps;
	}


	
}
