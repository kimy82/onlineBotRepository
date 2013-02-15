package com.online.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.online.dao.ForoDao;
import com.online.model.Foro;
import com.online.model.ForoBeguda;

public class ForoDaoImpl extends HibernateDaoSupport implements ForoDao {

	public void save(Foro foro) {

		getHibernateTemplate().save(foro);

	}
	
	public void saveBeguda(ForoBeguda foro) {

		getHibernateTemplate().save(foro);

	}

}
