package com.online.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.online.dao.ComandaDao;
import com.online.model.Comandes;
import com.online.model.PlatComanda;

public class ComandaDaoImpl extends HibernateDaoSupport implements ComandaDao{

	public List<Comandes> getAllByUserAndTemps( Long id, Integer lastNdias ){

		List<Comandes> comandaList = new ArrayList<Comandes>();

		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session.createQuery("from Comandes cmd where cmd.user.id=" + id + " and cmd.fentrada >=?");

		query.setDate(0, getDate(lastNdias));

		comandaList = ((List<Comandes>) query.list());

		session.close();

		return comandaList;

	}

	public void save( Comandes comanda ){

		getHibernateTemplate().save(comanda);
	}

	public void update( Comandes comanda ){

		getHibernateTemplate().update(comanda);
	}

	public void delete( Comandes comanda ){

		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();

		session.delete(comanda);

		session.getTransaction().commit();

		session.close();

	}

	public Comandes load( Long id ){

		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();

		Comandes comandes = (Comandes) session.load(Comandes.class, id);

		Hibernate.initialize(comandes.getPlats());

		for (PlatComanda pl : comandes.getPlats()) {
			Hibernate.initialize(pl.getPlat().getRestaurants());
		}

		session.close();

		return comandes;
	}

	public List<Comandes> getAll(){

		return getHibernateTemplate().loadAll(Comandes.class);
	}

	@SuppressWarnings("unchecked")
	public List<Comandes> getAllByUser( Long id ){

		List<Comandes> comandaList = new ArrayList<Comandes>();

		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session.createQuery("from Comandes cmd where cmd.user.id=" + id);

		comandaList = ((List<Comandes>) query.list());

		session.close();

		return comandaList;
	}

	
	// PRIVATES

	private Date getDate( int diaslessFromActualDate ){

		Calendar cal = Calendar.getInstance();
		cal.roll(Calendar.DAY_OF_MONTH, -diaslessFromActualDate);

		return cal.getTime();

	}

}
