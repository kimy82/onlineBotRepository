package com.online.action.comanda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.online.bo.ComandaBo;
import com.online.bo.PlatsBo;
import com.online.exceptions.ComandaException;
import com.online.exceptions.GeneralException;
import com.online.exceptions.WrongParamException;
import com.online.model.Comandes;
import com.online.model.Plat;
import com.online.model.PlatComanda;
import com.online.pojos.Basic;
import com.online.services.impl.ComandaServiceImpl;
import com.online.utils.Utils;
import com.opensymphony.xwork2.ActionSupport;

public class WelcomeComandaAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private PlatsBo				platsBo;
	private ComandaBo			comandaBo;
	private Comandes			comanda;

	List<Plat>					platList			= new ArrayList<Plat>();
	List<PlatComanda>			platComandaList		= new ArrayList<PlatComanda>();
	private List<Basic>			horaList			= new ArrayList<Basic>();

	private Long				idComanda			= null;
	private Long				idPlat				= null;	

	private String				nameAuth;

	private ComandaServiceImpl	comandaService;

	HttpServletResponse			response;
	HttpServletRequest			request;

	public String execute(){

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		this.nameAuth = auth.getName();
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
			if (this.idComanda != null) {
				// recuperem la comanda i afegim plat
				Comandes comanda = this.comandaBo.load(this.idComanda);
				List<PlatComanda> platList = comanda.getPlats();
				Plat platToAdd = this.platsBo.load(this.idPlat, false);

				if (!comandaService.checkPlatForMoreThanTwoRestaurants(platList, platToAdd)) {

					if (comandaService.checkPlatInList(platList, platToAdd)) {
						for (PlatComanda plt : platList) {
							if (plt.getPlat().getId().toString().equals(platToAdd.getId().toString())) {
								plt.setNumPlats(plt.getNumPlats() + 1);
							}
						}
						comanda.setPlats(platList);
					} else {
						PlatComanda platComanda = new PlatComanda();
						platComanda.setPlat(platToAdd);
						platComanda.setNumPlats(1);
						platList.add(platComanda);
						comanda.setPlats(platList);
					}
					this.comandaBo.update(comanda);
				}

				json = this.comandaService.createJSONForShoppingCart(platList, comanda.getId());

			} else {
				// creem comanda i afegim plat
				Comandes comanda = new Comandes();
				comanda.setPreu(0.0);
				List<PlatComanda> platList = new LinkedList<PlatComanda>();
				Plat platToAdd = this.platsBo.load(this.idPlat, false);
				PlatComanda platComanda = new PlatComanda();
				platComanda.setPlat(platToAdd);
				platComanda.setNumPlats(1);
				platList.add(platComanda);
				comanda.setPlats(platList);
				this.comandaBo.save(comanda);

				json = this.comandaService.createJSONForShoppingCart(platList, comanda.getId());
			}
		} catch (ComandaException ce) {
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

	public String goToPas1Action(){

		inizilizeComandaId();

		this.horaList = Utils.getHoraList();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		this.nameAuth = auth.getName();

		Comandes comanda = this.comandaBo.load(this.idComanda);

		this.platComandaList = comanda.getPlats();

		return SUCCESS;
	}

	// private methods
	private String createErrorJSON( String error ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"error\":\"" + error);
		jsonSB.append("}");
		return jsonSB.toString();
	}

	private void inizilizeDadesComanda() throws WrongParamException{

		inizilizeComandaId();
		this.idPlat = (request.getParameter("idPlat") == null || request.getParameter("idPlat").equals("")) ? null : Long.parseLong(request
				.getParameter("idPlat"));
		if (this.idPlat == null) {
			throw new WrongParamException("null plat to add");
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

	// SETTERS i GETTERS
	public void setPlatsBo( PlatsBo platsBo ){

		this.platsBo = platsBo;
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

	public void setComandaBo( ComandaBo comandaBo ){

		this.comandaBo = comandaBo;
	}

	public void setComandaService( ComandaServiceImpl comandaService ){

		this.comandaService = comandaService;
	}

	public List<PlatComanda> getPlatComandaList(){

		return platComandaList;
	}

	public void setPlatComandaList( List<PlatComanda> platComandaList ){

		this.platComandaList = platComandaList;
	}

	public Comandes getComanda(){

		return comanda;
	}

	public void setComanda( Comandes comanda ){

		this.comanda = comanda;
	}

	public List<Basic> getHoraList(){

		return horaList;
	}

	public void setHoraList( List<Basic> horaList ){

		this.horaList = horaList;
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

}