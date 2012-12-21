package com.online.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.online.bo.RestaurantsBo;
import com.online.model.Restaurant;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class WelcomeAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{

	HttpServletResponse		response;
	HttpServletRequest		request;
	private RestaurantsBo	restaurantsBo;
	private List<Restaurant> restaurantList;

	public String execute(){

		this.restaurantList = this.restaurantsBo.getAll();
		return SUCCESS;

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
	

}