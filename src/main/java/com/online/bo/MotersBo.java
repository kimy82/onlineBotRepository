package com.online.bo;

import java.util.Date;

import com.online.exceptions.BOException;
import com.online.model.Moters;

public interface MotersBo{

	void save( Moters moter) throws BOException;

	void update( Moters moter ) throws BOException;

	Moters load( Integer id ) throws BOException;

	Moters load( Date date, Integer idrestaurant ) throws BOException;
}
