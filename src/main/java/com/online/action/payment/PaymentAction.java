package com.online.action.payment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.online.bo.ComandaBo;
import com.online.bo.RestaurantsBo;
import com.online.exceptions.WrongParamException;
import com.online.model.Comandes;
import com.online.model.PlatComanda;
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

	public String execute() throws IOException{

		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		this.nameAuth = auth.getName();
	
		inizilizeComandaId();
		
		List<String> orders = getComandaOrders();
		
		for(String order : orders){
			RestClient client = new RestClient();
			Resource resource = client.resource("http://localhost/ComandaRest/jaxrs/comandes/file?" +
					"txt=&resid=AC001&comanda=Chiken;3.00;2;Beef;6.00;3;rice;2.50;");
			String response = resource.accept("text/plain").get(String.class);
		}
		
		
		return SUCCESS;

	}

	//PRIVATES
	
	private List<String> getComandaOrders(){
	
		List<String> orders = new ArrayList<String>();
		
		Map<String,String> comandes = new HashMap<String,String>();
		Set<String> restaurants = new HashSet<String>();
		
		this.comanda = this.comandaBo.load(this.idComanda);

		List<PlatComanda> listOfPlats = this.comanda.getPlats();
			
			//resId=codiMaquina ; orderNum = numComanda ; deliveryCharge = si va o no la moto ; total= preu total ; 
			//nom = nom de l'user ; address = adreça de l'usu ; diahora = diahora de la comanda ; telnumber= tel de l'usu ;
			//comanda = comanda de plats
			for(PlatComanda platComanda : listOfPlats){
				
				String codi = platComanda.getPlat().getRestaurants().iterator().next().getCodiMaquina();
				restaurants.add(codi);
				
				int nPlats = platComanda.getNumPlats();
				String nomPlat = platComanda.getPlat().getNom();
				Double preuPlat = platComanda.getPlat().getPreu()*nPlats;
				String comanda = nPlats+";"+nomPlat+";"+preuPlat;
				if(comandes.containsKey(codi)){
					String numIdsPlats = comandes.get(codi);
					numIdsPlats = numIdsPlats+";"+platComanda.getPlat().getId();
				}else{
					comandes.put(codi,platComanda.getPlat().getId().toString());
				}
				comandes.put(codi+"_"+platComanda.getPlat().getId(), comanda);
			}
			return orders;
	}
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