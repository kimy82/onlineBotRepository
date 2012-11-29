package com.online.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.online.bo.ImageBo;
import com.online.model.Image;
import com.opensymphony.xwork2.ActionSupport;

public class ImageAction extends ActionSupport implements ServletRequestAware {
	 
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	byte[] imageInByte = null;
	String imageId;
	private ImageBo imageBo;
	
	private HttpServletRequest request;
 
	public String getImageId() {
		return imageId;
	}
 
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
 
	public ImageAction() {
		System.out.println("ImageAction");
	}
 
	public String execute() {
		return SUCCESS;
	}
 
	public byte[] getCustomImageInBytes() {
 
		System.out.println("imageId" + imageId);
 
		BufferedImage originalImage;
		try {
			Image image = this.imageBo.load(Integer.parseInt(imageId));
			
		
			
			InputStream in = new ByteArrayInputStream(image.getImage());
		//	ImageInputStream iis = ImageIO.createImageInputStream(in);
			originalImage = ImageIO.read(in);
		//	originalImage = ImageIO.read(new File("C:\\Chrysanthemum.jpg"));
			// convert BufferedImage to byte array
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		return imageInByte;
	}
 
	private File getImageFile(String imageId) {
		String filePath = request.getSession().getServletContext().getRealPath("/");
		File file = new File(filePath + "/Image/", imageId);
		System.out.println(file.toString());
		return file;
	}
 
	public String getCustomContentType() {
		return "image/jpeg";
	}
 
	public String getCustomContentDisposition() {
		return "anyname.jpg";
	}
 
	
	public void setServletRequest( HttpServletRequest request ){

		this.request = request;
	}

	public HttpServletRequest getServletRequest(){

		return this.request;
	}

	public ImageBo getImageBo(){
	
		return imageBo;
	}

	public void setImageBo( ImageBo imageBo ){
	
		this.imageBo = imageBo;
	}
 
}