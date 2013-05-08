package com.online.pojos;


@SuppressWarnings({ "serial", "rawtypes" })
public class BasicSub extends Basic{

	private Long	idSub;
	private String  tipus;
	private Double  preu;
	private String  descripcioES;

	

	public BasicSub() {

	}

	public BasicSub( Integer id, String descripcio ) {

		super(id,descripcio);		
	}

	public Long getIdSub(){
	
		return idSub;
	}

	public void setIdSub( Long idSub ){
	
		this.idSub = idSub;
	}

	public String getTipus() {
		return tipus;
	}

	public void setTipus(String tipus) {
		this.tipus = tipus;
	}

	public Double getPreu() {
		return preu;
	}

	public void setPreu(Double preu) {
		this.preu = preu;
	}

	public String getDescripcioES() {
		return descripcioES;
	}

	public void setDescripcioES(String descripcioES) {
		this.descripcioES = descripcioES;
	}
	

	
}
