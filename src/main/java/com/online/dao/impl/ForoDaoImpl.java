package com.online.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.online.dao.ForoDao;
import com.online.model.Foro;

public class ForoDaoImpl extends HibernateDaoSupport implements ForoDao {

	public void save(Foro foro) {

		getHibernateTemplate().save(foro);

	}

}
