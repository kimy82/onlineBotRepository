package com.online.bo.impl;

import java.util.List;

import com.online.bo.RestaurantsBo;
import com.online.dao.RestaurantsDao;
import com.online.exceptions.BOException;
import com.online.model.Restaurant;

public class RestaurantsBoImpl implements RestaurantsBo{

	private RestaurantsDao	restaurantsDao;

	public void save( Restaurant restaurant ) throws BOException{

		checkRestaurant(restaurant);
		restaurantsDao.save(restaurant);
	}

	public void saveOrUpdate( Restaurant restaurant ) throws BOException{

		checkRestaurant(restaurant);
		restaurantsDao.save(restaurant);
	}

	public void update( Restaurant restaurant ) throws BOException{

		checkRestaurantWithId(restaurant);
		restaurantsDao.update(restaurant);
	}

	public void delete( Restaurant restaurant ) throws BOException{

		checkRestaurantWithId(restaurant);
		restaurantsDao.delete(restaurant);
	}

	public Restaurant load( Integer id, boolean initPlats, boolean initMoters, boolean initConfig ) throws BOException{

		if (id == null)
			throw new BOException("Null id");
		return restaurantsDao.load(id, initPlats, initMoters, initConfig);

	}

	public List<Restaurant> getAll(boolean lazyPlatsVotacio, boolean lazyConfigObert, boolean votacioRestaurant){

		return restaurantsDao.getAll(lazyPlatsVotacio,lazyConfigObert, votacioRestaurant);
	}

	// PRIVATE METHODS
	private void checkRestaurant( Restaurant restaurant ) throws BOException{

		if (restaurant == null || restaurant.getNom() == null || restaurant.getNom().equals("")) {
			throw new BOException("Null restaurant to save");
		}
	}

	private void checkRestaurantWithId( Restaurant restaurant ) throws BOException{

		if (restaurant == null || restaurant.getId() == null || restaurant.getNom() == null || restaurant.getNom().equals("")) {
			throw new BOException("Null restaurant to save");
		}
	}

	// SETTERS

	public void setRestaurantsDao( RestaurantsDao restaurantsDao ){

		this.restaurantsDao = restaurantsDao;
	}

}
