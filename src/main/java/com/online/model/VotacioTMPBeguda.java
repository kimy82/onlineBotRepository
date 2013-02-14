package com.online.model;

import java.io.Serializable;
import java.util.Date;

public class VotacioTMPBeguda implements Serializable {

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;

	
	private Long begudaId;

	
	private Long userId;

	
	private int punctuacio;
	
	private Date dia;
	

	// CONSTRUCTORS

	public VotacioTMPBeguda() {

		super();

	}

	// GETTERS i SETTERS


	public Long getUserId() {
		return userId;
	}

	public Long getBegudaId(){
	
		return begudaId;
	}

	public void setBegudaId( Long begudaId ){
	
		this.begudaId = begudaId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getPunctuacio() {
		return punctuacio;
	}

	public void setPunctuacio(int punctuacio) {
		this.punctuacio = punctuacio;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

}
