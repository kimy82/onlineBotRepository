package com.online.bo;

import java.util.List;

import com.online.exceptions.BOException;
import com.online.model.Plat;

public interface PlatsBo{

	void save( Plat plat ) throws BOException;

	void update( Plat plat ) throws BOException;
 
	void delete( Plat plat ) throws BOException;

	Plat load( Long id ,boolean lazy) throws BOException; 
	
	List<Plat> getAll() throws BOException;

	Plat loadPLatAndForos( Long id ) throws BOException;
}
