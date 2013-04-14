package com.online.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.online.dao.ClauDao;
import com.online.model.Clau;

public class ClauDaoImpl extends HibernateDaoSupport implements ClauDao{
	
	
	public Clau getClau(String entorn){

		List<Clau> claus = getHibernateTemplate().loadAll(Clau.class);
		
		for(Clau cl : claus){
			if(cl.getEntorn().equals(entorn))
				return cl;
		}
		return new Clau();
	}	

}
