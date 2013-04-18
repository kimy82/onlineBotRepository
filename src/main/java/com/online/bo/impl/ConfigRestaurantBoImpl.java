package com.online.bo.impl;

import java.util.Date;

import com.online.bo.ConfigRestaurantBo;
import com.online.dao.ConfigRestaurantDao;
import com.online.exceptions.BOException;
import com.online.model.ConfigRestaurant;

public class ConfigRestaurantBoImpl implements ConfigRestaurantBo{

	private ConfigRestaurantDao	configRestaurantDao;

	public void save( ConfigRestaurant configRestaurant ) throws BOException{

		configRestaurantDao.save(configRestaurant);
	}

	public void delete( ConfigRestaurant configRestaurant ) throws BOException{

		configRestaurantDao.delete(configRestaurant);
	}


	public void update( ConfigRestaurant configRestaurant ) throws BOException{

		checkConfigRestaurantWithId(configRestaurant);
		configRestaurantDao.update(configRestaurant);
	}

	public ConfigRestaurant load( Integer id ) throws BOException{

		if (id == null)
			throw new BOException("NUll id to load");
		return configRestaurantDao.load(id);

	}
	
	public ConfigRestaurant load( Date date, Integer idRestaurant ) throws BOException{
		
		if(date==null || idRestaurant==null) return null;
		return configRestaurantDao.load(date, idRestaurant);
	}

	// PRIVATE METHODS
	private void checkConfigRestaurantWithId( ConfigRestaurant configRestaurant ) throws BOException{

		if (configRestaurant == null || configRestaurant.getId() == null  || configRestaurant.getData() == null) {
			throw new BOException("Null configRestaurant to save");
		}
	}

	// SETTERS
	public void setConfigRestaurantDao( ConfigRestaurantDao configRestaurantDao ){
	
		this.configRestaurantDao = configRestaurantDao;
	}


	

}
