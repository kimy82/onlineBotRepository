package com.online.pojos;

import java.io.Serializable;

@SuppressWarnings({ "serial", "rawtypes" })
public class BasicString implements Serializable, Comparable{

	private String	id;

	private String	descripcio;

	public BasicString() {

	}

	public BasicString( String id, String descripcio ) {

		this.id = id;
		this.descripcio = descripcio;
	}

	public String getDescripcio(){

		return descripcio;
	}

	public void setDescripcio( String descripcio ){

		this.descripcio = descripcio;
	}

	public String getId(){

		return id;
	}

	public void setId( String id ){

		this.id = id;
	}

	public int compareTo( Object o ){

		BasicString b = (BasicString) o;
		return this.descripcio.compareTo(b.descripcio);
	}

}
