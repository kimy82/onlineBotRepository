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
	private static final long	serialVersionUID	= 1L;

	@Expose
	private Long				id;

	@Expose
	private String				hora;

	@Expose
	private Date				dia;

	@Expose
	private Double				preu;

	@Expose
	protected String			observacions;

	@Expose
	protected String			address;

	protected List<PlatComanda>	plats				= new LinkedList<PlatComanda>();

	protected Users				user;

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

}
