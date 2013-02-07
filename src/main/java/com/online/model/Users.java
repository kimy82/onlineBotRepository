
package com.online.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class Users implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private Long				id;

	@Expose
	private String				username;

	private String				password;

	@Expose
	private int					enabled;

	protected UserRole			userRole;

	private String				address;

	private String				telNumber;
	
	private String				indicacions;
	
	private String				nom;

	// CONSTRUCTORS
	public Users( String username ) {

		super();
		this.username = username;
		this.enabled = 1;
	}

	public Users() {

		super();
		this.enabled = 1;
	}

	// GETTERS i SETTERS
	public Long getId(){

		return id;
	}

	public void setId( Long id ){

		this.id = id;
	}

	public String getUsername(){

		return username;
	}

	public void setUsername( String username ){

		this.username = username;
	}

	public String getPassword(){

		return password;
	}

	public void setPassword( String password ){

		this.password = password;
	}

	public int getEnabled(){

		return enabled;
	}

	public void setEnabled( int enabled ){

		this.enabled = enabled;
	}

	public UserRole getUserRole(){

		return userRole;
	}

	public void setUserRole( UserRole userRole ){

		this.userRole = userRole;
	}

	public String getAddress(){
	
		return address;
	}

	public void setAddress( String address ){
	
		this.address = address;
	}

	public String getTelNumber(){
	
		return telNumber;
	}

	public void setTelNumber( String telNumber ){
	
		this.telNumber = telNumber;
	}

	public String getIndicacions(){
	
		return indicacions;
	}

	public void setIndicacions( String indicacions ){
	
		this.indicacions = indicacions;
	}

	public String getNom(){
	
		return nom;
	}

	public void setNom( String nom ){
	
		this.nom = nom;
	}		

}
