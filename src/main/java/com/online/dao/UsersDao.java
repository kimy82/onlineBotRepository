package com.online.dao;

import java.util.List;

import com.online.exceptions.UserExistException;
import com.online.model.NewsLetter;
import com.online.model.Users;


public interface UsersDao {
	
	void save(Users user) throws UserExistException;
	
	void update(Users user);
	
	void delete(Users user);
	
	Users findByUsername(String username);
	
	List<Users> getAll();
	
	boolean checkUserPlat(Long idUser, Long idPlat);
	
	void setEmailToDB( String email );
	
	List<NewsLetter> getEmailsFromDB();
	
	boolean checkUserBeguda( Long idUser, Long idBeguda );
	
	void deleteEmailToDB( Long id );
	
	Users load( Long id );
	
}
