package com.online.dao;

import java.util.Date;
import java.util.List;

import com.online.model.Promocio;
import com.online.model.PromocioAPartirDe;
import com.online.model.PromocioNumComandes;

public interface PromocionsDao{

	void save( Promocio promocio );

	void update( Promocio promocio );

	void delete( Promocio promocio );

	<E extends Promocio> E load( Integer id );
	
	List<Promocio> getAll();
	
	List<PromocioAPartirDe> getPromosAPartirDe(Double importAPartirDe, Date dia);
	
	List<PromocioNumComandes> getPromosNumComandes(Integer numComandes, Integer temps);
}
