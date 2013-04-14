package com.online.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

public class Clau implements Serializable{

	


	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	
	@Expose
	private Long				id;
	
	@Expose
	private String				entorn;
	
	@Expose
	private String				code;
	
	// CONSTRUCTORS
	 
	public Clau() {

		super();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEntorn() {
		return entorn;
	}

	public void setEntorn(String entorn) {
		this.entorn = entorn;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}			
}
