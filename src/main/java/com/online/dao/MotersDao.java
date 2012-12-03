package com.online.dao;

import java.util.Date;
import java.util.List;

import com.online.model.Moters;

public interface MotersDao{

	void save( Moters moter );

	void update( Moters moter );

	Moters load( Integer id );
	
	Moters load( String hora, Date dia );
	
	List<Moters> load( Date date );
}
