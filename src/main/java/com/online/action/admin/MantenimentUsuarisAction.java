package com.online.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.BeanUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.online.bo.ComandaBo;
import com.online.bo.PromocionsBo;
import com.online.bo.UsersBo;
import com.online.exceptions.BOException;
import com.online.exceptions.GeneralException;
import com.online.model.Comandes;
import com.online.model.PromocioAssociada;
import com.online.model.Users;
import com.online.pojos.Basic;
import com.online.pojos.UsersDialog;
import com.online.pojos.UsersTable;
import com.online.supplier.extend.ActionSuportOnline;
import com.online.utils.Utils;

public class MantenimentUsuarisAction extends ActionSuportOnline{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private Long				idUser				= null;
	private String				username			= "";
	private Integer				idPromo				= null;

	private UsersBo				usersBo;
	private ComandaBo			comandaBo;
	private PromocionsBo		promocionsBo;
	private List<Basic>			associadesList		= new ArrayList<Basic>();

	public String execute(){

		List<PromocioAssociada> listAssociades = this.promocionsBo.getAllAssociades();
		for (PromocioAssociada promo : listAssociades) {
			Basic basic = new Basic(promo.getId(), promo.getNom());
			associadesList.add(basic);
		}

		return SUCCESS;

	}
	
	
	public String linkAllUserToPromo(){

		ServletOutputStream out = null;
		String json = "";

		try {

			out = this.response.getOutputStream();
			initUserAndPromoId();
			List<Users> listUsers = this.usersBo.getAll();
			if(this.idPromo!=null){
			
				PromocioAssociada promo = this.promocionsBo.loadAssociada(idPromo);
				for(Users user : listUsers){	
					user.setCodePromo(promo.getCode());
					this.usersBo.update(user);
				}
			}else{
				for(Users user : listUsers){	
					user.setCodePromo("");
					this.usersBo.update(user);
				}
			}
		} catch (BOException boe) {
			json = Utils.createErrorJSON("error in ajax action: Error in BO");
		} catch (NumberFormatException e) {
			json = Utils.createErrorJSON("error in ajax action: wrong params" + e.getMessage());
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
	
	public String linkUserToPromo(){

		ServletOutputStream out = null;
		String json = "";

		try {

			out = this.response.getOutputStream();
			initUserAndPromoId();
			if(this.idPromo!=null && this.username!=null){
				Users user = this.usersBo.findByUsername(this.username);
				PromocioAssociada promo = this.promocionsBo.loadAssociada(idPromo);
				user.setCodePromo(promo.getCode());
				this.usersBo.update(user);
			}

		} catch (BOException boe) {
			json = Utils.createErrorJSON("error in ajax action: Error in BO");
		} catch (NumberFormatException e) {
			json = Utils.createErrorJSON("error in ajax action: wrong params" + e.getMessage());
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

	public String ajaxDeleteUserAction(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();

			inizializeParamTODeleteUser();
			Users user = new Users();
			user.setId(this.idUser);
			this.usersBo.delete(user);

		} catch (BOException boe) {
			json = Utils.createErrorJSON("error in ajax action: Error in BO");
		} catch (NumberFormatException e) {
			json = Utils.createErrorJSON("error in ajax action: wrong params" + e.getMessage());
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

	public String ajaxInfoUserAction(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();
			inizializeParamTODeleteUser();
			json = Utils.escapeUTF(searchInfoANDcreateJSONForInfoUsuaris());
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

	public String ajaxTableUsuarisAction(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();
			inizializeTableParams();
			json = Utils.escapeUTF(searchInfoANDcreateJSONForUsuaris());
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

	// private methods

	private void initUserAndPromoId(){

		this.idPromo = (request.getParameter("idPromocio") == null || request.getParameter("idPromocio").toString().equals("")) ? null
				: Integer.parseInt(request.getParameter("idPromocio"));
		this.username = (request.getParameter("username") == null || request.getParameter("username").toString().equals("")) ? "" : request
				.getParameter("username");
	}

	private void inizializeParamTODeleteUser() throws NumberFormatException{

		this.idUser = (request.getParameter("id") == null) ? null : Long.parseLong(request.getParameter("id"));
		if (this.idUser == null) {
			throw new NumberFormatException("Id of User is null");
		}
	}

	private String searchInfoANDcreateJSONForInfoUsuaris(){

		Users user = this.usersBo.load(idUser);
		List<Comandes> comandes = this.comandaBo.getAllByUser(idUser, false);
		List<Basic> associadesList = new ArrayList<Basic>();
		int numComandesRealitzades = 0;
		int numComandesAmbTargeta = 0;
		int numComandesSenseTargeta = 0;
		for (Comandes cmd : comandes) {

			if (cmd.getPagada() != null && cmd.getPagada() == true) {
				numComandesRealitzades++;
			}
			if (cmd.getTargeta() != null &&cmd.getPagada() != null && cmd.getTargeta() == true && cmd.getPagada() == true) {
				numComandesAmbTargeta++;
			} else if(cmd.getTargeta() != null && cmd.getPagada() != null && cmd.getTargeta() == false && cmd.getPagada() == true) {
				numComandesSenseTargeta++;
			}

		}

		List<PromocioAssociada> listAssociades = this.promocionsBo.getAllAssociades();

		for (PromocioAssociada promo : listAssociades) {
			Basic basic = new Basic(promo.getId(), promo.getNom());
			associadesList.add(basic);
		}

		UsersDialog userDialog = new UsersDialog();
		BeanUtils.copyProperties(user, userDialog);
		userDialog.setNumComandesAmbTargeta(numComandesAmbTargeta);
		userDialog.setNumComandesRealitzades(numComandesRealitzades);
		userDialog.setNumComandesSenseTargeta(numComandesSenseTargeta);
		userDialog.setPromos(associadesList);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(userDialog);
	}

	private String searchInfoANDcreateJSONForUsuaris(){

		List<Users> usersList = this.usersBo.getAll();

		List<Users> subUsersList = usersList.subList(inici, ((inici + lenght) < usersList.size()) ? (inici + lenght) : usersList.size());

		List<UsersTable> subusersTableList = new ArrayList<UsersTable>();
		for (Users users : subUsersList) {

			UsersTable usersTable = new UsersTable();
			BeanUtils.copyProperties(users, usersTable);
			usersTable.setUsername("<a href=\"#\" onclick=\"infoUser(" + users.getId() + ")\" >" + users.getUsername() + "</a>");
			usersTable.setAccio("<a href=\"#\" onclick=\"deleteUser(" + users.getId() + ")\" ><img src=\"../images/delete.png\"></a>");
			subusersTableList.add(usersTable);
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(subusersTableList);
		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + sEcho + ", \"iTotalRecords\":\"" + usersList.size() + "\", \"iTotalDisplayRecords\":\""
				+ usersList.size() + "\", \"aaData\":  ");
		jsonSB.append(json);
		jsonSB.append("}");
		return jsonSB.toString();

	}

	// Getters i setters
	public void setUsersBo( UsersBo usersBo ){

		this.usersBo = usersBo;
	}

	public void setComandaBo( ComandaBo comandaBo ){

		this.comandaBo = comandaBo;
	}

	public void setPromocionsBo( PromocionsBo promocionsBo ){

		this.promocionsBo = promocionsBo;
	}

	public List<Basic> getAssociadesList(){
	
		return associadesList;
	}

	public void setAssociadesList( List<Basic> associadesList ){
	
		this.associadesList = associadesList;
	}
	

}