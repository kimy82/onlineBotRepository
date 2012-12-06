package com.online.model;

import java.util.Calendar;
import java.util.Date;

public class PromocioAPartirDe extends Promocio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Double importAPartirDe;
	private String diaString;
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

	public String getDiaString() {
		return diaString;
	}

	public void setDiaString(String diaString) {
		this.diaString = diaString;
		String[] dateString = diaString.split("-");
		if(dateString.length==3){
			String dia =dateString[0];
			String mes = dateString[1];
			String any = dateString[2];
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH,Integer.parseInt(dia));
			cal.set(Calendar.MONTH, Integer.parseInt(mes));
			cal.set(Calendar.YEAR, Integer.parseInt(any));
			this.dia= cal.getTime();
			
			
		}
	}

}
