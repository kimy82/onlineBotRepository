package com.online.pojos;

import com.online.model.Promocio;

public class PromocioAPartirDeDTF extends Promocio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Double importAPartirDe;
	private String diaString;

	// CONSTRUCTORS
	public PromocioAPartirDeDTF() {

		super();
	}

	// GETTERS i SETTERS
	public Double getImportAPartirDe() {

		return importAPartirDe;
	}

	public void setImportAPartirDe(Double importAPartirDe) {

		this.importAPartirDe = importAPartirDe;
	}

	public String getDiaString() {
		return diaString;
	}

	public void setDiaString(String diaString) {
		this.diaString = diaString;

	}

}
