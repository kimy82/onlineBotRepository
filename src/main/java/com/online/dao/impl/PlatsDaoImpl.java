package com.online.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.online.dao.PlatsDao;
import com.online.exceptions.BOException;
import com.online.model.Plat;
import com.online.model.Restaurant;

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


	
	

}
