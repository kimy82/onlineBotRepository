package com.online.action.comanda;

import java.util.ArrayList;
import java.util.List;

import com.online.bo.PlatsBo;
import com.online.bo.RestaurantsBo;
import com.online.model.Plat;
import com.opensymphony.xwork2.ActionSupport;


public class WelcomeComandaAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private PlatsBo			platsBo;
	private RestaurantsBo	restaurantsBo;
	List<Plat> platList = new ArrayList<Plat>();

	public String execute(){
		//Recoperem tots els plats disponibles. 		
		this.platList = this.platsBo.getAll();
		return SUCCESS;

	}
	
	
	//SETTERS i GETTERS
	public void setPlatsBo( PlatsBo platsBo ){
	
		this.platsBo = platsBo;
	}

	public void setRestaurantsBo( RestaurantsBo restaurantsBo ){
	
		this.restaurantsBo = restaurantsBo;
	}


	public List<Plat> getPlatList(){
	
		return platList;
	}


	public void setPlatList( List<Plat> platList ){
	
		this.platList = platList;
	}
	

	
	
}