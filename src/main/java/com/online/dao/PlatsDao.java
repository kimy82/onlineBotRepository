package com.online.dao;

import com.online.model.Plat;

public interface PlatsDao{

	void save( Plat plat );

	void update( Plat plat );

	void delete( Plat plat );

	Plat load( Long id );
}
