package com.online.action.payment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.online.bo.ComandaBo;
import com.online.bo.UsersBo;
import com.online.exceptions.PaymentException;
import com.online.exceptions.WrongParamException;
import com.online.model.Comandes;
import com.online.model.Users;
import com.online.services.impl.PaymentServiceImpl;
import com.online.supplier.extend.ActionSuportOnline;

@SuppressWarnings("serial")
public class PaymentAction extends ActionSuportOnline{

	private ComandaBo			comandaBo;

	private UsersBo				usersBo;

	private PaymentServiceImpl	paymentService;

	private Long				idComanda	= null;
	private Comandes			comanda;

	public String execute() throws IOException{

		List<String> orders = new ArrayList<String>();

		setAuthenticationUser();

		int numComandes = initNumComandes();

		inizilizeComandaId();

		this.comanda = this.comandaBo.load(idComanda);

		try {
			setInComandaPromoImport();

			if (this.comanda.getTargeta() == true) {
				return "TPV";
			}

			orders = this.paymentService.getComandaOrders(this.comanda);

		} catch (PaymentException pe) {
			return ERROR;
		} catch (Exception e) {
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

}