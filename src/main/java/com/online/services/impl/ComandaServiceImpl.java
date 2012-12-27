package com.online.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.online.bo.ComandaBo;
import com.online.bo.ConfigRestaurantBo;
import com.online.bo.MotersBo;
import com.online.bo.PromocionsBo;
import com.online.bo.RestaurantsBo;
import com.online.exceptions.ComandaException;
import com.online.model.Beguda;
import com.online.model.BegudaComanda;
import com.online.model.Comandes;
import com.online.model.ConfigRestaurant;
import com.online.model.Moters;
import com.online.model.Plat;
import com.online.model.PlatComanda;
import com.online.model.PromocioAPartirDe;
import com.online.model.PromocioNumComandes;
import com.online.model.Restaurant;
import com.online.services.ComandaService;
import com.online.utils.Utils;

public class ComandaServiceImpl implements ComandaService{

	private ConfigRestaurantBo	configRestaurantBo;
	private MotersBo			motersBo;
	private RestaurantsBo		restaurantsBo;
	private PromocionsBo		promocionsBo;
	private ComandaBo			comandaBo;
	
	private ResourceBundle		resource;

	public String checkComandaPromocions( Comandes comanda, ResourceBundle resource ) throws ComandaException{

		this.resource = resource;
		List<PromocioNumComandes> promoNumComandesFinalList= new ArrayList<PromocioNumComandes>();
		
		List<PromocioAPartirDe> apartirDePromoFinalList = this.promocionsBo.getPromosAPartirDe(comanda.getPreu(), null);
		
		List<PromocioNumComandes> promoNumComandesList = this.promocionsBo.getPromosNumComandes(null, null);
		for(PromocioNumComandes promo :promoNumComandesList){
			Integer numComandes = promo.getNumComandes();
			Integer temps = promo.getTemps();
			if(comanda!=null && comanda.getUser()!=null){
			  List<Comandes> comandesList = this.comandaBo.getAllByUserAndTemps(comanda.getUser().getId(), temps);
			  if(comandesList!=null && comandesList.size() >= numComandes){
				  promoNumComandesFinalList.add(promo);
			  }
			}
		
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		StringBuffer json = new StringBuffer("{ \"promosNumComanda\": ["+gson.toJson(promoNumComandesFinalList)+"],");
		json.append(" \"promosAPartirDe\": ["+gson.toJson(apartirDePromoFinalList)+"]}");
		return json.toString();		
	}

	public String checkComandaProblems( Comandes comanda, ResourceBundle resource ) throws ComandaException{

		this.resource = resource;
		List<PlatComanda> platList = comanda.getPlats();
		String checkDades = checkDadesComanda(comanda);

		if (checkDades.equals("true")) {

			String hora = comanda.getHora();
			Date dia = comanda.getDia();

			// Control Dia i conexio restaurant
			String checkDiaHora = comprovaHoraDiaRestaurantsOberts(platList, dia, hora);
			String conRPS = comprovaConexioRPSambRestaurants(platList);

			if (!checkDiaHora.equals("true") || !conRPS.equals("true")) {

				return createJSONErrorComanda(checkDiaHora + " " + conRPS);
			}

			// Control Moters
			if (comanda.getaDomicili() != null && comanda.getaDomicili() == true) {
				String checkMoters = checkMoters(dia, hora);
				if (!checkMoters.equals("true")) {
					return checkMoters;
				}
			}

		} else {
			return checkDades;
		}
		return createJSONComandaOK(resource);
	}

	public Double getPreuOfComanda( Comandes comanda ) throws ComandaException{

		if (comanda == null)
			return 0.0;

		Double preuComanda = 0.0;
		List<BegudaComanda> listBeguda = comanda.getBegudes();
		List<PlatComanda> platList = comanda.getPlats();
		if (!listBeguda.isEmpty()) {
			for (BegudaComanda begudaComanda : listBeguda) {

				preuComanda = preuComanda + (begudaComanda.getNumBegudes() * begudaComanda.getBeguda().getPreu());
			}
		}

		if (!platList.isEmpty()) {
			for (PlatComanda platComanda : platList) {

				preuComanda = preuComanda + (platComanda.getNumPlats() * platComanda.getPlat().getPreu());
			}
		}

		return preuComanda;
	}

	public String createJSONForBegudaList( List<BegudaComanda> listBeguda ) throws ComandaException{

		Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		StringBuffer json = new StringBuffer(gson.toJson(listBeguda));
		return json.toString();

	}

	public List<BegudaComanda> addBegudaInList( List<BegudaComanda> begudaList, Beguda beguda ) throws ComandaException{

		boolean existInList = false;
		if (begudaList.size() > 0) {
			for (BegudaComanda bg : begudaList) {
				if (bg.getBeguda().getId().equals(beguda.getId())) {
					existInList = true;
					bg.setNumBegudes(bg.getNumBegudes() + 1);
				}
			}
		}
		if (!existInList) {
			BegudaComanda begudaComanda = new BegudaComanda();
			begudaComanda.setBeguda(beguda);
			begudaComanda.setNumBegudes(1);
			begudaList.add(begudaComanda);
		}
		return begudaList;
	}

	public boolean checkPlatForMoreThanTwoRestaurants( List<PlatComanda> platList, Plat plat ) throws ComandaException{

		boolean moreThanTwo = false;

		Set<String> restaurants = new HashSet<String>();

		if (platList.size() >= 2) {
			for (PlatComanda pl : platList) {
				Iterator<Restaurant> restaurantsIterator = pl.getPlat().getRestaurants().iterator();
				while (restaurantsIterator.hasNext()) {
					Restaurant rest = restaurantsIterator.next();
					restaurants.add(rest.getNom());
					break;
				}
			}
			if (restaurants.size() > 2) {
				throw new ComandaException("S'han assignat mes de dos resturants");
			}
		}

		Iterator<Restaurant> restaurantsIterator = plat.getRestaurants().iterator();
		while (restaurantsIterator.hasNext()) {
			Restaurant rest = restaurantsIterator.next();
			restaurants.add(rest.getNom());
			if (restaurants.size() <= 2) {
				moreThanTwo = false;
				break;
			} else {
				moreThanTwo = true;
				restaurants.remove(rest.getNom());
			}
		}
		return moreThanTwo;
	}

	public String createJSONForShoppingCart( List<PlatComanda> platList, Long id ) throws ComandaException{

		Double preuComanda = 0.0;
		List<String> platsSring = new ArrayList<String>();
		Integer numPlats = 0;
		for (PlatComanda pl : platList) {
			platsSring.add(pl.getPlat().getNom());
			preuComanda = preuComanda + (pl.getPlat().getPreu() * pl.getNumPlats());
			numPlats = numPlats + pl.getNumPlats();
		}

		ComandaCart comandaCart = new ComandaCart(String.valueOf(preuComanda), platsSring, String.valueOf(numPlats));

		Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		StringBuffer json = new StringBuffer(gson.toJson(comandaCart));
		json.setLength(json.length() - 1);

		json.append(", \"numComanda\" : \"" + id + "\" }");

		boolean valid = Utils.isValidJSON(json.toString());

		return json.toString();

	}

	public boolean checkPlatInList( List<PlatComanda> platList, Plat plat ) throws ComandaException{

		for (PlatComanda plt : platList) {
			if (plt.getPlat().getId().toString().equals(plat.getId().toString())) {
				return true;
			}
		}
		return false;
	}

	public class ComandaCart{

		@Expose
		private String			preu		= "0.0";

		@Expose
		private List<String>	platsNames	= new ArrayList<String>();

		@Expose
		private String			numPlats	= "0";

		public ComandaCart( String preu, List<String> platsNames, String numPlats ) {

			this.preu = preu;
			this.platsNames = platsNames;
			this.numPlats = numPlats;
		}

		public String getPreu(){

			return preu;
		}

		public void setPreu( String preu ){

			this.preu = preu;
		}

		public List<String> getPlatsNames(){

			return platsNames;
		}

		public void setPlatsNames( List<String> platsNames ){

			this.platsNames = platsNames;
		}

		public String getNumPlats(){

			return numPlats;
		}

		public void setNumPlats( String numPlats ){

			this.numPlats = numPlats;
		}

	}

	// PRIVATE
	private String checkDadesComanda( Comandes comanda ){

		List<PlatComanda> platList = comanda.getPlats();
		if (comanda.getDia() == null) {
			return createJSONErrorComanda(resource.getString("txt.alert.comanda.nodia"));
		}
		if (comanda.getHora() == null || comanda.getHora().equals("")) {
			return createJSONErrorComanda(resource.getString("txt.alert.comanda.nohora"));
		}
		if (platList.isEmpty()) {
			return createJSONErrorComanda(resource.getString("txt.alert.comanda.empty.plats"));
		}

		return "true";
	}

	private String checkMoters( Date dia, String hora ){

		Moters moter = this.motersBo.load(hora, dia);
		if (moter != null && moter.getNumeroMoters() != null && !moter.getNumeroMoters().equals(0)) {
			Integer numMoters = moter.getNumeroMoters() != null ? moter.getNumeroMoters() : 0;
			Integer numMotersUsed = moter.getNumeroMotersUsed() != null ? moter.getNumeroMotersUsed() : 0;
			if (numMotersUsed < numMoters) {
				return "true";
			}
		}
		return createJSONErrorComanda(this.resource.getString("txt.alert.no.enough.moters"));

	}

	private String comprovaConexioRPSambRestaurants( List<PlatComanda> platList ){

		return "true";
	}

	private String comprovaHoraDiaRestaurantsOberts( List<PlatComanda> platList, Date dia, String hora ){

		Set<Integer> restaurants = new HashSet<Integer>();

		for (PlatComanda pl : platList) {
			restaurants.add(pl.getPlat().getRestaurants().iterator().next().getId());
		}
		for (Integer idRestaurant : restaurants) {

			ConfigRestaurant configRestaurant = configRestaurantBo.load(dia, idRestaurant);
			if (configRestaurant == null || !configRestaurant.isObert()) {
				Restaurant restaurant = this.restaurantsBo.load(idRestaurant, false, false, false);
				return this.resource.getString("txt.restaurant.no.obert") + " " + restaurant.getNom();
			}
		}

		return "true";

	}

	private String createJSONComandaOK( ResourceBundle resource ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"comandaOK\":\"" + resource.getString("txt.comanda.dia.hora.comanda.ok"));
		jsonSB.append("\"}");
		return jsonSB.toString();
	}

	private String createJSONErrorComanda( String alert ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"alerta\":\"" + alert);
		jsonSB.append("\"}");
		return jsonSB.toString();
	}

	// GETTERS i SETTERS

	public void setConfigRestaurantBo( ConfigRestaurantBo configRestaurantBo ){

		this.configRestaurantBo = configRestaurantBo;
	}

	public void setMotersBo( MotersBo motersBo ){

		this.motersBo = motersBo;
	}

	public void setRestaurantsBo( RestaurantsBo restaurantsBo ){

		this.restaurantsBo = restaurantsBo;
	}

	public void setPromocionsBo( PromocionsBo promocionsBo ){
	
		this.promocionsBo = promocionsBo;
	}

	public void setComandaBo( ComandaBo comandaBo ){
	
		this.comandaBo = comandaBo;
	}

	
	
}
