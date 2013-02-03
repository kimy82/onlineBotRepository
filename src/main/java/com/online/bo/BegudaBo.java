package com.online.bo;

import java.util.List;

import com.online.exceptions.BOException;
import com.online.model.Beguda;

public interface BegudaBo{

	void save( Beguda beguda ) throws BOException;

	void update( Beguda beguda ) throws BOException;

	void delete( Beguda beguda ) throws BOException;

	Beguda load( Long id ) throws BOException;
	
	List<Beguda> getAll();
	
	List<Beguda> getAll(String tipus) throws BOException;

}
