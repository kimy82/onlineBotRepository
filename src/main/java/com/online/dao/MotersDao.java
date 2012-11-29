package com.online.dao;

import java.util.Date;

import com.online.model.Moters;

public interface MotersDao{

	void save( Moters moter );

	void update( Moters moter );

	Moters load( Integer id );
	
	Moters load( Date date, Integer idrestaurant );
}
