package com.online.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class Promocio implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Expose
	private Integer				id;
	
	@Expose
	private String				nom;
	
	@Expose
	private String				nomES;
	
	@Expose
	private String				tipuDescompte;
	
	@Expose
	private Double				descompteImport;
	
	@Expose
	private Integer				numBegudes;
	
	@Expose
	private String 				tipusBeguda;
	
	@Expose
	private Integer				numUses;
	
	private Integer				numUsed;
	
	@Expose
	private Boolean 			hora;
	
	@Expose
	private String   			dates;

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

	public Integer getNumBegudes(){
	
		return numBegudes;
	}

	public void setNumBegudes( Integer numBegudes ){
	
		this.numBegudes = numBegudes;
	}

	public String getTipusBeguda(){
	
		return tipusBeguda;
	}

	public void setTipusBeguda( String tipusBeguda ){
	
		this.tipusBeguda = tipusBeguda;
	}

	public String getNomES(){
	
		return nomES;
	}

	public void setNomES( String nomES ){
	
		this.nomES = nomES;
	}

	public Integer getNumUses(){
	
		return numUses;
	}

	public void setNumUses( Integer numUses ){
	
		this.numUses = numUses;
	}

	public Boolean getHora(){
	
		return hora;
	}

	public void setHora( Boolean hora ){
	
		this.hora = hora;
	}

	public Integer getNumUsed(){
	
		return numUsed;
	}

	public void setNumUsed( Integer numUsed ){
	
		this.numUsed = numUsed;
	}

	public String getDates(){
	
		return dates;
	}

	public void setDates( String dates ){
	
		this.dates = dates;
	}		
}
