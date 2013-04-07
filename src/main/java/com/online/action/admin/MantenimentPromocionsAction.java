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
import com.online.model.Promocio;
import com.online.model.PromocioAPartirDe;
import com.online.model.PromocioNumComandes;
import com.online.pojos.Basic;
import com.online.pojos.PromocioAPartirDeDTF;
import com.online.pojos.PromocioTable;
import com.online.supplier.extend.ActionSuportOnline;
import com.online.utils.Utils;
import com.opensymphony.xwork2.Action;

public class MantenimentPromocionsAction extends ActionSuportOnline{

	/**
	 * 
	 */
	private static final long		serialVersionUID		= 1L;

	private PromocionsBo			promocionsBo;
	private Promocio				promocio				= new Promocio();
	private PromocioAPartirDeDTF	promocioAPartirDeDTF	= new PromocioAPartirDeDTF();
	private PromocioNumComandes		promocioNumComandes		= new PromocioNumComandes();

	private List<Basic>				tipusDescompteList		= new ArrayList<Basic>();
	private List<Basic>				tipusBegudaList			= new ArrayList<Basic>();

	private Integer					idPromo					= null;

	public String execute(){

		this.tipusDescompteList = Utils.getTipusDescompte();
		this.tipusBegudaList = Utils.inizializeListTipusBeguda();
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

	public <E extends Promocio> String ajaxLoadPromoAction(){

		ServletOutputStream out = null;
		StringBuffer jsonSB = new StringBuffer("");
		String json = "";
		try {
			out = this.response.getOutputStream();
			Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
			inizializeParamIdPromo();
			E promo = this.promocionsBo.load(this.idPromo);
			if (promo instanceof PromocioAPartirDe) {
				PromocioAPartirDeDTF prDTF = new PromocioAPartirDeDTF();
				PromocioAPartirDe pr = (PromocioAPartirDe) promo;

				BeanUtils.copyProperties(pr, prDTF);
				if (pr.getDia() != null) {
					String data = Utils.formatDate(pr.getDia());
					prDTF.setDiaString(data);
				}

				jsonSB.append(gson.toJson(prDTF));
				jsonSB.setLength(jsonSB.length() - 1);
				jsonSB.append(",\"tipus\" :\"apd\" }");
			} else {
				jsonSB.append(gson.toJson(promo));
				jsonSB.setLength(jsonSB.length() - 1);
				jsonSB.append(",\"tipus\" :\"pnc\" }");
			}

			json = jsonSB.toString();
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

	public String ajaxDeletePromocio(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();

			inizializeParamIdPromo();
			Promocio promo = this.promocionsBo.load(this.idPromo);
			this.promocionsBo.delete(promo);

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

	public String savePromocioNumComandes(){

		try {
			
			this.tipusDescompteList = Utils.getTipusDescompte();
			this.tipusBegudaList = Utils.inizializeListTipusBeguda();
			
			if (this.promocioNumComandes == null) {
				addActionError("Error saving promocio");
				return Action.ERROR;
			}
			
			PromocioNumComandes promoNumComandes = new PromocioNumComandes();
			BeanUtils.copyProperties(this.promocioNumComandes, promoNumComandes);
			if (this.promocioNumComandes.getId() == null){
				promoNumComandes.setFentrada(new Date());
				promoNumComandes.setCode("PR_"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+Calendar.getInstance().get(Calendar.MINUTE));
				this.promocionsBo.save(promoNumComandes);
			}else{
				this.promocionsBo.update(promoNumComandes);
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

	public String savePromocioAPartirDe(){

		try {

			if (this.promocioAPartirDeDTF == null) {
				addActionError("Error saving promocio");
				return Action.ERROR;
			}

			PromocioAPartirDe promApartirDe = new PromocioAPartirDe();
			BeanUtils.copyProperties(this.promocioAPartirDeDTF, promApartirDe);
			promApartirDe = transformStringTODateInPromoDTF(promApartirDe);

			if (promApartirDe.getId() == null){
				promApartirDe.setFentrada(new Date());
				promApartirDe.setCode("PR_"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+Calendar.getInstance().get(Calendar.MINUTE));
				this.promocionsBo.save(promApartirDe);
			}else{
				this.promocionsBo.update(promApartirDe);
			}

			this.tipusDescompteList = Utils.getTipusDescompte();
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

	private PromocioAPartirDe transformStringTODateInPromoDTF( PromocioAPartirDe promApartirDe ){

		if (this.promocioAPartirDeDTF.getDiaString() != null && !this.promocioAPartirDeDTF.getDiaString().equals(""))
			promApartirDe.setDia(Utils.getDate(this.promocioAPartirDeDTF.getDiaString()));

		return promApartirDe;
	}

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

	public Promocio getPromocio(){

		return promocio;
	}

	public void setPromocio( Promocio promocio ){

		this.promocio = promocio;
	}

	public PromocioAPartirDeDTF getPromocioAPartirDeDTF(){

		return promocioAPartirDeDTF;
	}

	public void setPromocioAPartirDeDTF( PromocioAPartirDeDTF promocioAPartirDeDTF ){

		this.promocioAPartirDeDTF = promocioAPartirDeDTF;
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

	public List<Basic> getTipusBegudaList(){

		return tipusBegudaList;
	}

	public void setTipusBegudaList( List<Basic> tipusBegudaList ){

		this.tipusBegudaList = tipusBegudaList;
	}

}