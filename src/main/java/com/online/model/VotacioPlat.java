package com.online.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class VotacioPlat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Expose
	private Plat plat;

	@Expose
	private Long nvots;

	@Expose
	private Long id;
	
	@Expose
	private int punctuacio;

	// CONSTRUCTORS

	public VotacioPlat() {

		super();

	}


	// GETTERS i SETTERS
	public Plat getPlat() {
		return plat;
	}

	public void setPlat(Plat plat) {
		this.plat = plat;
	}

	public Long getNvots() {
		return nvots;
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
