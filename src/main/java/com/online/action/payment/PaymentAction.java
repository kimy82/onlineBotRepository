package com.online.action.payment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.online.bo.ComandaBo;
import com.online.exceptions.PaymentException;
import com.online.exceptions.WrongParamException;
import com.online.model.Comandes;
import com.online.services.impl.PaymentServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class PaymentAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{

	HttpServletResponse			response;
	HttpServletRequest			request;
	
	private ComandaBo			comandaBo;
	
	private PaymentServiceImpl	paymentService;
	
	private Long				idComanda			= null;
	private Comandes			comanda;

	private String				nameAuth;

	public String execute() throws IOException{

		List<String> orders = new ArrayList<String>();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		this.nameAuth = auth.getName();
	
		inizilizeComandaId();
		
		this.comanda = this.comandaBo.load(idComanda);
		try{
			orders =this.paymentService.getComandaOrders(this.comanda);
		}catch(PaymentException pe){
			return ERROR;
		}
		
		for(String order : orders){
			RestClient client = new RestClient();
			Resource resource = client.resource("http://localhost/ComandaRest/jaxrs/comandes/file");
			String[] orderVec = order.split("&");
			for(String param : orderVec){
				String[] params = param.split("=");
				resource.queryParam(params[0],params[1]);
			}					
			String response = resource.accept("text/plain").get(String.class);
		}
		
		
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

	public String getNameAuth(){

		return nameAuth;
	}

	public void setNameAuth( String nameAuth ){

		this.nameAuth = nameAuth;
	}

	public void setComandaBo( ComandaBo comandaBo ){
	
		this.comandaBo = comandaBo;
	}

	public void setPaymentService(PaymentServiceImpl paymentService) {
		this.paymentService = paymentService;
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