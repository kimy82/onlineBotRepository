package com.online.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.online.dao.PromocionsDao;
import com.online.model.Promocio;

public class PromocionsDaoImpl extends HibernateDaoSupport implements PromocionsDao{
	
	public void save(Promocio promocio){
		getHibernateTemplate().save(promocio);
	}
	
	public void update(Promocio promocio){
		getHibernateTemplate().update(promocio);
	}
	
	public void delete(Promocio promocio){
		
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.delete(promocio);
		
		session.getTransaction().commit();
	
		
		session.close();
						
	}

	public Promocio load(Integer id){
		return getHibernateTemplate().load(Promocio.class, id);
	}

	public List<Promocio> getAll(){
		return getHibernateTemplate().loadAll(Promocio.class);
	}


	
	

}
