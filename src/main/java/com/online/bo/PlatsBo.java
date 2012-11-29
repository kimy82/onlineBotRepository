package com.online.bo;

import com.online.exceptions.BOException;
import com.online.model.Plat;

public interface PlatsBo{

	void save( Plat plat ) throws BOException;

	void update( Plat plat ) throws BOException;

	void delete( Plat plat ) throws BOException;

	Plat load( Long id ) throws BOException;

}
