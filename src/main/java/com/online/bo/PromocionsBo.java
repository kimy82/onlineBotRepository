package com.online.bo;

import java.util.List;

import com.online.exceptions.BOException;
import com.online.model.Promocio;

public interface PromocionsBo{

	void save( Promocio promocio ) throws BOException;

	void update( Promocio promocio ) throws BOException;

	void delete( Promocio promocio ) throws BOException;
	
	List<Promocio> getAll();

	<E extends Promocio> E load( Integer id ) throws BOException;

}
