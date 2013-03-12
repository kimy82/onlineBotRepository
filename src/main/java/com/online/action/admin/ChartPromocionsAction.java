package com.online.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.BeanUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.online.bo.PromocionsBo;
import com.online.exceptions.ComandaException;
import com.online.exceptions.GeneralException;
import com.online.model.Promocio;
import com.online.pojos.Basic;
import com.online.pojos.PromocioChart;
import com.online.pojos.PromocioChartDates;
import com.online.supplier.extend.ActionSuportOnline;
import com.online.utils.Utils;

public class ChartPromocionsAction extends ActionSuportOnline{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private PromocionsBo		promocionsBo;

	private Integer				idPromo				= null;
	
	private List<Basic>        idsPromocions = new ArrayList<Basic>();

	public String execute(){

		String jsonChart = searchInfoANDcreateJSONForPromos();
		this.request.setAttribute("dataChart", jsonChart);
		return SUCCESS;
	}

	public String showDates(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();
			inizializeParamIdPromo();
			json = searchInfoANDcreateJSONForDatesPromo();

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

	// Private methods

	private void inizializeParamIdPromo() throws NumberFormatException{

		this.idPromo = (request.getParameter("idPromocio") == null || request.getParameter("idPromocio").toString().equals("")) ? null
				: Integer.parseInt(request.getParameter("idPromocio"));
	}

	private String searchInfoANDcreateJSONForPromos(){

		List<Promocio> promoList = this.promocionsBo.getAll();

		List<PromocioChart> promoChartList = new ArrayList<PromocioChart>();
		for (Promocio promo : promoList) {
			Basic basic = new Basic();
			basic.setDescripcio(promo.getNom());
			basic.setId(promo.getId());
			this.idsPromocions.add(basic);
			PromocioChart promoChart = new PromocioChart();
			BeanUtils.copyProperties(promo, promoChart);
			promoChartList.add(promoChart);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().serializeNulls().create();
		String json = gson.toJson(promoChartList);
		return json;

	}

	private String searchInfoANDcreateJSONForDatesPromo(){
 
		Promocio promo = this.promocionsBo.loadWithDates(this.idPromo);
		int month= Calendar.getInstance().get(Calendar.MONTH)+1;
		List<PromocioChartDates> promoChartDatesList = new ArrayList<PromocioChartDates>();
		String[] listDates = promo.getDates()!=null? promo.getDates().split(" ") : "".split(" ");
		Map<Integer,Integer> datesHash = new HashMap<Integer,Integer>();
		for (String  date : listDates) {
			Integer monthGet = Utils.getMonth(date);
			Integer dayGet = Utils.getDay(date);
			if(monthGet!=month)continue;
			
			if(datesHash.containsKey(dayGet)){
				Integer numUsed = datesHash.get(dayGet);
				datesHash.remove(dayGet);
				datesHash.put(dayGet, numUsed+1);
			}else{
				datesHash.put(dayGet, 1);
			}
			
			
		}
		Integer numDay=1;
		for(numDay=1 ;numDay<32; numDay++){
			Integer numUsed = datesHash.containsKey(numDay)?datesHash.get(numDay):0;
			PromocioChartDates promoChartDates = new PromocioChartDates();
			promoChartDates.setDate(numDay);
			promoChartDates.setNumUsed(numUsed);			
			promoChartDatesList.add(promoChartDates);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().serializeNulls().create();
		String json = gson.toJson(promoChartDatesList);
		return json;

	}

	// Getters i setters

	public void setPromocionsBo( PromocionsBo promocionsBo ){

		this.promocionsBo = promocionsBo;
	}

	public List<Basic> getIdsPromocions(){
	
		return idsPromocions;
	}

	public void setIdsPromocions( List<Basic> idsPromocions ){
	
		this.idsPromocions = idsPromocions;
	}
	
	
}