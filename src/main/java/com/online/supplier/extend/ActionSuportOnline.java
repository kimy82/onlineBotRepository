package com.online.supplier.extend;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.online.exceptions.WrongParamException;
import com.online.model.Image;
import com.opensymphony.xwork2.ActionSupport;

public class ActionSuportOnline extends ActionSupport implements ServletResponseAware, ServletRequestAware{

	/**
	 * 
	 */
	private static final long		serialVersionUID	= 1L;
	protected String				sEcho;
	protected int					lenght				= 0;
	protected int					inici				= 0;
	protected String				sortDireccio		= null;
	protected int					columna				= 0;

	protected HttpServletResponse	response;
	protected HttpServletRequest	request;

	protected File					fileUpload;
	protected String				fileUploadContentType;
	protected String				fileUploadFileName;

	protected Image getImageFromUpload() throws RuntimeException{

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

	protected void inizializeTableParams() throws WrongParamException{
		try{
			this.sEcho = request.getParameter("sEcho");
			this.lenght = (request.getParameter("iDisplayLength") == null) ? 10 : Integer.parseInt(request.getParameter("iDisplayLength"));
			this.inici = (request.getParameter("iDisplayStart") == null) ? 0 : Integer.parseInt(request.getParameter("iDisplayStart"));
			this.sortDireccio = request.getParameter("sSortDir_0");
			if (this.sortDireccio == null)
				this.sortDireccio = "ASC";
			if (request.getParameter("iSortCol_0") != null)
				this.columna = Integer.parseInt(request.getParameter("iSortCol_0"));
		} catch (NumberFormatException nfe) {
			throw new WrongParamException("wrong table params");
		}
	}

	// SETTERS
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