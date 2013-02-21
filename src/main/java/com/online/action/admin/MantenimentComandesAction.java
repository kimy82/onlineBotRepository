package com.online.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.online.bo.ComandaBo;
import com.online.bo.UsersBo;
import com.online.exceptions.GeneralException;
import com.online.model.Comandes;
import com.online.pojos.AllComandesTable;
import com.online.pojos.ComandesTable;
import com.online.services.impl.PaymentServiceImpl;
import com.online.utils.Utils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class MantenimentComandesAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private String				sEcho;
	private int					lenght				= 12;
	private int					inici				= 0;
	private String				sortDireccio		= null;
	private int					columna				= 0;

	private ComandaBo			comandaBo;
	private UsersBo				usersBo;
	private PaymentServiceImpl	paymentService;

	private Long				idComanda;
	HttpServletResponse			response;
	HttpServletRequest			request;

	
	
	public String execute(){

		return Action.SUCCESS;

	}

	public String allComandes(){

		return Action.SUCCESS;

	}

	public String ajaxSendComanda(){

		ServletOutputStream out = null;
		String json = "";

		try {

			out = this.response.getOutputStream();
			inizializeIdComanda();
			Comandes comanda = this.comandaBo.load(idComanda);
			List<String> orders = this.paymentService.getComandaOrders(comanda);
			this.paymentService.sendOrder(false, orders);

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

	public String ajaxDeleteComanda(){

		ServletOutputStream out = null;
		String json = "";
		ResourceBundle resource = getTexts("MessageResources");
		try {
			out = this.response.getOutputStream();

			inizializeIdComanda();
			Comandes comanda = this.comandaBo.load(this.idComanda);

			this.usersBo.sendEmail(resource.getString("txt.info.comanda.no.procesada"), comanda.getUser().getUsername());
			this.comandaBo.delete(comanda);

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

	public String ajaxTablePrimeresComandesAction(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();
			inizializeTableParams();
			json = searchInfoANDcreateJSONForComandes();
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

	public String ajaxTableAllComandesAction(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();
			inizializeTableParams();
			json = searchInfoANDcreateJSONForAllComandes();
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

	// PRIVATE METHODS
	private void inizializeIdComanda() throws NumberFormatException{

		this.idComanda = (request.getParameter("idComanda") != null && !request.getParameter("idComanda").equals("")) ? Long
				.parseLong(request.getParameter("idComanda")) : null;
		if (this.idComanda == null) {
			throw new NumberFormatException("Comanda id null");
		}

	}

	private String searchInfoANDcreateJSONForComandes(){

		List<Comandes> comandesList = this.comandaBo.getAllToConfirm();
		List<ComandesTable> comandesTableList = new ArrayList<ComandesTable>();

		if (!comandesList.isEmpty()) {

			List<Comandes> subComandaList = comandesList.subList(inici, ((inici + lenght) < comandesList.size()) ? (inici + lenght)
					: comandesList.size());

			for (Comandes comanda : subComandaList) {

				ComandesTable comandaTable = new ComandesTable(comanda.getUser().getNom() + " (" + comanda.getUser() + ")", comanda
						.getUser().getTelNumber(), comanda.getAddress(), Utils.formatDate2(comanda.getDia()) + " " + comanda.getHora(),
						comanda.getPreu().toString(), "", "");
				comandaTable
						.setAccioSend("<a href=\"#\" onclick=\"sendTo(" + comanda.getId() + ")\" ><img src=\"../images/mail.png\"></a>");
				comandaTable.setAccioBorrar("<a href=\"#\" onclick=\"deleteTo(" + comanda.getId()
						+ ")\" ><img src=\"../images/delete.png\"></a>");
				comandesTableList.add(comandaTable);
			}

			Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
			String json = gson.toJson(comandesTableList);
			StringBuffer jsonSB = new StringBuffer("{");
			jsonSB.append("\"sEcho\": " + sEcho + ", \"iTotalRecords\":\"" + comandesList.size() + "\", \"iTotalDisplayRecords\":\""
					+ comandesList.size() + "\", \"aaData\":  ");
			jsonSB.append(json);
			jsonSB.append("}");
			return jsonSB.toString();
		} else {
			return Utils.createEmptyJSONForDataTable(this.sEcho);
		}
	}

	private String searchInfoANDcreateJSONForAllComandes(){

		List<Comandes> comandesList = this.comandaBo.getAll();
		List<AllComandesTable> comandesTableList = new ArrayList<AllComandesTable>();

		if (!comandesList.isEmpty()) {

			List<Comandes> subComandaList = comandesList.subList(inici, ((inici + lenght) < comandesList.size()) ? (inici + lenght)
					: comandesList.size());

			for (Comandes comanda : subComandaList) {
				Comandes comandaInitialized = this.comandaBo.load(comanda.getId());
				String metodePagament = getMethodPagament(comanda);
				String nomRestaurant = getNomRestaurant(comandaInitialized);
				String username = (comanda.getUser()==null)?"-":comanda.getUser().getUsername(); 
				String tel = (comanda.getUser()==null || comanda.getUser().getTelNumber()==null)?"-":comanda.getUser().getTelNumber();
				AllComandesTable allComandesTable = new AllComandesTable(username, tel,comanda.getAddress()==null ? "" : comanda.getAddress() ,
																		 (comanda.getDia()==null?"-" : Utils.formatDate2(comanda.getDia())) + " " + (comanda.getHora()==null?"-":comanda.getHora()), 
																		 (comanda.getPreu()==null ? "-" :String.valueOf(comanda.getPreu())), nomRestaurant, metodePagament);

				
				comandesTableList.add(allComandesTable);
			}

			order(comandesTableList);
			
			Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
			String json = gson.toJson(comandesTableList);
			StringBuffer jsonSB = new StringBuffer("{");
			jsonSB.append("\"sEcho\": " + sEcho + ", \"iTotalRecords\":\"" + comandesList.size() + "\", \"iTotalDisplayRecords\":\""
					+ comandesList.size() + "\", \"aaData\":  ");
			jsonSB.append(json);
			jsonSB.append("}");
			return jsonSB.toString();
		} else {
			return Utils.createEmptyJSONForDataTable(this.sEcho);
		}
	}
	
	private void order(List<AllComandesTable> comandesTableList){
		if(this.columna==0)
			Collections.sort(comandesTableList, AllComandesTable.nameComparator);
		
		if(this.columna==1)
			Collections.sort(comandesTableList, AllComandesTable.telComparator);
		
		if(this.columna==3)
			Collections.sort(comandesTableList, AllComandesTable.horaComparator);
		
		if(this.columna==4)
			Collections.sort(comandesTableList, AllComandesTable.preuComparator);
		
		if(this.columna==5)
			Collections.sort(comandesTableList, AllComandesTable.restaurantComparator);
		
		if(this.columna==6)
			Collections.sort(comandesTableList, AllComandesTable.metodeComparator);
		
		if(this.sortDireccio!=null && this.sortDireccio.equals("desc")){
			Collections.reverse(comandesTableList);
		}
	}
	
	private String getMethodPagament( Comandes comanda ){

		if (comanda.getPagada()!=null && comanda.getPagada()==true) {

			if (comanda.getTargeta()!=null && comanda.getTargeta()==true) {
				return "TARGETA";
			} else {
				return "SENSE TARGETA";
			}

		} else {
			return "NO ACABADA";
		}
	}

	private String getNomRestaurant( Comandes comanda ){

		try {
			if (comanda != null && !comanda.getPlats().isEmpty()) {

				return comanda.getPlats().get(0).getPlat().getRestaurants().iterator().next().getNom();

			} else {
				return "-";
			}
		} catch (Exception e) {
			return "-";
		}
	}

	private void inizializeTableParams() throws NumberFormatException{

		this.sEcho = request.getParameter("sEcho");
		this.lenght = (request.getParameter("iDisplayLength") == null) ? 12 : Integer.parseInt(request.getParameter("iDisplayLength"));
		this.inici = (request.getParameter("iDisplayStart") == null) ? 0 : Integer.parseInt(request.getParameter("iDisplayStart"));
		this.sortDireccio = request.getParameter("sSortDir_0");
		if (request.getParameter("iSortCol_0") != null)
			this.columna = Integer.parseInt(request.getParameter("iSortCol_0"));
		if (this.sortDireccio == null)
			this.sortDireccio = "ASC";
	}

	// SETTERS
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

	public void setComandaBo( ComandaBo comandaBo ){

		this.comandaBo = comandaBo;
	}

	public void setPaymentService( PaymentServiceImpl paymentService ){

		this.paymentService = paymentService;
	}

	public void setUsersBo( UsersBo usersBo ){

		this.usersBo = usersBo;
	}

}