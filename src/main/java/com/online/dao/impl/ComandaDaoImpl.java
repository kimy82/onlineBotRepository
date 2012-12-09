package com.online.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.online.dao.ComandaDao;
import com.online.model.Comandes;

public class ComandaDaoImpl extends HibernateDaoSupport implements ComandaDao{
	
	public void save(Comandes comanda){
		getHibernateTemplate().save(comanda);
	}
	
	public void update(Comandes comanda){
		getHibernateTemplate().update(comanda);
	}
	
	public void delete(Comandes comanda){
		
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.delete(comanda);		
		
		session.getTransaction().commit();
	
		
		session.close();
						
	}

	public Comandes  load(Long id){
		
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		
		Comandes comandes =(Comandes) session.load(Comandes.class, id);							
		
		session.close();
		
		
		return comandes;
	}

	public List<Comandes> getAll(){

		return getHibernateTemplate().loadAll(Comandes.class);
	}


	
	

}
