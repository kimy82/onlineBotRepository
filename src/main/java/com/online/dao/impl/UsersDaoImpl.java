package com.online.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.online.dao.UsersDao;
import com.online.model.UserRole;
import com.online.model.Users;

public class UsersDaoImpl extends HibernateDaoSupport implements UsersDao{
	
	public void save(Users user){
		
		
		getHibernateTemplate().save(user);
		
		UserRole userRole = new UserRole(); 		
		userRole.setRole("ROLE_USER");
		userRole.setIdUser(user.getId());
		userRole.setId(user.getId());
		
		getHibernateTemplate().save(userRole);				
		
	}
	
	public void update(Users user){
		getHibernateTemplate().update(user);
	}
	
	public void delete(Users user){
		
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Users userToDelete = (Users)session.load(Users.class, user.getId());
		session.delete(userToDelete.getUserRole());
		session.delete(userToDelete);						
		session.getTransaction().commit();
		session.close();
		
	//	getHibernateTemplate().delete(user);
	}

	@SuppressWarnings("unchecked")
	public Users findByUsername( String username ){
		List<Users> userFounds = (List<Users>) getHibernateTemplate().find("from Users u where u.username = ?", username);
		if(userFounds.isEmpty())return null;
		Users userFound = userFounds.get(0);
		return userFound;
	}
	
	@Transactional
	public List<Users> getAll(){

		return getHibernateTemplate().loadAll(Users.class);
	}


}
