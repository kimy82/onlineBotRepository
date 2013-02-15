package com.online.action;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.online.bo.RestaurantsBo;
import com.online.bo.UsersBo;
import com.online.model.Restaurant;
import com.online.utils.Utils;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class WelcomeAction extends ActionSupport implements ServletResponseAware, ServletRequestAware, SessionAware{

	HttpServletResponse			response;
	HttpServletRequest			request;
	private RestaurantsBo		restaurantsBo;
	private UsersBo				usersBo;
	private List<Restaurant>	restaurantList;
	private String				nameAuth;
	private String				dataAvui;
	private String				localeToChange;
	Map<String, Object>			session;
	private String				email;
	private Integer				actualPage;
	private Integer				totalPage;
	private Integer				rppPage =9;

	public String execute(){

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		this.nameAuth = auth.getName();
		
		if(session.get("WW_TRANS_I18N_LOCALE")==null || session.get("WW_TRANS_I18N_LOCALE").equals(""))
			session.put("WW_TRANS_I18N_LOCALE", new Locale("ca"));
		
		this.restaurantList = this.restaurantsBo.getAll(true, false, false);
		inizializePagin();
		if(this.restaurantList.size()>this.rppPage)
			this.restaurantList = this.restaurantList.subList(actualPage*rppPage, (actualPage+1)*rppPage);
			
		this.dataAvui = Utils.formatDate2(new Date());

		return SUCCESS;

	}

	public String changeLocale(){
		try{
			initLocale();
		}catch(Exception e){
			return ERROR;
		}
		if (this.localeToChange.equals("CA")) {
			session.put("WW_TRANS_I18N_LOCALE", new Locale("ca"));
		} else {
			session.put("WW_TRANS_I18N_LOCALE", new Locale("es"));
		}

		return SUCCESS;
	}

	public String setNewsLetter(){
		
		try{
			initEmail();		
			this.usersBo.setEmailToDB(email);
		}catch(Exception e){
			return ERROR;
		}
		return null;
	}

	// PRIVATES
	private void  inizializePagin(){
		this.actualPage= (request.getParameter("actualPage")!=null && !request.getParameter("actualPage").equals(""))? Integer.parseInt(request.getParameter("actualPage")): 0;
		this.totalPage = this.restaurantList.size()/this.rppPage;
	}
	
	private void initEmail() throws Exception{
		this.email= request.getParameter("email");
	}

	private void initLocale() throws Exception{

		this.localeToChange = request.getParameter("locale");
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

	public void setServletResponse( HttpServletResponse response ){

		this.response = response;
	}

	public void setRestaurantsBo( RestaurantsBo restaurantsBo ){

		this.restaurantsBo = restaurantsBo;
	}

	public List<Restaurant> getRestaurantList(){

		return restaurantList;
	}

	public void setRestaurantList( List<Restaurant> restaurantList ){

		this.restaurantList = restaurantList;
	}

	public String getNameAuth(){

		return nameAuth;
	}

	public void setNameAuth( String nameAuth ){

		this.nameAuth = nameAuth;
	}

	public String getDataAvui(){

		return dataAvui;
	}

	public void setDataAvui( String dataAvui ){

		this.dataAvui = dataAvui;
	}

	public String getLocaleToChange(){

		return localeToChange;
	}

	public void setLocaleToChange( String localeToChange ){

		this.localeToChange = localeToChange;
	}

	public void setSession( Map<String, Object> session ){

		this.session = session;
	}

	public void setUsersBo( UsersBo usersBo ){
	
		this.usersBo = usersBo;
	}

	public Integer getActualPage(){
	
		return actualPage;
	}

	public void setActualPage( Integer actualPage ){
	
		this.actualPage = actualPage;
	}

	public Integer getRppPage(){
	
		return rppPage;
	}

	public void setRppPage( Integer rppPage ){
	
		this.rppPage = rppPage;
	}

	public Integer getTotalPage(){
	
		return totalPage;
	}

	public void setTotalPage( Integer totalPage ){
	
		this.totalPage = totalPage;
	}		

}