package com.online.action.comanda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.online.bo.BegudaBo;
import com.online.bo.ComandaBo;
import com.online.bo.PlatsBo;
import com.online.bo.PromocionsBo;
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
import com.online.model.Promocio;
import com.online.model.PromocioAssociada;
import com.online.model.Restaurant;
import com.online.model.Users;
import com.online.pojos.ARecollirDTO;
import com.online.pojos.BasicSub;
import com.online.services.impl.ComandaServiceImpl;
import com.online.supplier.extend.ActionSuportOnlineSession;
import com.online.utils.Constants;
import com.online.utils.Utils;

public class WelcomeComandaAction extends ActionSuportOnlineSession {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlatsBo platsBo;
	private ComandaBo comandaBo;
	private BegudaBo begudaBo;
	private RestaurantsBo restaurantsBo;
	private UsersBo usersBo;
	private PromocionsBo promocionsBo;
	private Comandes comanda;
	private Restaurant restaurant;

	List<Plat> platList = new ArrayList<Plat>();
	List<Beguda> begudaList = new ArrayList<Beguda>();
	List<PlatComanda> platComandaList = new ArrayList<PlatComanda>();
	List<BegudaComanda> begudaComandaList = new ArrayList<BegudaComanda>();

	private List<BasicSub> refrescList = new ArrayList<BasicSub>();

	private Long idComanda = null;
	private Long idPlat = null;
	private Long idBeguda = null;
	private Integer idRestaurant = null;
	private Integer nplats = null;
	private String data;
	private String dataActual;
	private boolean promo;
	private HoresDTO horesDTO;
	private int numPlats = 0;
	private int numBegudes = 0;
	private Integer amount = 0;
	private String code = "";

	private String nameUser;

	private ComandaServiceImpl comandaService;
	private List<Restaurant> restaurantList;

	private Users user;

	private Integer actualPage;
	private Integer totalPage;
	private Integer rppPage = 9;
	private String order = "";
	private String dataAvui;
	private boolean aDomicili = false;
	private boolean morethanone = false;

	public String execute() {

		setAuthenticationUser();
		setUserName();

		setLocaleIfNull("ca");

		inizializeRestaurantId();
		inizilizeComandaId();
		// Recoperem tots els plats disponibles.

		this.platList.clear();
		this.restaurant = this.restaurantsBo.load(this.idRestaurant, true,
				false, false);

		inizializePagin();

		this.platList.addAll(this.restaurant.getPlats(this.order));
		if (this.platList.size() > this.rppPage)
			this.platList = this.platList.subList(actualPage * rppPage,
					(actualPage + 1) * rppPage);

		inizializeComments();

		this.begudaList = this.begudaBo.getAll("vi", true);

		if (begudaList.size() > 5)
			this.begudaList = this.begudaList.subList(0, 5);
		this.dataActual = Utils.formatDate2(new Date());

		this.restaurantList = this.restaurantsBo.getAll(true, false, false);

		this.dataAvui = Utils.formatDate2(new Date());
		// si teniem una comanda la recuperem
		if (this.idComanda != null) {
			goToPas1Action();
		}

		return SUCCESS;

	}

	public String checkPromosEspecial() {
		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();
			setAuthenticationUser();

			if (this.nameAuth.equals("anonymousUser")) {
				json = Utils
						.createNotLogedJSON("User not loged. Login before...");
			} else {
				Users user = this.usersBo.findByUsername(this.nameAuth);
				if (user != null && user.getCodePromo() != null
						&& !user.getCodePromo().equals("")) {
					List<PromocioAssociada> promo = this.promocionsBo
							.loadAssociadaByCode(user.getCodePromo());
					Gson gson = new GsonBuilder().setPrettyPrinting()
							.excludeFieldsWithoutExposeAnnotation().create();
					json = gson.toJson(promo);
				}
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
	
	public String checkPromosVisibility() {
		ServletOutputStream out = null;
		String json = "";
		
		try {
			out = this.response.getOutputStream();
			setAuthenticationUser();
			inizializeCode();
			if (this.nameAuth.equals("anonymousUser")) {
				json = Utils
						.createNotLogedJSON("User not loged. Login before...");
			} else {
				Users user = this.usersBo.findByUsername(this.nameAuth);
				if (user != null){
					List<Promocio> promo = this.promocionsBo.loadByCode(this.code);
					if(promo!=null && !promo.isEmpty()){
						Gson gson = new GsonBuilder().setPrettyPrinting()
								.excludeFieldsWithoutExposeAnnotation().create();
						json = gson.toJson(promo);
					}else{
						ResourceBundle resource = getTexts("MessageResources");
						String alert = resource.getString("txt.no.promo.for.code");
						json="{\"alert\":\""+alert+"\"}";
					}
				}
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
	
	public String checkComandaPromos() {

		ServletOutputStream out = null;
		String json = "";
		ResourceBundle resource = getTexts("MessageResources");

		try {
			out = this.response.getOutputStream();
			setAuthenticationUser();

			if (this.nameAuth.equals("anonymousUser")) {
				json = Utils
						.createNotLogedJSON("User not loged. Login before...");
			} else {
				inizilizeComandaId();

				this.comanda = this.comandaBo.load(this.idComanda);
				if (this.comanda.getUser() == null)
					this.comanda.setUser(getUserFromContext());

				if (this.comanda.getPreu() == null
						|| this.comanda.getPreu() == 0.0)
					this.comanda.setPreu(this.comandaService
							.getPreuOfComanda(comanda));

				this.comandaBo.update(comanda);

				json = this.comandaService.checkComandaPromocions(comanda,
						resource);
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

	public String ajaxLoadInfoARecollir() {

		ServletOutputStream out = null;
		String json = null;

		try {
			out = this.response.getOutputStream();
			inizilizeComandaId();
			this.comanda = this.comandaBo.load(this.idComanda);

			boolean moreThanOne = this.comandaService
					.checkMoreThanOneRestaurant(comanda);

			String address = this.comandaService
					.getAddressOfRestaurant(comanda);

			ARecollirDTO aRecollir = new ARecollirDTO(moreThanOne, address);

			Gson gson = new GsonBuilder().setPrettyPrinting()
					.excludeFieldsWithoutExposeAnnotation().create();
			StringBuffer jsonSB = new StringBuffer(gson.toJson(aRecollir));

			json = jsonSB.toString();

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

	public String ajaxLoadNumPlat() {

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
						if (plt.getPlat().getId().toString()
								.equals(platToAdd.getId().toString())) {
							plt.setNumPlats(plt.getNumPlats() + this.nplats);
						}
					}
					comanda.setPlats(platList);
				}
				this.comandaBo.update(comanda);

				json = null;

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

	public String ajaxLoadPlat() {

		ServletOutputStream out = null;

		String json = "";

		// comprovar que el restaurant estigui obert
		// comprovar que no tingui plats de mes de dos restaurants

		try {

			out = this.response.getOutputStream();
			ResourceBundle resource = getTexts("MessageResources");
			inizilizeDadesComandaPlat();
			if (this.idComanda != null) {
				// recuperem la comanda i afegim plat
				Comandes comanda = this.comandaBo.load(this.idComanda);
				List<PlatComanda> platList = comanda.getPlats();
				Plat platToAdd = this.platsBo.load(this.idPlat, false);

				if (!comandaService.checkPlatForMoreThanTwoRestaurants(
						platList, platToAdd)) {

					if (comandaService.checkPlatInList(platList, platToAdd)) {
						for (PlatComanda plt : platList) {
							if (plt.getPlat().getId().toString()
									.equals(platToAdd.getId().toString())) {
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
					json = this.comandaService.createJSONForShoppingCart(
							comanda.getPlats(), comanda.getId(), resource);
				} else {
					json = Utils.createAlertJSON(resource
							.getString("txt.alerta.moreRests"));
				}
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

				json = this.comandaService.createJSONForShoppingCart(platList,
						comanda.getId(), resource);

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

	public String ajaxLoadBeguda() {

		ServletOutputStream out = null;

		String json = "";

		try {

			out = this.response.getOutputStream();
			inizilizeDadesComandaBeguda();
			inizializeAmount();
			if (this.idComanda != null) {
				// recuperem la comanda i afegim beguda
				Comandes comanda = this.comandaBo.load(this.idComanda);
				List<BegudaComanda> begudaList = comanda.getBegudes();
				Beguda begudaToAdd = this.begudaBo.load(this.idBeguda);
				if (this.amount < 0) {
					for (int i = this.amount; i < 0; i++)
						begudaList = comandaService.removeBegudaInList(
								begudaList, begudaToAdd, this.promo);
				} else {
					for (int i = 0; i < this.amount; i++)
						begudaList = comandaService.addBegudaInList(begudaList,
								begudaToAdd, this.promo);
				}
				comanda.setBegudes(begudaList);

				this.comandaBo.update(comanda);

				json = this.comandaService.createJSONForBegudaList(begudaList);
				StringBuffer jsonSB = new StringBuffer("{ \"begudes\": " + json);
				jsonSB.append(", \"numComanda\" : \"" + comanda.getId()
						+ "\" }");
				json = jsonSB.toString();
			} else {
				Comandes comanda = new Comandes();
				comanda.setPreu(0.0);
				comanda.setFentrada(new Date());
				Beguda begudaToAdd = this.begudaBo.load(this.idBeguda);
				BegudaComanda begudaComanda = new BegudaComanda();
				begudaComanda.setBeguda(begudaToAdd);
				begudaComanda.setPromo(this.promo);
				begudaComanda.setNumBegudes(1);
				begudaComanda.setNumBegudesPromo(0);
				List<BegudaComanda> begudes = new LinkedList<BegudaComanda>();
				begudes.add(begudaComanda);
				comanda.setBegudes(begudes);
				this.comandaBo.save(comanda);

				json = this.comandaService.createJSONForBegudaList(begudes);
				StringBuffer jsonSB = new StringBuffer("{ \"begudes\": " + json);
				jsonSB.append(", \"numComanda\" : \"" + comanda.getId()
						+ "\" }");
				json = jsonSB.toString();

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

	public String getHoraComanda() {

		ServletOutputStream out = null;

		String json = "";

		try {

			out = this.response.getOutputStream();

			inizializeRestaurantId();
			inizializeData();
			Integer guardatime = Integer.parseInt(this.request.getSession()
					.getServletContext().getInitParameter("guardaTime"));
			String hora = this.comandaService.getHora(this.idRestaurant,
					this.data, guardatime);

			json = "{\"hora\":\"" + hora + "\"}";

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

	public String loadHores() {

		ServletOutputStream out = null;

		String json = "";

		try {

			out = this.response.getOutputStream();

			inizilizeComandaId();
			inizializeData();

			this.comanda = this.comandaBo.load(this.idComanda);
			horesDTO = new HoresDTO();
			horesDTO.setData(data);
			Integer motertime = Integer.parseInt(this.request.getSession()
					.getServletContext().getInitParameter("moterTime"));
			horesDTO = this.comandaService.setHoresFeature(horesDTO, this.data,
					this.comanda, this.aDomicili, motertime);

			Gson gson = new GsonBuilder().setPrettyPrinting()
					.excludeFieldsWithoutExposeAnnotation().create();

			json = gson.toJson(horesDTO);

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

	public String goToPas1Action() {

		inizilizeComandaId();
		inizializeData();
		getUserAllInfoFromContext();

		this.comanda = this.comandaBo.load(this.idComanda);
		
		horesDTO = new HoresDTO();
		horesDTO.setData(data);
		horesDTO = this.comandaService.setHoresFeature(horesDTO, this.data,
				this.comanda, false, 0);
		setAuthenticationUser();
		if (!this.nameAuth.equals("anonymousUser")) {
			Users user = this.usersBo.findByUsername(nameAuth);
			this.comanda.setUser(user);
		}

		Double preu = this.comandaService.getPreuOfComanda(this.comanda);
		this.comanda.setPreu(preu);

		this.comandaBo.update(comanda);
		List<Beguda> begudaList = this.begudaBo.getAll();
		for (Beguda beguda : begudaList) {

			BasicSub basic = new BasicSub((beguda.getFoto() == null ? 0
					: beguda.getFoto().getId()), beguda.getNom());
			basic.setIdSub(beguda.getId());
			basic.setTipus(beguda.getTipus());
			basic.setPreu(beguda.getPreu());
			this.refrescList.add(basic);

		}

		if (this.restaurantList == null || this.restaurantList.isEmpty()) {
			this.restaurantList = this.restaurantsBo.getAll(true, false, false);
		}

		this.morethanone = this.comandaService.checkMoreThanOneRestaurant(comanda);
		this.numPlats = this.comandaService.getNumPlats(this.platComandaList);
		this.begudaComandaList = comanda.getBegudes();
		this.numBegudes = this.comandaService.getNumBegudes(comanda
				.getBegudes());
		
		this.platComandaList = comanda.getPlats();

		return SUCCESS;
	}

	public String deleteBegudesPromo() {

		ServletOutputStream out = null;

		String json = "";

		try {

			out = this.response.getOutputStream();
			inizilizeComandaId();
			this.comanda = this.comandaBo.load(this.idComanda);
			this.comandaService.deleteBegudesPromo(this.comanda);

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
	private void setUserName() {

		try {
			this.nameUser = Utils.getNameUser(nameAuth, usersBo);
		} catch (Exception e) {
			this.nameUser = "";
		}
	}
	
	private void inizializeCode() {

		this.code = (request.getParameter("code") != null && !request
				.getParameter("code").equals("")) ? request
				.getParameter("code") : null;
	}
	
	private void inizializeAmount() {

		this.amount = (request.getParameter("amount") != null && !request
				.getParameter("amount").equals("")) ? Integer.parseInt(request
				.getParameter("amount")) : 1;
	}

	private void inizializePagin() {

		this.actualPage = (request.getParameter("actualPage") != null && !request
				.getParameter("actualPage").equals("")) ? Integer
				.parseInt(request.getParameter("actualPage")) : 0;
		this.order = (request.getParameter("order") != null && !request
				.getParameter("order").equals("")) ? request
				.getParameter("order") : Constants.TIPUS_PLAT_ANY;
		this.totalPage = this.platList.size() / this.rppPage;
	}

	private void inizializeComments() {

		List<Plat> list = new ArrayList<Plat>();

		for (Plat plt : this.platList) {
			list.add(this.platsBo.loadPLatAndForos(plt.getId()));
		}
		this.platList = list;

	}

	private Users getUserFromContext() {

		setAuthenticationUser();
		return this.usersBo.findByUsername(this.nameAuth);

	}

	private void inizilizeDadesComandaPlat() throws WrongParamException {

		inizilizeComandaId();
		this.idPlat = (request.getParameter("idPlat") == null || request
				.getParameter("idPlat").equals("")) ? null : Long
				.parseLong(request.getParameter("idPlat"));
		if (this.idPlat == null) {
			throw new WrongParamException("null plat to add");
		}
	}

	private void inizilizeDadesComandaNumPlat() throws WrongParamException {

		this.nplats = (request.getParameter("nplats") == null || request
				.getParameter("nplats").equals("")) ? null : Integer
				.parseInt(request.getParameter("nplats"));
		if (this.nplats == null) {
			throw new WrongParamException("null plat to add");
		}
	}

	private void inizializeData() throws WrongParamException {

		this.data = (request.getParameter("data") == null || request
				.getParameter("data").equals("")) ? null : request
				.getParameter("data");
		this.aDomicili = (request.getParameter("aDomicili") == null || request
				.getParameter("aDomicili").equals("")) ? false : Boolean
				.parseBoolean(request.getParameter("aDomicili"));
		if (this.data == null) {
			throw new WrongParamException("null data of comanda");
		}
	}

	private void inizializeRestaurantId() throws WrongParamException {

		this.idRestaurant = (request.getParameter("restaurantId") == null || request
				.getParameter("restaurantId").equals("")) ? null : Integer
				.parseInt(request.getParameter("restaurantId"));
		if (this.idRestaurant == null) {
			throw new WrongParamException("null restaurant  to get plats");
		}
	}

	private void inizilizeDadesComandaBeguda() throws WrongParamException {

		inizilizeComandaId();
		this.idBeguda = (request.getParameter("idBeguda") == null || request
				.getParameter("idBeguda").equals("")) ? null : Long
				.parseLong(request.getParameter("idBeguda"));
		this.promo = (request.getParameter("promo") != null && request
				.getParameter("promo").equals("true")) ? true : false;
		if (this.idBeguda == null) {
			throw new WrongParamException("null beguda to add");
		}
	}

	private void inizilizeComandaId() throws WrongParamException {

		try {
			this.idComanda = (request.getParameter("idComanda") == null || request
					.getParameter("idComanda").equals("")) ? null : Long
					.parseLong(request.getParameter("idComanda"));
		} catch (NumberFormatException e) {
			throw new WrongParamException("wrong id of comanda");
		}

	}

	private void getUserAllInfoFromContext() {

		setAuthenticationUser();
		if (!this.nameAuth.equals("anonymousUser")) {
			this.user = this.usersBo.findByUsername(this.nameAuth);
		} else {
			this.user = null;
		}
	}

	// SETTERS i GETTERS
	public void setPlatsBo(PlatsBo platsBo) {

		this.platsBo = platsBo;
	}

	public List<Plat> getPlatList() {

		return platList;
	}

	public void setPlatList(List<Plat> platList) {

		this.platList = platList;
	}

	public void setComandaBo(ComandaBo comandaBo) {

		this.comandaBo = comandaBo;
	}

	public void setComandaService(ComandaServiceImpl comandaService) {

		this.comandaService = comandaService;
	}

	public List<PlatComanda> getPlatComandaList() {

		return platComandaList;
	}

	public void setPlatComandaList(List<PlatComanda> platComandaList) {

		this.platComandaList = platComandaList;
	}

	public Comandes getComanda() {

		return comanda;
	}

	public void setComanda(Comandes comanda) {

		this.comanda = comanda;
	}

	public Long getIdComanda() {

		return idComanda;
	}

	public void setIdComanda(Long idComanda) {

		this.idComanda = idComanda;
	}

	public List<BasicSub> getRefrescList() {

		return refrescList;
	}

	public void setRefrescList(List<BasicSub> refrescList) {

		this.refrescList = refrescList;
	}

	public void setBegudaBo(BegudaBo begudaBo) {

		this.begudaBo = begudaBo;
	}

	public void setRestaurantsBo(RestaurantsBo restaurantsBo) {

		this.restaurantsBo = restaurantsBo;
	}

	public void setUsersBo(UsersBo usersBo) {

		this.usersBo = usersBo;
	}

	public HoresDTO getHoresDTO() {

		return horesDTO;
	}

	public void setHoresDTO(HoresDTO horesDTO) {

		this.horesDTO = horesDTO;
	}

	public Users getUser() {

		return user;
	}

	public void setUser(Users user) {

		this.user = user;
	}

	public String getDataActual() {

		return dataActual;
	}

	public void setDataActual(String dataActual) {

		this.dataActual = dataActual;
	}

	public int getNumPlats() {

		return numPlats;
	}

	public void setNumPlats(int numPlats) {

		this.numPlats = numPlats;
	}

	public List<Beguda> getBegudaList() {

		return begudaList;
	}

	public void setBegudaList(List<Beguda> begudaList) {

		this.begudaList = begudaList;
	}

	public Restaurant getRestaurant() {

		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {

		this.restaurant = restaurant;
	}

	public Integer getActualPage() {

		return actualPage;
	}

	public void setActualPage(Integer actualPage) {

		this.actualPage = actualPage;
	}

	public Integer getTotalPage() {

		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {

		this.totalPage = totalPage;
	}

	public Integer getRppPage() {

		return rppPage;
	}

	public void setRppPage(Integer rppPage) {

		this.rppPage = rppPage;
	}

	public int getNumBegudes() {

		return numBegudes;
	}

	public void setNumBegudes(int numBegudes) {

		this.numBegudes = numBegudes;
	}

	public List<Restaurant> getRestaurantList() {

		return restaurantList;
	}

	public void setRestaurantList(List<Restaurant> restaurantList) {

		this.restaurantList = restaurantList;
	}

	public List<BegudaComanda> getBegudaComandaList() {

		return begudaComandaList;
	}

	public void setBegudaComandaList(List<BegudaComanda> begudaComandaList) {

		this.begudaComandaList = begudaComandaList;
	}

	public String getDataAvui() {

		return dataAvui;
	}

	public void setDataAvui(String dataAvui) {

		this.dataAvui = dataAvui;
	}

	public String getNameUser() {

		return nameUser;
	}

	public void setNameUser(String nameUser) {

		this.nameUser = nameUser;
	}

	public void setPromocionsBo(PromocionsBo promocionsBo) {

		this.promocionsBo = promocionsBo;
	}

	public boolean isMorethanone() {
		return morethanone;
	}

	public void setMorethanone(boolean morethanone) {
		this.morethanone = morethanone;
	}
	

}