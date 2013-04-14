package com.online.action.payment;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.online.bo.ComandaBo;
import com.online.bo.UsersBo;
import com.online.exceptions.PaymentException;
import com.online.exceptions.WrongParamException;
import com.online.model.Comandes;
import com.online.model.Users;
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
	
	private String urlTPV;

	public String execute() throws IOException{

		List<String> orders = new ArrayList<String>();
		DecimalFormat formateador = new DecimalFormat("####.##");
		
		
		setAuthenticationUser();

		int numComandes = initNumComandes();
		
		inizilizeComandaId();

		this.comanda = this.comandaBo.load(idComanda);

		try {
			setInComandaPromoImport();

			if (this.comanda.getTargeta() == true) {
				String entorn = this.request.getSession().getServletContext().getInitParameter("entorn");
				String context = this.request.getSession().getServletContext().getInitParameter("app");
				StringBuffer url= new StringBuffer("");
				if(entorn.equals(Constants.ENTORN_LOCAL)){
					url.append("https://sis-t.redsys.es:25443/sis/realizarPago");
				}else{
					url.append("https://sis.redsys.es/sis/realizarPago");
				}
				String Ds_Merchant_Amount= formateador.format(this.comanda.getPreu());
				url.append("Ds_Merchant_Amount="+Ds_Merchant_Amount);
				url.append("&Ds_Merchant_Currency=978");
				String Ds_Merchant_Currency="978";
				String id = this.comanda.getId().toString();
				if(id.length()<4){
					
					for(int numIndex=id.length(); numIndex==4; numIndex++){
						id=id+"0";
					}
				}
				String Ds_Merchant_Order= id;
				url.append("&Ds_Merchant_Order="+id);
				String Ds_Merchant_ProductDescription= this.comandaService.getListOfPlatsAndDrinks(comanda);
				url.append("&Ds_Merchant_ProductDescription="+Ds_Merchant_ProductDescription);
				String Ds_Merchant_Titular = this.nameAuth;
				url.append("&Ds_Merchant_Titular="+Ds_Merchant_Titular);
				String Ds_Merchant_MerchantCode="327318309";
				
				String Ds_Merchant_UrlOK="http://www.portamu.com/"+context+"/payment/paymentOK.action";
				url.append("&Ds_Merchant_UrlOK="+Ds_Merchant_UrlOK);
				String Ds_Merchant_UrlKO="http://www.portamu.com/"+context+"/payment/paymentKO.action";
				url.append("&Ds_Merchant_UrlKO="+Ds_Merchant_UrlKO);
				String Ds_Merchant_ConsumerLanguage="0";
				url.append("&Ds_Merchant_ConsumerLanguage="+Ds_Merchant_ConsumerLanguage);
				String Ds_Merchant_MerchantName="PORTAMU";
				url.append("&Ds_Merchant_MerchantName="+Ds_Merchant_MerchantName);
				String Ds_Merchant_Terminal="1";
				url.append("&Ds_Merchant_Terminal="+Ds_Merchant_Terminal);
				String Ds_Merchant_TransactionType="0";
				url.append("&Ds_Merchant_TransactionType="+Ds_Merchant_TransactionType);
				String Ds_Merchant_MerchantSignature=this.paymentService.SHA(formateador.format((this.comandaService.getPreuOfComanda(comanda)*100)), Ds_Merchant_Order, Ds_Merchant_MerchantCode, Ds_Merchant_Currency, "0","",entorn);
				url.append("&Ds_Merchant_MerchantSignature="+Ds_Merchant_MerchantSignature);

				this.urlTPV=url.toString();
				
				return "TPV";
			}

			orders = this.paymentService.getComandaOrders(this.comanda);

		} catch (PaymentException pe) {
			pe.printStackTrace();
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

		if (numComandes > 1) {
			this.paymentService.sendOrder(true, orders);
			this.paymentService.sendOrder(false, orders);
		} else {
			this.comanda.setRevisio(true);
			this.comandaBo.update(comanda);
			this.paymentService.sendOrder(true, orders);
		}

		return SUCCESS;

	}
	
	public String paymentTpvNotDone() throws IOException{

		return SUCCESS;
	}

	public String paymentTpvDone() throws IOException{

		List<String> orders = new ArrayList<String>();
		inizilizeComandaId();

		this.comanda = this.comandaBo.load(idComanda);
		this.comanda.setPagada(true);
		this.comandaBo.update(comanda);
		try {
			orders = this.paymentService.getComandaOrders(this.comanda);

		} catch (PaymentException pe) {
			return ERROR;
		} catch (Exception e) {
			return ERROR;
		}

		this.paymentService.sendOrder(true, orders);
		this.paymentService.sendOrder(false, orders);
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

	public String getUrlTPV() {
		return urlTPV;
	}

	public void setUrlTPV(String urlTPV) {
		this.urlTPV = urlTPV;
	}
	
	
	

}