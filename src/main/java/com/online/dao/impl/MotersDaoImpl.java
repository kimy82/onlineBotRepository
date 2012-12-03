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
	
	public List<Moters> load( Date date ){
		Session session = this.getSessionFactory().openSession();

		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Moters> motersList = (List<Moters>) session.createQuery("from Moters mts where mts.data= ? ").setDate(0, date).list();
		if (motersList.isEmpty())
			return null;						
		session.close();

		return motersList;
	}

	public Moters load( String hora, Date dia ){

		Session session = this.getSessionFactory().openSession();

		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Moters> motersList = (List<Moters>) session.createQuery("from Moters mts where mts.data= ? and mts.hora= ? ").setDate(0, dia).setString(1, hora).list();
		if (motersList.isEmpty())
			return null;						
		session.close();

		return motersList.get(0);
	}
}
