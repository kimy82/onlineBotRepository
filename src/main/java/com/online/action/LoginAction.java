package com.online.action;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.online.bo.UsersBo;
import com.online.exceptions.BOException;
import com.online.exceptions.UserExistException;
import com.online.model.Users;
import com.online.supplier.extend.ActionSuportOnlineSession;
import com.online.utils.Utils;

@SuppressWarnings("serial")
public class LoginAction extends ActionSuportOnlineSession{

	private UsersBo		usersBo;
	private String		username;
	private String		password;
	private String 		address; 
	private String		telefon;
	private String		altres;
	private String		email;
	
	 protected AuthenticationManager authenticationManager;
	
	HttpServletRequest	request;

	public String execute(){

		return SUCCESS;

	}

	public String login(){

		if(request.getParameter("dialog")!=null)
			request.setAttribute("dialog", "true");
		
		return SUCCESS;

	}

	public String preRegisterUser(){

		if(request.getParameter("dialog")!=null)
			request.setAttribute("dialog", "true");
		
		return SUCCESS;

	}
	
	public String registerUser(){

		Users user = new Users();

		try {

			user.setPassword(Utils.createSHA(this.password));
			user.setUsername(this.email);
			user.setNom(this.username);
			user.setAddress(this.address);
			user.setTelNumber(this.telefon);
			user.setIndicacions(this.altres);
			
			this.usersBo.save(user);
			
			authenticateUserAndSetSession(user.getUsername(), this.password,this.request);

		} catch (NoSuchAlgorithmException e) {
			addActionError("Error hashing password");
			return ERROR;
		} catch (BOException e) {
			addActionError("Sorry, it has been an error:" + e.getMessage());
			return ERROR;
		}  catch (UserExistException e) {
			request.getSession().setAttribute("userExist", "true");
			return "user_exist";
		}catch (Exception e) {
			addActionError("Sorry, it has been an unknown error");
			return ERROR;
		}
		return SUCCESS;

	}
	
	 private void authenticateUserAndSetSession(String userName,String password,
		        HttpServletRequest request)
		{
		    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
		    		userName, password);

		    // generate session if one doesn't exist
		    request.getSession();

		    token.setDetails(new WebAuthenticationDetails(request));
		    Authentication authenticatedUser = authenticationManager.authenticate(token);

		    SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
		}

	public String recoverAccount() throws Exception{

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
			String app =this.request.getSession().getServletContext().getInitParameter("app");
			try{
			this.usersBo.sendEmail("<h1>PORTAMU Recover account</h1><br>your new password is:" + password ,username,app);
			}catch(Exception e){
				e.printStackTrace();
				throw e;
			}
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

	public void setAddress( String address ){
	
		this.address = address;
	}

	public void setTelefon( String telefon ){
	
		this.telefon = telefon;
	}

	public void setAltres( String altres ){
	
		this.altres = altres;
	}

	public void setEmail( String email ){
	
		this.email = email;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}		
	
	

}