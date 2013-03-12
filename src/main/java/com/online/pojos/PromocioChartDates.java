package com.online.pojos;

import com.google.gson.annotations.Expose;

public class PromocioChartDates{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;


	@Expose
	private Integer				date				= 0;

	@Expose
	private Integer				numUsed				= 0;

	// CONSTRUCTORS
	public PromocioChartDates() {

		super();
	}

	// GETTERS i SETTERS
	public Integer getNumUsed(){

		return numUsed;
	}

	public void setNumUsed( Integer numUsed ){

		this.numUsed = numUsed;
	}

	public Integer getDate(){
	
		return date;
	}

	public void setDate( Integer date ){
	
		this.date = date;
	}
}
