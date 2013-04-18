package com.online.bo;

import java.util.Date;

import com.online.exceptions.BOException;
import com.online.model.ConfigRestaurant;

public interface ConfigRestaurantBo{

	void save( ConfigRestaurant configRestaurant ) throws BOException;

	void update( ConfigRestaurant configRestaurant ) throws BOException;

	ConfigRestaurant load( Integer id ) throws BOException;
	
	ConfigRestaurant load( Date date, Integer idRestaurant ) throws BOException;
	
	void delete( ConfigRestaurant configRestaurant ) throws BOException;

}
