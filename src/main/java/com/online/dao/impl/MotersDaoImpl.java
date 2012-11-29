package com.online.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.online.dao.MotersDao;
import com.online.model.Moters;

public class MotersDaoImpl extends HibernateDaoSupport implements MotersDao{

	public void save( Moters moter ){

		getHibernateTemplate().save(moter);
	}

	public void update( Moters moter ){

		getHibernateTemplate().update(moter);
	}

	public Moters load( Integer id ){

		return getHibernateTemplate().load(Moters.class, id);
	}
	
	public Moters load( Date date, Integer idrestaurant ){
		Session session = this.getSessionFactory().openSession();

		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Moters> motersList = (List<Moters>) session.createQuery("from Moters mts where mts.data= ? and mts.idRestaurant= ?").setDate(0, date).setInteger(1, idrestaurant).list();
		if (motersList.isEmpty())
			return null;
		Moters moter = motersList.get(0);
				
		session.close();

		return moter;
	}
}
