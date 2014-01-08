package com.online.action.admin;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.online.bo.ConfigRestaurantBo;
import com.online.bo.RestaurantsBo;
import com.online.exceptions.BOException;
import com.online.exceptions.GeneralException;
import com.online.model.ConfigRestaurant;
import com.online.model.Restaurant;
import com.online.pojos.Basic;
import com.online.supplier.extend.ActionSuportOnline;
import com.online.utils.Utils;
import com.opensymphony.xwork2.Action;

public class MantenimentConfigAction extends ActionSuportOnline{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private RestaurantsBo		restaurantsBo;
	private ConfigRestaurantBo	configRestaurantBo;

	private String				dia;
	private String				idRestaurants;
	private Integer				idRestaurant		= null;
	private ConfigRestaurant	configRestaurant;

	private List<Basic>			restaurantBasicList	= new LinkedList<Basic>();

	public String execute(){

		loadRestaurantsBasicList();
		return SUCCESS;

	}

	public String saveConfig(){

		try {
			if (this.configRestaurant != null) {

				if (!this.idRestaurants.isEmpty()) {
					String[] stringRestaurants = this.idRestaurants.split(",");
					for (String idStringRestaurant : stringRestaurants) {

						Restaurant restaurant = this.restaurantsBo.load(Integer.parseInt(idStringRestaurant), false, true, true);

						Date date = Utils.getDate(this.dia);

						ConfigRestaurant configRestToSave = new ConfigRestaurant();
						configRestToSave.setData(date);
						configRestToSave.setObert(this.configRestaurant.isObert());
						configRestToSave.setHores(this.configRestaurant.getHores());
						configRestToSave.setIdRestaurant(Integer.parseInt(idStringRestaurant));

						saveConfig(restaurant, date, configRestToSave);
						saveLastObert(restaurant);

					}
				}
			}

			loadRestaurantsBasicList();

		} catch (NumberFormatException nfe) {
			addActionError(nfe.getMessage());
			return ERROR;
		} catch (BOException boe) {
			addActionError(boe.getMessage());
			return ERROR;
		} catch (RuntimeException e) {
			addActionError(e.getMessage());
			return Action.ERROR;
		} catch (Exception e) {
			addActionError("Error unKnown");
			return Action.ERROR;
		}
		return Action.SUCCESS;

	}

	public String loadMotersAndConfig(){

		ServletOutputStream out = null;
		this.idRestaurant = (request.getParameter("id") != null && !request.getParameter("id").equals("")) ? Integer.parseInt(request
				.getParameter("id")) : null;
		this.dia = request.getParameter("dia");

		StringBuffer json = new StringBuffer("");
		try {
			out = this.response.getOutputStream();
			if (this.idRestaurant == null || this.dia == null || this.dia.equals("")) {
				json.append(Utils.createEmptyJSON());
			} else {
				Date date = Utils.getDate(this.dia);
				ConfigRestaurant connfigrestaurant = this.configRestaurantBo.load(date, this.idRestaurant);
				if (connfigrestaurant == null || connfigrestaurant.getHores() == null || connfigrestaurant.getHores().equals("")) {
					Restaurant restaurant = this.restaurantsBo.load(idRestaurant, false, false, false);

					connfigrestaurant = new ConfigRestaurant();
					connfigrestaurant.setObert(true);
					connfigrestaurant.setHores(restaurant.getHores());

				}

				Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
				json = new StringBuffer(gson.toJson(connfigrestaurant));

			}
		} catch (Exception e) {
			json.append(Utils.createErrorJSON("error in ajax action"));
		}

		try {
			out.print(json.toString());
		} catch (IOException e) {
			throw new GeneralException(e, "possibly ServletOutputStream null");
		}
		return null;
	}

	// private methods

	private void saveLastObert( Restaurant restaurant ){

		Restaurant restaurantBis = this.restaurantsBo.load(restaurant.getId(), false, true, true);
		Set<ConfigRestaurant> setconfig = restaurantBis.getConfigRestaurants();
		if (setconfig.isEmpty())
			return;


		Date lastClosed=null;
		StringBuffer datasClosedSB = new StringBuffer("");
		for (ConfigRestaurant cnf : setconfig) {
			if (!cnf.isObert()){
				datasClosedSB.append(Utils.formatDate2(cnf.getData()));
				lastClosed=cnf.getData();
			}
				
				
		}

		String datasClosed = datasClosedSB.toString();
		if(datasClosed.equals(""))
			return;
		
			Calendar calendar = Calendar.getInstance();
			boolean continueLoop = true;
			int i = 0;
			int day = calendar.get(Calendar.DAY_OF_YEAR);
			
			while (continueLoop) {
				Calendar calendarToCheck = Calendar.getInstance();
				calendarToCheck.set(Calendar.DAY_OF_YEAR, day+i);
				Date dataToCheck = calendarToCheck.getTime();
				i++;
				if (!datasClosed.contains(Utils.formatDate2(dataToCheck))) {

					ConfigRestaurant configRestToSave = new ConfigRestaurant();
					configRestToSave.setData(dataToCheck);
					configRestToSave.setObert(true);
					configRestToSave.setHores(this.configRestaurant.getHores());
					configRestToSave.setIdRestaurant(restaurantBis.getId());
					saveConfig(restaurantBis, dataToCheck, configRestToSave);
				}
				if(lastClosed==null || i>35){
					continueLoop = false;
				}

				if(Utils.formatDate2(dataToCheck).equals(Utils.formatDate2(lastClosed))){
					Calendar lastObert = Calendar.getInstance();
					lastObert.setTime(lastClosed);
					int dayOfYear = lastObert.get(Calendar.DAY_OF_YEAR);
					lastObert.set(Calendar.DAY_OF_YEAR, dayOfYear+1);
					ConfigRestaurant configRestToSave = new ConfigRestaurant();
					configRestToSave.setData(lastObert.getTime());
					configRestToSave.setObert(true);
					configRestToSave.setHores(this.configRestaurant.getHores());
					configRestToSave.setIdRestaurant(restaurantBis.getId());

					saveConfig(restaurantBis, lastObert.getTime(), configRestToSave);	
					
					continueLoop = false;
				}
			}
			restaurantBis = this.restaurantsBo.load(restaurant.getId(), false, true, true);
			setconfig = restaurantBis.getConfigRestaurants();
			if (setconfig.isEmpty())
				return;
			
			Iterator<ConfigRestaurant> itera = setconfig.iterator();
			Date diaAvui = new Date();
			Set<ConfigRestaurant> confRestaurantNew = new HashSet<ConfigRestaurant>();
			Set<ConfigRestaurant> confRestaurantToDelete = new HashSet<ConfigRestaurant>();
			
			while(itera.hasNext()){
				ConfigRestaurant cr= itera.next();
				Calendar calConfig = Calendar.getInstance();
				calConfig.setTime(cr.getData());
				Calendar calAvui = Calendar.getInstance();
				calAvui.setTime(diaAvui);
				int dayConfig = calConfig.get(Calendar.DAY_OF_YEAR);
				int dayAvui = calAvui.get(Calendar.DAY_OF_YEAR);
				int yearAvui = calAvui.get(Calendar.YEAR);
				int yearConfig = calConfig.get(Calendar.YEAR);
				if(dayConfig>=dayAvui && yearAvui == yearConfig){
					confRestaurantNew.add(cr);
				}else if(dayConfig<dayAvui && yearAvui == yearConfig){
					confRestaurantToDelete.add(cr);
				}else if(yearAvui < yearConfig){
					confRestaurantToDelete.add(cr);
				}
			}
			
			restaurant.setConfigRestaurants(confRestaurantNew);
			restaurantsBo.update(restaurant);
			for(ConfigRestaurant config : confRestaurantToDelete){
				configRestaurantBo.delete(config);
			}
	}

	private void saveConfig( Restaurant restaurant, Date date, ConfigRestaurant configRestToSave ){

		ConfigRestaurant configRestaurantInDB = this.configRestaurantBo.load(date, restaurant.getId());
		if (configRestaurantInDB == null) {
			this.configRestaurantBo.save(configRestToSave);
			Set<ConfigRestaurant> configRestaurants = restaurant.getConfigRestaurants();
			configRestaurants.add(configRestToSave);
		} else {
			configRestaurantInDB.setObert(configRestToSave.isObert());
			configRestaurantInDB.setHores(configRestToSave.getHores());
			this.configRestaurantBo.update(configRestaurantInDB);
		}

		if (configRestaurantInDB == null) {
			this.restaurantsBo.update(restaurant);
		}

	}

	private void loadRestaurantsBasicList(){

		List<Restaurant> restaurantList = this.restaurantsBo.getAll(true, true, true);

		if (restaurantList != null) {
			for (Restaurant restaurant : restaurantList) {
				Basic basic = new Basic();
				basic.setDescripcio(restaurant.getNom());
				basic.setId(restaurant.getId());
				restaurantBasicList.add(basic);
			}
		}
		Collections.sort(restaurantBasicList);
	}

	// Getters i setters

	public void setRestaurantsBo( RestaurantsBo restaurantsBo ){

		this.restaurantsBo = restaurantsBo;
	}

	public List<Basic> getRestaurantBasicList(){

		return restaurantBasicList;
	}

	public void setRestaurantBasicList( List<Basic> restaurantBasicList ){

		this.restaurantBasicList = restaurantBasicList;
	}

	public String getIdRestaurants(){

		return idRestaurants;
	}

	public void setIdRestaurants( String idRestaurants ){

		this.idRestaurants = idRestaurants;
	}

	public void setConfigRestaurantBo( ConfigRestaurantBo configRestaurantBo ){

		this.configRestaurantBo = configRestaurantBo;
	}

	public String getDia(){

		return dia;
	}

	public void setDia( String dia ){

		this.dia = dia;
	}

	public void setConfigRestaurant( ConfigRestaurant configRestaurant ){

		this.configRestaurant = configRestaurant;
	}

	public ConfigRestaurant getConfigRestaurant(){

		return configRestaurant;
	}

	public Integer getIdRestaurant(){

		return idRestaurant;
	}

	public void setIdRestaurant( Integer idRestaurant ){

		this.idRestaurant = idRestaurant;
	}

}