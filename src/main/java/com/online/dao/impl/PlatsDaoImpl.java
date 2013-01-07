package com.online.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.online.dao.PlatsDao;
import com.online.model.Plat;

public class PlatsDaoImpl extends HibernateDaoSupport implements PlatsDao{
	
	public void save(Plat plat){
		getHibernateTemplate().save(plat);
	}
	
	public void update(Plat plat){
		getHibernateTemplate().update(plat);
	}
	
	public void delete(Plat plat){
		
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.delete(plat);
		SQLQuery sql =session.createSQLQuery("delete from restaurants_plats where plat_id="+plat.getId());
		sql.executeUpdate();	
		
		session.getTransaction().commit();
	
		
		session.close();
						
	}
	
	public List<Plat> getAll(){
		
		return getHibernateTemplate().loadAll(Plat.class);
	}

	public Plat load(Long id){
		return getHibernateTemplate().load(Plat.class, id);
	}


	public Plat loadLaziFalse(Long id){
		Session session = this.getSessionFactory().openSession();

		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Plat> platsList = (List<Plat>) session.createQuery("from Plat plts where plts.id=" + id).list();
		if (platsList.isEmpty())
			return null;
		Plat plat = platsList.get(0);
	
		Hibernate.initialize(plat.getRestaurants());
		
		session.close();

		return plat;
	}

	public Plat loadPLatAndForos( Long id ){

		Session session = this.getSessionFactory().openSession();

		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Plat> platList = (List<Plat>) session.createQuery("from Plat pl where pl.id=" + id).list();
		if (platList.isEmpty())
			return null;
		
		Plat plat = platList.get(0);
		
		Hibernate.initialize(plat.getComments());
		
		session.close();

		return plat;
	}
	

}
