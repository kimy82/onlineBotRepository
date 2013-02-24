package com.online.action.comanda;

import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.online.bo.ComandaBo;
import com.online.bo.UsersBo;
import com.online.exceptions.ComandaException;
import com.online.exceptions.GeneralException;
import com.online.exceptions.WrongParamException;
import com.online.model.Comandes;
import com.online.model.HoresDTO;
import com.online.model.Users;
import com.online.services.impl.ComandaServiceImpl;
import com.online.utils.Utils;
import com.opensymphony.xwork2.ActionSupport;

public class FinalComandaAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private ComandaBo			comandaBo;

	private UsersBo				usersBo;
	private Comandes			comanda;

	private Long				idComanda			= null;
	private String				hora;
	private Date				dia;
	private String				aDomicili;
	private String				targeta;
	private String				address;
	private String				dataActual;

	private String				nameAuth;
	private ComandaServiceImpl	comandaService;

	HttpServletResponse			response;
	HttpServletRequest			request;
	
	public String execute(){

		return SUCCESS;

	}

	public String checkComanda(){

		ServletOutputStream out = null;
		String json = "";
		ResourceBundle resource = getTexts("MessageResources");

		try {
			out = this.response.getOutputStream();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			this.nameAuth = auth.getName();

			if (this.nameAuth.equals("anonymousUser")) {
				json = Utils.createNotLogedJSON("User not loged. Login before...");
			} else {
				inizilizeComandaId();
				inizilizeComandaDiaHoraADomicili();
				inizializeAddress();

				this.comanda = this.comandaBo.load(this.idComanda);

				this.comanda.setHora(Utils.getHora(this.hora));
				this.comanda.setDia(this.dia);
				this.comanda.setaDomicili(Boolean.valueOf(aDomicili));
				this.comanda.setTargeta(Boolean.valueOf(targeta));
				this.comanda.setAddress(this.address);

				if (this.comanda.getUser() == null) {
					Users user = this.usersBo.findByUsername(this.nameAuth);
					this.comanda.setUser(user);
				}
				this.comanda.setPreu(this.comandaService.getPreuOfComanda(comanda));
				this.comandaBo.update(this.comanda);
				json = this.comandaService.checkComandaProblems(this.comanda, resource);
			}

		} catch (ComandaException ce) {
			json = Utils.createErrorJSON("error in comanda service action");
		} catch (Exception e) {
			json = Utils.createErrorJSON("error in ajax action");
		}
		
		try {
			out.print(json);
		} catch (IOException e) {
			throw new GeneralException(e, "possibly ServletOutputStream null");
		}
		return null;
	}
	
	// private methods
	private void inizializeAddress() throws WrongParamException{

		this.address = (request.getParameter("address") == null || request.getParameter("address").equals("")) ? null : request
				.getParameter("address");
		if (this.address == null && this.aDomicili==null || this.aDomicili.equals("true")) {
			throw new WrongParamException("null address of comanda");
		}
	}
	
	private void inizilizeComandaId() throws WrongParamException{

		try {
			this.idComanda = (request.getParameter("idComanda") == null || request.getParameter("idComanda").equals("")) ? null : Long
					.parseLong(request.getParameter("idComanda"));
		} catch (NumberFormatException e) {
			throw new WrongParamException("wrong id of comanda");
		}

	}

	private void inizilizeComandaDiaHoraADomicili() throws WrongParamException{

		try {

			this.hora = (request.getParameter("hora") == null || request.getParameter("hora").equals("")) ? null : request
					.getParameter("hora");
			this.dia = (request.getParameter("dia") == null || request.getParameter("dia").equals("")) ? null : Utils.getDate2(request
					.getParameter("dia"));
			this.aDomicili = (request.getParameter("aDomicili") == null || request.getParameter("aDomicili").equals("")) ? null : request
					.getParameter("aDomicili");
			this.targeta = (request.getParameter("targeta") == null || request.getParameter("targeta").equals("")) ? null : request
					.getParameter("targeta");

		} catch (Exception e) {
			throw new WrongParamException("wrong id of comanda");
		}
	}

	// SETTERS i GETTERS
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

	public void setComandaBo( ComandaBo comandaBo ){

		this.comandaBo = comandaBo;
	}

	public void setComandaService( ComandaServiceImpl comandaService ){

		this.comandaService = comandaService;
	}

	public Comandes getComanda(){

		return comanda;
	}

	public void setComanda( Comandes comanda ){

		this.comanda = comanda;
	}

	public String getNameAuth(){

		return nameAuth;
	}

	public void setNameAuth( String nameAuth ){

		this.nameAuth = nameAuth;
	}

	public Long getIdComanda(){

		return idComanda;
	}

	public void setIdComanda( Long idComanda ){

		this.idComanda = idComanda;
	}

	public void setUsersBo( UsersBo usersBo ){

		this.usersBo = usersBo;
	}

	public String getDataActual(){

		return dataActual;
	}

	public void setDataActual( String dataActual ){

		this.dataActual = dataActual;
	}


}