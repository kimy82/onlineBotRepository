package com.online.bo.impl;

import java.util.List;

import com.online.bo.PromocionsBo;
import com.online.dao.PlatsDao;
import com.online.dao.PromocionsDao;
import com.online.exceptions.BOException;
import com.online.model.Plat;
import com.online.model.Promocio;

public class PromocionsBoImpl implements PromocionsBo{

	private PromocionsDao	promocionsDao;

	public void save( Promocio promocio) throws BOException{

		checkPromocio(promocio);
		promocionsDao.save(promocio);
	}

	public void update(Promocio promocio) throws BOException{

		checkPromocioWithId(promocio);
		promocionsDao.update(promocio);
	}

	public void delete( Promocio promocio ) throws BOException{

		checkPromocioId(promocio);
		promocionsDao.delete(promocio);
	}

	public Promocio load( Integer id ) throws BOException{

		if (id == null)
			throw new BOException("NUll id to load");
		return promocionsDao.load(id);

	}
	
	public List<Promocio> getAll(){

		
		return this.promocionsDao.getAll();
	}

	// PRIVATE METHODS
	private void checkPromocio( Promocio promocio ) throws BOException{

		if (promocio == null || promocio.getNom() == null || promocio.getNom().equals("") || promocio.getDescompteImport() == null
				||promocio.getTipuDescompte()==null) {
			throw new BOException("Null promo to save");
		}
	}

	private void checkPromocioWithId( Promocio promocio ) throws BOException{

		if (promocio == null || promocio.getId() == null || promocio.getNom() == null || promocio.getNom().equals("") || promocio.getDescompteImport() == null
				||promocio.getTipuDescompte()==null) {
			throw new BOException("Null promocio to save");
		}
	}

	private void checkPromocioId( Promocio promocio ) throws BOException{

		if (promocio == null || promocio.getId() == null) {
			throw new BOException("Null promocio to delete");
		}
	}
	// SETTERS

	public void setPromocionsDao( PromocionsDao promocionsDao ){
	
		this.promocionsDao = promocionsDao;
	}

}
