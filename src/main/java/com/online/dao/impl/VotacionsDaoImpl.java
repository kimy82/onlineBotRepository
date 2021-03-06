package com.online.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.online.dao.VotacionsDao;
import com.online.model.VotacioTMP;
import com.online.model.VotacioTMPBeguda;
import com.online.utils.Utils;

public class VotacionsDaoImpl extends HibernateDaoSupport implements VotacionsDao{
	
	
		
	
	public void saveTMP(VotacioTMP votacioTMP){
				
		getHibernateTemplate().save(votacioTMP);
		
	}
	
	public void saveTMPBeguda(VotacioTMPBeguda votacioTMP){
		
		getHibernateTemplate().save(votacioTMP);
		
	}
	
	public int count(Long idplat , int star){
		List<VotacioTMP> votList = getHibernateTemplate().find("from VotacioTMP vot where vot.platId="+idplat+" and vot.punctuacio="+star);
		return votList.size();
	}
	
	public int countBeguda(Long idbeguda , int star){
		List<VotacioTMPBeguda> votList = getHibernateTemplate().find("from VotacioTMPBeguda vot where vot.begudaId="+idbeguda+" and vot.punctuacio="+star);
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
	
	
	public VotacioTMPBeguda getBeguda(Long idBeguda, Long idUser){
		
	List<VotacioTMPBeguda> list =  (List<VotacioTMPBeguda>) getHibernateTemplate().find("from VotacioTMPBeguda vtmp where vtmp.begudaId="+idBeguda+" and vtmp.userId="+idUser+" and vtmp.dia='"+Utils.formatDate2(new Date())+"'");
	if(!list.isEmpty()){
		return list.get(0);
	}
		return null;
	}
	
	
	public VotacioTMP getLast(Long idplat, Long idUser){
		
		List<VotacioTMP> list =  (List<VotacioTMP>) getHibernateTemplate().find("from VotacioTMP vtmp where vtmp.platId="+idplat+" and vtmp.userId="+idUser+" order by  vtmp.dia desc");
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
