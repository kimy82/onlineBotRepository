package com.online.dao;

import java.util.List;

import com.online.model.Restaurant;


public interface RestaurantsDao {
	
	void save(Restaurant restaurant);
	
	void saveOrUpdate(Restaurant restaurant);
	
	void update(Restaurant restaurant);
	
	void delete(Restaurant restaurant);
	
	Restaurant load (Integer id, boolean initPlats, boolean initMoters, boolean initConfig );
	
	List<Restaurant> getAll(boolean lazyPlatsVotacio, boolean lazyConfigObert, boolean votacioRestaurant);
}
