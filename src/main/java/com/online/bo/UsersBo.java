package com.online.bo;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.online.exceptions.BOException;
import com.online.exceptions.UserExistException;
import com.online.model.NewsLetter;
import com.online.model.Users;



public interface UsersBo {
	
	void save(Users user) throws BOException,UserExistException, Exception;
	
	void update(Users user) throws BOException;
	
	void delete(Users user) throws BOException;
	
	void sendEmail(String txt, String email) throws BOException; 
	
	void sendEmails(String txt, String[] emails) throws BOException;
	
    List<Users> getAll();
	
	Users findByUsername(String username) throws BOException;
	
	String changeUserPasswordRandomly(Users user)  throws NoSuchAlgorithmException,BOException;
	
	boolean checkUserPlat(Long idUser, Long idPlat) throws BOException;
	
	void setEmailToDB( String email ) throws BOException;
	
	List<NewsLetter> getEmailsFromDB( ) throws BOException;
	
	boolean checkUserBeguda( Long idUser, Long idBeguda ) throws BOException;
	
	void deleteEmailToDB( Long id ) throws BOException;
	
	Users load( Long id )throws BOException;

}
