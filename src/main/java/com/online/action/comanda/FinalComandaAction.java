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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import com.online.model.HoresDTO;
import com.online.model.Plat;
import com.online.model.PlatComanda;
import com.online.model.Restaurant;
import com.online.model.Users;
import com.online.pojos.ARecollirDTO;
import com.online.pojos.BasicSub;
import com.online.services.impl.ComandaServiceImpl;
import com.online.utils.Constants;
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
	private Restaurant			restaurant;

	List<Plat>					platList			= new ArrayList<Plat>();
	List<Beguda>				begudaList			= new ArrayList<Beguda>();
	List<PlatComanda>			platComandaList		= new ArrayList<PlatComanda>();
	List<BegudaComanda>			begudaComandaList	= new ArrayList<BegudaComanda>();

	private List<BasicSub>		refrescList			= new ArrayList<BasicSub>();

	private Long				idComanda			= null;
	private String				hora;
	private Date				dia;
	private String				aDomicili;
	private String				targeta;
	private String				address;
	private String				dataActual;

	private HoresDTO			horesDTO;
	private int					numPlats			= 0;
	private int					numBegudes			= 0;


	private String				nameAuth;
	private ComandaServiceImpl	comandaService;
	private List<Restaurant>	restaurantList;

	private Users				user;

	HttpServletResponse			response;
	HttpServletRequest			request;
	
	private Integer				actualPage;
	private Integer				totalPage;
	private Integer				rppPage =9;



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
		if (this.address == null) {
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

	public void setUsersBo( UsersBo usersBo ){

		this.usersBo = usersBo;
	}

	public HoresDTO getHoresDTO(){

		return horesDTO;
	}

	public void setHoresDTO( HoresDTO horesDTO ){

		this.horesDTO = horesDTO;
	}

	public Users getUser(){

		return user;
	}

	public void setUser( Users user ){

		this.user = user;
	}

	public String getDataActual(){

		return dataActual;
	}

	public void setDataActual( String dataActual ){

		this.dataActual = dataActual;
	}

	public int getNumPlats(){

		return numPlats;
	}

	public void setNumPlats( int numPlats ){

		this.numPlats = numPlats;
	}

	public List<Beguda> getBegudaList(){

		return begudaList;
	}

	public void setBegudaList( List<Beguda> begudaList ){

		this.begudaList = begudaList;
	}

	public Restaurant getRestaurant(){
	
		return restaurant;
	}

	public void setRestaurant( Restaurant restaurant ){
	
		this.restaurant = restaurant;
	}

	public Integer getActualPage(){
	
		return actualPage;
	}

	public void setActualPage( Integer actualPage ){
	
		this.actualPage = actualPage;
	}

	public Integer getTotalPage(){
	
		return totalPage;
	}

	public void setTotalPage( Integer totalPage ){
	
		this.totalPage = totalPage;
	}

	public Integer getRppPage(){
	
		return rppPage;
	}

	public void setRppPage( Integer rppPage ){
	
		this.rppPage = rppPage;
	}

	public int getNumBegudes() {
		return numBegudes;
	}

	public void setNumBegudes(int numBegudes) {
		this.numBegudes = numBegudes;
	}

	public List<Restaurant> getRestaurantList(){
	
		return restaurantList;
	}

	public void setRestaurantList( List<Restaurant> restaurantList ){
	
		this.restaurantList = restaurantList;
	}

	public List<BegudaComanda> getBegudaComandaList() {
		return begudaComandaList;
	}

	public void setBegudaComandaList(List<BegudaComanda> begudaComandaList) {
		this.begudaComandaList = begudaComandaList;
	}

	
}