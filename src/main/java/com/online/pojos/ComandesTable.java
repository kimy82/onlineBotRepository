package com.online.pojos;

import com.google.gson.annotations.Expose;


public class ComandesTable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Expose
	private String nom;
	
	@Expose
	private String telefon;
	
	@Expose
	private String address;
	
	@Expose
	private String hora;
	
	@Expose
	private String preu;
	
	@Expose
	private String accioSend;
	
	@Expose
	private String accioBorrar;
	
	
	
	public ComandesTable() {	
	}
	
	public ComandesTable( String nom, String telefon, String address, String hora, String preu, String accioSend, String accioBorrar ) {

		this.nom = nom;
		this.telefon = telefon;
		this.address = address;
		this.hora = hora;
		this.preu = preu;
		this.accioSend = accioSend;
		this.accioBorrar = accioBorrar;
		
	}
	
	public String getNom(){
	
		return nom;
	}
	public void setNom( String nom ){
	
		this.nom = nom;
	}
	public String getTelefon(){
	
		return telefon;
	}
	public void setTelefon( String telefon ){
	
		this.telefon = telefon;
	}
	public String getAddress(){
	
		return address;
	}
	public void setAddress( String address ){
	
		this.address = address;
	}
	public String getHora(){
	
		return hora;
	}
	public void setHora( String hora ){
	
		this.hora = hora;
	}
	public String getPreu(){
	
		return preu;
	}
	public void setPreu( String preu ){
	
		this.preu = preu;
	}
	public String getAccioSend(){
	
		return accioSend;
	}
	public void setAccioSend( String accioSend ){
	
		this.accioSend = accioSend;
	}
	public String getAccioBorrar(){
	
		return accioBorrar;
	}
	public void setAccioBorrar( String accioBorrar ){
	
		this.accioBorrar = accioBorrar;
	}

}
