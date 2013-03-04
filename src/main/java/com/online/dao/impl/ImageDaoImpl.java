package com.online.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.online.dao.ImageDao;
import com.online.model.Image;

public class ImageDaoImpl extends HibernateDaoSupport implements ImageDao{

	public Image load( Integer id ) {

		Session session = this.getSessionFactory().openSession();

		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Image> images = (List<Image>) session.createQuery("from Image img where img.id=" + id).setCacheable(true).list();
		if (images.isEmpty())
			return null;
		Image image = images.get(0);
		Hibernate.initialize(image.getImage());
		session.close();

		return image;
	}

}
