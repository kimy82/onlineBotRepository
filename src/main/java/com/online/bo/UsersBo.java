package com.online.bo;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.online.exceptions.BOException;
import com.online.model.Users;



public interface UsersBo {
	
	void save(Users user) throws BOException,Exception;
	
	void update(Users user) throws BOException;
	
	void delete(Users user) throws BOException;
	
	void sendEmail(String txt, String email) throws BOException;
	
	void sendEmails(String txt, String[] emails) throws BOException;
	
    List<Users> getAll();
	
	Users findByUsername(String username) throws BOException;
	
	String changeUserPasswordRandomly(Users user)  throws NoSuchAlgorithmException,BOException;

}
