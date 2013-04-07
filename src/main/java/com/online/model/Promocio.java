package com.online.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

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
	private String				tipusBeguda;

	@Expose
	private Integer				numUses;

	private Integer				numUsed;

	@Expose
	private Boolean				hora;

	@Expose
	private String				dates;

	@Expose
	private Date				fentrada;

	@Expose
	private boolean				dilluns;

	@Expose
	private boolean				dimarts;

	@Expose
	private boolean				dimecres;

	@Expose
	private boolean				dijous;

	@Expose
	private boolean				divendres;

	@Expose
	private boolean				dissabte;

	@Expose
	private boolean				diumenge;
	
	@Expose
	private String				code;
	
	@Expose
	private boolean				visibility;

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

	public Date getFentrada(){

		return fentrada;
	}

	public void setFentrada( Date fentrada ){

		this.fentrada = fentrada;
	}

	public boolean isDilluns(){

		return dilluns;
	}

	public void setDilluns( boolean dilluns ){

		this.dilluns = dilluns;
	}

	public boolean isDimarts(){

		return dimarts;
	}

	public void setDimarts( boolean dimarts ){

		this.dimarts = dimarts;
	}

	public boolean isDimecres(){

		return dimecres;
	}

	public void setDimecres( boolean dimecres ){

		this.dimecres = dimecres;
	}

	public boolean isDijous(){

		return dijous;
	}

	public void setDijous( boolean dijous ){

		this.dijous = dijous;
	}

	public boolean isDivendres(){

		return divendres;
	}

	public void setDivendres( boolean divendres ){

		this.divendres = divendres;
	}

	public boolean isDissabte(){

		return dissabte;
	}

	public void setDissabte( boolean dissabte ){

		this.dissabte = dissabte;
	}

	public boolean isDiumenge(){

		return diumenge;
	}

	public void setDiumenge( boolean diumenge ){

		this.diumenge = diumenge;
	}		
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean getVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public boolean checkDayOfWeekOpen(int dayOfWeek){
		
		switch(dayOfWeek){
			case Calendar.MONDAY:
				return this.dilluns;
			case Calendar.TUESDAY:
				return this.dimarts;
			case Calendar.WEDNESDAY:
				return this.dimecres;
			case Calendar.THURSDAY:
				return this.dijous;
			case Calendar.FRIDAY:
				return this.divendres;
			case Calendar.SATURDAY:
				return this.dissabte;
			case Calendar.SUNDAY:
				return this.diumenge;
		}
		
		return false;
	}

}
