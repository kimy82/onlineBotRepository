package com.online.pojos;

import com.google.gson.annotations.Expose;

public class ARecollirDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Expose
	private String address = "";

	@Expose
	private boolean moreThanOne = false;

	// CONSTRUCTORS
	public ARecollirDTO() {

		super();

	}

	public ARecollirDTO(boolean moreThanOne, String address) {

		this.moreThanOne = moreThanOne;
		this.address = address;

	}

	// GETTERS i SETTERS
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean getMoreThanOne() {
		return moreThanOne;
	}

	public void setMoreThanOne(boolean moreThanOne) {
		this.moreThanOne = moreThanOne;
	}

}
