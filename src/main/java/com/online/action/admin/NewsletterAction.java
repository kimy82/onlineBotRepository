package com.online.action.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.online.bo.UsersBo;
import com.online.exceptions.GeneralException;
import com.online.model.Users;
import com.opensymphony.xwork2.ActionSupport;


public class NewsletterAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{


	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	HttpServletResponse			response;
	HttpServletRequest			request;
	
	private UsersBo usersBo;
	
	private String txtToSend = "";

	public String execute(){
		return SUCCESS;

	}
	
	public String ajaxSendLetterAction(){

		ServletOutputStream out = null;
		this.txtToSend = request.getParameter("txt");
		
		String json = "{}";
		try {
			
			out = this.response.getOutputStream();
			String[] emails = getEmailsFromUsers();
			this.usersBo.sendEmails(this.txtToSend, emails);
			
		} catch (Exception e) {
			json = createErrorJSON("error in ajax action");
		}

		try {
			out.print(json);
		} catch (IOException e) {
			throw new GeneralException(e,"possibly ServletOutputStream null");
		}
		return null;
	}

	//Private
	private String[] getEmailsFromUsers(){
		StringBuffer emails = new StringBuffer(); 		
		List<Users> users = this.usersBo.getAll();
		for (Users user : users){
			emails.append(user.getUsername()+",");
		}
		emails.setLength(emails.length()-1);
		return emails.toString().split(",");
	}
	private String createErrorJSON( String error ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"error\":\"" + error
				+ "\" ");
		jsonSB.append("}");
		return jsonSB.toString();
	}
	// Getters i setters

	public void setServletResponse( HttpServletResponse response ){

		this.response = response;
	}

	public HttpServletResponse getServletResponse(){

		return this.response;
	}

	public void setServletRequest( HttpServletRequest request ){

		this.request = request;
	}

	public HttpServletRequest getServletRequest(){

		return this.request;
	}

	public void setUsersBo( UsersBo usersBo ){
	
		this.usersBo = usersBo;
	}
	
	
}