package com.online.dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.online.dao.RestaurantsDao;
import com.online.model.Plat;
import com.online.model.Restaurant;

public class RestaurantsDaoImpl extends HibernateDaoSupport implements RestaurantsDao{

	@Transactional
	public void save( Restaurant restaurant ){

		getHibernateTemplate().save(restaurant);
	}

	@Transactional
	public void saveOrUpdate( Restaurant restaurant ){

		getHibernateTemplate().saveOrUpdate(restaurant);
	}

	@Transactional
	public void update( Restaurant restaurant ){

		getHibernateTemplate().update(restaurant);
	}

	@Transactional
	public void delete( Restaurant restaurant ){

		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();

		session.delete(restaurant);
		session.getTransaction().commit();

		session.close();
	}

	public Restaurant load( Integer id, boolean initPlats, boolean initMoters, boolean initConfig ){

		Session session = this.getSessionFactory().openSession();

		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Restaurant> restaurantsList = (List<Restaurant>) session.createQuery("from Restaurant res where res.id=" + id).list();
		if (restaurantsList.isEmpty())
			return null;
		Restaurant restaurant = restaurantsList.get(0);
		if (initPlats)
			Hibernate.initialize(restaurant.getPlats());

		if (initConfig)
			Hibernate.initialize(restaurant.getConfigRestaurants());
		
		session.close();

		return restaurant;
	}

	@Transactional
	public List<Restaurant> getAll(boolean lazyPlatsVotacio, boolean lazyConfigObert, boolean votacioRestaurant){
		
		Session session = this.getSessionFactory().openSession();

		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Restaurant> restaurantsList = (List<Restaurant>) session.createQuery("from Restaurant res").list();
		if (restaurantsList.isEmpty())
			return null;
		if(!lazyPlatsVotacio){

			for(Restaurant restaurant :restaurantsList){
				Hibernate.initialize(restaurant.getVotacio());
				Hibernate.initialize(restaurant.getPlats());
				Set<Plat> plats = restaurant.getPlats();
				for(Plat pl : plats){
					Hibernate.initialize(pl.getVotacio());
				}
			}	
		}		
		if(!lazyConfigObert){
			for(Restaurant restaurant :restaurantsList){
				Hibernate.initialize(restaurant.getConfigRestaurants());
			}
		}
		if(!votacioRestaurant){
			for(Restaurant restaurant :restaurantsList){
				Hibernate.initialize(restaurant.getVotacio());
			}
		}
		session.close();
		
		return restaurantsList;
						
	}
	
	public List<Restaurant> getAllLoadPlats(){

		Session session = this.getSessionFactory().openSession();

		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Restaurant> restaurantsList = (List<Restaurant>) session.createQuery("from Restaurant res").list();
		if (restaurantsList.isEmpty())
			return null;
		for(Restaurant restaurant :restaurantsList){
			Hibernate.initialize(restaurant.getVotacio());
			Hibernate.initialize(restaurant.getPlats());
			Set<Plat> plats = restaurant.getPlats();
			for(Plat pl : plats){
				Hibernate.initialize(pl.getVotacio());
			}
		}	
				
		session.close();
		
		return restaurantsList;

	}

}
