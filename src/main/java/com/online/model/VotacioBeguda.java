package com.online.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class VotacioBeguda implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Expose
	private Beguda beguda;

	@Expose
	private Long nvots;

	@Expose
	private Long id;
	
	@Expose
	private int punctuacio;

	// CONSTRUCTORS

	public VotacioBeguda() {

		super();

	}


	// GETTERS i SETTERS
	
	public Long getNvots() {
		return nvots;
	}

	public Beguda getBeguda(){
	
		return beguda;
	}


	public void setBeguda( Beguda beguda ){
	
		this.beguda = beguda;
	}


	public void setNvots(Long nvots) {
		this.nvots = nvots;
	}

	public int getPunctuacio() {
		return punctuacio;
	}

	public void setPunctuacio(int punctuacio) {
		this.punctuacio = punctuacio;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
}
