package com.online.model;

import java.io.Serializable;

public class UserRole implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private Long				id;
	
	
	private String				role;
	
	private Long				idUser;
	

	
	
	
	//CONSTRUCTORS
	public UserRole( ) {
		super();	
	}





	//GETTERS i SETTERS
	
	public Long getId(){
	
		return id;
	}
	public void setId( Long id ){
	
		this.id = id;
	}

	public String getRole(){
	
		return role;
	}





	public void setRole( String role ){
	
		this.role = role;
	}





	public Long getIdUser(){
	
		return idUser;
	}





	public void setIdUser( Long idUser ){
	
		this.idUser = idUser;
	}


	
}
