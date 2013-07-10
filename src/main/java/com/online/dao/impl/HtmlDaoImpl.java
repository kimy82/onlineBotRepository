package com.online.dao.impl;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.online.dao.HtmlDao;
import com.online.model.Html;

public class HtmlDaoImpl extends HibernateDaoSupport implements HtmlDao{
	
	public void save(Html html){
		getHibernateTemplate().save(html);
	}
	
	public void update(Html html){
		Html htmlFromDB = getHibernateTemplate().load(Html.class, html.getId());
		
		if(html.getHtml()!=null){
			htmlFromDB.setHtml(html.getHtml());
		}
		
		getHibernateTemplate().update(htmlFromDB);
	}
	
	
	public Html  load(Long id){
		try{
			Session session = this.getSessionFactory().openSession();
			session.beginTransaction();
			
			Html html =(Html) session.load(Html.class, id);		
					
			session.close();		
			
			return html;
		}catch(Exception e){
			return null;
		}
	}
}
