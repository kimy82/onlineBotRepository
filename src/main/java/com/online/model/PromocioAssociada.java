package com.online.model;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.annotations.Expose;

public class PromocioAssociada implements Serializable{

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
	private Double				importAPartirDe;

	@Expose
	private Integer				numBegudes;

	@Expose
	private String				tipusBeguda;

	@Expose
	private Boolean				hora;

	@Expose
	private Date				fentrada;

	@Expose
	private String				code;

	// CONSTRUCTORS
	public PromocioAssociada() {

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

	public Boolean getHora(){

		return hora;
	}

	public void setHora( Boolean hora ){

		this.hora = hora;
	}

	public Date getFentrada(){

		return fentrada;
	}

	public void setFentrada( Date fentrada ){

		this.fentrada = fentrada;
	}

	public Double getImportAPartirDe(){
	
		return importAPartirDe;
	}

	public void setImportAPartirDe( Double importAPartirDe ){
	
		this.importAPartirDe = importAPartirDe;
	}

	public String getCode(){
	
		return code;
	}

	public void setCode( String code ){
	
		this.code = code;
	}		
}
