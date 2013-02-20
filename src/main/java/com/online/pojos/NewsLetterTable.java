package com.online.pojos;

import com.google.gson.annotations.Expose;
import com.online.model.NewsLetter;

public class NewsLetterTable extends NewsLetter{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	@Expose
	private String accio;	


	// CONSTRUCTORS

	public NewsLetterTable() {

		super();

	}


	public String getAccio(){
	
		return accio;
	}


	public void setAccio( String accio ){
	
		this.accio = accio;
	}

}