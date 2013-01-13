package com.online.model;

import java.io.Serializable;
import java.util.Date;

public class VotacioTMP implements Serializable {

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;

	
	private Long platId;

	
	private Long userId;

	
	private int punctuacio;
	
	private Date dia;
	

	// CONSTRUCTORS

	public VotacioTMP() {

		super();

	}

	// GETTERS i SETTERS
	public Long getPlatId() {
		return platId;
	}

	public void setPlatId(Long platId) {
		this.platId = platId;
	}

	public Long getUserId() {
		return userId;
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
