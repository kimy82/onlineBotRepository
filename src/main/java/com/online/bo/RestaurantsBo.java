package com.online.bo;

import java.util.List;

import com.online.exceptions.BOException;
import com.online.model.Restaurant;



public interface RestaurantsBo {
	
	void save(Restaurant restaurant) throws BOException;
	
	void saveOrUpdate(Restaurant restaurant) throws BOException;
	
	void update(Restaurant restaurant) throws BOException;
	
	void delete(Restaurant restaurant) throws BOException;
		
	Restaurant load(Integer id,  boolean initPlats, boolean initMoters, boolean initConfig) throws BOException;
	
	List<Restaurant> getAll();
	

}
