package com.online.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.online.dao.BegudaDao;
import com.online.model.Beguda;
import com.online.model.Plat;

public class BegudaDaoImpl extends HibernateDaoSupport implements BegudaDao{
	
	public void save(Beguda beguda){
		getHibernateTemplate().save(beguda);
	}
	
	public void update(Beguda beguda){
		getHibernateTemplate().update(beguda);
	}
	
	public void delete(Beguda beguda){
		
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.delete(beguda);		
		
		session.getTransaction().commit();
	
		
		session.close();
						
	}

	public Beguda  load(Long id){
		
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		
		Beguda beguda =(Beguda) session.load(Beguda.class, id);							
		
		session.close();
		
		
		return beguda;
	}

	public List<Beguda> getAll(){

		return getHibernateTemplate().loadAll(Beguda.class);
	}
	
	public List<Beguda> getAll(String tipus){

		return getHibernateTemplate().find("from Beguda bg where bg.tipus=?", tipus);
	}



	
	

}
