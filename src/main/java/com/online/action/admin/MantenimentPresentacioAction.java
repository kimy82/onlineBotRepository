package com.online.action.admin;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.online.exceptions.ImageException;
import com.online.model.Image;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class MantenimentPresentacioAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{


	

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	HttpServletResponse		response;
	HttpServletRequest		request;	
	
	private File			fileUpload;
	private String			fileUploadContentType;
	private String			fileUploadFileName;

	public String execute(){

		return Action.SUCCESS;

	}
	
	public String newFotoPresentacio(){

		try {
			
			int numPhoto = request.getParameter("foto")!=null ? Integer.parseInt(request.getParameter("foto")): 1;
			Image image = getImageFromUpload(numPhoto);

		} catch (ImageException ime) {
			addActionError(ime.getMessage());
			return ERROR;
		} catch (Exception e) {
			addActionError(e.getMessage());
			return ERROR;
		}
		return Action.SUCCESS;

	}


	// PRIVATE METHODS


	private Image getImageFromUpload(int numPhoto) throws ImageException{ 

		Image image = null;
		if (this.fileUpload != null) {
			byte[] bFile = new byte[(int) this.fileUpload.length()];

			try {
				FileInputStream fileInputStream = new FileInputStream(this.fileUpload);
				// convert file into array of bytes			
				try {
				    // retrieve image
				    BufferedImage bi =  ImageIO.read(fileInputStream);
				    String whereToSave=request.getRealPath("/images/presentacio")+"\\image"+numPhoto+".jpg";
				    
				    File outputfile = new File(whereToSave);
				    ImageIO.write(bi, "jpg", outputfile);
				    
				    whereToSave =whereToSave.replace("\\target\\onlineBot", "\\src\\main\\webapp");
			
				    
				    File outputfileReal = new File(whereToSave.replace("\\target", ""));
				    ImageIO.write(bi, "jpg", outputfileReal);
				    
				} catch (IOException e) {
				    throw new IOException(e);
				}
				
				fileInputStream.read(bFile);
				fileInputStream.close();
			} catch (Exception e) {
				throw new ImageException(e,"error retrieving image in action");
			}
			image = new Image();
			image.setImage(bFile);
			image.setDescripcio(this.fileUploadFileName);
		}
		return image;
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