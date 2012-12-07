package com.online.action.comanda;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.online.bo.PlatsBo;
import com.online.bo.RestaurantsBo;
import com.online.exceptions.WrongParamException;
import com.online.model.Plat;
import com.opensymphony.xwork2.ActionSupport;

public class WelcomeComandaAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private PlatsBo				platsBo;
	private RestaurantsBo		restaurantsBo;
	List<Plat>					platList			= new ArrayList<Plat>();

	private Integer				idComanda			= null;
	private Integer				idPlat				= null;
	
	HttpServletResponse			response;
	HttpServletRequest			request;

	public String execute(){

		// Recoperem tots els plats disponibles.
		this.platList = this.platsBo.getAll();
		return SUCCESS;

	}

	public String ajaxLoadPlat(){

		// comprovar que el restaurant estigui obert
		// comprovar que no tingui plats de mes de dos restaurants
		inizilizeDadesComanda();
		if(this.idComanda!=null){
			//recuperem la comanda i afegim plat
		}else{
			//creem comanda i afegim plat
		}
		return null;
	}

	//private methods
	private void inizilizeDadesComanda() throws WrongParamException{
		this.idComanda = (request.getParameter("idComanda")==null || request.getParameter("idComanda").equals(""))? null : Integer.parseInt(request.getParameter("idComanda"));
		this.idPlat = (request.getParameter("idPlat")==null || request.getParameter("idPlat").equals(""))? null : Integer.parseInt(request.getParameter("idPlat"));
		if(this.idPlat==null){
			throw new WrongParamException("null plat to add");
		}
	}
	
	// SETTERS i GETTERS
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



}