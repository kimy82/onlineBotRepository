package com.online.action;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.online.bo.UsersBo;
import com.online.exceptions.BOException;
import com.online.model.Users;
import com.online.utils.Utils;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport implements ServletRequestAware{

	private UsersBo		usersBo;
	private String		username;
	private String		password;
	HttpServletRequest	request;

	public String execute(){

		return SUCCESS;

	}

	public String login(){

		return SUCCESS;

	}

	public String registerUser(){

		Users user = new Users();

		try {

			user.setPassword(Utils.createSHA(this.password));
			user.setUsername(this.username);
			this.usersBo.save(user);

		} catch (NoSuchAlgorithmException e) {
			addActionError("Error hashing password");
			return ERROR;
		} catch (BOException e) {
			addActionError("Sorry, it has been an error:" + e.getMessage());
			return ERROR;
		} catch (Exception e) {
			addActionError("Sorry, it has been an unknown error");
			return ERROR;
		}
		return SUCCESS;

	}

	public String recoverAccount(){

		Users userFound = this.usersBo.findByUsername(username);
		String password = "";
		if (userFound != null) {
			try {
				password = this.usersBo.changeUserPasswordRandomly(userFound);
			} catch (BOException e) {
				addActionError("Sorry, it has been an error:" + e.getMessage());
				return ERROR;
			} catch (NoSuchAlgorithmException e) {
				addActionError("Error hashing password");
				return ERROR;
			}
			this.usersBo.sendEmail("<h1>PORTAMU Recover account</h1><br>your new password is:" + password ,username);
		} else {
			request.setAttribute("userNotFound", "user not found");
			return "notfound";
		}
		return SUCCESS;
	}

	public String loginfailed(){

		return SUCCESS;

	}

	public String logout(){

		return SUCCESS;
	}

	// Gettes i setters
	public String getUsername(){

		return username;
	}

	public void setUsername( String username ){

		this.username = username;
	}

	public String getPassword(){

		return password;
	}

	public void setPassword( String password ){

		this.password = password;
	}

	public void setUsersBo( UsersBo usersBo ){

		this.usersBo = usersBo;
	}

	public void setServletRequest( HttpServletRequest request ){

		this.request = request;
	}

	public HttpServletRequest getServletRequest(){

		return this.request;
	}

}