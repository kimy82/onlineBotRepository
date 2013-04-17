package com.online.model;

import java.io.Serializable;
import java.util.Date;

public class Foro implements Serializable{

	


	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	
	private Long				id;	
	
	private String				comment;
	
	private Plat				plat;
	
	private Date 				dia;
	
	private String 				nomUsu;

	
	// CONSTRUCTORS	 
	public Foro() {

		super();

	}

	public Foro( Long id ) {

		super();
		this.id = id;
	}

	// GETTERS i SETTERS
	public Long getId(){

		return id;
	}

	public void setId( Long id ){

		this.id = id;
	}

	public String getComment(){
	
		return comment;
	}

	public void setComment( String comment ){
	
		this.comment = comment;
	}

	public Plat getPlat(){
	
		return plat;
	}

	public void setPlat( Plat plat ){
	
		this.plat = plat;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public String getNomUsu() {
		return nomUsu;
	}

	public void setNomUsu(String nomUsu) {
		this.nomUsu = nomUsu;
	}					
}
