package com.online.action.admin;

import java.io.File;
import java.io.FileInputStream;
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
import com.online.bo.BegudaBo;
import com.online.exceptions.BOException;
import com.online.exceptions.GeneralException;
import com.online.exceptions.ImageException;
import com.online.model.Beguda;
import com.online.model.Image;
import com.online.pojos.Basic;
import com.online.pojos.BegudaTable;
import com.online.utils.Utils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class MantenimentBegudaAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private BegudaBo			begudaBo;
	private String				sEcho;
	private int					lenght				= 0;
	private int					inici				= 0;
	private String				sortDireccio		= null;
	HttpServletResponse			response;
	HttpServletRequest			request;
	List<Basic>					tipusBegudaList		= new ArrayList<Basic>();

	private File				fileUpload;
	private String				fileUploadContentType;
	private String				fileUploadFileName;

	private Beguda				beguda				= new Beguda();
	private Long				idBeguda;

	public String execute(){

		this.tipusBegudaList = Utils.inizializeListTipusBeguda();
		return Action.SUCCESS;

	}

	public String ajaxLoadBegudaAction(){

		ServletOutputStream out = null;

		String json = "";
		try {
			out = this.response.getOutputStream();
			inizializeParamIDBeguda();
			Beguda beguda = this.begudaBo.load(idBeguda);
			Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
			json = gson.toJson(beguda);
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

	public String ajaxTableBegudes(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();
			inizializeTableParams();
			json = searchInfoANDcreateJSONForBegudes();
		} catch (NumberFormatException e) {
			json = Utils.createErrorJSONForDataTable("error in ajax action: wrong params",this.sEcho);
		} catch (Exception e) {
			json = Utils.createErrorJSONForDataTable("error in ajax action",this.sEcho);
		}

		try {
			out.print(json);
		} catch (IOException e) {
			throw new GeneralException(e, "possibly ServletOutputStream null");
		}
		return null;
	}

	public String saveBeguda(){

		try {
			this.tipusBegudaList = Utils.inizializeListTipusBeguda();
			if (this.beguda == null) {
				addActionError("Error saving beguda");
				return Action.ERROR;
			}
			
			Image image = getImageFromUpload();
			if(image!=null && image.getImage()!=null)
				this.beguda.setFoto(image);
			
			if (this.beguda.getId() == null) {
				// Fem un save
				this.begudaBo.save(beguda);
			} else {
				// Fem un update
				this.begudaBo.update(beguda);

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
		return Action.SUCCESS;

	}

	public String ajaxDeleteBeguda(){

		ServletOutputStream out = null;
		String json = "";

		try {

			out = this.response.getOutputStream();
			inizializeParamIDBeguda();
			Beguda beguda = this.begudaBo.load(this.idBeguda);
			this.begudaBo.delete(beguda);

		} catch (BOException boe) {
			json = Utils.createErrorJSONForDataTable("error in ajax action: Error in BO",this.sEcho);
		} catch (NumberFormatException e) {
			json = Utils.createErrorJSONForDataTable("error in ajax action: wrong params",this.sEcho);
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
	
	private void inizializeParamIDBeguda() throws NumberFormatException{

		this.idBeguda = (request.getParameter("idBeguda") != null && !request.getParameter("idBeguda").equals("")) ? Long.parseLong(request
				.getParameter("idBeguda")) : null;
		if (this.idBeguda == null) {
			throw new NumberFormatException("Restaurant id null");
		}
	}

	private String searchInfoANDcreateJSONForBegudes(){

		List<Beguda> begudaList = this.begudaBo.getAll();

		List<Beguda> subBegudaList = begudaList.subList(inici,
				((inici + lenght) < begudaList.size()) ? (inici + lenght) : begudaList.size());

		List<BegudaTable> subrestaurantTableList = new ArrayList<BegudaTable>();
		for (Beguda beguda : subBegudaList) {
			beguda.setNom("<a href='#' onclick='showDivBeguda(this.id)' id='" + beguda.getId() + "' >" + beguda.getNom() + "</a>");
			BegudaTable begudaTable = new BegudaTable();
			BeanUtils.copyProperties(beguda, begudaTable);
			begudaTable.setAccio("<a href=\"#\" onclick=\"deleteBeguda(" + beguda.getId() + ")\" ><img src=\"../images/delete.png\"></a>");
			subrestaurantTableList.add(begudaTable);
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(subrestaurantTableList);
		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + sEcho + ", \"iTotalRecords\":\"" + begudaList.size() + "\", \"iTotalDisplayRecords\":\""
				+ begudaList.size() + "\", \"aaData\":  ");
		jsonSB.append(json);
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

	public Long getIdBeguda(){

		return idBeguda;
	}

	public void setIdBeguda( Long idBeguda ){

		this.idBeguda = idBeguda;
	}

	public void setBegudaBo( BegudaBo begudaBo ){

		this.begudaBo = begudaBo;
	}

	public HttpServletResponse getServletResponse(){

		return this.response;
	}

	public void setServletResponse( HttpServletResponse response ){

		this.response = response;
	}

	public void setServletRequest( HttpServletRequest request ){

		this.request = request;
	}

	public HttpServletRequest getServletRequest(){

		return this.request;
	}

	public List<Basic> getTipusBegudaList(){

		return tipusBegudaList;
	}

	public void setTipusBegudaList( List<Basic> tipusBegudaList ){

		this.tipusBegudaList = tipusBegudaList;
	}

	public Beguda getBeguda(){

		return beguda;
	}

	public void setBeguda( Beguda beguda ){

		this.beguda = beguda;
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