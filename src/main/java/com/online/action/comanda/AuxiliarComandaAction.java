package com.online.action.comanda;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.online.bo.BegudaBo;
import com.online.bo.RestaurantsBo;
import com.online.model.Beguda;
import com.online.model.Restaurant;
import com.opensymphony.xwork2.ActionSupport;

public class AuxiliarComandaAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private BegudaBo			begudaBo;
	private RestaurantsBo		restaurantsBo;
	
	List<Beguda>				begudaList			= new ArrayList<Beguda>();
	private List<Restaurant>	restaurantList;
	
	private String				nameAuth;

	HttpServletResponse			response;
	HttpServletRequest			request;

	public String execute(){		

		return SUCCESS;

	}

	public String getVins(){

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		this.nameAuth = auth.getName();

		this.begudaList = this.begudaBo.getAll("vi",true);
		this.restaurantList = this.restaurantsBo.getAll(true, false, false);

		return SUCCESS;

	}
	
	public String getRefrescos(){

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		this.nameAuth = auth.getName();

		this.begudaList = this.begudaBo.getAll("refresc",false);
		this.restaurantList = this.restaurantsBo.getAll(true, false, false);

		return SUCCESS;

	}
	
	// SETTERS i GETTERS
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
	
	public String getNameAuth(){

		return nameAuth;
	}

	public void setNameAuth( String nameAuth ){

		this.nameAuth = nameAuth;
	}

	public void setBegudaBo( BegudaBo begudaBo ){

		this.begudaBo = begudaBo;
	}

	public void setRestaurantsBo( RestaurantsBo restaurantsBo ){

		this.restaurantsBo = restaurantsBo;
	}

	public List<Beguda> getBegudaList(){

		return begudaList;
	}

	public void setBegudaList( List<Beguda> begudaList ){

		this.begudaList = begudaList;
	}

	public List<Restaurant> getRestaurantList(){
	
		return restaurantList;
	}

	public void setRestaurantList( List<Restaurant> restaurantList ){
	
		this.restaurantList = restaurantList;
	}
}