package com.online.services.impl;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.online.model.HoresDTO;
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
	
	
	public Comandes getComandaToRepeat(Comandes comanda) throws ComandaException{
		Comandes newComanda = new Comandes();
		newComanda.setAddress(comanda.getAddress());
		newComanda.setaDomicili(comanda.getaDomicili());
		newComanda.setDia(new Date());
		newComanda.setFentrada(new Date());
		newComanda.setObservacions(comanda.getObservacions());
		newComanda.setPlats(comanda.getPlats());
		newComanda.setUser(comanda.getUser());
		Double preu =getPreuOfComanda(newComanda);
		newComanda.setPreu(preu);
		return newComanda;
	}
	
	public int getNumBegudes(List<BegudaComanda> listBeguda) throws ComandaException{
		int numBegudes=0;
		for(BegudaComanda begudacomanda : listBeguda){
			numBegudes = numBegudes+begudacomanda.getNumBegudes();
		}
		return numBegudes;
	}
	
	public int getNumPlats(List<PlatComanda> platList) throws ComandaException{
		int numPlats=0;
		for(PlatComanda platcomanda : platList){
			numPlats = numPlats+platcomanda.getNumPlats();
		}
		return numPlats;
	}
	
	public HoresDTO setHoresFeature( HoresDTO horesDTO, String data, Comandes comanda ) throws ComandaException{

		Set<Restaurant> restaurantSet = getRestaurants(comanda);
		Iterator iteraRestaurant = restaurantSet.iterator();
		
		while (iteraRestaurant.hasNext()) {
			
			Restaurant restaurant = (Restaurant) iteraRestaurant.next();
			
			ConfigRestaurant config = this.configRestaurantBo.load(Utils.getDate2(data), restaurant.getId());

			if (config == null || config.isObert()) {

				String[] horesArray = (config == null || config.getHores()==null) ? restaurant.getHores().split("\\W+") : config.getHores().split("\\W+");
				Date dataAvui = new Date();
				int nextHour = 0;
				if (data == Utils.formatDate2(dataAvui)) {
					int tempsPreparacio = calculaTempsPreparacioGlobal(comanda);
					int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
					int minute = Calendar.getInstance().get(Calendar.MINUTE) + tempsPreparacio;
					if (minute < 30) {
						nextHour = Integer.parseInt(String.valueOf(hour + 1) + "00");
					} else if (minute > 30 && minute < 60) {
						nextHour = Integer.parseInt(String.valueOf(hour + 1) + "30");
					} else if (minute > 60 && minute < 90) {
						nextHour = Integer.parseInt(String.valueOf(hour + 2) + "00");
					} else if (minute > 90 && minute < 120) {
						nextHour = Integer.parseInt(String.valueOf(hour + 2) + "30");
					}
				}
				for (String hora : horesArray) {
					if (hora.equals("")) {

					} else if (hora.equals("0800") && nextHour<=800) {
						horesDTO.set_0800("true");
					} else if (hora.equals("0830")&& nextHour<=830) {
						horesDTO.set_0830("true");
					} else if (hora.equals("0900")&& nextHour<=900) {
						horesDTO.set_0900("true");
					} else if (hora.equals("0930")&& nextHour<=930) {
						horesDTO.set_0930("true");
					} else if (hora.equals("1000")&& nextHour<=1000) {
						horesDTO.set_1000("true");
					} else if (hora.equals("1030")&& nextHour<=1030) {
						horesDTO.set_1030("true");
					} else if (hora.equals("1100")&& nextHour<=1100) {
						horesDTO.set_1100("true");
					} else if (hora.equals("1130")&& nextHour<=1130) {
						horesDTO.set_1130("true");
					} else if (hora.equals("1200")&& nextHour<=1200) {
						horesDTO.set_1200("true");
					} else if (hora.equals("1230")&& nextHour<=1230) {
						horesDTO.set_1230("true");
					} else if (hora.equals("1300")&& nextHour<=1300) {
						horesDTO.set_1300("true");
					} else if (hora.equals("1330")&& nextHour<=1330) {
						horesDTO.set_1330("true");
					} else if (hora.equals("1400")&& nextHour<=1400) {
						horesDTO.set_1400("true");
					} else if (hora.equals("1430")&& nextHour<=1430) {
						horesDTO.set_1430("true");
					} else if (hora.equals("1500")&& nextHour<=1500) {
						horesDTO.set_1500("true");
					} else if (hora.equals("1530")&& nextHour<=1530) {
						horesDTO.set_1530("true");
					} else if (hora.equals("1600")&& nextHour<=1600) {
						horesDTO.set_1600("true");
					} else if (hora.equals("1630")&& nextHour<=1630) {
						horesDTO.set_1630("true");
					} else if (hora.equals("1700")&& nextHour<=1700) {
						horesDTO.set_1700("true");
					} else if (hora.equals("1730")&& nextHour<=1730) {
						horesDTO.set_1730("true");
					} else if (hora.equals("1800")&& nextHour<=1800) {
						horesDTO.set_1800("true");
					} else if (hora.equals("1830")&& nextHour<=1830) {
						horesDTO.set_1830("true");
					} else if (hora.equals("1900")&& nextHour<=1900) {
						horesDTO.set_1900("true");
					} else if (hora.equals("1930")&& nextHour<=1930) {
						horesDTO.set_1930("true");
					} else if (hora.equals("2000")&& nextHour<=2000) {
						horesDTO.set_2000("true");
					} else if (hora.equals("2030")&& nextHour<=2030) {
						horesDTO.set_2030("true");
					} else if (hora.equals("2100")&& nextHour<=2100) {
						horesDTO.set_2100("true");
					} else if (hora.equals("2130")&& nextHour<=2130) {
						horesDTO.set_2130("true");
					} else if (hora.equals("2200")&& nextHour<=2200) {
						horesDTO.set_2200("true");
					} else if (hora.equals("2230")&& nextHour<=2230) {
						horesDTO.set_2230("true");
					} else if (hora.equals("2300") && nextHour<=2300) {
						horesDTO.set_2300("true");
					} else if (hora.equals("2330")&& nextHour<=2330) {
						horesDTO.set_2330("true");
					} else if (hora.equals("2400")&& nextHour<=2400) {
						horesDTO.set_2400("true");
					}
				}
			}
		}

		return horesDTO;
	}

	public void deleteBegudesPromo( Comandes comanda ) throws ComandaException{

		try {
			List<BegudaComanda> begudaComandaList = comanda.getBegudes();
			List<BegudaComanda> newBegudaComandaList = new ArrayList<BegudaComanda>();
			for (BegudaComanda begudaComanda : begudaComandaList) {
				if (!begudaComanda.isPromo()) {
					newBegudaComandaList.add(begudaComanda);
				}
			}
			comanda.setBegudes(newBegudaComandaList);
			this.comandaBo.update(comanda);

		} catch (Exception e) {
			throw new ComandaException(e, "Error deleting begudes of promo");
		}
	}

	public String checkComandaPromocions( Comandes comanda, ResourceBundle resource ) throws ComandaException{

		try {
			this.resource = resource;
			List<PromocioNumComandes> promoNumComandesFinalList = new ArrayList<PromocioNumComandes>();

			List<PromocioAPartirDe> apartirDePromoFinalList = this.promocionsBo.getPromosAPartirDe(comanda.getPreu(), null);

			List<PromocioNumComandes> promoNumComandesList = this.promocionsBo.getPromosNumComandes(null, null);
			for (PromocioNumComandes promo : promoNumComandesList) {
				Integer numComandes = promo.getNumComandes();
				Integer temps = promo.getTemps();
				if (comanda != null && comanda.getUser() != null) {
					List<Comandes> comandesList = this.comandaBo.getAllByUserAndTemps(comanda.getUser().getId(), temps);
					if (comandesList != null && comandesList.size() >= numComandes) {
						promoNumComandesFinalList.add(promo);
					}
				}

			}

			Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
			StringBuffer json = new StringBuffer("{ \"promosNumComanda\": [" + gson.toJson(promoNumComandesFinalList) + "],");
			json.append(" \"promosAPartirDe\": [" + gson.toJson(apartirDePromoFinalList) + "]}");
			return json.toString();
		} catch (Exception e) {
			throw new ComandaException(e, "Errorgetting promos");
		}

	}

	public String checkComandaProblems( Comandes comanda, ResourceBundle resource ) throws ComandaException{

		try {
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
		} catch (Exception e) {
			throw new ComandaException(e, "Error checking problems");
		}
	}

	public Double getPreuOfComanda( Comandes comanda ) throws ComandaException{

		try {
			if (comanda == null)
				return 0.0;

			Double preuComanda = 0.0;
			List<BegudaComanda> listBeguda = comanda.getBegudes();
			List<PlatComanda> platList = comanda.getPlats();
			if (!listBeguda.isEmpty()) {
				for (BegudaComanda begudaComanda : listBeguda) {
					if (!begudaComanda.isPromo())
						preuComanda = preuComanda + (begudaComanda.getNumBegudes() * begudaComanda.getBeguda().getPreu());
				}
			}

			if (!platList.isEmpty()) {
				for (PlatComanda platComanda : platList) {

					preuComanda = preuComanda + (platComanda.getNumPlats() * platComanda.getPlat().getPreu());
				}
			}

			return preuComanda;
		} catch (Exception e) {
			throw new ComandaException(e, "Error getting preu");
		}
	}

	public String createJSONForBegudaList( List<BegudaComanda> listBeguda ) throws ComandaException{

		try {

			Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
			StringBuffer json = new StringBuffer(gson.toJson(listBeguda));
			return json.toString();

		} catch (Exception e) {
			throw new ComandaException(e, "Error creating JSON of beguda list");
		}
	}

	public List<BegudaComanda> addBegudaInList( List<BegudaComanda> begudaList, Beguda beguda, boolean promo ) throws ComandaException{

		try {
			boolean existInList = false;
			if (begudaList.size() > 0) {
				for (BegudaComanda bg : begudaList) {
					if (bg.getBeguda().getId().equals(beguda.getId()) && bg.isPromo() == promo) {
						existInList = true;
						bg.setNumBegudes(bg.getNumBegudes() + 1);
					}
				}
			}
			if (!existInList) {
				BegudaComanda begudaComanda = new BegudaComanda();
				begudaComanda.setPromo(promo);
				begudaComanda.setBeguda(beguda);
				begudaComanda.setNumBegudes(1);
				begudaList.add(begudaComanda);
			}
			return begudaList;

		} catch (Exception e) {
			throw new ComandaException(e, "Error adding beguda in list");
		}
	}

	public boolean checkPlatForMoreThanTwoRestaurants( List<PlatComanda> platList, Plat plat ) throws ComandaException{

		try {

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

		} catch (Exception e) {
			throw new ComandaException(e, "Error checking plat from more than one restaurant");
		}
	}

	public String createJSONForShoppingCart( List<PlatComanda> platList, Long id ) throws ComandaException{

		try {
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

		} catch (Exception e) {
			throw new ComandaException(e, "Error creating JSON for cart");
		}
	}

	public boolean checkPlatInList( List<PlatComanda> platList, Plat plat ) throws ComandaException{

		try {

			for (PlatComanda plt : platList) {
				if (plt.getPlat().getId().toString().equals(plat.getId().toString())) {
					return true;
				}
			}
			return false;

		} catch (Exception e) {
			throw new ComandaException(e, "Error checking plat in list");
		}
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
	
	private Set<Restaurant>  getRestaurants( Comandes comanda){
		int temps = 0;
		List<PlatComanda> platComandaList = comanda.getPlats();
		
		Set<Restaurant> restaurantSet = new HashSet<Restaurant>();
		for (PlatComanda platComanda : platComandaList) {
			restaurantSet.add(platComanda.getPlat().getRestaurants().iterator().next());
		}
		return restaurantSet;
		
	}
	private int calculaTempsPreparacioGlobal( Comandes comanda ){

		int temps = 0;
		List<PlatComanda> platComandaList = comanda.getPlats();

		for (PlatComanda platComanda : platComandaList) {
			int tempsPlat = platComanda.getPlat().getTempsPreparacio();
			if (temps < tempsPlat) {
				temps = tempsPlat;
			}
		}

		return temps;

	}

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
			Restaurant restaurant = this.restaurantsBo.load(idRestaurant, false, false, false);
			if(configRestaurant == null){
				
				String hores = restaurant.getHores();
				if(!hores.contains(hora)){
					return this.resource.getString("txt.restaurant.no.obert") + " " + restaurant.getNom();
				}
			}else if (configRestaurant != null && configRestaurant.isObert()) {
				String hores = configRestaurant.getHores();
				if(!hores.contains(hora)){
					return this.resource.getString("txt.restaurant.no.obert") + " " + restaurant.getNom();
				}
				
			}else if(configRestaurant != null && !configRestaurant.isObert()){
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
