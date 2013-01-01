package com.online.bo.impl;

import java.util.Date;
import java.util.List;

import com.online.bo.PromocionsBo;
import com.online.dao.PromocionsDao;
import com.online.exceptions.BOException;
import com.online.model.Promocio;
import com.online.model.PromocioAPartirDe;
import com.online.model.PromocioNumComandes;

public class PromocionsBoImpl implements PromocionsBo{

	private PromocionsDao	promocionsDao;

	public void save( Promocio promocio ) throws BOException{

		checkPromocio(promocio);
		promocionsDao.save(promocio);
	}

	public void update( Promocio promocio ) throws BOException{

		checkPromocioWithId(promocio);
		promocionsDao.update(promocio);
	}

	public void delete( Promocio promocio ) throws BOException{

		checkPromocioId(promocio);
		promocionsDao.delete(promocio);
	}

	public <E extends Promocio> E load( Integer id ) throws BOException{

		if (id == null)
			throw new BOException("NUll id to load");
		E p = promocionsDao.load(id);
		return p;

	}

	public List<Promocio> getAll(){

		return this.promocionsDao.getAll();
	}

	public List<PromocioAPartirDe> getPromosAPartirDe( Double importAPartirDe, Date dia ) throws BOException{

		if (importAPartirDe == null)
			return null;
		List<PromocioAPartirDe> list = this.promocionsDao.getPromosAPartirDe(importAPartirDe, dia);
		return list;
	}

	public List<PromocioNumComandes> getPromosNumComandes( Integer numComandes, Integer temps ) throws BOException{

		List<PromocioNumComandes> list = this.promocionsDao.getPromosNumComandes(numComandes, temps);
		return list;

	}

	// PRIVATE METHODS
	private void checkPromocio( Promocio promocio ) throws BOException{

		if (promocio == null || promocio.getNom() == null || promocio.getNom().equals("") ) {
			throw new BOException("Null promo to save");
		}
	}

	private void checkPromocioWithId( Promocio promocio ) throws BOException{

		if (promocio == null || promocio.getId() == null || promocio.getNom() == null || promocio.getNom().equals("")
				|| promocio.getDescompteImport() == null || promocio.getTipuDescompte() == null) {
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
