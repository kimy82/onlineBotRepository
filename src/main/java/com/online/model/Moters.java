package com.online.model;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.annotations.Expose;

public class Moters implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private Integer				id;

	@Expose
	private Integer				numeroMoters;
	
	private Integer				numeroMotersUsed;

	private Date				data;

	private String				hora;

	// CONSTRUCTORS

	public Moters() {

		super();

	}

	// GETTERS i SETTERS

	public Integer getId(){

		return id;
	}

	public void setId( Integer id ){

		this.id = id;
	}

	public Integer getNumeroMoters(){

		return numeroMoters;
	}

	public void setNumeroMoters( Integer numeroMoters ){

		this.numeroMoters = numeroMoters;
	}

	public Date getData(){

		return data;
	}

	public void setData( Date data ){

		this.data = data;
	}

	public String getHora(){
	
		return hora;
	}

	public void setHora( String hora ){
	
		this.hora = hora;
	}

	public Integer getNumeroMotersUsed(){
	
		return numeroMotersUsed;
	}

	public void setNumeroMotersUsed( Integer numeroMotersUsed ){
	
		this.numeroMotersUsed = numeroMotersUsed;
	}
		

}
