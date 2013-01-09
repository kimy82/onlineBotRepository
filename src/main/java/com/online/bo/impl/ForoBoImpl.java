package com.online.bo.impl;

import com.online.bo.ForoBo;
import com.online.dao.ForoDao;
import com.online.exceptions.BOException;
import com.online.model.Foro;

public class ForoBoImpl implements ForoBo {

	private ForoDao foroDao;

	public void save(Foro foro) throws BOException, Exception {

		checkForoToSave(foro);
		foroDao.save(foro);
	}

	// PRIVATE METHODS

	private void checkForoToSave(Foro foro) throws BOException {

		if (foro == null || foro.getComment() == null
				|| foro.getComment().equals("") || foro.getPlat() == null) {
			throw new BOException("Null foro to save");
		}
	}

	// SETTERS
	public void setForoDao(ForoDao foroDao) {
		this.foroDao = foroDao;
	}

}
