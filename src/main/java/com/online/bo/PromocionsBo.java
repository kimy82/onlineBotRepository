package com.online.bo;

import java.util.Date;
import java.util.List;

import com.online.exceptions.BOException;
import com.online.model.Promocio;
import com.online.model.PromocioAPartirDe;
import com.online.model.PromocioNumComandes;

public interface PromocionsBo{

	void save( Promocio promocio ) throws BOException;

	void update( Promocio promocio ) throws BOException;

	void delete( Promocio promocio ) throws BOException;
	
	List<Promocio> getAll();

	<E extends Promocio> E load( Integer id ) throws BOException;
	
	List<PromocioAPartirDe> getPromosAPartirDe(Double importAPartirDe, Date dia)throws BOException;
	
	List<PromocioNumComandes> getPromosNumComandes(Integer numComandes, Integer temps)throws BOException;

}
