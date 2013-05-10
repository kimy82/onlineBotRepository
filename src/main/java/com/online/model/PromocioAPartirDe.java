package com.online.model;

import java.util.Date;

import com.google.gson.annotations.Expose;

public class PromocioAPartirDe extends Promocio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Expose
	private Double importAPartirDe;
	
	private Date dia;

	// CONSTRUCTORS
	public PromocioAPartirDe() {

		super();
	}

	// GETTERS i SETTERS
	public Double getImportAPartirDe() {

		return importAPartirDe;
	}

	public void setImportAPartirDe(Double importAPartirDe) {

		this.importAPartirDe = importAPartirDe;
	}

	public Date getDia() {

		return dia;
	}

	public void setDia(Date dia) {

		this.dia = dia;
	}
}
