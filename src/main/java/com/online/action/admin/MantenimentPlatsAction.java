package com.online.action.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.online.bo.PlatsBo;
import com.online.bo.RestaurantsBo;
import com.online.exceptions.BOException;
import com.online.exceptions.GeneralException;
import com.online.model.Image;
import com.online.model.Plat;
import com.online.model.Restaurant;
import com.online.pojos.Basic;
import com.online.utils.Constants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class MantenimentPlatsAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	HttpServletResponse		response;
	HttpServletRequest		request;

	private File			fileUpload;
	private String			fileUploadContentType;
	private String			fileUploadFileName;

	private Plat			plat				= new Plat();
	private PlatsBo			platsBo;
	private RestaurantsBo	restaurantsBo;
	private Integer			idRestaurant;
	private String			idRestaurants;
	private Long			idPlat=null;

	private List<Basic>		restaurantBasicList	= new LinkedList<Basic>();
	private List<Basic>		tipusPlat	= new LinkedList<Basic>();

	public String execute(){

		return Action.SUCCESS;

	}

	public String consultaPlats(){

		try {
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
			
			this.tipusPlat.add(new Basic(1,Constants.TIPUS_PLAT_PRIMER));
			this.tipusPlat.add(new Basic(1,Constants.TIPUS_PLAT_SEGON));
			this.tipusPlat.add(new Basic(1,Constants.TIPUS_PLAT_POSTRE));
			
		} catch (BOException boe) {
			addActionError(boe.getMessage());
			return ERROR;
		} catch (Exception e) {
			addActionError(e.getMessage());
			return ERROR;
		}
		return Action.SUCCESS;

	}

	public String ajaxDeletePlatAction(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();

			inizializeParamsTODeletePlat();			
			Plat plat = this.platsBo.load(this.idPlat);
			this.platsBo.delete(plat);
			

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

	public String saveNewPlat() throws BOException{

		try {
			if (this.plat != null) {

						
				if(!this.idRestaurants.isEmpty()){
					String[] stringRestaurants = this.idRestaurants.split(",");
					for(String idStringRestaurant : stringRestaurants ){
						Plat platToSave = new Plat();
						platToSave.setDescripcio(this.plat.getDescripcio());
						platToSave.setNom(this.plat.getNom());
						platToSave.setPreu(this.plat.getPreu());			
						platToSave.setTipus(this.plat.getTipus());
						Image image = getImageFromUpload();
						platToSave.setFoto(image);
						this.platsBo.save(platToSave);						
						Restaurant restaurant = this.restaurantsBo.load(Integer.parseInt(idStringRestaurant.trim()),true,false,false);
						Set<Plat> plats = restaurant.getPlats();
						plats.add(platToSave);
						this.restaurantsBo.update(restaurant);
						
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
	private Image getImageFromUpload() throws RuntimeException{

		Image image = null;
		if (this.fileUpload != null) {
			byte[] bFile = new byte[(int) this.fileUpload.length()];

			try {
				FileInputStream fileInputStream = new FileInputStream(this.fileUpload);
				// convert file into array of bytes
				fileInputStream.read(bFile);
				fileInputStream.close();
			} catch (Exception e) {
				throw new RuntimeException();
			}
			image = new Image();
			image.setImage(bFile);
			image.setDescripcio(this.fileUploadFileName);
		}
		return image;
	}

	private String createErrorJSON( String error ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"error\":\"" + error + "\" ");
		jsonSB.append("}");
		return jsonSB.toString();
	}

	private void inizializeParamsTODeletePlat() throws NumberFormatException{

		this.idPlat = (request.getParameter("idPlat") != null && !request.getParameter("idPlat").equals("")) ? Long.parseLong(request
				.getParameter("idPlat")) : null;
		this.idRestaurant = (request.getParameter("idRestaurant") != null && !request.getParameter("idRestaurant").equals("")) ? Integer.parseInt(request
				.getParameter("idRestaurant")) : null;
		if (idPlat == null || this.idRestaurant==null) {
			throw new NumberFormatException("Plat or restaurant id null");
		}

	}

	// Getters i setters
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