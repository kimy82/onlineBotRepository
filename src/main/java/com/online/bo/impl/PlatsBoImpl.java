package com.online.bo.impl;

import java.util.List;

import com.online.bo.PlatsBo;
import com.online.dao.PlatsDao;
import com.online.exceptions.BOException;
import com.online.model.Plat;

public class PlatsBoImpl implements PlatsBo{

	private PlatsDao	platsDao;

	public void save( Plat plat ) throws BOException{

		checkPlat(plat);
		platsDao.save(plat);
	}

	public void update( Plat plat ) throws BOException{

		checkPlatWithId(plat);
		platsDao.update(plat);
	}

	public void delete( Plat plat ) throws BOException{

		checkPlatId(plat);
		platsDao.delete(plat);
	}

	public Plat load( Long id ,boolean lazy) throws BOException{

		if (id == null)
			throw new BOException("NUll id to load");
		
		if(lazy)
			return platsDao.load(id);
		else
			return platsDao.loadLaziFalse(id);
	}

	public List<Plat> getAll() throws BOException{
		
		return this.platsDao.getAll();
	}
	
	// PRIVATE METHODS
	private void checkPlat( Plat plat ) throws BOException{

		if (plat == null || plat.getNom() == null || plat.getNom().equals("") || plat.getPreu() == null
				|| plat.getPreu().toString().equals("")) {
			throw new BOException("Null plat to save");
		}
	}

	private void checkPlatWithId( Plat plat ) throws BOException{

		if (plat == null || plat.getId() == null || plat.getNom() == null || plat.getNom().equals("") || plat.getPreu() == null
				|| plat.getPreu().toString().equals("")) {
			throw new BOException("Null plat to save");
		}
	}

	private void checkPlatId( Plat plat ) throws BOException{

		if (plat == null || plat.getId() == null) {
			throw new BOException("Null plat to delete");
		}
	}
	// SETTERS

	public void setPlatsDao( PlatsDao platsDao ){

		this.platsDao = platsDao;
	}

}
