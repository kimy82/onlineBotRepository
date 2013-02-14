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
import com.online.bo.UsersBo;
import com.online.exceptions.PaymentException;
import com.online.exceptions.WrongParamException;
import com.online.model.Comandes;
import com.online.model.PlatComanda;
import com.online.model.Users;
import com.online.services.impl.PaymentServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class PaymentAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{

	HttpServletResponse			response;
	HttpServletRequest			request;
	
	private ComandaBo			comandaBo;
	
	private UsersBo				usersBo;
	
	private PaymentServiceImpl	paymentService;
	
	private Long				idComanda			= null;
	private Comandes			comanda;

	private String				nameAuth;

	public String execute() throws IOException{

		List<String> orders = new ArrayList<String>();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		this.nameAuth = auth.getName();
		
		int numComandes = initNumComandes();
		
		inizilizeComandaId();
		
		this.comanda = this.comandaBo.load(idComanda);
		
		try{
			setInComandaPromoImport();
			
			if(this.comanda.getTargeta()==true){
				return "TPV";
			}
						
			orders =this.paymentService.getComandaOrders(this.comanda);
			
		}catch(PaymentException pe){
			return ERROR;
		}catch (Exception e){
			return ERROR;
		}
		
		if(numComandes>1){
			this.paymentService.sendOrder(false, orders);
		}else{
			this.paymentService.sendOrder(true, orders);
		}
		
		
		
		return SUCCESS;

	}
	public String paymentTpvDone() throws IOException{
		
		List<String> orders = new ArrayList<String>();
		inizilizeComandaId();
		
		this.comanda = this.comandaBo.load(idComanda);
		this.comanda.setPagada(true);
		this.comandaBo.update(comanda);
		try{						
			orders =this.paymentService.getComandaOrders(this.comanda);
			
		}catch(PaymentException pe){
			return ERROR;
		}catch (Exception e){
			return ERROR;
		}
		
	
		this.paymentService.sendOrder(false, orders);		
		return SUCCESS;
	}
	
	//PRIVATES
	
	
	private int initNumComandes(){
		
		Users user = this.usersBo.findByUsername(nameAuth);		
		List<Comandes> comandes = this.comandaBo.getAllByUser(user.getId(), false);
		if(comandes!=null && !comandes.isEmpty()){
			return comandes.size();
		}
		return 0;
	}
	
	private void setInComandaPromoImport(){
		
		String tipusDescompte =  (request.getParameter("tipuDescomte") == null || request.getParameter("tipuDescomte").equals("")) ? null : request.getParameter("tipuDescomte");
		Double importe = (request.getParameter("importDescomte") == null || request.getParameter("importDescomte").equals("")) ? null : Double.parseDouble(request.getParameter("importDescomte"));
		this.comanda.setTipuDescomte(tipusDescompte);
		this.comanda.setImportDescomte(importe);
		this.comandaBo.update(comanda);
		
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
	public void setUsersBo(UsersBo usersBo) {
		this.usersBo = usersBo;
	}

}