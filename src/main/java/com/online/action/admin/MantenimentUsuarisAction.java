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
import com.online.model.Restaurant;
import com.online.model.Users;
import com.online.pojos.RestaurantTable;
import com.online.pojos.UsersTable;
import com.online.utils.Utils;
import com.opensymphony.xwork2.ActionSupport;


public class MantenimentUsuarisAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{

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
	
	
	public String ajaxDeleteUserAction(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();

			inizializeParamTODeleteUser();
			Users user = new Users();
			user.setId(this.idUser);
			this.usersBo.delete(user);
			

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
	
	public String ajaxTableUsuarisAction(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();
			inizializeTableParams();		
			json = searchInfoANDcreateJSONForUsuaris();
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
	private String searchInfoANDcreateJSONForUsuaris(){

		List<Users> usersList = this.usersBo.getAll();

		List<Users> subUsersList = usersList.subList(inici, ((inici + lenght) < usersList.size()) ? (inici + lenght)
				: usersList.size());
		
		List<UsersTable> subusersTableList = new ArrayList<UsersTable>();
		for (Users users : subUsersList) {
		
			UsersTable usersTable = new UsersTable();
			BeanUtils.copyProperties(users, usersTable);
			usersTable.setAccio("<a href=\"#\" onclick=\"deleteUser("+users.getId()+")\" ><img src=\"../images/delete.png\"></a>");
			subusersTableList.add(usersTable);
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(subusersTableList);
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