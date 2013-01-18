package com.online.action.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javassist.Modifier;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.BeanUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.online.bo.RestaurantsBo;
import com.online.exceptions.BOException;
import com.online.exceptions.GeneralException;
import com.online.exceptions.ImageException;
import com.online.model.Image;
import com.online.model.Plat;
import com.online.model.Restaurant;
import com.online.pojos.PlatTable;
import com.online.pojos.RestaurantTable;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class MantenimentRestaurantsAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{


	

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private RestaurantsBo	restaurantsBo;
	private String			sEcho;
	private int				lenght			= 0;
	private int				inici			= 0;
	private String			sortDireccio	= null;
	HttpServletResponse		response;
	HttpServletRequest		request;
	private Integer			idRestaurant	= null;
	private Restaurant		restaurant		= new Restaurant();
	private File			fileUpload;
	private String			fileUploadContentType;
	private String			fileUploadFileName;

	public String execute(){

		return Action.SUCCESS;

	}

	public String consultaRestaurants(){

		return Action.SUCCESS;

	}

	public String saveRestaurant(){

		try {

			if (this.restaurant == null || this.restaurant.getId() == null) {
				addActionError("Error updating restaurant");
				return Action.ERROR;
			}
			Restaurant restaurant = this.restaurantsBo.load(this.restaurant.getId(),true,false,false);

			if (restaurant == null)
				return Action.SUCCESS;

			restaurant.setDescripcio(this.restaurant.getDescripcio());
			restaurant.setNom(this.restaurant.getNom());
			restaurant.setHores(this.restaurant.getHores());
			Image image = getImageFromUpload();
			restaurant.setFoto(image);

			this.restaurantsBo.update(restaurant);
		} catch (BOException boe) {
			addActionError(boe.getMessage());
			return ERROR;
		} catch (ImageException ime) {
			addActionError(ime.getMessage());
			return ERROR;
		} catch (Exception e) {
			addActionError(e.getMessage());
			return ERROR;
		}
		return Action.SUCCESS;

	}

	public String newRestaurant(){

		return Action.SUCCESS;

	}
	

	public String saveNewRestaurant(){

		try {
			if (this.restaurant == null) {
				addActionError("Error saving restaurant");
				return Action.ERROR;
			}

			Restaurant restaurant = new Restaurant();

			restaurant.setDescripcio(this.restaurant.getDescripcio());
			restaurant.setNom(this.restaurant.getNom());
			restaurant.setCodiMaquina(this.restaurant.getCodiMaquina());

			Image image = getImageFromUpload();
			restaurant.setFoto(image);

			this.restaurantsBo.save(restaurant);
		} catch (BOException boe) {
			addActionError(boe.getMessage());
			return ERROR;
		} catch (ImageException ime) {
			addActionError(ime.getMessage());
			return ERROR;
		} catch (Exception e) {
			addActionError(e.getMessage());
			return ERROR;
		}
		return Action.SUCCESS;
	}

	public String ajaxLoadRestaurantAction(){

		ServletOutputStream out = null;
		this.idRestaurant = (request.getParameter("id") != null && !request.getParameter("id").equals("")) ? Integer.parseInt(request
				.getParameter("id")) : null;
		String json = "";
		try {
			out = this.response.getOutputStream();
			if (this.idRestaurant == null) {
				json = createErrorJSON("Not restaurant selected");
			} else {
				Restaurant restaurant = restaurantsBo.load(idRestaurant,true,false,false);
				Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithModifiers(Modifier.PROTECTED).create();
				json = gson.toJson(restaurant);
			}
		} catch (Exception e) {
			json = createErrorJSON("error in ajax action");
		}

		try {
			out.print(json);
		} catch (IOException e) {
			throw new GeneralException(e,"possibly ServletOutputStream null");
		}
		return null;
	}

	public String ajaxTableRestaurants(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();
			inizializeTableParams();
			json = searchInfoANDcreateJSONForRestaurants();
		} catch (NumberFormatException e) {
			json = createErrorJSON("error in ajax action: wrong params");
		} catch (Exception e) {
			json = createErrorJSON("error in ajax action");
		}

		try {
			out.print(json);
		} catch (IOException e) {
			throw new GeneralException(e,"possibly ServletOutputStream null");
		}
		return null;
	}

	public String ajaxTablePlatsAction(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();
			inizializeTableParams();
			this.idRestaurant = (request.getParameter("id") != null && !request.getParameter("id").equals("")) ? Integer.parseInt(request
					.getParameter("id")) : 1;
			json = searchInfoANDcreateJSONForPlats();
		} catch (NumberFormatException e) {
			json = createErrorJSON("error in ajax action: wrong params");
		} catch (Exception e) {
			json = createErrorJSON("error in ajax action");
		}

		try {
			out.print(json);
		} catch (IOException e) {
			throw new GeneralException(e,"possibly ServletOutputStream null");
		}
		return null;
	}
	public String ajaxDeleteRestaurantAction(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();

			inizializeParamTODeleteRestaurant();
			Restaurant restaurant = this.restaurantsBo.load(this.idRestaurant,true,false,false);
			this.restaurantsBo.delete(restaurant);
			

		} catch (BOException boe) {
			json = createErrorJSON("error in ajax action: Error in BO");
		} catch (NumberFormatException e) {
			json = createErrorJSON("error in ajax action: wrong params");
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

	// PRIVATE METHODS

	private void inizializeParamTODeleteRestaurant() throws NumberFormatException{

	
		this.idRestaurant = (request.getParameter("idRestaurant") != null && !request.getParameter("idRestaurant").equals("")) ? Integer.parseInt(request
				.getParameter("idRestaurant")) : null;
		if ( this.idRestaurant==null) {
			throw new NumberFormatException("Restaurant id null");
		}

	}

	private String searchInfoANDcreateJSONForRestaurants(){

		List<Restaurant> restaurantList = restaurantsBo.getAll(true,true,true);

		List<Restaurant> subRestaurantList = restaurantList.subList(inici, ((inici + lenght) < restaurantList.size()) ? (inici + lenght)
				: restaurantList.size());
		
		List<RestaurantTable> subrestaurantTableList = new ArrayList<RestaurantTable>();
		for (Restaurant restaurant : subRestaurantList) {
			restaurant.setNom("<a href='#' onclick='showDivRestaurant(this.id)' id='" + restaurant.getId() + "' >" + restaurant.getNom()
					+ "</a>");
			RestaurantTable restaurantTable = new RestaurantTable();
			BeanUtils.copyProperties(restaurant, restaurantTable);
			restaurantTable.setAccio("<a href=\"#\" onclick=\"deleteRestaurant("+restaurant.getId()+")\" ><img src=\"../images/delete.png\"></a>");
			subrestaurantTableList.add(restaurantTable);
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(subrestaurantTableList);
		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + sEcho + ", \"iTotalRecords\":\"" + restaurantList.size() + "\", \"iTotalDisplayRecords\":\""
				+ restaurantList.size() + "\", \"aaData\":  ");
		jsonSB.append(json);
		jsonSB.append("}");
		return jsonSB.toString();

	}

	private Image getImageFromUpload() throws ImageException{ 

		Image image = null;
		if (this.fileUpload != null) {
			byte[] bFile = new byte[(int) this.fileUpload.length()];

			try {
				FileInputStream fileInputStream = new FileInputStream(this.fileUpload);
				// convert file into array of bytes
				fileInputStream.read(bFile);
				fileInputStream.close();
			} catch (Exception e) {
				throw new ImageException(e,"error retrieving image in action");
			}
			image = new Image();
			image.setImage(bFile);
			image.setDescripcio(this.fileUploadFileName);
		}
		return image;
	}

	private String searchInfoANDcreateJSONForPlats(){

		Restaurant restaurant = restaurantsBo.load(idRestaurant,true,false,false);
		if (!restaurant.getPlats().isEmpty()) {
			List<Plat> platList = new ArrayList<Plat>(restaurant.getPlats());
			List<Plat> subPlatList = platList.subList(inici, ((inici + lenght) < platList.size()) ? (inici + lenght) : platList.size());
			List<PlatTable> subPlatTableList = new ArrayList<PlatTable>();
			for (Plat plat : subPlatList) {
				plat.setNom("<a href='#' onclick='goToPlatInfo(this.id)' id='" + plat.getId() + "' >" + plat.getNom() + "</a>");
				PlatTable platTable = new PlatTable();
				BeanUtils.copyProperties(plat, platTable);
				platTable.setAccio("<a href=\"#\" onclick=\"deletePlat("+plat.getId()+")\" ><img src=\"../images/delete.png\"></a>");
				subPlatTableList.add(platTable);
			}
			Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
			String json = gson.toJson(subPlatTableList);
			StringBuffer jsonSB = new StringBuffer("{");
			jsonSB.append("\"sEcho\": " + sEcho + ", \"iTotalRecords\":\"" + platList.size() + "\", \"iTotalDisplayRecords\":\""
					+ platList.size() + "\", \"aaData\":  ");
			jsonSB.append(json);
			jsonSB.append("}");
			return jsonSB.toString();
		} else {
			return createEmptyJSON();
		}
	}

	private String createErrorJSON( String error ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + sEcho + ",\"error\":\"" + error
				+ "\" ,\"iTotalRecords\":\"0\", \"iTotalDisplayRecords\":\"0\", \"aaData\":  []");
		jsonSB.append("}");
		return jsonSB.toString();
	}

	private String createEmptyJSON(){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + sEcho + ",\"iTotalRecords\":\"0\", \"iTotalDisplayRecords\":\"0\", \"aaData\":  []");
		jsonSB.append("}");
		return jsonSB.toString();
	}

	private void inizializeTableParams() throws NumberFormatException{
		
		this.sEcho = request.getParameter("sEcho");
		this.lenght = (request.getParameter("iDisplayLength") == null) ? 10 : Integer.parseInt(request.getParameter("iDisplayLength"));
		this.inici = (request.getParameter("iDisplayStart") == null) ? 0 : Integer.parseInt(request.getParameter("iDisplayStart"));
		this.sortDireccio = request.getParameter("sSortDir_0");
		if (this.sortDireccio == null)
			this.sortDireccio = "ASC";
	}

	// SETTERS
	public void setRestaurantsBo( RestaurantsBo restaurantsBo ){

		this.restaurantsBo = restaurantsBo;
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

	public Restaurant getRestaurant(){

		return restaurant;
	}

	public void setRestaurant( Restaurant restaurant ){

		this.restaurant = restaurant;
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

}