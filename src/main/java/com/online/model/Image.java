package com.online.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class Image implements Serializable{



	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	@Expose
	private Integer	id;
	
	protected byte[]	image;
	private String	descripcio;

	public Image() {

	}

	public Image( byte[] image ) {

		this.image = image;
	}

	public Integer getId(){

		return this.id;
	}

	public void setId( Integer id ){

		this.id = id;
	}

	public byte[] getImage(){

		return this.image;
	}

	public void setImage( byte[] image ){

		this.image = image;
	}

	public String getDescripcio(){

		return descripcio;
	}

	public void setDescripcio( String descripcio ){

		this.descripcio = descripcio;
	}

}
