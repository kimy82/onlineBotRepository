package com.online.bo.impl;

import com.online.bo.ClauBo;
import com.online.dao.ClauDao;
import com.online.exceptions.BOException;
import com.online.model.Clau;

public class ClauBoImpl implements ClauBo{

	private ClauDao	clauDao;

	public Clau getClau(String entorn) throws BOException{ 
		
		return clauDao.getClau(entorn);
		
	}

	
	// SETTERS
	public void setClauDao(ClauDao clauDao) {
		this.clauDao = clauDao;
	}
	




}
