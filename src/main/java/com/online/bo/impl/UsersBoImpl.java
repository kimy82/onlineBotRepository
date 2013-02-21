package com.online.bo.impl;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.mail.internet.AddressException;

import org.apache.commons.lang.RandomStringUtils;

import com.online.bo.UsersBo;
import com.online.dao.UsersDao;
import com.online.exceptions.BOException;
import com.online.exceptions.EmailException;
import com.online.model.NewsLetter;
import com.online.model.Users;
import com.online.services.SendingEmailService;
import com.online.utils.Utils;

public class UsersBoImpl implements UsersBo{

	private UsersDao			usersDao;
	private SendingEmailService	sendingEmailService;

	public boolean checkUserPlat( Long idUser, Long idPlat ) throws BOException{

		if (idPlat == null) {
			throw new BOException("Null plat to check");
		}

		if (idUser == null)
			return false;

		return this.usersDao.checkUserPlat(idUser, idPlat);
	}

	public boolean checkUserBeguda( Long idUser, Long idBeguda ) throws BOException{

		if (idBeguda == null) {
			throw new BOException("Null beguda to check");
		}

		if (idUser == null)
			return false;

		return this.usersDao.checkUserBeguda(idUser, idBeguda);
	}

	public void save( Users user ) throws BOException, Exception{

		checkUser(user);
		usersDao.save(user);
	}

	public void update( Users user ) throws BOException{

		checkUserWithId(user);
		usersDao.update(user);
	}

	public Users load( Long id )throws BOException{

		if (id == null || id.equals("")) {
			throw new BOException("error id user buit");
		}

		return this.usersDao.load(id);
	}

	public void setEmailToDB( String email ) throws BOException{

		if (email == null || email.equals("")) {
			throw new BOException("error email buit");
		}
		usersDao.setEmailToDB(email);
	}

	public List<NewsLetter> getEmailsFromDB() throws BOException{

		return usersDao.getEmailsFromDB();
	}

	public void delete( Users user ) throws BOException{

		checkUserToDeleteWithId(user);
		usersDao.delete(user);
	}

	public void deleteEmailToDB( Long id ) throws BOException{

		if (id == null || id.equals("")) {
			throw new BOException("error id null");
		}
		this.usersDao.deleteEmailToDB(id);
	}

	public void sendEmail( String txt, String email ) throws BOException{

		try {
			this.sendingEmailService.sendEmail(txt, email);
		} catch (EmailException e) {
			throw new BOException(e, "error en el Servei de mail");
		}
	}

	public void sendEmails( String txt, String[] emails ) throws BOException{

		try {

			this.sendingEmailService.sendEmailsTo(txt, emails);

		} catch (AddressException e) {
			throw new BOException(e, "error en el Servei de mail");
		} catch (EmailException e) {
			throw new BOException(e, "error en el Servei de mail");
		}
	}

	public Users findByUsername( String username ) throws BOException{

		if (username == null || username.equals(""))
			throw new BOException("username is empty or Null");
		Users userFound = usersDao.findByUsername(username);

		return userFound;

	}

	public String changeUserPasswordRandomly( Users user ) throws NoSuchAlgorithmException, BOException{

		String password = generateRandomPassword();
		String encryptpassword = Utils.createSHA(password);
		user.setPassword(encryptpassword);
		usersDao.update(user);
		return password;
	}

	public List<Users> getAll(){

		return usersDao.getAll();
	}

	// PRIVATE METHODS
	private String generateRandomPassword(){

		return RandomStringUtils.randomAlphanumeric(8);
	}

	private void checkUserToDeleteWithId( Users user ) throws BOException{

		if (user == null || user.getId() == null) {
			throw new BOException("Null user to save");
		}
	}

	private void checkUser( Users user ) throws BOException{

		if (user == null || user.getUsername() == null || user.getUsername().equals("") || user.getPassword() == null
				|| user.getPassword().equals("")) {
			throw new BOException("Null user to save");
		}
	}

	private void checkUserWithId( Users user ) throws BOException{

		if (user == null || user.getId() == null || user.getUsername() == null || user.getUsername().equals("")
				|| user.getPassword() == null || user.getPassword().equals("")) {
			throw new BOException("Null user to save");
		}
	}

	// SETTERS
	public void setSendingEmailService( SendingEmailService sendingEmailService ){

		this.sendingEmailService = sendingEmailService;
	}

	public void setUsersDao( UsersDao usersDao ){

		this.usersDao = usersDao;
	}
}
