package com.online.action;

import java.util.Date;
import java.util.List;

import com.online.bo.RestaurantsBo;
import com.online.bo.UsersBo;
import com.online.dao.HtmlDao;
import com.online.model.Html;
import com.online.model.Restaurant;
import com.online.supplier.extend.ActionSuportOnlineSession;
import com.online.utils.Utils;

@SuppressWarnings("serial")
public class WelcomeAction extends ActionSuportOnlineSession{

	
	private RestaurantsBo		restaurantsBo;
	private UsersBo				usersBo;
	private HtmlDao				htmlDao;
	private List<Restaurant>	restaurantList;
	
	private String				 nameUser;
	private String				dataAvui;
	private String				localeToChange;
	private String				email;
	private Integer				actualPage;
	private Integer				totalPage;
	private Integer				rppPage =9;
	private String				innerHTML;

	public String execute() {

		setAuthenticationUser();
		
		setUserName();
		
		setLocaleIfNull("ca");
		
		if(request.getSession().getAttribute("userExist")!=null){
			request.getSession().removeAttribute("userExist");
			request.setAttribute("userExist", "true");
		}
		
		this.restaurantList = this.restaurantsBo.getAll(true, false, false);
		if(this.restaurantList!=null){
			inizializePagin();
			if(this.restaurantList.size()>this.rppPage)
				this.restaurantList = this.restaurantList.subList(actualPage*rppPage, (actualPage+1)*rppPage);
		}
		this.dataAvui = Utils.formatDate2(new Date());

		return SUCCESS;

	}
	public String menuPromos(){
		Html html = this.htmlDao.load(1L);
		if(html==null)
			this.innerHTML="<h1>EN CONSTRUCCIÓ</h1>";
		this.innerHTML=html.getHtml();
		return SUCCESS;
	}
	
	public String politicaPrivacitat() {

		setAuthenticationUser();
		
		setUserName();
		
		setLocaleIfNull("ca");
		
		
		this.restaurantList = this.restaurantsBo.getAll(true, false, false);
		if(this.restaurantList!=null){
			inizializePagin();
			if(this.restaurantList.size()>this.rppPage)
				this.restaurantList = this.restaurantList.subList(actualPage*rppPage, (actualPage+1)*rppPage);
		}
		this.dataAvui = Utils.formatDate2(new Date());

		return SUCCESS;

	}
	
	public String politicaCompra() {

		setAuthenticationUser();
		
		setUserName();
		
		setLocaleIfNull("ca");
		
		
		this.restaurantList = this.restaurantsBo.getAll(true, false, false);
		if(this.restaurantList!=null){
			inizializePagin();
			if(this.restaurantList.size()>this.rppPage)
				this.restaurantList = this.restaurantList.subList(actualPage*rppPage, (actualPage+1)*rppPage);
		}
		this.dataAvui = Utils.formatDate2(new Date());

		return SUCCESS;

	}
	
	public String preguntesFrequents() {

		setAuthenticationUser();
		
		setUserName();
		
		setLocaleIfNull("ca");
		
		
		this.restaurantList = this.restaurantsBo.getAll(true, false, false);
		if(this.restaurantList!=null){
			inizializePagin();
			if(this.restaurantList.size()>this.rppPage)
				this.restaurantList = this.restaurantList.subList(actualPage*rppPage, (actualPage+1)*rppPage);
		}
		this.dataAvui = Utils.formatDate2(new Date());

		return SUCCESS;

	}
	
	public String comFerComanda() {

		setAuthenticationUser();
		
		setUserName();
		
		setLocaleIfNull("ca");
		
		
		this.restaurantList = this.restaurantsBo.getAll(true, false, false);
		if(this.restaurantList!=null){
			inizializePagin();
			if(this.restaurantList.size()>this.rppPage)
				this.restaurantList = this.restaurantList.subList(actualPage*rppPage, (actualPage+1)*rppPage);
		}
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
			setLocale("ca");
		} else {
			setLocale("es");
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
	
	private void setUserName(){
		
		try{
			
			this.nameUser = Utils.getNameUser(nameAuth, usersBo);
			
		}catch(Exception e){
			this.nameUser="";
		}
		
	}
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
	
	
	//GETTERS i SETTERS
	public void setRestaurantsBo( RestaurantsBo restaurantsBo ){

		this.restaurantsBo = restaurantsBo;
	}

	public List<Restaurant> getRestaurantList(){

		return restaurantList;
	}

	public void setRestaurantList( List<Restaurant> restaurantList ){

		this.restaurantList = restaurantList;
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

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	
	public String getInnerHTML(){
	
		return innerHTML;
	}
	public void setInnerHTML( String innerHTML ){
	
		this.innerHTML = innerHTML;
	}
	public void setHtmlDao( HtmlDao htmlDao ){
	
		this.htmlDao = htmlDao;
	}
	
}