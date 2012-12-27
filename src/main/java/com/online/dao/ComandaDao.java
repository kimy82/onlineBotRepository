package com.online.dao;

import java.util.List;

import com.online.model.Comandes;

public interface ComandaDao{

	void save( Comandes comanda );

	void update(Comandes comanda);

	void delete( Comandes comanda);

	Comandes load( Long id );
	
	List<Comandes> getAll();
	
	List<Comandes> getAllByUser(Long id);
	
	List<Comandes> getAllByUserAndTemps(Long id, Integer lastNdias);
}
