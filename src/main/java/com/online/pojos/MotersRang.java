package com.online.pojos;

import java.io.Serializable;


public class MotersRang implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String	diaIni;
	
	private String	diaFi;

	private String	horaIni;
	
	private String	horaFi;
	
	private Integer numMoters;

	
	//CONSTRUCTOR
	public MotersRang() {

	}

	//GETTERS I SETTERS
	public String getDiaIni() {
		return diaIni;
	}


	public void setDiaIni(String diaIni) {
		this.diaIni = diaIni;
	}


	public String getDiaFi() {
		return diaFi;
	}


	public void setDiaFi(String diaFi) {
		this.diaFi = diaFi;
	}


	public String getHoraIni() {
		return horaIni;
	}


	public void setHoraIni(String horaIni) {
		this.horaIni = horaIni;
	}


	public String getHoraFi() {
		return horaFi;
	}


	public void setHoraFi(String horaFi) {
		this.horaFi = horaFi;
	}


	public Integer getNumMoters() {
		return numMoters;
	}


	public void setNumMoters(Integer numMoters) {
		this.numMoters = numMoters;
	}
	
	

	

}
