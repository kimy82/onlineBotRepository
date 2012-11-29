package com.online.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.online.dao.ConfigRestaurantDao;
import com.online.model.ConfigRestaurant;

public class ConfigRestaurantDaoImpl extends HibernateDaoSupport implements ConfigRestaurantDao{

	public void save( ConfigRestaurant configRestaurant ){

		getHibernateTemplate().save(configRestaurant);
	}

	public void update( ConfigRestaurant configRestaurant ){

		getHibernateTemplate().update(configRestaurant);
	}

	public ConfigRestaurant load( Integer id ){

		return getHibernateTemplate().load(ConfigRestaurant.class, id);
	}
	
	public ConfigRestaurant load( Date date, Integer idRestaurant ){
		Session session = this.getSessionFactory().openSession();

		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<ConfigRestaurant> ConfigRestaurantsList = (List<ConfigRestaurant>) session.createQuery("from ConfigRestaurant confRest where confRest.data= ? and confRest.idRestaurant= ?").setDate(0, date).setInteger(1, idRestaurant).list();
		if (ConfigRestaurantsList.isEmpty())
			return null;
		ConfigRestaurant configRestaurant = ConfigRestaurantsList.get(0);
				
		session.close();

		return configRestaurant;
		
	}

}
