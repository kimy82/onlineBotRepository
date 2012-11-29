package com.online.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class Promocio implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private Integer				id;
	
	@Expose
	private String				nom;
	
	@Expose
	private String				tipuDescompte;
	
	@Expose
	private Double				descompteImport;

	// CONSTRUCTORS
	public Promocio() {

		super();
	}

	// GETTERS i SETTERS
	public Integer getId(){

		return id;
	}

	public void setId( Integer id ){

		this.id = id;
	}

	public String getNom(){

		return nom;
	}

	public void setNom( String nom ){

		this.nom = nom;
	}

	public String getTipuDescompte(){
	
		return tipuDescompte;
	}

	public void setTipuDescompte( String tipuDescompte ){
	
		this.tipuDescompte = tipuDescompte;
	}

	public Double getDescompteImport(){
	
		return descompteImport;
	}

	public void setDescompteImport( Double descompteImport ){
	
		this.descompteImport = descompteImport;
	}

}
