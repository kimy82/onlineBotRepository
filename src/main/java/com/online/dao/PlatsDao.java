package com.online.dao;

import java.util.List;

import com.online.model.Plat;

public interface PlatsDao{

	void save( Plat plat );

	void update( Plat plat );

	void delete( Plat plat );
	
	Plat loadLaziFalse(Long id);

	Plat load( Long id );
	
	List<Plat> getAll();
	
	Plat loadPLatAndForos( Long id );
	
	void changePriority(Long idPlat, Integer prioritat);
}
