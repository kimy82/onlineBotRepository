package com.online.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.online.dao.PromocionsDao;
import com.online.model.Promocio;
import com.online.model.PromocioAPartirDe;
import com.online.model.PromocioAssociada;
import com.online.model.PromocioNumComandes;

public class PromocionsDaoImpl extends HibernateDaoSupport implements PromocionsDao{

	public void save( Promocio promocio ){

		getHibernateTemplate().save(promocio);
	}

	public void update( Promocio promocio ){

		getHibernateTemplate().update(promocio);
	}

	public void saveAssociada( PromocioAssociada promocio ){

		getHibernateTemplate().save(promocio);
	}

	public void updateAssociada( PromocioAssociada promocio ){

		getHibernateTemplate().update(promocio);
	}
	
	public void updateNumUsed(Integer promoId,String data){
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Promocio promo = (Promocio) session.load(Promocio.class, promoId);
		StringBuffer dates = new StringBuffer("");
		dates.append(promo.getDates()==null?data: promo.getDates()+" "+data);
		promo.setDates(dates.toString());
		if(promo.getNumUsed()==null){
			promo.setNumUsed(1);			
		}else{
			promo.setNumUsed(promo.getNumUsed()+1);
		}
		session.update(promo);
		session.getTransaction().commit();
		session.close();
	}

	public List<PromocioAPartirDe> getPromosAPartirDe( Double importAPartirDe, Date dia ){

		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(PromocioAPartirDe.class);
		criteria.add(Restrictions.le("importAPartirDe", importAPartirDe));
		//if (dia != null)
			//criteria.add(Restrictions.eq("dia", dia));
		//else
			//criteria.add(Restrictions.or(Restrictions.isEmpty("dia"), Restrictions.eq("dia", Calendar.getInstance().getTime())));

		return criteria.list();
	}

	public List<PromocioNumComandes> getPromosNumComandes( Integer numComandes, Integer temps ){

		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(PromocioNumComandes.class);
		if(numComandes!=null)
		criteria.add(Restrictions.ge("numComandes", numComandes));
		return criteria.list();

	}

	public void delete( Promocio promocio ){

		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();

		session.delete(promocio);

		session.getTransaction().commit();

		session.close();

	}
	
	public void deleteAssociada( PromocioAssociada promocio ){

		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();

		session.delete(promocio);

		session.getTransaction().commit();

		session.close();

	}

	@SuppressWarnings("unchecked")
	public <E extends Promocio> E load( Integer id ){

		try {
			PromocioNumComandes pnum = getHibernateTemplate().load(PromocioNumComandes.class, id);
			return (E) pnum;
		} catch (HibernateObjectRetrievalFailureException e) {
			PromocioAPartirDe papd = getHibernateTemplate().load(PromocioAPartirDe.class, id);
			return (E) papd;
		} catch (java.lang.ClassCastException e){
			PromocioAPartirDe papd = getHibernateTemplate().load(PromocioAPartirDe.class, id);
			return (E) papd;
		}

	}
	
	public PromocioAssociada loadAssociada( Integer id ){
		
			return getHibernateTemplate().load(PromocioAssociada.class, id);
	}
	
	public List<PromocioAssociada>  loadAssociadaByCode( String code ){
		
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		List<PromocioAssociada> promo = (List<PromocioAssociada>) session.createQuery("from PromocioAssociada where code=?").setString(1, code).list();
		
		
	
		session.getTransaction().commit();
		session.close();
		return promo;
		
	}
	
	public Promocio loadWithDates(Integer promoId){
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Promocio promo = (Promocio) session.load(Promocio.class, promoId);
		Hibernate.initialize(promo.getDates());
		session.getTransaction().commit();
		session.close();
		return promo;
	}
	

	public List<Promocio> getAll(){

		return getHibernateTemplate().loadAll(Promocio.class);
	}
	
	public List<PromocioAssociada> getAllAssociades(){

		return getHibernateTemplate().loadAll(PromocioAssociada.class);
	}
	
	
	public List<PromocioNumComandes> getAllNumComandes(){

		return getHibernateTemplate().loadAll(PromocioNumComandes.class);
	}
	
	public List<PromocioAPartirDe> getAllAPartirDe(){

		return getHibernateTemplate().loadAll(PromocioAPartirDe.class);
	}

}
