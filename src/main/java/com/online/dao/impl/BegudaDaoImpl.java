package com.online.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.online.dao.BegudaDao;
import com.online.model.Beguda;
import com.online.model.BegudaComanda;

public class BegudaDaoImpl extends HibernateDaoSupport implements BegudaDao{
	
	public void save(Beguda beguda){
		getHibernateTemplate().save(beguda);
	}
	
	public void update(Beguda beguda){
		Beguda begudaFromDB = getHibernateTemplate().load(Beguda.class, beguda.getId());
		
		if(beguda.getNom()!=null){
			begudaFromDB.setNom(beguda.getNom());
		}
		if(beguda.getNomES()!=null){
			begudaFromDB.setNomES(beguda.getNomES());
		}
		
		if(beguda.getFoto()!=null){
			begudaFromDB.setFoto(beguda.getFoto());
		}
		if(beguda.getDescripcio()!=null){
			begudaFromDB.setDescripcio(beguda.getDescripcio());
		}
		if(beguda.getDescripcioES()!=null){
			begudaFromDB.setDescripcioES(beguda.getDescripcioES());
		}
		if(beguda.getTipus()!=null){
			begudaFromDB.setTipus(beguda.getTipus());
		}
		if(beguda.getPreu()!=null){
			begudaFromDB.setPreu(beguda.getPreu());
		}
		getHibernateTemplate().update(begudaFromDB);
	}
	
	public void delete(Beguda beguda){
		
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Long begudaId = beguda.getId();
	
		List<BegudaComanda> begudaComandaList = session.createCriteria(BegudaComanda.class).add(Restrictions.eq("beguda.id", begudaId)).list();
		for(BegudaComanda bg :begudaComandaList){
			session.createSQLQuery("delete from comanda_begudes where BEGUDACOMANDA_ID="+bg.getId()).executeUpdate();
			session.delete(bg);
		}
		Beguda begudaToDelete = (Beguda) session.load(Beguda.class, begudaId);
		session.delete(begudaToDelete);
	
		
		
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
	
	public List<Beguda> getAll(String tipus, boolean initComentsAndVotacions){

		Session session = this.getSessionFactory().openSession();

		session.beginTransaction();
		
		List<Beguda> begudaList =(List<Beguda>) session.createQuery("from Beguda bg where bg.tipus='"+tipus+"'").setCacheable(true).list();
		if(initComentsAndVotacions){
			for(Beguda bg : begudaList){
				Hibernate.initialize(bg.getComments());				
				Hibernate.initialize(bg.getVotacio());
			}
		}
		session.close();
		return begudaList;
	}

	public Beguda loadBegudaAndForos( Long id ){

		Session session = this.getSessionFactory().openSession();

		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Beguda> begudaList = (List<Beguda>) session.createQuery("from Beguda bg where bg.id=" + id).list();
		if (begudaList.isEmpty())
			return null;
		
		Beguda beguda = begudaList.get(0);
		
		Hibernate.initialize(beguda.getComments());
		
		Hibernate.initialize(beguda.getVotacio());
		
		session.close();

		return beguda;
	}

	
	

}
