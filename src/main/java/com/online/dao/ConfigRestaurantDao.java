package com.online.dao;

import java.util.Date;

import com.online.model.ConfigRestaurant;

public interface ConfigRestaurantDao{

	void save( ConfigRestaurant configRestaurant );

	void update( ConfigRestaurant configRestaurant );

	ConfigRestaurant load( Integer id );

	ConfigRestaurant load( Date date, Integer idRestaurant );
}
