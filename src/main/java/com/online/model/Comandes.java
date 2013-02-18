package com.online.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Comandes implements Serializable{

	/**
	 * 
	 */
	private static final long		serialVersionUID	= 1L;

	@Expose
	private Long					id;

	@Expose
	private String					hora;

	@Expose
	private Date					dia;

	@Expose
	private Double					preu;

	@Expose
	protected String				observacions;

	@Expose
	protected String				address;

	protected List<PlatComanda>		plats				= new LinkedList<PlatComanda>();

	protected List<BegudaComanda>	begudes				= new LinkedList<BegudaComanda>();

	protected Users					user;

	protected Boolean				aDomicili;
	
	protected Boolean				pagada;
	
	protected String 				tipuDescomte;
	
	protected Double 				importDescomte;

	private Date					fentrada;
	
	protected Boolean				targeta;
	
	protected Boolean				revisio;

	

	// CONSTRUCTORS
	public Comandes() {

		super();

	}

	public Comandes( Long id, String hora, Date dia, Double preu ) {

		this.id = id;
		this.dia = dia;
		this.hora = hora;
		this.preu = preu;

	}

	// GETTERS i SETTERS
	public Long getId(){

		return id;
	}

	public void setId( Long id ){

		this.id = id;
	}

	public Double getPreu(){

		return preu;
	}

	public void setPreu( Double preu ){

		this.preu = preu;
	}

	public String getHora(){

		return hora;
	}

	public void setHora( String hora ){

		this.hora = hora;
	}

	public Date getDia(){

		return dia;
	}

	public void setDia( Date dia ){

		this.dia = dia;
	}

	public String getObservacions(){

		return observacions;
	}

	public void setObservacions( String observacions ){

		this.observacions = observacions;
	}

	public List<PlatComanda> getPlats(){

		return plats;
	}

	public void setPlats( List<PlatComanda> plats ){

		this.plats = plats;
	}

	public String getAddress(){

		return address;
	}

	public void setAddress( String address ){

		this.address = address;
	}

	public Users getUser(){

		return user;
	}

	public void setUser( Users user ){

		this.user = user;
	}

	public List<BegudaComanda> getBegudes(){

		return begudes;
	}

	public void setBegudes( List<BegudaComanda> begudes ){

		this.begudes = begudes;
	}

	public Date getFentrada(){

		return fentrada;
	}

	public void setFentrada( Date fentrada ){

		this.fentrada = fentrada;
	}

	public String getTipuDescomte(){
	
		return tipuDescomte;
	}

	public void setTipuDescomte( String tipuDescomte ){
	
		this.tipuDescomte = tipuDescomte;
	}

	public Double getImportDescomte(){
	
		return importDescomte;
	}

	public void setImportDescomte( Double importDescomte ){
	
		this.importDescomte = importDescomte;
	}
	public Boolean getaDomicili(){

		return aDomicili;
	}

	public void setaDomicili( Boolean aDomicili ){

		this.aDomicili = aDomicili;
	}
	
	public Boolean getTargeta() {
		return targeta;
	}

	public void setTargeta(Boolean targeta) {
		this.targeta = targeta;
	}

	public Boolean getPagada(){
	
		return pagada;
	}

	public void setPagada( Boolean pagada ){
	
		this.pagada = pagada;
	}

	public Boolean getRevisio(){
	
		return revisio;
	}

	public void setRevisio( Boolean revisio ){
	
		this.revisio = revisio;
	}			
	
	
}
