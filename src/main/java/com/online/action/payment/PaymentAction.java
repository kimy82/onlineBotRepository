package com.online.action.payment;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.online.bo.ComandaBo;
import com.online.bo.MotersBo;
import com.online.bo.UsersBo;
import com.online.exceptions.BOException;
import com.online.exceptions.PaymentException;
import com.online.exceptions.WrongParamException;
import com.online.model.Comandes;
import com.online.model.Moters;
import com.online.model.Users;
import com.online.pojos.Payment;
import com.online.services.impl.ComandaServiceImpl;
import com.online.services.impl.PaymentServiceImpl;
import com.online.supplier.extend.ActionSuportOnline;
import com.online.utils.Constants;

@SuppressWarnings("serial")
public class PaymentAction extends ActionSuportOnline{

	private ComandaBo			comandaBo;

	private UsersBo				usersBo;

	private PaymentServiceImpl	paymentService;
	private ComandaServiceImpl	comandaService;

	private Long				idComanda	= null;
	private Comandes			comanda;
	private MotersBo			motersBo;
	
	private Payment  payment = new Payment();
	

	public String execute() throws IOException{

		List<String> orders = new ArrayList<String>();
		DecimalFormat formateador = new DecimalFormat("####");
		String transport = this.request.getSession().getServletContext().getInitParameter("transport");
		String transportDouble = this.request.getSession().getServletContext().getInitParameter("transport_double");
		String moterTime = this.request.getSession().getServletContext().getInitParameter("moterTime");
		String comandarest = this.request.getSession().getServletContext().getInitParameter("comandarest");
		
		
		setAuthenticationUser();

		int numComandes = initNumComandes();
		
		inizilizeComandaId();

		this.comanda = this.comandaBo.load(idComanda);

		try {
			setInComandaPromoImport();

			if (this.comanda.getTargeta() == true) {
				String entorn = this.request.getSession().getServletContext().getInitParameter("entorn");
				String context = this.request.getSession().getServletContext().getInitParameter("app");
				
				if(entorn.equals(Constants.ENTORN_LOCAL)){
					this.payment.setUrl("https://sis-t.redsys.es:25443/sis/realizarPago");					
				}else{
					this.payment.setUrl("https://sis.redsys.es/sis/realizarPago");
				}
				
				Double preu = this.comandaService.getPreuOfComanda(comanda);
				
				if(this.comanda.getImportDescomte()!=null && this.comanda.getTipuDescomte()!=null){
					if(this.comanda.getTipuDescomte().equals(Constants.TIPUS_DESCOMPTE_CENT_1)){
						preu = preu-((this.comanda.getImportDescomte()*preu)/100);
					}else if(this.comanda.getTipuDescomte().equals(Constants.TIPUS_DESCOMPTE_AMOUNT_2)){
						preu = preu-this.comanda.getImportDescomte();
					}else{
						
					}
				}
				
				if(this.comanda.getaDomicili()!=null && this.comanda.getaDomicili()==true){
					boolean morethanOne =this.comandaService.checkMoreThanOneRestaurant(comanda);
					if(morethanOne){
						Double add = Double.parseDouble(this.request.getSession().getServletContext().getInitParameter("transport_double"));
						preu= preu+add;
					}else{
						Double add = Double.parseDouble(this.request.getSession().getServletContext().getInitParameter("transport"));
						preu= preu+add;
					}
				}
				
				this.payment.setDs_Merchant_Amount(formateador.format(preu*100));
						
				String id = this.comanda.getId().toString();
				id = "2"+id+"340";
				if(id.length()<4){				
					for(int numIndex=id.length(); numIndex<=4; numIndex++){
						id=id+"0";
					}
				}				
				this.payment.setDs_Merchant_Order(id);
				
				this.payment.setDs_Merchant_ProductDescription(this.comandaService.getListOfPlatsAndDrinks(comanda));
				this.payment.setDs_Merchant_Titular(this.nameAuth);
				
				if(comandarest!=null && comandarest.equals(Constants.ENTORN_LOCAL)){
					this.payment.setDs_Merchant_UrlOK("https://localhost/"+context+"/paymentPOK.action?orderId="+this.comanda.getId()+"&order="+this.paymentService.SHAOrder(String.valueOf(this.comanda.getId()), entorn));
					this.payment.setDs_Merchant_UrlKO("https://localhost/"+context+"/paymentPKO.action");
				}else if (comandarest!=null && comandarest.equals(Constants.ENTORN_PRO)){
					this.payment.setDs_Merchant_UrlOK("https://www.portamu.com/"+context+"/paymentPOK.action?orderId="+this.comanda.getId()+"&order="+this.paymentService.SHAOrder(String.valueOf(this.comanda.getId()), entorn));
					this.payment.setDs_Merchant_UrlKO("https://www.portamu.com/"+context+"/paymentPKO.action");
				}
				
				
				this.payment.setDs_Merchant_MerchantSignature(this.paymentService.SHA(formateador.format((preu*100)), id, "327318309", "978", "0","",entorn));				
				
				this.comanda.setRevisio(false);
				this.comandaBo.update(comanda);
				
				return "TPV";
			}
			int tempsPreparacio = this.comandaService.calculaTempsPreparacioGlobal(comanda);
			orders = this.paymentService.getComandaOrders(this.comanda,this.comandaService.checkMoreThanOneRestaurant(this.comanda),transport , transportDouble,moterTime,tempsPreparacio);

		} catch (PaymentException pe) {
			pe.printStackTrace(); 
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		
		
		
		if (numComandes > 1) {

			if(this.comanda.getHora()!=null && this.comanda.getDia()!=null && this.comanda.getaDomicili()!=null && this.comanda.getaDomicili()==true){
				Moters moters = this.motersBo.load(this.comanda.getHora(), this.comanda.getDia());
				moters.setNumeroMotersUsed(moters.getNumeroMotersUsed()==null?1:(moters.getNumeroMotersUsed()+1));
				this.motersBo.update(moters);
			}
			
			
			this.comanda.setRevisio(false);
			this.comanda.setPagada(true);
			this.comandaBo.update(comanda);
			this.paymentService.sendOrder(true,true, orders,comandarest);
			this.paymentService.sendOrder(true,false, orders,comandarest);
			this.paymentService.sendOrder(false,false, orders,comandarest);
		} else {
			this.comanda.setRevisio(true);
			this.comanda.setPagada(false);
			this.comandaBo.update(comanda);
			this.paymentService.sendOrder(true,false, orders,comandarest);
		}

		return SUCCESS;

	}
	
	public String paymentOK() throws IOException, PaymentException, NoSuchAlgorithmException, BOException{
			
		try{
			String order = this.request.getParameter("order");
			String orderId = this.request.getParameter("orderId");
			String entorn = this.request.getSession().getServletContext().getInitParameter("entorn");
			String transport = this.request.getSession().getServletContext().getInitParameter("transport");
			String transportDouble = this.request.getSession().getServletContext().getInitParameter("transport_double");
			String moterTime = this.request.getSession().getServletContext().getInitParameter("moterTime");
			String comandarest = this.request.getSession().getServletContext().getInitParameter("comandarest");
			
			this.request.setAttribute("order", order);
			this.request.setAttribute("orderId", orderId);
			if(this.paymentService.CheckOrderOK(order, entorn,orderId)){
				List<String> orders = new ArrayList<String>();
			
		
				this.comanda = this.comandaBo.load(Long.parseLong(orderId));
				this.comanda.setPagada(true);
				this.comandaBo.update(comanda);
				try {
					int tempsPreparacio = this.comandaService.calculaTempsPreparacioGlobal(comanda);
					orders = this.paymentService.getComandaOrders(this.comanda, this.comandaService.checkMoreThanOneRestaurant(this.comanda),transport , transportDouble,moterTime,tempsPreparacio);
		
				} catch (PaymentException pe) {
					return ERROR;
				} catch (Exception e) {
					return ERROR;
				}
				
				if(this.comanda.getHora()!=null && this.comanda.getDia()!=null && this.comanda.getaDomicili()!=null && this.getComanda().getaDomicili()==true){
					Moters moters = this.motersBo.load(this.comanda.getHora(), this.comanda.getDia());
					moters.setNumeroMotersUsed(moters.getNumeroMotersUsed()==null?1:(moters.getNumeroMotersUsed()+1));
					this.motersBo.update(moters);
				}
		
				this.paymentService.sendOrder(true,true, orders,comandarest);
				this.paymentService.sendOrder(true,false, orders,comandarest);
				this.paymentService.sendOrder(false,false, orders,comandarest);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	
	public String paymentTpvNotDone() throws IOException{

		return SUCCESS;
	}

	public String paymentTpvDone() throws IOException, PaymentException, NoSuchAlgorithmException, BOException{
		
		String order = this.request.getParameter("order");
		String entorn = this.request.getSession().getServletContext().getInitParameter("entorn");
		String transport = this.request.getSession().getServletContext().getInitParameter("transport");
		String transportDouble = this.request.getSession().getServletContext().getInitParameter("transport_double");
		String moterTime = this.request.getSession().getServletContext().getInitParameter("moterTime");
		String comandarest = this.request.getSession().getServletContext().getInitParameter("comandarest");
		
		setAuthenticationUser();
		inizilizeComandaId();
		if(this.paymentService.CheckOrderOK(order, entorn,String.valueOf(this.idComanda))){
			List<String> orders = new ArrayList<String>();
		
	
			this.comanda = this.comandaBo.load(idComanda);
			this.comanda.setPagada(true);
			this.comandaBo.update(comanda);
			try {
				
				int tempsPreparacio = this.comandaService.calculaTempsPreparacioGlobal(comanda);
				orders = this.paymentService.getComandaOrders(this.comanda, this.comandaService.checkMoreThanOneRestaurant(this.comanda),transport , transportDouble, moterTime,tempsPreparacio );
	
			} catch (PaymentException pe) {
				return ERROR;
			} catch (Exception e) {
				return ERROR;
			}

			if(this.comanda.getHora()!=null && this.comanda.getDia()!=null && this.comanda.getaDomicili()!=null && this.getComanda().getaDomicili()==true){
				Moters moters = this.motersBo.load(this.comanda.getHora(), this.comanda.getDia());
				moters.setNumeroMotersUsed(moters.getNumeroMotersUsed()==null?1:(moters.getNumeroMotersUsed()+1));
				this.motersBo.update(moters);
			}			
			this.paymentService.sendOrder(true,true, orders, comandarest);
			this.paymentService.sendOrder(true,false, orders, comandarest);
			this.paymentService.sendOrder(false,false, orders, comandarest);
		}
		return SUCCESS;
	}

	// PRIVATES
	private int initNumComandes(){

		Users user = this.usersBo.findByUsername(nameAuth);
		List<Comandes> comandes = this.comandaBo.getAllByUser(user.getId(), false);
		if (comandes != null && !comandes.isEmpty()) {
			return comandes.size();
		}
		return 0;
	}

	private void setInComandaPromoImport(){

		String tipusDescompte = (request.getParameter("tipuDescomte") == null || request.getParameter("tipuDescomte").equals("")) ? null
				: request.getParameter("tipuDescomte");
		Double importe = (request.getParameter("importDescomte") == null || request.getParameter("importDescomte").equals("")) ? null
				: Double.parseDouble(request.getParameter("importDescomte"));
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

	// GETTERS i SETTERS
	public void setComandaBo( ComandaBo comandaBo ){

		this.comandaBo = comandaBo;
	}

	public void setPaymentService( PaymentServiceImpl paymentService ){

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

	public void setUsersBo( UsersBo usersBo ){

		this.usersBo = usersBo;
	}

	public void setComandaService(ComandaServiceImpl comandaService) {
		this.comandaService = comandaService;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public void setMotersBo(MotersBo motersBo) {
		this.motersBo = motersBo;
	}			
	
}