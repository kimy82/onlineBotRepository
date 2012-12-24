package com.online.action.payment;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.online.bo.ComandaBo;
import com.online.bo.RestaurantsBo;
import com.online.exceptions.WrongParamException;
import com.online.model.Comandes;
import com.online.model.Restaurant;
import com.online.services.impl.ComandaServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class PaymentAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{

	HttpServletResponse			response;
	HttpServletRequest			request;
	
	private ComandaBo			comandaBo;
	private RestaurantsBo		restaurantsBo;
	private ComandaServiceImpl	comandaService;
	
	private Long				idComanda			= null;
	private Comandes			comanda;

	private String				nameAuth;

	public String execute(){

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		this.nameAuth = auth.getName();
		
		inizilizeComandaId();
		
		this.comanda = this.comandaBo.load(this.idComanda);
		return SUCCESS;

	}

	//PRIVATES
	private void inizilizeComandaId() throws WrongParamException{

		try {
			this.idComanda = (request.getParameter("idComanda") == null || request.getParameter("idComanda").equals("")) ? null : Long
					.parseLong(request.getParameter("idComanda"));
		} catch (NumberFormatException e) {
			throw new WrongParamException("wrong id of comanda");
		}

	}
	
	//GETTERS i SETTERS
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

	public String getNameAuth(){

		return nameAuth;
	}

	public void setNameAuth( String nameAuth ){

		this.nameAuth = nameAuth;
	}

	public void setComandaBo( ComandaBo comandaBo ){
	
		this.comandaBo = comandaBo;
	}

	public void setComandaService( ComandaServiceImpl comandaService ){
	
		this.comandaService = comandaService;
	}
	
	public Long getIdComanda(){
		
		return idComanda;
	}

	public void setIdComanda( Long idComanda ){
	
		this.idComanda = idComanda;
	}

	public Comandes getComanda(){
	
		return comanda;
	}

	public void setComanda( Comandes comanda ){
	
		this.comanda = comanda;
	}
	
	
	

}