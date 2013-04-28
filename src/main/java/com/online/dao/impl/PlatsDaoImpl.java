package com.online.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.online.dao.PlatsDao;
import com.online.model.Comandes;
import com.online.model.Plat;
import com.online.model.PlatComanda;

public class PlatsDaoImpl extends HibernateDaoSupport implements PlatsDao{
	
	public void save(Plat plat){
		getHibernateTemplate().save(plat);
	}
	
	public void update(Plat plat){
		getHibernateTemplate().update(plat);
	}
	
	public void changePriority(Long idPlat, Integer prioritat){
		
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		
		SQLQuery sql =session.createSQLQuery("update plats set prioritat="+prioritat+"  where plat_id="+idPlat);
		sql.executeUpdate();	
		
		session.getTransaction().commit();	
		
		session.close();
	}
	
	public void delete(Plat plat){
		
		updateComandes(plat);
		Long platId = plat.getId();
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		
		
		
		Plat platToDelete = (Plat) session.load(Plat.class, platId);		
		session.delete(platToDelete);
		SQLQuery sql =session.createSQLQuery("delete from restaurants_plats where plat_id="+platId);
		sql.executeUpdate();	
		
		session.getTransaction().commit();				
		session.close();
						
	}
	 
	private void updateComandes(Plat plat){
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Long platId = plat.getId();
		List<PlatComanda> platComandaList = session.createCriteria(PlatComanda.class).add(Restrictions.eq("plat.id", platId)).list();
		for(PlatComanda pltc : platComandaList){
			
			List<Integer> comandesToAlter = (List<Integer>) session.createSQLQuery("select c.COMANDA_ID from comandes c inner join  comanda_plats pltc ON (pltc.PLATCOMANDA_ID="+pltc.getId()+" and pltc.COMANDA_ID=c.COMANDA_ID )").list();
			for(Object obCmd : comandesToAlter){
				Integer id =(Integer)obCmd;
				Comandes cmd = (Comandes) session.load(Comandes.class, Long.parseLong(String.valueOf(id)));
				if(cmd.getPlatsBorrats()==null)
					cmd.setPlatsBorrats(pltc.getPlat().getNom()+"(BORRAT) ");
				else
					cmd.setPlatsBorrats(cmd.getPlatsBorrats()+" "+pltc.getPlat().getNom()+"(BORRAT) ");
				
				List<PlatComanda> newPlatComandaList = new ArrayList<PlatComanda>();
				for(PlatComanda platcomanda : cmd.getPlats()){
					if(platcomanda==null)continue;
					if(platcomanda.getId()!=pltc.getId()){
						newPlatComandaList.add(platcomanda);
					}
				}
				cmd.setPlats(newPlatComandaList);
				session.update(cmd);
			}									
		}	
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
		List<Plat> platList = (List<Plat>) session.createQuery("from Plat pl where pl.id=" + id).setCacheable(true).list();
		if (platList.isEmpty())
			return null;
		
		Plat plat = platList.get(0);
		
		Hibernate.initialize(plat.getComments());
		
		Hibernate.initialize(plat.getVotacio());
		
		session.close();

		return plat;
	}
	

}
