package com.online.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.BeanUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.online.bo.PromocionsBo;
import com.online.exceptions.BOException;
import com.online.exceptions.GeneralException;
import com.online.exceptions.ImageException;
import com.online.model.Promocio;
import com.online.model.PromocioAPartirDe;
import com.online.model.PromocioNumComandes;
import com.online.pojos.Basic;
import com.online.pojos.PromocioTable;
import com.online.utils.Utils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class MantenimentPromocionsAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	HttpServletResponse			response;
	HttpServletRequest			request;

	private String				sEcho;
	private int					lenght				= 0;
	private int					inici				= 0;
	private String				sortDireccio		= null;

	private PromocionsBo		promocionsBo;
	private Promocio			promocio			= new Promocio();
	private PromocioAPartirDe	promocioAPartirDe	= new PromocioAPartirDe();
	private PromocioNumComandes	promocioNumComandes	= new PromocioNumComandes();

	private List<Basic>			tipusDescompteList	= new ArrayList<Basic>();

	private Integer				idPromo				= null;

	public String execute(){

		this.tipusDescompteList = Utils.getTipusDescompte();
		return SUCCESS;

	}

	public String ajaxTablePromos(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();
			inizializeTableParams();
			json = searchInfoANDcreateJSONForPromos();
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

	public <E extends Promocio> String ajaxLoadPromoAction(){

		ServletOutputStream out = null;
		StringBuffer jsonSB = new StringBuffer("");
		String json = "";
		try {
			out = this.response.getOutputStream();
			inizializeParamIdPromo();
			E promo = this.promocionsBo.load(this.idPromo);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			jsonSB.append(gson.toJson(promo));
			jsonSB.setLength(jsonSB.length() - 1);
			if (promo instanceof PromocioAPartirDe) {
				jsonSB.append(",\"tipus\" :\"apd\" }");
			} else if (promo instanceof PromocioNumComandes) {
				jsonSB.append(",\"tipus\" :\"pnc\" }");
			}
			json = jsonSB.toString();
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

	public String ajaxDeletePromocio(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();

			inizializeParamIdPromo();
			Promocio promo = this.promocionsBo.load(this.idPromo);
			this.promocionsBo.delete(promo);

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

	public String savePromocioNumComandes(){

		try {

			if (this.promocioNumComandes == null) {
				addActionError("Error saving promocio");
				return Action.ERROR;
			}

			PromocioNumComandes promoNumComandes = new PromocioNumComandes();
			BeanUtils.copyProperties(this.promocioNumComandes, promoNumComandes);
			if (this.promocioNumComandes.getId() == null)
				this.promocionsBo.save(promoNumComandes);
			else
				this.promocionsBo.update(promoNumComandes);

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
		this.tipusDescompteList = Utils.getTipusDescompte();
		return Action.SUCCESS;

	}

	public String savePromocioAPartirDe(){

		try {

			if (this.promocioAPartirDe == null) {
				addActionError("Error saving promocio");
				return Action.ERROR;
			}

			PromocioAPartirDe promApartirDe = new PromocioAPartirDe();
			BeanUtils.copyProperties(this.promocioAPartirDe, promApartirDe);
			if (this.promocioAPartirDe.getId() == null)
				this.promocionsBo.save(promApartirDe);
			else
				this.promocionsBo.update(promApartirDe);

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

	// Private methods

	private void inizializeParamIdPromo() throws NumberFormatException{

		this.idPromo = (request.getParameter("idPromocio") == null || request.getParameter("idPromocio").toString().equals("")) ? null
				: Integer.parseInt(request.getParameter("idPromocio"));
	}

	private String searchInfoANDcreateJSONForPromos(){

		List<Promocio> promoList = this.promocionsBo.getAll();

		List<PromocioTable> promoTableList = new ArrayList<PromocioTable>();
		for (Promocio promo : promoList) {

			PromocioTable promoTable = new PromocioTable();
			BeanUtils.copyProperties(promo, promoTable);
			promoTable.setNom("<a href=\"#\" onclick=\"goToPromocio(" + promo.getId() + ")\" >" + promo.getNom() + "</a>");
			promoTable.setAccio("<a href=\"#\" onclick=\"deletePromocio(" + promo.getId() + ")\" ><img src=\"../images/delete.png\"></a>");
			promoTableList.add(promoTable);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(promoTableList);
		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + sEcho + ", \"iTotalRecords\":\"" + promoList.size() + "\", \"iTotalDisplayRecords\":\""
				+ promoList.size() + "\", \"aaData\":  ");
		jsonSB.append(json);
		jsonSB.append("}");
		return jsonSB.toString();

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

	public void setPromocionsBo( PromocionsBo promocionsBo ){

		this.promocionsBo = promocionsBo;
	}

	public Promocio getPromocio(){

		return promocio;
	}

	public void setPromocio( Promocio promocio ){

		this.promocio = promocio;
	}

	public PromocioAPartirDe getPromocioAPartirDe(){

		return promocioAPartirDe;
	}

	public void setPromocioAPartirDe( PromocioAPartirDe promocioAPartirDe ){

		this.promocioAPartirDe = promocioAPartirDe;
	}

	public PromocioNumComandes getPromocioNumComandes(){

		return promocioNumComandes;
	}

	public void setPromocioNumComandes( PromocioNumComandes promocioNumComandes ){

		this.promocioNumComandes = promocioNumComandes;
	}

	public List<Basic> getTipusDescompteList(){
	
		return tipusDescompteList;
	}

	public void setTipusDescompteList( List<Basic> tipusDescompteList ){
	
		this.tipusDescompteList = tipusDescompteList;
	}
	

}