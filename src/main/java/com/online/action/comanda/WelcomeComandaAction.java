package com.online.action.comanda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.online.bo.ComandaBo;
import com.online.bo.PlatsBo;
import com.online.bo.RestaurantsBo;
import com.online.exceptions.ComandaException;
import com.online.exceptions.GeneralException;
import com.online.exceptions.WrongParamException;
import com.online.model.Comandes;
import com.online.model.Plat;
import com.online.services.impl.ComandaServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class WelcomeComandaAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private PlatsBo				platsBo;
	private RestaurantsBo		restaurantsBo;
	private ComandaBo	    	comandaBo;
	List<Plat>					platList			= new ArrayList<Plat>();

	private Long				idComanda			= null;
	private Long				idPlat				= null;
	
	private ComandaServiceImpl  comandaService;
	
	HttpServletResponse			response;
	HttpServletRequest			request;

	public String execute(){

		// Recoperem tots els plats disponibles.
		this.platList = this.platsBo.getAll();
		return SUCCESS;

	}

	public String ajaxLoadPlat(){

		ServletOutputStream out = null;

		String json = "";
		
		// comprovar que el restaurant estigui obert
		// comprovar que no tingui plats de mes de dos restaurants
		
		try {
			
			out = this.response.getOutputStream();
			inizilizeDadesComanda();
			if(this.idComanda!=null){
				//recuperem la comanda i afegim plat
				Comandes comanda = this.comandaBo.load(this.idComanda);
				Set<Plat> platList =comanda.getPlats();
				Plat platToAdd = this.platsBo.load(this.idPlat,false);
				if(!comandaService.checkPlatForMoreThanTwoRestaurants(platList, platToAdd)){
					platList.add(platToAdd);
					comanda.setPlats(platList);
					this.comandaBo.update(comanda);
				}
				
				json= this.comandaService.createJSONForShoppingCart(platList, comanda.getId());
				
			}else{
				//creem comanda i afegim plat
				Comandes comanda = new Comandes();
				comanda.setPreu(0.0);
				Set<Plat> platList = new HashSet<Plat>();
				Plat platToAdd = this.platsBo.load(this.idPlat, false);
				platList.add(platToAdd);
				this.comandaBo.save(comanda);
			
				
				json= this.comandaService.createJSONForShoppingCart(platList,comanda.getId());
			}
		} catch (ComandaException ce){
			json = createErrorJSON("error in comanda service action");
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

	
	
	//private methods
	private String createErrorJSON( String error ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"error\":\"" + error);
		jsonSB.append("}");
		return jsonSB.toString();
	}

	
	
	private void inizilizeDadesComanda() throws WrongParamException{
		this.idComanda = (request.getParameter("idComanda")==null || request.getParameter("idComanda").equals(""))? null : Long.parseLong(request.getParameter("idComanda"));
		this.idPlat = (request.getParameter("idPlat")==null || request.getParameter("idPlat").equals(""))? null : Long.parseLong(request.getParameter("idPlat"));
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

	public void setComandaBo(ComandaBo comandaBo) {
		this.comandaBo = comandaBo;
	}

	public void setComandaService(ComandaServiceImpl comandaService) {
		this.comandaService = comandaService;
	}

	
	
}