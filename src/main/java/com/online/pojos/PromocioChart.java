package com.online.pojos;

import com.google.gson.annotations.Expose;

public class PromocioChart{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Expose
	private String				nom					= "";

	@Expose
	private Integer				numUses				= 0;

	@Expose
	private Integer				numUsed				= 0;

	// CONSTRUCTORS
	public PromocioChart() {

		super();
	}

	// GETTERS i SETTERS
	public String getNom(){

		return nom;
	}

	public void setNom( String nom ){

		this.nom = nom;
	}

	public Integer getNumUses(){

		return numUses;
	}

	public void setNumUses( Integer numUses ){

		this.numUses = numUses;
	}

	public Integer getNumUsed(){

		return numUsed;
	}

	public void setNumUsed( Integer numUsed ){

		this.numUsed = numUsed;
	}

}
