package com.online.action.comanda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import com.online.bo.BegudaBo;
import com.online.bo.ComandaBo;
import com.online.bo.PlatsBo;
import com.online.bo.PromocionsBo;
import com.online.exceptions.ComandaException;
import com.online.exceptions.GeneralException;
import com.online.exceptions.WrongParamException;
import com.online.model.Beguda;
import com.online.model.BegudaComanda;
import com.online.model.Comandes;
import com.online.model.Plat;
import com.online.model.PlatComanda;
import com.online.model.Promocio;
import com.online.services.impl.ComandaServiceImpl;
import com.online.supplier.extend.ActionSuportOnlineSession;
import com.online.utils.Utils;

public class AjaxComandaAction extends ActionSuportOnlineSession {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlatsBo platsBo;
	private ComandaBo comandaBo;
	private BegudaBo begudaBo;
	private PromocionsBo promocionsBo;

	private ComandaServiceImpl comandaService;
	
	private Long idComanda = null;
	private Long idPlat = null;
	private Long idBeguda = null;
	private Integer idPromo=null;

	public String execute() {		

		return SUCCESS; 

	}

	public String deletePlatFromCarrito() {

		ServletOutputStream out = null;
		StringBuffer json = new StringBuffer("");

		try {

			out = this.response.getOutputStream();
			inizilizeDadesComandaPlat();
			if (this.idComanda != null) {
				
				Comandes comanda = this.comandaBo.load(this.idComanda);
				List<PlatComanda> platList = comanda.getPlats();
				List<PlatComanda> newplatList = new ArrayList<PlatComanda>(); 
				Plat platToDelete = this.platsBo.load(this.idPlat, false);

				if (comandaService.checkPlatInList(platList, platToDelete)) {
					for (PlatComanda plt : platList) {
						if (!plt.getPlat().getId().toString()
								.equals(platToDelete.getId().toString())) {					
							newplatList.add(plt);
						}else{
							json.append("{\"numPlats\" : \""+plt.getNumPlats()+"\",");
							Double preuToRest = plt.getPlat().getPreu()*plt.getNumPlats();
							json.append("\"preuToRest\" : \""+preuToRest+"\"}");
						}
					}
					comanda.setPlats(newplatList);
				}				
				this.comandaBo.update(comanda);
			}
		} catch (ComandaException ce) {
			json=  new StringBuffer("");json=  new StringBuffer("");
			json.append(Utils.createErrorJSON("error in comanda service action"));
		} catch (Exception e) {
			json=  new StringBuffer("");
			json.append(Utils.createErrorJSON("error in ajax action"));
		}

		try {
			out.print(json.toString());
		} catch (IOException e) {
			throw new GeneralException(e, "possibly ServletOutputStream null");
		}

		return null;
	}
	
	public String deleteBegudaFromCarrito() {

		ServletOutputStream out = null;
		StringBuffer json = new StringBuffer("");

		try {

			out = this.response.getOutputStream();
			inizilizeDadesComandaBeguda();
			if (this.idComanda != null) {
				
				Comandes comanda = this.comandaBo.load(this.idComanda);
				List<BegudaComanda> begudaList = comanda.getBegudes();				
				Beguda begudaToDelete = this.begudaBo.load(this.idBeguda);

				comanda.setBegudes(comandaService.removeAllBegudaInList(begudaList, begudaToDelete));
				json.append(this.comandaService.removeAllBegudaInListGetJson());
				
				this.comandaBo.update(comanda);
			}
		} catch (ComandaException ce) {
			json=  new StringBuffer("");json=  new StringBuffer("");
			json.append(Utils.createErrorJSON("error in comanda service action"));
		} catch (Exception e) {
			json=  new StringBuffer("");
			json.append(Utils.createErrorJSON("error in ajax action"));
		}

		try {
			out.print(json.toString());
		} catch (IOException e) {
			throw new GeneralException(e, "possibly ServletOutputStream null");
		}

		return null;
	}
	
	public String checkBegudaToAddPromo(){
		
		ServletOutputStream out = null;
		String json = "";

		try {

			out = this.response.getOutputStream();
			inizilizeDadesComandaPromo();
		
			if (this.idComanda != null) {
				// recuperem la comanda i afegim beguda
				Comandes comanda = this.comandaBo.load(this.idComanda);				
				Promocio promo = this.promocionsBo.load(idPromo);				
				List<BegudaComanda> begudaList = comanda.getBegudes();
				int numBegudesAddToPromo=1;
				for(BegudaComanda bgcom : begudaList){
					if(bgcom.getNumBegudes()!=null &&bgcom.getNumBegudes()>0 && bgcom.getBeguda().getTipus().equals(promo.getTipusBeguda())){
						if(numBegudesAddToPromo>=promo.getNumBegudes()) break;						
						for(;numBegudesAddToPromo<= promo.getNumBegudes(); numBegudesAddToPromo++){
							if(bgcom.getNumBegudes()==0)break;
							bgcom.setNumBegudesPromo(bgcom.getNumBegudesPromo()+1);
							bgcom.setNumBegudes(bgcom.getNumBegudes()-1);													
						}											
					}
				}
				
				comanda.setBegudes(begudaList);

				this.comandaBo.update(comanda);

				json = Utils.escapeUTF(this.comandaService.createJSONForBegudaList(begudaList));
				StringBuffer jsonSB = new StringBuffer("{ \"begudes\": " + json);
				jsonSB.append(", \"numComanda\" : \"" + comanda.getId()
						+ "\" }");
				json = jsonSB.toString();
			} 
		} catch (ComandaException ce) {
			json = Utils.createErrorJSON("error in comanda service action");
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
	
	// private methods
	
	private void inizilizeDadesComandaPlat() throws WrongParamException {

		inizilizeComandaId();
		this.idPlat = (request.getParameter("idPlat") == null || request
				.getParameter("idPlat").equals("")) ? null : Long
				.parseLong(request.getParameter("idPlat"));
		if (this.idPlat == null) {
			throw new WrongParamException("null plat to rest");
		}
	}
	
	private void inizilizeDadesComandaBeguda() throws WrongParamException {

		inizilizeComandaId();
		this.idBeguda = (request.getParameter("idBeguda") == null || request
				.getParameter("idBeguda").equals("")) ? null : Long
				.parseLong(request.getParameter("idBeguda"));
		if (this.idBeguda == null) {
			throw new WrongParamException("null beguda to rest");
		}
	}
	
	private void inizilizeDadesComandaPromo() throws WrongParamException {

		inizilizeComandaId();
		this.idPromo = (request.getParameter("promo") == null || request
				.getParameter("promo").equals("")) ? null : Integer.parseInt(request.getParameter("promo"));
		if (this.idPromo == null) {
			throw new WrongParamException("null promo to check");
		}
	}


	private void inizilizeComandaId() throws WrongParamException {

		try {
			this.idComanda = (request.getParameter("idComanda") == null || request
					.getParameter("idComanda").equals("")) ? null : Long
					.parseLong(request.getParameter("idComanda"));
		} catch (NumberFormatException e) {
			throw new WrongParamException("wrong id of comanda");
		}

	}

	// SETTERS i GETTERS
	public void setPlatsBo(PlatsBo platsBo) {

		this.platsBo = platsBo;
	}

	public void setComandaBo(ComandaBo comandaBo) {

		this.comandaBo = comandaBo;
	}		

	public void setBegudaBo(BegudaBo begudaBo) {
		this.begudaBo = begudaBo;
	}

	public void setPromocionsBo(PromocionsBo promocionsBo) {
		this.promocionsBo = promocionsBo;
	}

	public void setComandaService(ComandaServiceImpl comandaService) {

		this.comandaService = comandaService;
	}
}