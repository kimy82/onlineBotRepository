package com.online.dao.impl;

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
						
		
		//getHibernateTemplate().delete(plat);
	}

	public Plat load(Long id){
		return getHibernateTemplate().load(Plat.class, id);
	}


	
	

}
