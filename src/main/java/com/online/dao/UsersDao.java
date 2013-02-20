package com.online.dao;

import java.util.List;

import com.online.model.NewsLetter;
import com.online.model.Users;


public interface UsersDao {
	
	void save(Users user);
	
	void update(Users user);
	
	void delete(Users user);
	
	Users findByUsername(String username);
	
	List<Users> getAll();
	
	boolean checkUserPlat(Long idUser, Long idPlat);
	
	void setEmailToDB( String email );
	
	List<NewsLetter> getEmailsFromDB();
	
	boolean checkUserBeguda( Long idUser, Long idBeguda );
	
	public void deleteEmailToDB( Long id );
	
}
