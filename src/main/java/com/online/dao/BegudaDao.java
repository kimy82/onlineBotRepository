package com.online.dao;

import java.util.List;

import com.online.model.Beguda;

public interface BegudaDao{

	void save( Beguda beguda );

	void update(Beguda beguda);

	void delete( Beguda beguda);

	Beguda load( Long id );
	
	List<Beguda> getAll();
	
	List<Beguda> getAll(String tipus);
}
