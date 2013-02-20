package com.online.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.BeanUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.online.bo.UsersBo;
import com.online.exceptions.BOException;
import com.online.exceptions.GeneralException;
import com.online.model.NewsLetter;
import com.online.model.Users;
import com.online.pojos.NewsLetterTable;
import com.online.pojos.UsersTable;
import com.online.utils.Utils;
import com.opensymphony.xwork2.ActionSupport;


public class MantenimentLetterUsuarisAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	HttpServletResponse		response;
	HttpServletRequest		request;
	
	private String			sEcho;
	private int				lenght			= 0;
	private int				inici			= 0;
	private String			sortDireccio	= null;
	
	private Long idUser=null;
	
	private UsersBo usersBo;
	
	public String execute(){

		return SUCCESS;

	}
	
	
	public String ajaxDeleteLetterUserAction(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();

			inizializeParamTODeleteUser();			
			this.usersBo.deleteEmailToDB(this.idUser);
			
		} catch (BOException boe) {
			json = Utils.createErrorJSON("error in ajax action: Error in BO");
		} catch (NumberFormatException e) {
			json = Utils.createErrorJSON("error in ajax action: wrong params"+ e.getMessage());
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
	
	public String ajaxTableLetterUsuarisAction(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();
			inizializeTableParams();		
			json = searchInfoANDcreateJSONForLetterUsuaris();
		} catch (NumberFormatException e) {
			json = Utils.createErrorJSONForDataTable("error in ajax action: wrong params",this.sEcho);		
		} catch (Exception e) {
			json = Utils.createErrorJSONForDataTable("error in ajax action",this.sEcho);
		}

		try {
			out.print(json);
		} catch (IOException e) {
			throw new GeneralException(e,"possibly ServletOutputStream null");
		}
		return null;
	}
	//private methods
	
	private void inizializeParamTODeleteUser() throws NumberFormatException{
		
		this.idUser = (request.getParameter("id") == null) ? null : Long.parseLong(request.getParameter("id"));
		if(this.idUser==null){
			throw new  NumberFormatException("Id of User is null");
		}
	}
	private String searchInfoANDcreateJSONForLetterUsuaris(){

		List<NewsLetter> usersList = this.usersBo.getEmailsFromDB();

		List<NewsLetter> subUsersList = usersList.subList(inici, ((inici + lenght) < usersList.size()) ? (inici + lenght)
				: usersList.size());
		
		List<NewsLetterTable> newsLetterTableList = new ArrayList<NewsLetterTable>();
		for (NewsLetter letterUsu : subUsersList) {
		
			NewsLetterTable usersTable = new NewsLetterTable();
			BeanUtils.copyProperties(letterUsu, usersTable);
			usersTable.setAccio("<a href=\"#\" onclick=\"deleteUser("+letterUsu.getId()+")\" ><img src=\"../images/delete.png\"></a>");
			newsLetterTableList.add(usersTable);
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(newsLetterTableList);
		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + sEcho + ", \"iTotalRecords\":\"" + usersList.size() + "\", \"iTotalDisplayRecords\":\""
				+ usersList.size() + "\", \"aaData\":  ");
		jsonSB.append(json);
		jsonSB.append("}");
		return jsonSB.toString();

	}
	
	private void inizializeTableParams() throws NumberFormatException{
		
		this.sEcho = request.getParameter("sEcho");
		this.lenght = (request.getParameter("iDisplayLength") == null) ? 10 : Integer.parseInt(request.getParameter("iDisplayLength"));
		this.inici = (request.getParameter("iDisplayStart") == null) ? 0 : Integer.parseInt(request.getParameter("iDisplayStart"));
		this.sortDireccio = request.getParameter("sSortDir_0");
		if (this.sortDireccio == null)
			this.sortDireccio = "ASC";
	}
	
	
	//Getters i setters
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