package com.online.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.BeanUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.online.bo.PromocionsBo;
import com.online.exceptions.BOException;
import com.online.exceptions.GeneralException;
import com.online.exceptions.ImageException;
import com.online.model.PromocioAssociada;
import com.online.pojos.Basic;
import com.online.pojos.PromocioTable;
import com.online.supplier.extend.ActionSuportOnline;
import com.online.utils.Utils;
import com.opensymphony.xwork2.Action;

public class MantenimentPromosAssociadesAction extends ActionSuportOnline{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private PromocionsBo		promocionsBo;
	private PromocioAssociada	promocioAssociada			= new PromocioAssociada();

	private List<Basic>			tipusDescompteList	= new ArrayList<Basic>();
	private List<Basic>			tipusBegudaList		= new ArrayList<Basic>();

	private Integer				idPromo				= null;

	public String execute(){

		this.tipusDescompteList = Utils.getTipusDescompte();
		this.tipusBegudaList = Utils.inizializeListTipusBeguda();
		return SUCCESS;

	}

	public String ajaxTablePromosAss(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();
			inizializeTableParams();
			json = Utils.escapeUTF(searchInfoANDcreateJSONForPromos());
		} catch (NumberFormatException e) {
			json = Utils.createErrorJSONForDataTable("error in ajax action: wrong params", this.sEcho);
		} catch (Exception e) {
			json = Utils.createErrorJSONForDataTable("error in ajax action", this.sEcho);
		}

		try {
			out.print(json);
		} catch (IOException e) {
			throw new GeneralException(e, "possibly ServletOutputStream null");
		}
		return null;
	}

	public String ajaxLoadPromoAssAction(){

		ServletOutputStream out = null;
		String json = "";
		try {
			out = this.response.getOutputStream();
			Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
			inizializeParamIdPromo();
			PromocioAssociada promo = this.promocionsBo.loadAssociada(this.idPromo);
			json = gson.toJson(promo);

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

	public String ajaxDeletePromocioAss(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();

			inizializeParamIdPromo();
			PromocioAssociada promo = this.promocionsBo.loadAssociada(this.idPromo);
			this.promocionsBo.deleteAssociada(promo);

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

	public String savePromocioAssociada(){

		try {

			if (this.promocioAssociada == null) {
				addActionError("Error saving promocio");
				return Action.ERROR;
			}

			if (this.promocioAssociada.getId() == null) {
				promocioAssociada.setFentrada(new Date());
				promocioAssociada.setCode("PR_"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+Calendar.getInstance().get(Calendar.MINUTE));
				this.promocionsBo.saveAssociada(promocioAssociada);
			} else {
				this.promocionsBo.updateAssociada(promocioAssociada);
			}

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
		this.tipusBegudaList = Utils.inizializeListTipusBeguda();
		return Action.SUCCESS;

	}

	// Private methods
	private void inizializeParamIdPromo() throws NumberFormatException{

		this.idPromo = (request.getParameter("idPromocio") == null || request.getParameter("idPromocio").toString().equals("")) ? null
				: Integer.parseInt(request.getParameter("idPromocio"));
	}

	private String searchInfoANDcreateJSONForPromos(){

		List<PromocioAssociada> promoList = this.promocionsBo.getAllAssociades();

		List<PromocioTable> promoTableList = new ArrayList<PromocioTable>();
		for (PromocioAssociada promo : promoList) {

			PromocioTable promoTable = new PromocioTable();
			BeanUtils.copyProperties(promo, promoTable);
			if (promoTable.getNumBegudes() == null)
				promoTable.setNumBegudes(0);
			if (promoTable.getTipusBeguda() == null)
				promoTable.setTipusBeguda("-");
			if (promoTable.getDescompteImport() == null)
				promoTable.setDescompteImport(0.0);
			promoTable.setNom("<a href=\"#\" onclick=\"goToPromocio(" + promo.getId() + ")\" >" + promo.getNom() + "</a>");
			promoTable.setAccio("<a href=\"#\" onclick=\"deletePromocio(" + promo.getId() + ")\" ><img src=\"../images/delete.png\"></a>");
			promoTableList.add(promoTable);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().serializeNulls().create();
		String json = gson.toJson(promoTableList);
		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + sEcho + ", \"iTotalRecords\":\"" + promoList.size() + "\", \"iTotalDisplayRecords\":\""
				+ promoList.size() + "\", \"aaData\":  ");
		jsonSB.append(json);
		jsonSB.append("}");
		return jsonSB.toString();

	}

	// Getters i setters

	public void setPromocionsBo( PromocionsBo promocionsBo ){

		this.promocionsBo = promocionsBo;
	}

	public List<Basic> getTipusDescompteList(){

		return tipusDescompteList;
	}

	public void setTipusDescompteList( List<Basic> tipusDescompteList ){

		this.tipusDescompteList = tipusDescompteList;
	}

	public List<Basic> getTipusBegudaList(){

		return tipusBegudaList;
	}

	public void setTipusBegudaList( List<Basic> tipusBegudaList ){

		this.tipusBegudaList = tipusBegudaList;
	}

	public PromocioAssociada getPromocioAssociada(){
	
		return promocioAssociada;
	}

	public void setPromocioAssociada( PromocioAssociada promocioAssociada ){
	
		this.promocioAssociada = promocioAssociada;
	}
	

}