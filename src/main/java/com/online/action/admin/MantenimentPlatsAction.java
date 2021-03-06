package com.online.action.admin;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;

import com.online.bo.PlatsBo;
import com.online.bo.RestaurantsBo;
import com.online.exceptions.BOException;
import com.online.exceptions.GeneralException;
import com.online.model.Image;
import com.online.model.Plat;
import com.online.model.Restaurant;
import com.online.pojos.Basic;
import com.online.supplier.extend.ActionSuportOnline;
import com.online.utils.Constants;
import com.online.utils.Utils;
import com.opensymphony.xwork2.Action;

public class MantenimentPlatsAction extends ActionSuportOnline{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private Plat				plat				= new Plat();
	private PlatsBo				platsBo;
	private RestaurantsBo		restaurantsBo;
	private Integer				idRestaurant;
	private String				idRestaurants;
	private Long				idPlat				= null;
	private Integer				prioritat			= 0;

	private List<Basic>			restaurantBasicList	= new LinkedList<Basic>();
	private List<Basic>			tipusPlat			= new LinkedList<Basic>();

	public String execute(){

		try {
			inizializeIdPlat();
			this.plat = this.platsBo.load(this.idPlat, false);

			List<Restaurant> restaurantList = this.restaurantsBo.getAll(true, true, true);
			initRestaurantsBasicList(restaurantList);
			initTipusPlat();

			this.idRestaurants = this.plat.getRestaurants().iterator().next().getId().toString();
		} catch (BOException boe) {
			addActionError(boe.getMessage());
			return ERROR;
		} catch (Exception e) {
			addActionError(e.getMessage());
			return ERROR;
		}
		return Action.SUCCESS;

	}

	public String consultaPlats(){

		try {
			List<Restaurant> restaurantList = this.restaurantsBo.getAll(true, true, true);

			initRestaurantsBasicList(restaurantList);
			initTipusPlat();

		} catch (BOException boe) {
			addActionError(boe.getMessage());
			return ERROR;
		} catch (Exception e) {
			addActionError(e.getMessage());
			return ERROR;
		}
		return Action.SUCCESS;

	}

	public String ajaxChangePrioritatPlat(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();
			inizializeIdPlat();
			inizializePrioritat();
			this.platsBo.changePriority(idPlat, prioritat);
			json = "{\"estat\" : \"ok\"}";
		} catch (BOException boe) {
			json = Utils.createErrorJSON("error in ajax action: Error in BO");
		} catch (NumberFormatException e) {
			json = Utils.createErrorJSON("error in ajax action: wrong params");
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

	public String ajaxDeletePlatAction(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();

			inizializeParamsTODeletePlat();
			Plat plat = this.platsBo.load(this.idPlat, true);
			this.platsBo.delete(plat);

		} catch (BOException boe) {
			json = Utils.createErrorJSON("error in ajax action: Error in BO");
		} catch (NumberFormatException e) {
			json = Utils.createErrorJSON("error in ajax action: wrong params");
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

	public String saveNewPlat() throws BOException{

		try {
			if (this.plat != null) {

				if (!this.idRestaurants.isEmpty()) {
					String[] stringRestaurants = this.idRestaurants.split(",");
					for (String idStringRestaurant : stringRestaurants) {
						if (this.plat.getId() != null) {
							Plat platToSave = this.platsBo.load(this.plat.getId(), false);
							platToSave.setDescripcio(this.plat.getDescripcio());
							platToSave.setDescripcioES(this.plat.getDescripcioES());
							platToSave.setNom(this.plat.getNom());
							platToSave.setNomES(this.plat.getNomES());
							platToSave.setPreu(this.plat.getPreu());
							platToSave.setTipus(this.plat.getTipus());
							platToSave.setTempsPreparacio(this.plat.getTempsPreparacio());
							platToSave.setCodi(this.plat.getCodi());
							platToSave.setPrioritat(this.plat.getPrioritat());
							platToSave.setActiu(this.plat.isActiu());
							platToSave.setCeliacs(this.plat.isCeliacs());
							platToSave.setFruitsCecs(this.plat.isFruitsCecs());
							platToSave.setLactics(this.plat.isLactics());
							platToSave.setOus(this.plat.isOus());
							platToSave.setVegetarians(this.plat.isVegetarians());
							Image image = getImageFromUpload();
							if (image != null && image.getImage() != null)
								platToSave.setFoto(image);
							this.platsBo.update(platToSave);
						} else {
							Plat platToSave = new Plat();
							platToSave.setDescripcio(this.plat.getDescripcio());
							platToSave.setDescripcioES(this.plat.getDescripcioES());
							platToSave.setNom(this.plat.getNom());
							platToSave.setNomES(this.plat.getNomES());
							platToSave.setPreu(this.plat.getPreu());
							platToSave.setTipus(this.plat.getTipus());
							platToSave.setTempsPreparacio(this.plat.getTempsPreparacio());
							platToSave.setCodi(this.plat.getCodi());
							platToSave.setPrioritat(this.plat.getPrioritat());
							platToSave.setActiu(this.plat.isActiu());
							platToSave.setCeliacs(this.plat.isCeliacs());
							platToSave.setFruitsCecs(this.plat.isFruitsCecs());
							platToSave.setLactics(this.plat.isLactics());
							platToSave.setOus(this.plat.isOus());
							platToSave.setVegetarians(this.plat.isVegetarians());
							Image image = getImageFromUpload();
							platToSave.setFoto(image);
							this.platsBo.save(platToSave);
							Restaurant restaurant = this.restaurantsBo
									.load(Integer.parseInt(idStringRestaurant.trim()), true, false, false);
							Set<Plat> plats = restaurant.getPlats();
							plats.add(platToSave);
							this.restaurantsBo.update(restaurant);
						}
					}
				}
			}
		} catch (NumberFormatException nfe) {
			addActionError(nfe.getMessage());
			return ERROR;
		} catch (BOException boe) {
			addActionError(boe.getMessage());
			return ERROR;
		} catch (RuntimeException e) {
			addActionError("Error getting image");
			return Action.ERROR;
		} catch (Exception e) {
			addActionError("Error unKnown");
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}

	// private methods
	private void initTipusPlat(){

		this.tipusPlat.clear();
		this.tipusPlat.add(new Basic(1, Constants.TIPUS_PLAT_PRIMER));
		this.tipusPlat.add(new Basic(1, Constants.TIPUS_PLAT_SEGON));
		this.tipusPlat.add(new Basic(1, Constants.TIPUS_PLAT_POSTRE));

	}

	private void initRestaurantsBasicList( List<Restaurant> restaurantList ){

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

	private void inizializeParamsTODeletePlat() throws NumberFormatException{

		this.idPlat = (request.getParameter("idPlat") != null && !request.getParameter("idPlat").equals("")) ? Long.parseLong(request
				.getParameter("idPlat")) : null;
		this.idRestaurant = (request.getParameter("idRestaurant") != null && !request.getParameter("idRestaurant").equals("")) ? Integer
				.parseInt(request.getParameter("idRestaurant")) : null;
		if (idPlat == null || this.idRestaurant == null) {
			throw new NumberFormatException("Plat or restaurant id null");
		}

	}

	private void inizializeIdPlat() throws NumberFormatException{

		this.idPlat = (request.getParameter("idPlat") != null && !request.getParameter("idPlat").equals("")) ? Long.parseLong(request
				.getParameter("idPlat")) : null;

		if (idPlat == null) {
			throw new NumberFormatException("Plat or restaurant id null");
		}

	}

	private void inizializePrioritat() throws NumberFormatException{

		this.prioritat = (request.getParameter("prioritat") != null && !request.getParameter("prioritat").equals("")) ? Integer
				.parseInt(request.getParameter("prioritat")) : null;

		if (prioritat == null) {
			this.prioritat = 0;
		}

	}

	// Getters i setters
	public void setPlatsBo( PlatsBo platsBo ){

		this.platsBo = platsBo;
	}

	public Plat getPlat(){

		return plat;
	}

	public void setPlat( Plat plat ){

		this.plat = plat;
	}

	public String getFileUploadContentType(){

		return fileUploadContentType;
	}

	public void setFileUploadContentType( String fileUploadContentType ){

		this.fileUploadContentType = fileUploadContentType;
	}

	public String getFileUploadFileName(){

		return fileUploadFileName;
	}

	public void setFileUploadFileName( String fileUploadFileName ){

		this.fileUploadFileName = fileUploadFileName;
	}

	public File getFileUpload(){

		return fileUpload;
	}

	public void setFileUpload( File fileUpload ){

		this.fileUpload = fileUpload;
	}

	public void setRestaurantsBo( RestaurantsBo restaurantsBo ){

		this.restaurantsBo = restaurantsBo;
	}

	public List<Basic> getRestaurantBasicList(){

		return restaurantBasicList;
	}

	public void setRestaurantBasicList( List<Basic> restaurantBasicList ){

		this.restaurantBasicList = restaurantBasicList;
	}

	public Integer getIdRestaurant(){

		return idRestaurant;
	}

	public void setIdRestaurant( Integer idRestaurant ){

		this.idRestaurant = idRestaurant;
	}

	public String getIdRestaurants(){

		return idRestaurants;
	}

	public void setIdRestaurants( String idRestaurants ){

		this.idRestaurants = idRestaurants;
	}

	public List<Basic> getTipusPlat(){

		return tipusPlat;
	}

	public void setTipusPlat( List<Basic> tipusPlat ){

		this.tipusPlat = tipusPlat;
	}

}