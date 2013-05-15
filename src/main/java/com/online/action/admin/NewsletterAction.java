package com.online.action.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import com.online.bo.UsersBo;
import com.online.exceptions.GeneralException;
import com.online.model.NewsLetter;
import com.online.model.Users;
import com.online.supplier.extend.ActionSuportOnline;
import com.online.utils.Utils;

public class NewsletterAction extends ActionSuportOnline{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private UsersBo				usersBo;

	private String				txtToSend			= "";
	private String				target				= "news";

	public String execute(){

		return SUCCESS;

	}

	public String ajaxSendLetterAction(){

		ServletOutputStream out = null;

		this.txtToSend = Utils.decodeUTFONlyWords(request.getParameter("txt"));
		
		this.target = request.getParameter("target");

		String json = "{}";
		try {

			out = this.response.getOutputStream();
			String[] emails = getEmailsFromUsers();
			String app =this.request.getSession().getServletContext().getInitParameter("app");
			this.usersBo.sendEmails(this.txtToSend, emails,app);

		} catch (Exception e) {
			json = Utils.createErrorJSON("error in ajax action");
		}

		try {
			out.print(json);
		} catch (IOException e) {
			throw new GeneralException(e, "possibly ServletOutputStream null");
		}
		return null;
	}

	// Private
	private String[] getEmailsFromUsers(){

		StringBuffer emails = new StringBuffer();
		List<Users> users = this.usersBo.getAll();

		if (this.target != null && this.target.equals("users")) {
			for (Users user : users) {
				emails.append(user.getUsername() + ",");
			}
		}

		if (this.target != null && this.target.equals("news")) {
			List<NewsLetter> newsEmails = this.usersBo.getEmailsFromDB();
			for (NewsLetter email : newsEmails) {
				emails.append(email.getEmail() + ",");
			}
		}
		
		if(this.target.equals("tots")){
			for (Users user : users) {
				emails.append(user.getUsername() + ",");
			}
			List<NewsLetter> newsEmails = this.usersBo.getEmailsFromDB();
			for (NewsLetter email : newsEmails) {
				emails.append(email.getEmail() + ",");
			}
		}
		emails.setLength(emails.length() - 1);
		return emails.toString().split(",");
	}

	// Getters i setters
	public void setUsersBo( UsersBo usersBo ){

		this.usersBo = usersBo;
	}

}