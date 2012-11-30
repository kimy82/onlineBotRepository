package com.online.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.online.dao.PromocionsDao;
import com.online.model.Promocio;
import com.online.model.PromocioAPartirDe;
import com.online.model.PromocioNumComandes;

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

	@SuppressWarnings("unchecked")
	public <E extends Promocio> E load(Integer id){
		try{
			PromocioNumComandes pnum = getHibernateTemplate().load(PromocioNumComandes.class, id);
			return (E) pnum;
		}catch(HibernateObjectRetrievalFailureException e){
			PromocioAPartirDe papd = getHibernateTemplate().load(PromocioAPartirDe.class, id);
			return (E) papd;
		}
		
	}

	public List<Promocio> getAll(){
		return getHibernateTemplate().loadAll(Promocio.class);
	}


	
	

}
