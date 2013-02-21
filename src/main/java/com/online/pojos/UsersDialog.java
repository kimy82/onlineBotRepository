package com.online.pojos;

import com.google.gson.annotations.Expose;
import com.online.model.Users;

public class UsersDialog {

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
	
	@Expose
	private String				username;
	
	@Expose
	private String				address;

	@Expose
	private String				telNumber;
	
	@Expose
	private String				indicacions;
	
	@Expose
	private String				nom;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getIndicacions() {
		return indicacions;
	}

	public void setIndicacions(String indicacions) {
		this.indicacions = indicacions;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
