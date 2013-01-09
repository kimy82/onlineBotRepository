package com.online.action.users;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.online.bo.ComandaBo;
import com.online.bo.UsersBo;
import com.online.exceptions.BOException;
import com.online.exceptions.GeneralException;
import com.online.exceptions.WrongParamException;
import com.online.model.Comandes;
import com.online.model.PlatComanda;
import com.online.model.Users;
import com.online.pojos.Basic;
import com.online.pojos.ComandesUserTable;
import com.online.utils.Utils;
import com.opensymphony.xwork2.ActionSupport;

public class WelcomeUserAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	HttpServletResponse			response;
	HttpServletRequest			request;

	
	private ComandaBo			comandaBo;
	private UsersBo				usersBo;
	private Users 				user;
	private Long				idComanda			= null;
	private Comandes			comanda				= null;
	

	private String				sEcho;
	private int					lenght				= 0;
	private int					inici				= 0;
	private String				sortDireccio		= null;
	private int					numComandes			= 0;

	private List<Basic>			horaList			= new ArrayList<Basic>();
	List<Comandes>				ComandaList			= new ArrayList<Comandes>();

	public String execute(){

		return SUCCESS;

	}

	public String comandesPasades(){
		
		this.user = getUserFromContext();
		return SUCCESS;

	}

	public String saveUserDetails(){
		
		try{
			
			if(this.user.getPassword()!=null && !this.user.getPassword().equals(""))
			this.user.setPassword(Utils.createSHA(this.user.getPassword()));
			this.usersBo.update(this.user);
			
		}catch (BOException e){
			return ERROR;
		}catch (NoSuchAlgorithmException e){
			return ERROR;
		}
		return SUCCESS;
		
	}
	public String repeatComanda(){

		try {

			inicializeIdComanda();
			this.comanda = this.comandaBo.load(this.idComanda);
			this.horaList = Utils.getHoraList();
		} catch (Exception e) {
			return ERROR;
		}
		return SUCCESS;

	}
	
	public String checkComanda(){

		try {
			
		} catch (Exception e) {
			return ERROR;
		}
		return SUCCESS;

	}
	

	public String ajaxTableComandesUser(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();
			inizializeTableParams();
			json = getInfoAndCreateJSONForComandesUser();
		} catch (BOException boe) {
			json = createErrorJSON("error in ajax action: Error in BO");
		} catch (NumberFormatException e) {
			json = createErrorJSON("error in ajax action: wrong params");
		} catch (Exception e) {
			json = createErrorJSON("error in ajax action");
		}

		try {
			out.print(json);
		} catch (IOException e) {
			throw new GeneralException(e, "possibly ServletOutputStream null");
		}
		return null;
	}

	// private methods

	private void inicializeIdComanda(){

		try {
			this.idComanda = (request.getParameter("idComanda") != null && !request.getParameter("idComanda").equals("")) ? Long
					.parseLong(request.getParameter("idComanda")) : null;
		} catch (NumberFormatException nfe) {
			throw new WrongParamException("Id comanda wrong");
		}
	}

	private String createErrorJSON( String error ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + sEcho + ",\"error\":\"" + error
				+ "\" ,\"iTotalRecords\":\"0\", \"iTotalDisplayRecords\":\"0\", \"aaData\":  []");
		jsonSB.append("}");
		return jsonSB.toString();
	}

	private String createEmptyJSON(){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + sEcho + ",\"iTotalRecords\":\"0\", \"iTotalDisplayRecords\":\"0\", \"aaData\":  []");
		jsonSB.append("}");
		return jsonSB.toString();
	}

	private void inizializeTableParams() throws WrongParamException{

		try {
			this.sEcho = request.getParameter("sEcho");
			this.lenght = (request.getParameter("iDisplayLength") == null) ? 10 : Integer.parseInt(request.getParameter("iDisplayLength"));
			this.inici = (request.getParameter("iDisplayStart") == null) ? 0 : Integer.parseInt(request.getParameter("iDisplayStart"));
			this.sortDireccio = request.getParameter("sSortDir_0");
			if (this.sortDireccio == null)
				this.sortDireccio = "ASC";
		} catch (NumberFormatException nfe) {
			throw new WrongParamException("wrong table params");
		}

	}

	public String getInfoAndCreateJSONForComandesUser(){

		List<Comandes> ComandaList = getComandesUserListAndSetNum();
		List<ComandesUserTable> comandaTableList = new ArrayList<ComandesUserTable>();
		for (Comandes comanda : ComandaList) {
			ComandesUserTable cmdUserTable = new ComandesUserTable();
			BeanUtils.copyProperties(comanda, cmdUserTable);
			cmdUserTable.setPlatsString(getNomPLats(comanda));
			cmdUserTable.setAccio("<a href=\"#\" onclick=\"repeatComanda(" + comanda.getId()
					+ ")\" ><img src=\"../images/shopping_cart.png\"></a>");
			comandaTableList.add(cmdUserTable);
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(comandaTableList);
		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + sEcho + ", \"iTotalRecords\":\"" + this.numComandes + "\", \"iTotalDisplayRecords\":\""
				+ this.numComandes + "\", \"aaData\":  ");
		jsonSB.append(json);
		jsonSB.append("}");

		return jsonSB.toString();

	}

	private String getNomPLats( Comandes comanda ){

		StringBuffer nomPlats = new StringBuffer("");
		for (PlatComanda platComanda : comanda.getPlats()) {
			nomPlats.append(platComanda.getPlat().getNom() + ",");
		}
		nomPlats.setLength(nomPlats.length() - 1);
		return nomPlats.toString();
	}

	private List<Comandes> getComandesUserListAndSetNum(){

		Users user = getUserFromContext();
		List<Comandes> comandeslist = this.comandaBo.getAllByUser(user.getId(),true);
		List<Comandes> subComandaList = new ArrayList<Comandes>();
		if (!comandeslist.isEmpty()) {
			this.numComandes = comandeslist.size();
			subComandaList = comandeslist.subList(inici, ((inici + lenght) < comandeslist.size()) ? (inici + lenght) : comandeslist.size());
		}
		return subComandaList;
	}

	private Users getUserFromContext(){

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		return this.usersBo.findByUsername(name);

	}

	// GETERS I SETTERS
	public List<Comandes> getComandaList(){

		return ComandaList;
	}

	public void setComandaList( List<Comandes> comandaList ){

		ComandaList = comandaList;
	}

	public void setComandaBo( ComandaBo comandaBo ){

		this.comandaBo = comandaBo;
	}

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

	public Comandes getComanda(){

		return comanda;
	}

	public void setComanda( Comandes comanda ){

		this.comanda = comanda;
	}

	public Users getUser(){
	
		return user;
	}

	public void setUser( Users user ){
	
		this.user = user;
	}

	public List<Basic> getHoraList(){
	
		return horaList;
	}

	public void setHoraList( List<Basic> horaList ){
	
		this.horaList = horaList;
	}

	public void setUsersBo( UsersBo usersBo ){
	
		this.usersBo = usersBo;
	}
	
	

}