package com.online.dao;

import java.util.List;

import com.online.model.Promocio;

public interface PromocionsDao{

	void save( Promocio promocio );

	void update( Promocio promocio );

	void delete( Promocio promocio );

	Promocio load( Integer id );
	
	List<Promocio> getAll();
}
