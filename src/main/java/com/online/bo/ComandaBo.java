package com.online.bo;

import java.util.List;

import com.online.exceptions.BOException;
import com.online.model.Comandes;

public interface ComandaBo{

	void save( Comandes comanda ) throws BOException;

	void update( Comandes comanda ) throws BOException;

	void delete( Comandes comanda ) throws BOException;

	Comandes load( Long id ) throws BOException;
	
	List<Comandes> getAll();
	
	List<Comandes> getAllByUser(Long id, boolean initPLatComanda);

	List<Comandes> getAllByUserAndTemps( Long id, Integer lastNdias ) throws BOException;
	
	List<Comandes> getAllToConfirm() throws BOException;
}
