package com.online.action.comanda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.online.bo.BegudaBo;
import com.online.bo.ComandaBo;
import com.online.bo.PlatsBo;
import com.online.bo.RestaurantsBo;
import com.online.bo.UsersBo;
import com.online.exceptions.ComandaException;
import com.online.exceptions.GeneralException;
import com.online.exceptions.WrongParamException;
import com.online.model.Beguda;
import com.online.model.BegudaComanda;
import com.online.model.Comandes;
import com.online.model.Plat;
import com.online.model.PlatComanda;
import com.online.model.Users;
import com.online.pojos.Basic;
import com.online.pojos.BasicSub;
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
	private BegudaBo			begudaBo;
	private RestaurantsBo		restaurantsBo;
	private UsersBo				usersBo;
	private Comandes			comanda;

	List<Plat>					platList			= new ArrayList<Plat>();
	List<PlatComanda>			platComandaList		= new ArrayList<PlatComanda>();
	private List<Basic>			horaList			= new ArrayList<Basic>();

	private List<BasicSub>		refrescList			= new ArrayList<BasicSub>();

	private Long				idComanda			= null;
	private Long				idPlat				= null;
	private Long				idBeguda			= null;
	private Integer				idRestaurant		= null;
	private String				hora;
	private Date				dia;
	private String				aDomicili;
	private Integer				nplats				= null;

	private String				nameAuth;

	private ComandaServiceImpl	comandaService;

	HttpServletResponse			response;
	HttpServletRequest			request;

	public String execute(){

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		this.nameAuth = auth.getName();
		inizializeRestaurantId();
		inizilizeComandaId();
		// Recoperem tots els plats disponibles.

		this.platList.clear();
		this.platList.addAll(this.restaurantsBo.load(this.idRestaurant, true, false, false).getPlats());

		// si teniem una comanda la recuperem
		if (this.idComanda != null) {
			goToPas1Action();
		}

		return SUCCESS;

	}

	public String checkComandaPromos(){

		ServletOutputStream out = null;
		String json = "";
		ResourceBundle resource = getTexts("MessageResources");

		try {
			out = this.response.getOutputStream();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			this.nameAuth = auth.getName();

			if (this.nameAuth.equals("anonymousUser")) {
				json = createNotLogedJSON("User not loged. Login before...");
			} else {
				inizilizeComandaId();

				this.comanda = this.comandaBo.load(this.idComanda);
				if (this.comanda.getUser() == null)
					this.comanda.setUser(getUserFromContext());
				json = this.comandaService.checkComandaPromocions(comanda, resource);
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

	public String checkComanda(){

		ServletOutputStream out = null;
		String json = "";
		ResourceBundle resource = getTexts("MessageResources");

		try {
			out = this.response.getOutputStream();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			this.nameAuth = auth.getName();

			if (this.nameAuth.equals("anonymousUser")) {
				json = createNotLogedJSON("User not loged. Login before...");
			} else {
				inizilizeComandaId();
				inizilizeComandaDiaHoraADomicili();

				this.comanda = this.comandaBo.load(this.idComanda);

				this.comanda.setHora(Utils.getHora(this.hora));
				this.comanda.setDia(this.dia);
				this.comanda.setaDomicili(Boolean.valueOf(aDomicili));

				json = this.comandaService.checkComandaProblems(this.comanda, resource);
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

	public String ajaxLoadNumPlat(){

		ServletOutputStream out = null;
		String json = null;

		try {

			out = this.response.getOutputStream();
			inizilizeDadesComandaPlat();
			inizilizeDadesComandaNumPlat();
			if (this.idComanda != null) {
				// recuperem la comanda i afegim nplat
				Comandes comanda = this.comandaBo.load(this.idComanda);
				List<PlatComanda> platList = comanda.getPlats();
				Plat platToAdd = this.platsBo.load(this.idPlat, false);

					if (comandaService.checkPlatInList(platList, platToAdd)) {
						for (PlatComanda plt : platList) {
							if (plt.getPlat().getId().toString().equals(platToAdd.getId().toString())) {
								plt.setNumPlats(this.nplats);
							}
						}
						comanda.setPlats(platList);
					} 
					this.comandaBo.update(comanda);
				
					json = null;

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

	public String ajaxLoadPlat(){

		ServletOutputStream out = null;

		String json = "";

		// comprovar que el restaurant estigui obert
		// comprovar que no tingui plats de mes de dos restaurants

		try {

			out = this.response.getOutputStream();
			inizilizeDadesComandaPlat();
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
				comanda.setFentrada(new Date());
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

	public String ajaxLoadBeguda(){

		ServletOutputStream out = null;

		String json = "";

		try {

			out = this.response.getOutputStream();
			inizilizeDadesComandaBeguda();
			if (this.idComanda != null) {
				// recuperem la comanda i afegim plat
				Comandes comanda = this.comandaBo.load(this.idComanda);
				List<BegudaComanda> begudaList = comanda.getBegudes();
				Beguda begudaToAdd = this.begudaBo.load(this.idBeguda);

				begudaList = comandaService.addBegudaInList(begudaList, begudaToAdd);

				comanda.setBegudes(begudaList);

				this.comandaBo.update(comanda);

				json = this.comandaService.createJSONForBegudaList(begudaList);
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

		ResourceBundle resource = getTexts("MessageResources");

		this.comanda = this.comandaBo.load(this.idComanda);

		Double preu = this.comandaService.getPreuOfComanda(this.comanda);

		this.comanda.setPreu(preu);

		List<Beguda> begudaList = this.begudaBo.getAll();
		for (Beguda beguda : begudaList) {

			BasicSub basic = new BasicSub(beguda.getFoto().getId(), beguda.getNom());
			basic.setIdSub(beguda.getId());
			this.refrescList.add(basic);

		}

		// this.comandaService.checkComandaPromocions(comanda, resource);

		this.platComandaList = comanda.getPlats();

		return SUCCESS;
	}

	// private methods

	private Users getUserFromContext(){

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		return this.usersBo.findByUsername(name);

	}

	private String createErrorJSON( String error ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"error\":\"" + error);
		jsonSB.append("\"}");
		return jsonSB.toString();
	}

	private String createNotLogedJSON( String error ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"alertLoged\":\"" + error + "\"");
		jsonSB.append("}");
		return jsonSB.toString();
	}

	private void inizilizeDadesComandaPlat() throws WrongParamException{

		inizilizeComandaId();
		this.idPlat = (request.getParameter("idPlat") == null || request.getParameter("idPlat").equals("")) ? null : Long.parseLong(request
				.getParameter("idPlat"));
		if (this.idPlat == null) {
			throw new WrongParamException("null plat to add");
		}
	}

	private void inizilizeDadesComandaNumPlat() throws WrongParamException{
	
		this.nplats = (request.getParameter("nplats") == null || request.getParameter("nplats").equals("")) ? null : Integer.parseInt(request
				.getParameter("nplats"));
		if (this.nplats == null) {
			throw new WrongParamException("null plat to add");
		}
	}

	private void inizializeRestaurantId() throws WrongParamException{

		this.idRestaurant = (request.getParameter("restaurantId") == null || request.getParameter("restaurantId").equals("")) ? null
				: Integer.parseInt(request.getParameter("restaurantId"));
		if (this.idRestaurant == null) {
			throw new WrongParamException("null restaurant  to get plats");
		}
	}

	private void inizilizeDadesComandaBeguda() throws WrongParamException{

		inizilizeComandaId();
		this.idBeguda = (request.getParameter("idBeguda") == null || request.getParameter("idBeguda").equals("")) ? null : Long
				.parseLong(request.getParameter("idBeguda"));
		if (this.idBeguda == null) {
			throw new WrongParamException("null beguda to add");
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
			this.dia = (request.getParameter("dia") == null || request.getParameter("dia").equals("")) ? null : Utils.getDate(request
					.getParameter("dia"));
			this.aDomicili = (request.getParameter("aDomicili") == null || request.getParameter("aDomicili").equals("")) ? null : request
					.getParameter("aDomicili");

		} catch (Exception e) {
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

	public List<BasicSub> getRefrescList(){

		return refrescList;
	}

	public void setRefrescList( List<BasicSub> refrescList ){

		this.refrescList = refrescList;
	}

	public void setBegudaBo( BegudaBo begudaBo ){

		this.begudaBo = begudaBo;
	}

	public void setRestaurantsBo( RestaurantsBo restaurantsBo ){

		this.restaurantsBo = restaurantsBo;
	}

	public void setUsersBo( UsersBo usersBo ){

		this.usersBo = usersBo;
	}

}