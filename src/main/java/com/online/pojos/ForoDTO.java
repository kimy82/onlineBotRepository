package com.online.pojos;

import com.google.gson.annotations.Expose;

public class ForoDTO{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Expose
	private Long				idPlat;

	@Expose
	private Long				idBeguda;

	@Expose
	private Long				idComment;

	@Expose
	private String				comment;

	// CONSTRUCTORS

	public ForoDTO() {

		super();

	}

	public ForoDTO( Long idPlat, Long idBeguda, Long idComment, String comment ) {

		super();
		this.idPlat = idPlat;
		this.idBeguda = idBeguda;
		this.idComment = idComment;
		this.comment = comment;
	}

	// GETTERS i SETTERS
	public Long getIdPlat(){

		return idPlat;
	}

	public void setIdPlat( Long idPlat ){

		this.idPlat = idPlat;
	}

	public Long getIdBeguda(){

		return idBeguda;
	}

	public void setIdBeguda( Long idBeguda ){

		this.idBeguda = idBeguda;
	}

	public Long getIdComment(){

		return idComment;
	}

	public void setIdComment( Long idComment ){

		this.idComment = idComment;
	}

	public String getComment(){

		return comment;
	}

	public void setComment( String comment ){

		this.comment = comment;
	}

}
