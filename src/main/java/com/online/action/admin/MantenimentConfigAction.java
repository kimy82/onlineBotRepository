package com.online.action.admin;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javassist.Modifier;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.online.bo.ConfigRestaurantBo;
import com.online.bo.MotersBo;
import com.online.bo.RestaurantsBo;
import com.online.exceptions.BOException;
import com.online.exceptions.GeneralException;
import com.online.model.ConfigRestaurant;
import com.online.model.Moters;
import com.online.model.Restaurant;
import com.online.pojos.Basic;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class MantenimentConfigAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{

	private RestaurantsBo		restaurantsBo;
	private MotersBo			motersBo;
	private ConfigRestaurantBo	configRestaurantBo;

	private String				dia;
	private String				idRestaurants;
	private Integer				idRestaurant		= null;
	private Moters				moter				= new Moters();
	private ConfigRestaurant	configRestaurant	= new ConfigRestaurant();

	private List<Basic>			restaurantBasicList	= new LinkedList<Basic>();

	HttpServletResponse			response;
	HttpServletRequest			request;

	public String execute(){

		loadRestaurantsBasicList();
		return SUCCESS;

	}

	public String saveConfig(){

		try {
			if (this.moter != null && this.moter.getNumeroMoters() != null && this.configRestaurant != null) {

				if (!this.idRestaurants.isEmpty()) {
					String[] stringRestaurants = this.idRestaurants.split(",");
					for (String idStringRestaurant : stringRestaurants) {
						Moters moterToSave = new Moters();
						Restaurant restaurant = this.restaurantsBo.load(Integer.parseInt(idStringRestaurant.trim()), false, true, true);

						Date date = getDate(this.dia);
						moterToSave.setData(date);
						moterToSave.setIdRestaurant(Integer.parseInt(idStringRestaurant));
						moterToSave.setNumeroMoters(this.moter.getNumeroMoters());

						Moters moterInDB = this.motersBo.load(date, Integer.parseInt(idStringRestaurant));

						if (moterInDB == null) {
							this.motersBo.save(moterToSave);
							Set<Moters> moters = restaurant.getMoters();
							moters.add(moterToSave);
						} else {
							moterInDB.setNumeroMoters(moterToSave.getNumeroMoters());
							this.motersBo.update(moterInDB);
						}

						ConfigRestaurant configRestToSave = new ConfigRestaurant();
						configRestToSave.setData(date);
						configRestToSave.setObert(this.configRestaurant.isObert());
						configRestToSave.setIdRestaurant(Integer.parseInt(idStringRestaurant));

						ConfigRestaurant configRestaurantInDB = this.configRestaurantBo.load(date, Integer.parseInt(idStringRestaurant));
						if (configRestaurantInDB == null) {
							this.configRestaurantBo.save(configRestToSave);
							Set<ConfigRestaurant> configRestaurants = restaurant.getConfigRestaurants();
							configRestaurants.add(configRestToSave);
						} else {
							configRestaurantInDB.setObert(configRestToSave.isObert());
							this.configRestaurantBo.update(configRestaurantInDB);
						}

						if (configRestaurantInDB == null || moterInDB == null) {
							this.restaurantsBo.update(restaurant);
						}

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
				json.append(createEmptyJSON());
			} else {
				Date date = getDate(this.dia);
				Moters moter = this.motersBo.load(date, this.idRestaurant);
				ConfigRestaurant connfigrestaurant = this.configRestaurantBo.load(date, this.idRestaurant);
				if (moter == null || connfigrestaurant == null) {
					json = new StringBuffer(createEmptyJSON());
				} else {
					Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
					json.append(gson.toJson(moter));
					json.setLength(json.length() - 1);
					json.append(",");
					StringBuffer jsonconfig = new StringBuffer(gson.toJson(connfigrestaurant));
					json.append(jsonconfig.substring(1));
				}

			}
		} catch (Exception e) {
			json.append(createErrorJSON("error in ajax action"));
		}

		try {
			out.print(json.toString());
		} catch (IOException e) {
			throw new GeneralException(e, "possibly ServletOutputStream null");
		}
		return null;
	}

	// private methods

	private void loadRestaurantsBasicList(){

		List<Restaurant> restaurantList = this.restaurantsBo.getAll();

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

	private String createErrorJSON( String error ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"error\":\"" + error + "\"");
		jsonSB.append("}");
		return jsonSB.toString();
	}

	private String createEmptyJSON(){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("}");
		return jsonSB.toString();
	}

	private Date getDate( String dia ) throws RuntimeException{

		String[] diaS = dia.split("-");
		if (diaS.length != 3) {
			throw new RuntimeException("wrong dia");
		}

		String numDia = diaS[0];
		String month = diaS[1];
		String year = diaS[2];

		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(numDia));
		return calendar.getTime();

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

	public void setMotersBo( MotersBo motersBo ){

		this.motersBo = motersBo;
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

	public void setMoter( Moters moter ){

		this.moter = moter;
	}

	public void setConfigRestaurant( ConfigRestaurant configRestaurant ){

		this.configRestaurant = configRestaurant;
	}

	public void setServletResponse( HttpServletResponse response ){

		this.response = response;
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

	public Integer getIdRestaurant(){

		return idRestaurant;
	}

	public void setIdRestaurant( Integer idRestaurant ){

		this.idRestaurant = idRestaurant;
	}

}