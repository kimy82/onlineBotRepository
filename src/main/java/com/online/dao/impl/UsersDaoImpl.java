package com.online.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.online.dao.UsersDao;
import com.online.exceptions.BOException;
import com.online.model.Comandes;
import com.online.model.NewsLetter;
import com.online.model.UserRole;
import com.online.model.Users;

public class UsersDaoImpl extends HibernateDaoSupport implements UsersDao{

	public void save( Users user ){

		getHibernateTemplate().save(user);

		UserRole userRole = new UserRole();
		userRole.setRole("ROLE_USER");
		userRole.setIdUser(user.getId());
		userRole.setId(user.getId());

		getHibernateTemplate().save(userRole);

	}

	public void setEmailToDB( String email ){

		NewsLetter newsletterEmail = new NewsLetter();
		newsletterEmail.setEmail(email);
		List<NewsLetter> emailsFounds = (List<NewsLetter>) getHibernateTemplate().find("from NewsLetter nl where nl.email= ?", email);
		if(emailsFounds.isEmpty())
			getHibernateTemplate().saveOrUpdate(newsletterEmail);
	}

	public void deleteEmailToDB( Long id ){

		NewsLetter newsLetter =  getHibernateTemplate().load(NewsLetter.class, id);		
		getHibernateTemplate().delete(newsLetter);
		
	}
	
	public List<NewsLetter> getEmailsFromDB(){
		return getHibernateTemplate().loadAll(NewsLetter.class);
	}
	
	public void update( Users user ){

		getHibernateTemplate().update(user);
	}

	public void delete( Users user ){

		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Users userToDelete = (Users) session.load(Users.class, user.getId());
		session.delete(userToDelete.getUserRole());
		session.delete(userToDelete);
		session.getTransaction().commit();
		session.close();

		// getHibernateTemplate().delete(user);
	}

	@SuppressWarnings("unchecked")
	public Users findByUsername( String username ){

		List<Users> userFounds = (List<Users>) getHibernateTemplate().find("from Users u where u.username = ?", username);
		if (userFounds.isEmpty())
			return null;
		Users userFound = userFounds.get(0);
		return userFound;
	}

	public boolean checkUserPlat( Long idUser, Long idPlat ){

		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Comandes.class);
		criteria.add(Restrictions.eq("pagada", true));
		criteria.createAlias("user", "user").add(Restrictions.eq("user.id", idUser));
		criteria.createAlias("plats", "plats").add(Restrictions.eq("plats.plat.id", idPlat));
		List<Comandes> comandalist = criteria.list();

		if (!comandalist.isEmpty()) {
			return true;
		}

		return false;

	}
	
	public boolean checkUserBeguda( Long idUser, Long idBeguda ){

		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Comandes.class);
		criteria.createAlias("user", "user").add(Restrictions.eq("user.id", idUser));
		criteria.createAlias("begudes", "begudes").add(Restrictions.eq("begudes.beguda.id", idBeguda));
		List<Comandes> comandalist = criteria.list();

		if (!comandalist.isEmpty()) {
			return true;
		}

		return false;

	}
	
	public Users load( Long id ){
		List<Users> userFounds = (List<Users>) getHibernateTemplate().find("from Users u where u.id = ?",id);
		if (userFounds.isEmpty())
			return null;
		Users userFound = userFounds.get(0);
		return userFound;
		// getHibernateTemplate().load(Users.class,id);
	}
	
	@Transactional
	public List<Users> getAll(){

		return getHibernateTemplate().loadAll(Users.class);
	}

}
