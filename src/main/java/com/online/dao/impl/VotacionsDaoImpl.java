package com.online.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.online.dao.VotacionsDao;
import com.online.model.VotacioTMP;
import com.online.utils.Utils;

public class VotacionsDaoImpl extends HibernateDaoSupport implements VotacionsDao{
	
	
		
	
	public void saveTMP(VotacioTMP votacioTMP){
				
		getHibernateTemplate().save(votacioTMP);
		
	}
	
	public int count(Long idplat , int star){
		List<VotacioTMP> votList = getHibernateTemplate().find("from VotacioTMP vot where vot.platId="+idplat+" and vot.punctuation="+star);
		return votList.size();
	}
	
	public void deleteAllTMP(){
		
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		session.createSQLQuery("delete from TMP_VT");					
		session.getTransaction().commit();
		session.close();
			
	}

	public VotacioTMP get(Long idplat, Long idUser){
		
	List<VotacioTMP> list =  (List<VotacioTMP>) getHibernateTemplate().find("from VotacioTMP vtmp where vtmp.platId="+idplat+" and vtmp.userId="+idUser+" and vtmp.dia='"+Utils.formatDate2(new Date())+"'");
	if(!list.isEmpty()){
		return list.get(0);
	}
		return null;
	}
	
		@Transactional
	public List<VotacioTMP> getAll(){

		return getHibernateTemplate().loadAll(VotacioTMP.class);
	}


}
