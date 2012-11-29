package com.online.bo.impl;

import com.online.bo.ImageBo;
import com.online.dao.ImageDao;
import com.online.exceptions.BOException;
import com.online.model.Image;

public class ImageBoImpl implements ImageBo{

	private ImageDao	imageDao;

	public Image load( Integer id ) throws BOException{

		if (id == null)
			return null;
		return imageDao.load(id);

	}

	// SETTERS
	public ImageDao getImageDao(){

		return imageDao;
	}

	public void setImageDao( ImageDao imageDao ){

		this.imageDao = imageDao;
	}

}
