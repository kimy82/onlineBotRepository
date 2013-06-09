package com.online.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.online.bo.ComandaBo;
import com.online.bo.ConfigRestaurantBo;
import com.online.bo.MotersBo;
import com.online.bo.PlatsBo;
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
	private PlatsBo				platsBo;
	private String 				jsonBegudaDeleted;   

	private ResourceBundle		resource;

	public Comandes getComandaToRepeat( Comandes comanda ) throws ComandaException{

		List<PlatComanda> platList = new LinkedList<PlatComanda>();
		
		Comandes newComanda = new Comandes();			
		newComanda.setAddress(comanda.getAddress());
		newComanda.setaDomicili(comanda.getaDomicili());
		newComanda.setDia(new Date());
		newComanda.setFentrada(new Date());
		newComanda.setObservacions(comanda.getObservacions());
		
		for(PlatComanda pl : comanda.getPlats()){
			
			PlatComanda platComanda = new PlatComanda();
			platComanda.setPlat(pl.getPlat());
			platComanda.setNumPlats(pl.getNumPlats());
			platList.add(platComanda);
		}
		
		newComanda.setPlats(platList);
		newComanda.setUser(comanda.getUser());
		Double preu = getPreuOfComanda(newComanda);
		newComanda.setPreu(preu);
		return newComanda;

	}
	
	public String getAddressOfRestaurant (Comandes comanda) throws ComandaException{
		
		List<PlatComanda> listPlats = comanda.getPlats();
		String address="";
		for (PlatComanda pl : listPlats) {
			Iterator<Restaurant> restaurantsIterator = pl.getPlat().getRestaurants().iterator();
			while (restaurantsIterator.hasNext()) {
				Restaurant rest = restaurantsIterator.next();
				address =rest.getAddress();
				break;
			}
		}
		
		return address;
	}
	
	public boolean checkMoreThanOneRestaurant (Comandes comanda) throws ComandaException{
		
		List<PlatComanda> listPlats = comanda.getPlats();
		Set<String> restaurants = new HashSet<String>();
		
		for (PlatComanda pl : listPlats) {
			Iterator<Restaurant> restaurantsIterator = pl.getPlat().getRestaurants().iterator();
			while (restaurantsIterator.hasNext()) {
				Restaurant rest = restaurantsIterator.next();
				restaurants.add(rest.getNom());
				break;
			}
		}
		
		if (restaurants.size() >= 2) {
			return true;
		}
		return false;
	}

	public int getNumBegudes( List<BegudaComanda> listBeguda ) throws ComandaException{

		int numBegudes = 0;
		for (BegudaComanda begudacomanda : listBeguda) {
			numBegudes = numBegudes + begudacomanda.getNumBegudes();
		}
		return numBegudes;
	}

	public int getNumPlats( List<PlatComanda> platList ) throws ComandaException{

		int numPlats = 0;
		for (PlatComanda platcomanda : platList) {
			numPlats = numPlats + platcomanda.getNumPlats();
		}
		return numPlats;
	}

	public String getHora( Integer idRestaurant, String data, Integer guardaTime ) throws ComandaException{

		String hora = "";
		try {
			Restaurant restaurant = this.restaurantsBo.load(idRestaurant, false, false, true);
			ConfigRestaurant config = this.configRestaurantBo.load(Utils.getDate2(data), idRestaurant);
			if ((config == null || config.isObert()) && restaurant.getHores() != null) {

				String[] horesArray = (config == null || config.getHores() == null) ? restaurant.getHores().split("\\W+") : config
						.getHores().split("\\W+");
				Date dataAvui = new Date();
				int nextHour = 0;
				if (data.equals(Utils.formatDate2(dataAvui))) {
					int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
					int minute = Calendar.getInstance().get(Calendar.MINUTE) + guardaTime;
					if (minute < 30) {
						nextHour = Integer.parseInt(String.valueOf(hour) + "30");
					} else if (minute > 30 && minute < 60) {
						nextHour = Integer.parseInt(String.valueOf(hour + 1) + "00");
					} else if (minute > 60 && minute < 90) {
						nextHour = Integer.parseInt(String.valueOf(hour + 1) + "30");
					} else if (minute > 90 && minute < 120) {
						nextHour = Integer.parseInt(String.valueOf(hour + 2) + "00");
					}
				}
				
				Arrays.sort(horesArray);
				
				for (String hor : horesArray) {
					if (hor.equals("")) {

					} else if (hor.equals("0800") && nextHour <= 830) {
						hora = "08:00-08:30";
						break;
					} else if (hor.equals("0830") && nextHour <= 900) {
						hora = "08:30-09:00";
						break;
					} else if (hor.equals("0900") && nextHour <= 930) {
						hora = "09:00-09:30";
						break;
					} else if (hor.equals("0930") && nextHour <= 1000) {
						hora = "09:30-10:00";
						break;
					} else if (hor.equals("1000") && nextHour <= 1030) {
						hora = "10:00-10:30";
						break;
					} else if (hor.equals("1030") && nextHour <= 1100) {
						hora = "10:30-11:00";
						break;
					} else if (hor.equals("1100") && nextHour <= 1130) {
						hora = "11:00-11:30";
						break;
					} else if (hor.equals("1130") && nextHour <= 1200) {
						hora = "11:30-12:00";
						break;
					} else if (hor.equals("1200") && nextHour <= 1230) {
						hora = "12:00-12:30"; 
						break;
					} else if (hor.equals("1230") && nextHour <= 1300) {
						hora = "12:30-13:00";
						break;
					} else if (hor.equals("1300") && nextHour <= 1330) {
						hora = "13:00-13:30";
						break;
					} else if (hor.equals("1330") && nextHour <= 1400) {
						hora = "13:30-14:00";
						break;
					} else if (hor.equals("1400") && nextHour <= 1430) {
						hora = "14:00-14:30";
						break;
					} else if (hor.equals("1430") && nextHour <= 1500) {
						hora = "14:30-15:00";
						break;
					} else if (hor.equals("1500") && nextHour <= 1530) {
						hora = "15:00-15:30";
						break;
					} else if (hor.equals("1530") && nextHour <= 1600) {
						hora = "15:30-16:00";
						break;
					} else if (hor.equals("1600") && nextHour <= 1630) {
						hora = "16:00-16:30";
						break;
					} else if (hor.equals("1630") && nextHour <= 1700) {
						hora = "16:30-17:00";
						break;
					} else if (hor.equals("1700") && nextHour <= 1730) {
						hora = "17:00-17:30";
						break;
					} else if (hor.equals("1730") && nextHour <= 1800) {
						hora = "17:30-18:00";
						break;
					} else if (hor.equals("1800") && nextHour <= 1830) {
						hora = "18:00-18:30";
						break;
					} else if (hor.equals("1830") && nextHour <= 1900) {
						hora = "18:30-19:00";
						break;
					} else if (hor.equals("1900") && nextHour <= 1930) {
						hora = "19:00-19:30";
						break;
					} else if (hor.equals("1930") && nextHour <= 2000) {
						hora = "19:30-20:00";
						break;
					} else if (hor.equals("2000") && nextHour <= 2030) {
						hora = "20:00-20:30";
						break;
					} else if (hor.equals("2030") && nextHour <= 2100) {
						hora = "20:30-21:00";
						break;
					} else if (hor.equals("2100") && nextHour <= 2130) {
						hora = "21:00-21:30";
						break;
					} else if (hor.equals("2130") && nextHour <= 2200) {
						hora = "21:30-22:00";
						break;
					} else if (hor.equals("2200") && nextHour <= 2230) {
						hora = "22:00-22:30";
						break;
					} else if (hor.equals("2230") && nextHour <= 2300) {
						hora = "22:30-23:00";
						break;
					} else if (hor.equals("2300") && nextHour <= 2330) {
						hora = "23:00-23:30";
						break;
					} else if (hor.equals("2330") && nextHour <= 2400) {
						hora = "23:30-24:00";
						break;
					} else if (hor.equals("2400") && nextHour <= 2430) {
						hora = "24:00-24:30";
						break;
					}
				}
			}
		} catch (Exception e) {
			throw new ComandaException(e, "Error getting hora of comanda");
		}
		return hora;

	}

	public HoresDTO setHoresFeature( HoresDTO horesDTO, String data, Comandes comanda, boolean aDomicili,Integer minuteTransport ) throws ComandaException{

		Set<Restaurant> restaurantSet = getRestaurants(comanda);
		Iterator iteraRestaurant = restaurantSet.iterator();
		boolean secondRestaurant=false;
		String dataToLoad = horesDTO.getData();
		HoresDTO horesDTOSecond = new HoresDTO();
		HoresDTO horesDTOMoters = new HoresDTO();
		while (iteraRestaurant.hasNext()) {

			Restaurant restaurant = (Restaurant) iteraRestaurant.next();

			ConfigRestaurant config = this.configRestaurantBo.load(Utils.getDate2(data), restaurant.getId());

			if ((config == null || config.isObert()) && restaurant.getHores() != null) {

				String[] horesArray = (config==null || config.getHores() == null) ? restaurant.getHores().split("\\W+") : config.getHores().split("\\W+");
				Date dataAvui = new Date();
				int nextHour = 0;
				if (data.equals(Utils.formatDate2(dataAvui))) {
					int tempsPreparacio = calculaTempsPreparacioGlobal(comanda);
					int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
					
					int minute = Calendar.getInstance().get(Calendar.MINUTE) + tempsPreparacio;
					
					if(aDomicili && minuteTransport!=null){
						minute = minute+minuteTransport;
					}
					
					if (minute < 30) {
						nextHour = Integer.parseInt(String.valueOf(hour) + "30");
					} else if (minute > 30 && minute < 60) {
						nextHour = Integer.parseInt(String.valueOf(hour + 1) + "00");
					} else if (minute > 60 && minute < 90) {
						nextHour = Integer.parseInt(String.valueOf(hour + 1) + "30");
					} else if (minute > 90 && minute < 120) {
						nextHour = Integer.parseInt(String.valueOf(hour + 2) + "00");
					}
				}
				if(secondRestaurant==false){
					for (String hora : horesArray) {
						if (hora.equals("")) {
	
						} else if (hora.equals("0800") && nextHour <= 830) {
							horesDTO.set_0800("true");
						} else if (hora.equals("0830") && nextHour <= 900) {
							horesDTO.set_0830("true");
						} else if (hora.equals("0900") && nextHour <= 930) {
							horesDTO.set_0900("true");
						} else if (hora.equals("0930") && nextHour <= 100) {
							horesDTO.set_0930("true");
						} else if (hora.equals("1000") && nextHour <= 1030) {
							horesDTO.set_1000("true");
						} else if (hora.equals("1030") && nextHour <= 1100) {
							horesDTO.set_1030("true");
						} else if (hora.equals("1100") && nextHour <= 1130) {
							horesDTO.set_1100("true");
						} else if (hora.equals("1130") && nextHour <= 1200) {
							horesDTO.set_1130("true");
						} else if (hora.equals("1200") && nextHour <= 1230) {
							horesDTO.set_1200("true");
						} else if (hora.equals("1230") && nextHour <= 1300) {
							horesDTO.set_1230("true");
						} else if (hora.equals("1300") && nextHour <= 1330) {
							horesDTO.set_1300("true");
						} else if (hora.equals("1330") && nextHour <= 1400) {
							horesDTO.set_1330("true");
						} else if (hora.equals("1400") && nextHour <= 1430) {
							horesDTO.set_1400("true");
						} else if (hora.equals("1430") && nextHour <= 1500) {
							horesDTO.set_1430("true");
						} else if (hora.equals("1500") && nextHour <= 1530) {
							horesDTO.set_1500("true");
						} else if (hora.equals("1530") && nextHour <= 1600) {
							horesDTO.set_1530("true");
						} else if (hora.equals("1600") && nextHour <= 1630) {
							horesDTO.set_1600("true");
						} else if (hora.equals("1630") && nextHour <= 1700) {
							horesDTO.set_1630("true");
						} else if (hora.equals("1700") && nextHour <= 1730) {
							horesDTO.set_1700("true");
						} else if (hora.equals("1730") && nextHour <= 1800) {
							horesDTO.set_1730("true");
						} else if (hora.equals("1800") && nextHour <= 1830) {
							horesDTO.set_1800("true");
						} else if (hora.equals("1830") && nextHour <= 1900) {
							horesDTO.set_1830("true");
						} else if (hora.equals("1900") && nextHour <= 1930) {
							horesDTO.set_1900("true");
						} else if (hora.equals("1930") && nextHour <= 2000) {
							horesDTO.set_1930("true");
						} else if (hora.equals("2000") && nextHour <= 2030) {
							horesDTO.set_2000("true");
						} else if (hora.equals("2030") && nextHour <= 2100) {
							horesDTO.set_2030("true");
						} else if (hora.equals("2100") && nextHour <= 2130) {
							horesDTO.set_2100("true");
						} else if (hora.equals("2130") && nextHour <= 2200) {
							horesDTO.set_2130("true");
						} else if (hora.equals("2200") && nextHour <= 2230) {
							horesDTO.set_2200("true");
						} else if (hora.equals("2230") && nextHour <= 2300) {
							horesDTO.set_2230("true");
						} else if (hora.equals("2300") && nextHour <= 2330) {
							horesDTO.set_2300("true");
						} else if (hora.equals("2330") && nextHour <= 2400) {
							horesDTO.set_2330("true");
						} else if (hora.equals("2400") && nextHour <= 2430) {
							horesDTO.set_2400("true");
						}
					}
				}
				if(secondRestaurant){
					for (String hora : horesArray) {
						if (hora.equals("")) {
	
						} else if (hora.equals("0800") && nextHour <= 800 && horesDTO.get_0800().equals("true") ) {
							horesDTOSecond.set_0800("true");
						} else if (hora.equals("0830") && nextHour <= 830 && horesDTO.get_0830().equals("true")) {
							horesDTOSecond.set_0830("true");
						} else if (hora.equals("0900") && nextHour <= 900 && horesDTO.get_0900().equals("true")) {
							horesDTOSecond.set_0900("true");
						} else if (hora.equals("0930") && nextHour <= 930 && horesDTO.get_0930().equals("true")) {
							horesDTOSecond.set_0930("true");
						} else if (hora.equals("1000") && nextHour <= 1000 && horesDTO.get_1000().equals("true")) {
							horesDTOSecond.set_1000("true");
						} else if (hora.equals("1030") && nextHour <= 1030 && horesDTO.get_1030().equals("true")) {
							horesDTOSecond.set_1030("true");
						} else if (hora.equals("1100") && nextHour <= 1100 && horesDTO.get_1100().equals("true")) {
							horesDTOSecond.set_1100("true");
						} else if (hora.equals("1130") && nextHour <= 1130 && horesDTO.get_1130().equals("true")) {
							horesDTOSecond.set_1130("true");
						} else if (hora.equals("1200") && nextHour <= 1200 && horesDTO.get_1200().equals("true")) {
							horesDTOSecond.set_1200("true");
						} else if (hora.equals("1230") && nextHour <= 1230 && horesDTO.get_1230().equals("true")) {
							horesDTOSecond.set_1230("true");
						} else if (hora.equals("1300") && nextHour <= 1300 && horesDTO.get_1300().equals("true")) {
							horesDTOSecond.set_1300("true");
						} else if (hora.equals("1330") && nextHour <= 1330 && horesDTO.get_1330().equals("true")) {
							horesDTOSecond.set_1330("true");
						} else if (hora.equals("1400") && nextHour <= 1400 && horesDTO.get_1400().equals("true")) {
							horesDTOSecond.set_1400("true");
						} else if (hora.equals("1430") && nextHour <= 1430 && horesDTO.get_1430().equals("true")) {
							horesDTOSecond.set_1430("true");
						} else if (hora.equals("1500") && nextHour <= 1500 && horesDTO.get_1500().equals("true")) {
							horesDTOSecond.set_1500("true");
						} else if (hora.equals("1530") && nextHour <= 1530 && horesDTO.get_1530().equals("true")) {
							horesDTOSecond.set_1530("true");
						} else if (hora.equals("1600") && nextHour <= 1600 && horesDTO.get_1600().equals("true")) {
							horesDTOSecond.set_1600("true");
						} else if (hora.equals("1630") && nextHour <= 1630 && horesDTO.get_1630().equals("true")) {
							horesDTOSecond.set_1630("true");
						} else if (hora.equals("1700") && nextHour <= 1700 && horesDTO.get_1700().equals("true")) {
							horesDTOSecond.set_1700("true");
						} else if (hora.equals("1730") && nextHour <= 1730 && horesDTO.get_1730().equals("true")) {
							horesDTOSecond.set_1730("true");
						} else if (hora.equals("1800") && nextHour <= 1800 && horesDTO.get_1800().equals("true")) {
							horesDTOSecond.set_1800("true");
						} else if (hora.equals("1830") && nextHour <= 1830 && horesDTO.get_1830().equals("true")) {
							horesDTOSecond.set_1830("true");
						} else if (hora.equals("1900") && nextHour <= 1900 && horesDTO.get_1900().equals("true")) {
							horesDTOSecond.set_1900("true");
						} else if (hora.equals("1930") && nextHour <= 1930 && horesDTO.get_1930().equals("true")) {
							horesDTOSecond.set_1930("true");
						} else if (hora.equals("2000") && nextHour <= 2000 && horesDTO.get_2000().equals("true")) {
							horesDTOSecond.set_2000("true");
						} else if (hora.equals("2030") && nextHour <= 2030 && horesDTO.get_2030().equals("true")) {
							horesDTOSecond.set_2030("true");
						} else if (hora.equals("2100") && nextHour <= 2100 && horesDTO.get_2100().equals("true")) {
							horesDTOSecond.set_2100("true");
						} else if (hora.equals("2130") && nextHour <= 2130 && horesDTO.get_2130().equals("true")) {
							horesDTOSecond.set_2130("true");
						} else if (hora.equals("2200") && nextHour <= 2200 && horesDTO.get_2200().equals("true")) {
							horesDTOSecond.set_2200("true");
						} else if (hora.equals("2230") && nextHour <= 2230 && horesDTO.get_2230().equals("true")) {
							horesDTOSecond.set_2230("true");
						} else if (hora.equals("2300") && nextHour <= 2300 && horesDTO.get_2300().equals("true")) {
							horesDTOSecond.set_2300("true");
						} else if (hora.equals("2330") && nextHour <= 2330 && horesDTO.get_2330().equals("true")) {
							horesDTOSecond.set_2330("true");
						} else if (hora.equals("2400") && nextHour <= 2400 && horesDTO.get_2400().equals("true")) {
							horesDTOSecond.set_2400("true");
						}
					}
					horesDTO = horesDTOSecond;
				}
				secondRestaurant=true;
			}
		}
		if(aDomicili){
			List<Moters> motersList =  this.motersBo.load(Utils.getDate2(data));
			if(motersList==null || motersList.isEmpty()){
				horesDTO= new HoresDTO();
				
			}else{
				for(Moters moter : motersList){
					int numMotersAvailable = 0;
					if(moter.getNumeroMoters()==null || moter.getNumeroMoters()==0){
						numMotersAvailable= 0;  
					}else{
						numMotersAvailable= moter.getNumeroMoters()-(moter.getNumeroMotersUsed()==null? 0:moter.getNumeroMotersUsed());  
					}
					
					if(numMotersAvailable<=0)continue;
					
					if (moter.getHora().equals("")) {
						
					} else if (moter.getHora().equals("0800") &&  horesDTO.get_0800().equals("true") ) {
						horesDTOMoters.set_0800("true");
					} else if (moter.getHora().equals("0830") &&  horesDTO.get_0830().equals("true")) {
						horesDTOMoters.set_0830("true");
					} else if (moter.getHora().equals("0900") &&  horesDTO.get_0900().equals("true")) {
						horesDTOMoters.set_0900("true");
					} else if (moter.getHora().equals("0930") &&  horesDTO.get_0930().equals("true")) {
						horesDTOMoters.set_0930("true");
					} else if (moter.getHora().equals("1000") &&  horesDTO.get_1000().equals("true")) {
						horesDTOMoters.set_1000("true");
					} else if (moter.getHora().equals("1030") &&  horesDTO.get_1030().equals("true")) {
						horesDTOMoters.set_1030("true");
					} else if (moter.getHora().equals("1100") &&  horesDTO.get_1100().equals("true")) {
						horesDTOMoters.set_1100("true");
					} else if (moter.getHora().equals("1130") &&  horesDTO.get_1130().equals("true")) {
						horesDTOMoters.set_1130("true");
					} else if (moter.getHora().equals("1200") &&  horesDTO.get_1200().equals("true")) {
						horesDTOMoters.set_1200("true");
					} else if (moter.getHora().equals("1230") &&  horesDTO.get_1230().equals("true")) {
						horesDTOMoters.set_1230("true");
					} else if (moter.getHora().equals("1300") &&  horesDTO.get_1300().equals("true")) {
						horesDTOMoters.set_1300("true");
					} else if (moter.getHora().equals("1330") &&  horesDTO.get_1330().equals("true")) {
						horesDTOMoters.set_1330("true");
					} else if (moter.getHora().equals("1400") &&  horesDTO.get_1400().equals("true")) {
						horesDTOMoters.set_1400("true");
					} else if (moter.getHora().equals("1430") &&  horesDTO.get_1430().equals("true")) {
						horesDTOMoters.set_1430("true");
					} else if (moter.getHora().equals("1500") &&  horesDTO.get_1500().equals("true")) {
						horesDTOMoters.set_1500("true");
					} else if (moter.getHora().equals("1530") &&  horesDTO.get_1530().equals("true")) {
						horesDTOMoters.set_1530("true");
					} else if (moter.getHora().equals("1600") &&  horesDTO.get_1600().equals("true")) {
						horesDTOMoters.set_1600("true");
					} else if (moter.getHora().equals("1630") &&  horesDTO.get_1630().equals("true")) {
						horesDTOMoters.set_1630("true");
					} else if (moter.getHora().equals("1700") &&  horesDTO.get_1700().equals("true")) {
						horesDTOMoters.set_1700("true");
					} else if (moter.getHora().equals("1730") &&  horesDTO.get_1730().equals("true")) {
						horesDTOMoters.set_1730("true");
					} else if (moter.getHora().equals("1800") &&  horesDTO.get_1800().equals("true")) {
						horesDTOMoters.set_1800("true");
					} else if (moter.getHora().equals("1830") &&  horesDTO.get_1830().equals("true")) {
						horesDTOMoters.set_1830("true");
					} else if (moter.getHora().equals("1900") &&  horesDTO.get_1900().equals("true")) {
						horesDTOMoters.set_1900("true");
					} else if (moter.getHora().equals("1930") &&  horesDTO.get_1930().equals("true")) {
						horesDTOMoters.set_1930("true");
					} else if (moter.getHora().equals("2000") &&  horesDTO.get_2000().equals("true")) {
						horesDTOMoters.set_2000("true");
					} else if (moter.getHora().equals("2030") &&  horesDTO.get_2030().equals("true")) {
						horesDTOMoters.set_2030("true");
					} else if (moter.getHora().equals("2100") &&  horesDTO.get_2100().equals("true")) {
						horesDTOMoters.set_2100("true");
					} else if (moter.getHora().equals("2130") &&  horesDTO.get_2130().equals("true")) {
						horesDTOMoters.set_2130("true");
					} else if (moter.getHora().equals("2200") &&  horesDTO.get_2200().equals("true")) {
						horesDTOMoters.set_2200("true");
					} else if (moter.getHora().equals("2230") &&  horesDTO.get_2230().equals("true")) {
						horesDTOMoters.set_2230("true");
					} else if (moter.getHora().equals("2300") &&  horesDTO.get_2300().equals("true")) {
						horesDTOMoters.set_2300("true");
					} else if (moter.getHora().equals("2330") &&  horesDTO.get_2330().equals("true")) {
						horesDTOMoters.set_2330("true");
					} else if (moter.getHora().equals("2400") &&  horesDTO.get_2400().equals("true")) {
						horesDTOMoters.set_2400("true");
					}
				}
				horesDTO= horesDTOMoters;
			}
		}
		horesDTO.setData(dataToLoad);
		return horesDTO;
	}

	public void deleteBegudesPromo( Comandes comanda ) throws ComandaException{

		try {
			List<BegudaComanda> begudaComandaList = comanda.getBegudes();
			List<BegudaComanda> newBegudaComandaList = new ArrayList<BegudaComanda>();
			for (BegudaComanda begudaComanda : begudaComandaList) {
				begudaComanda.setNumBegudesPromo(0);
				newBegudaComandaList.add(begudaComanda);

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
			
			int actualHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
			Date diaAvui = new Date();
			
			List<PromocioNumComandes> promoNumComandesFinalList = new ArrayList<PromocioNumComandes>();
			List<PromocioAPartirDe> apartirDePromoFinalList = new ArrayList<PromocioAPartirDe>();

			List<PromocioAPartirDe> apartirDePromoList = this.promocionsBo.getPromosAPartirDe(comanda.getPreu(), null,true);			
			List<PromocioNumComandes> promoNumComandesList = this.promocionsBo.getPromosNumComandes(null, null, true);
			
			for (PromocioAPartirDe promo : apartirDePromoList) {
				int numUsed = promo.getNumUsed()==null?0:promo.getNumUsed();
				if(promo.getNumUses()!=null && promo.getNumUses()<=numUsed) continue;
				
				if(comanda.getDia()==null) comanda.setDia(diaAvui);
				if(comanda.getDia()!=null && Utils.formatDate2(comanda.getDia()).equals(Utils.formatDate2(diaAvui)) && actualHour>16 && promo.getHora()!=null && promo.getHora()==true){
					continue;					
				}
				if(!promo.checkDayOfWeekOpen(dayOfWeek)) continue;
				
					apartirDePromoFinalList.add(promo);
			}
			
			for (PromocioNumComandes promo : promoNumComandesList) {
				int numUsed = promo.getNumUsed()==null?0:promo.getNumUsed();
				if(promo.getNumUses()!=null && promo.getNumUses()<=numUsed) continue;
				if(comanda.getDia()==null) comanda.setDia(diaAvui);
				if(comanda.getDia()!=null && Utils.formatDate2(comanda.getDia()).equals(Utils.formatDate2(diaAvui)) && actualHour>16 && promo.getHora()!=null && promo.getHora()==true){
					continue;					
				}
				if(!promo.checkDayOfWeekOpen(dayOfWeek)) continue;
				
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
				
				//Control plat inactiu
				if(Utils.formatDate2(dia).equals(Utils.formatDate2(new Date()))){
					List<PlatComanda> listPlats = comanda.getPlats();
					for(PlatComanda platComanda :  listPlats){
						if(!platComanda.getPlat().isActiu()){
							Alerta alert= new Alerta(this.resource.getString("txt.infocomanda.comandako.plat.inactiu"));
							return alert.getJSON();
						}
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

			Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().serializeNulls().create();
			StringBuffer json = new StringBuffer(gson.toJson(listBeguda));
			return json.toString();

		} catch (Exception e) {
			throw new ComandaException(e, "Error creating JSON of beguda list");
		}
	}
	public List<BegudaComanda> removeBegudaInList(List<BegudaComanda> begudaList, Beguda beguda,boolean promo) throws ComandaException{
		
		try {
			List<BegudaComanda> newbegudaList= new ArrayList<BegudaComanda>();
			
			if (begudaList.size() > 0) {
				for (BegudaComanda bg : begudaList) {
					if (bg.getBeguda().getId().equals(beguda.getId())) {
						if (promo == true) {
							bg.setNumBegudesPromo(bg.getNumBegudesPromo() -1 );
						} else {
							bg.setNumBegudes(bg.getNumBegudes() -1);
						}	
						if((bg.getNumBegudes()!=null && bg.getNumBegudes()>0) || (bg.getNumBegudesPromo()!=null && bg.getNumBegudesPromo()>0)){
							newbegudaList.add(bg);
						}
					}
				}
			}
			
			return newbegudaList;

		} catch (Exception e) {
			throw new ComandaException(e, "Error adding beguda in list");
		}
	}
	
	public List<BegudaComanda> removeAllBegudaInList(List<BegudaComanda> begudaList, Beguda beguda) throws ComandaException{
		
		try {
			List<BegudaComanda> newbegudaList= new ArrayList<BegudaComanda>();
			if (begudaList.size() > 0) {
				for (BegudaComanda bg : begudaList) {
					if (!bg.getBeguda().getId().equals(beguda.getId())) {
						newbegudaList.add(bg);			
					}else{
						Double preuToRest = bg.getNumBegudes()*bg.getBeguda().getPreu();
						this.jsonBegudaDeleted = "{\"numBegudes\": \""+bg.getNumBegudes()+"\", \"preuToRest\": \""+preuToRest+"\"}";
						if(bg.getNumBegudes()!=null && bg.getNumBegudes()!=0){							
							bg.setNumBegudes(0);									
						}
						if(bg.getNumBegudesPromo()!=null && bg.getNumBegudesPromo()!=0){
							newbegudaList.add(bg);
						}
					}
				}
			}
			
			return newbegudaList;

		} catch (Exception e) {
			throw new ComandaException(e, "Error adding beguda in list");
		}
	}
	
	public String removeAllBegudaInListGetJson(){
		return this.jsonBegudaDeleted;		
	}
	
	public String getListOfPlatsAndDrinks( Comandes comanda){
		
		StringBuffer noms = new StringBuffer();
		List<BegudaComanda> begudaList=comanda.getBegudes();
		List<PlatComanda> platList = comanda.getPlats();
		for(PlatComanda plat : platList){
			
			noms.append(plat.getPlat().getNom()+"("+plat.getNumPlats()+")  ");
			
		}
		
		for(BegudaComanda beguda : begudaList ){
			if(beguda.getNumBegudes()!=null && beguda.getNumBegudes()!=0)
				noms.append(beguda.getBeguda().getNom()+"("+beguda.getNumBegudes()+")");
		}
		
		return noms.toString();
		
	}
	
	public List<BegudaComanda> addBegudaInList( List<BegudaComanda> begudaList, Beguda beguda, boolean promo ) throws ComandaException{

		try {
			boolean existInList = false;
			if (begudaList.size() > 0) {
				for (BegudaComanda bg : begudaList) {
					if (bg.getBeguda().getId().equals(beguda.getId())) {
						if (promo == true) {
							bg.setNumBegudesPromo(bg.getNumBegudesPromo() + 1);
						} else {
							bg.setNumBegudes(bg.getNumBegudes() + 1);
						}
						existInList = true;
					}
				}
			}
			if (!existInList) {
				BegudaComanda begudaComanda = new BegudaComanda();
				begudaComanda.setPromo(promo);// CANVI
				begudaComanda.setBeguda(beguda);
				if (promo == true) {
					begudaComanda.setNumBegudesPromo(1);
					begudaComanda.setNumBegudes(0);
				} else {
					begudaComanda.setNumBegudesPromo(0);
					begudaComanda.setNumBegudes(1);
				}
				//begudaComanda.setNumBegudes(1);
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

	public String createJSONForShoppingCart( List<PlatComanda> platList, Long id, ResourceBundle resource,String locale ) throws ComandaException{
		
		this.resource=resource;
		
		try {
			Double preuComanda = 0.0;
			Integer numPlats = 0;
			List<PlatComandaCart> platComandaCartList = new ArrayList<PlatComandaCart>();
			for (PlatComanda pl : platList) {
				
				preuComanda = preuComanda + (pl.getPlat().getPreu() * pl.getNumPlats());
				numPlats = numPlats + pl.getNumPlats();
				String nomPlat=Utils.escapeUTF(pl.getPlat().getNom());
				if(!locale.equals("ca")){
					nomPlat=Utils.escapeUTF(pl.getPlat().getNomES());
				}
				
				if(!pl.getPlat().isActiu()){
					nomPlat = nomPlat+"<font color=\"red\" >"+this.resource.getString("txt.plat.no.actiu")+"</font>";
				}
				PlatComandaCart platComandaCart = new PlatComandaCart(pl.getNumPlats(),pl.getPlat().getId(),nomPlat);
				platComandaCartList.add(platComandaCart);
			}

			ComandaCart comandaCart = new ComandaCart(String.valueOf(preuComanda), platComandaCartList, String.valueOf(numPlats), id);

			String json =createJSONComandaCart(comandaCart);		

			return json;

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

	public class PlatComandaCart{
		
		@Expose
		private Integer				numPlats;
		
		@Expose
		private Long				idPlat;		
		
		@Expose
		private String				nomPlat;	
				
		public PlatComandaCart( Integer numPlats, Long idPlat, String nomPlat ) {

			super();
			this.numPlats = numPlats;
			this.idPlat = idPlat;
			this.nomPlat= nomPlat;
		}
		

		public Integer getNumPlats(){
		
			return numPlats;
		}

		public void setNumPlats( Integer numPlats ){
		
			this.numPlats = numPlats;
		}

		public Long getIdPlat(){
		
			return idPlat;
		}

		public void setIdPlat( Long idPlat ){
		
			this.idPlat = idPlat;
		}


		public String getNomPlat(){
		
			return nomPlat;
		}


		public void setNomPlat( String nomPlat ){
		
			this.nomPlat = nomPlat;
		}
		
		
	}
	public class Alerta{
		
		@Expose
		private String				alerta		= "";

						
		public Alerta(String alerta) {
			this.alerta = alerta;
		}

		public String getAlerta() {
			return alerta;
		}

		public void setAlerta(String alerta) {
			this.alerta = alerta;
		}
		
		public String getJSON(){
			Alerta alert = new Alerta(this.alerta);
			Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
			StringBuffer json = new StringBuffer(gson.toJson(alert));
			
			return json.toString();
			
		}
		
		
	}
	
	public class ComandaCart{

		@Expose
		private String				preu		= "0.0";

		@Expose
		private Long				numComanda;
		
		@Expose
		private List<PlatComandaCart>	platsNames	= new ArrayList<PlatComandaCart>();

		@Expose
		private String				numPlats	= "0";

		public ComandaCart( String preu, List<PlatComandaCart> platsNames, String numPlats, Long numComanda ) {

			this.preu = preu;
			this.platsNames = platsNames;
			this.numPlats = numPlats;
			this.numComanda = numComanda;
		}

		public String getPreu(){

			return preu;
		}

		public void setPreu( String preu ){

			this.preu = preu;
		}

		public List<PlatComandaCart> getPlatsNames(){

			return platsNames;
		}

		public void setPlatsNames( List<PlatComandaCart> platsNames ){

			this.platsNames = platsNames;
		}

		public String getNumPlats(){

			return numPlats;
		}

		public void setNumPlats( String numPlats ){

			this.numPlats = numPlats;
		}

		public Long getNumComanda(){
		
			return numComanda;
		}

		public void setNumComanda( Long numComanda ){
		
			this.numComanda = numComanda;
		}

	}

	// PRIVATE
	private String createJSONComandaCart( ComandaCart comandacart){

		Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		StringBuffer json = new StringBuffer(gson.toJson(comandacart));
		
		return json.toString();
	}

	private Set<Restaurant> getRestaurants( Comandes comanda ){

		int temps = 0;
		List<PlatComanda> platComandaList = comanda.getPlats();

		Set<Restaurant> restaurantSet = new HashSet<Restaurant>();
		for (PlatComanda platComanda : platComandaList) {
			restaurantSet.add(platComanda.getPlat().getRestaurants().iterator().next());
		}
		return restaurantSet;

	}

	public int calculaTempsPreparacioGlobal( Comandes comanda ){ 

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
			if (configRestaurant == null) {

				String hores = restaurant.getHores();
				if (!hores.contains(hora)) {
					return this.resource.getString("txt.restaurant.no.obert") + " " + restaurant.getNom();
				}
			} else if (configRestaurant != null && configRestaurant.isObert()) {
				String hores = configRestaurant.getHores();
				if (!hores.contains(hora)) {
					return this.resource.getString("txt.restaurant.no.obert") + " " + restaurant.getNom();
				}

			} else if (configRestaurant != null && !configRestaurant.isObert()) {
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

	public void setPlatsBo( PlatsBo platsBo ){
	
		this.platsBo = platsBo;
	}
}
