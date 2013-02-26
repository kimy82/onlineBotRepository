package com.online.action.admin;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.online.exceptions.ImageException;
import com.online.model.Image;
import com.online.supplier.extend.ActionSuportOnline;
import com.opensymphony.xwork2.Action;

public class MantenimentPresentacioAction extends ActionSuportOnline{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public String execute(){

		return Action.SUCCESS;

	}

	public String newFotoPresentacio(){

		try {

			String numPhoto = request.getParameter("foto") != null ? request.getParameter("foto") : "1";
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

	private Image getImageFromUpload( String numPhoto ) throws ImageException{

		Image image = null;
		if (this.fileUpload != null) {
			byte[] bFile = new byte[(int) this.fileUpload.length()];

			try {
				FileInputStream fileInputStream = new FileInputStream(this.fileUpload);
				// convert file into array of bytes
				try {
					// retrieve image
					BufferedImage bi = ImageIO.read(fileInputStream);
					String whereToSave = request.getRealPath("/images/presentacio") + "\\image" + numPhoto + ".jpg";

					File outputfile = new File(whereToSave);
					ImageIO.write(bi, "jpg", outputfile);

					whereToSave = whereToSave.replace("\\target\\onlineBot", "\\src\\main\\webapp");

					File outputfileReal = new File(whereToSave.replace("\\target", ""));
					ImageIO.write(bi, "jpg", outputfileReal);

				} catch (IOException e) {
					throw new IOException(e);
				}

				fileInputStream.read(bFile);
				fileInputStream.close();
			} catch (Exception e) {
				throw new ImageException(e, "error retrieving image in action");
			}
			image = new Image();
			image.setImage(bFile);
			image.setDescripcio(this.fileUploadFileName);
		}
		return image;
	}

}