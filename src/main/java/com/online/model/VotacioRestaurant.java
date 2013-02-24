package com.online.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class VotacioRestaurant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Expose
	private Restaurant restaurant;

	@Expose
	private Long nvots;

	@Expose
	private Integer id;
	
	@Expose
	private int punctuacio;

	// CONSTRUCTORS

	public VotacioRestaurant() {

		super();

	}


	// GETTERS i SETTER
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


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Restaurant getRestaurant(){
	
		return restaurant;
	}

	public void setRestaurant( Restaurant restaurant ){
	
		this.restaurant = restaurant;
	}
	
	
	
}
