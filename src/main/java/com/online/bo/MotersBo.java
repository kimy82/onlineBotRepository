package com.online.bo;

import java.util.Date;
import java.util.List;

import com.online.exceptions.BOException;
import com.online.model.Moters;

public interface MotersBo{

	void save( Moters moter) throws BOException;

	void update( Moters moter ) throws BOException;

	Moters load( Integer id ) throws BOException;

	List<Moters> load( Date date ) throws BOException;
	
	Moters load( String hora, Date dia ) throws BOException;
}
