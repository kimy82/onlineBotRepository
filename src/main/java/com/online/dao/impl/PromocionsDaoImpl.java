package com.online.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.online.dao.PromocionsDao;
import com.online.model.Promocio;
import com.online.model.PromocioAPartirDe;
import com.online.model.PromocioNumComandes;

public class PromocionsDaoImpl extends HibernateDaoSupport implements PromocionsDao{

	public void save( Promocio promocio ){

		getHibernateTemplate().save(promocio);
	}

	public void update( Promocio promocio ){

		getHibernateTemplate().update(promocio);
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

	@SuppressWarnings("unchecked")
	public <E extends Promocio> E load( Integer id ){

		try {
			PromocioNumComandes pnum = getHibernateTemplate().load(PromocioNumComandes.class, id);
			return (E) pnum;
		} catch (HibernateObjectRetrievalFailureException e) {
			PromocioAPartirDe papd = getHibernateTemplate().load(PromocioAPartirDe.class, id);
			return (E) papd;
		}

	}

	public List<Promocio> getAll(){

		return getHibernateTemplate().loadAll(Promocio.class);
	}

}
