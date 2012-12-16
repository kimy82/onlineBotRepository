package com.online.bo.impl;

import java.util.List;

import com.online.bo.ComandaBo;
import com.online.dao.ComandaDao;
import com.online.exceptions.BOException;
import com.online.model.Comandes;

public class ComandaBoImpl implements ComandaBo{

	private ComandaDao	comandaDao;

	public void save( Comandes comanda ) throws BOException{

		checkComanda(comanda);
		comandaDao.save(comanda);
	}

	public void update( Comandes comanda ) throws BOException{

		checkComandaWithId(comanda);
		comandaDao.update(comanda);
	}

	public void delete( Comandes comanda ) throws BOException{

		checkComandaId(comanda);
		comandaDao.delete(comanda);
		
	}

	public Comandes load( Long id ) throws BOException{

		if (id == null)
			throw new BOException("NUll id to load");
		return comandaDao.load(id);

	}

	public List<Comandes> getAll(){

		return comandaDao.getAll();
	}
	
	public List<Comandes> getAllByUser(Long id)throws BOException{
		if(id ==null){
			throw new BOException("Null id of User");
		} 
		
		return this.comandaDao.getAllByUser(id);
	}

	// PRIVATE METHODS
	private void checkComanda( Comandes comanda ) throws BOException{

		if (comanda == null) {
			throw new BOException("Null comanda to save");
		}
	}

	private void checkComandaWithId( Comandes comanda ) throws BOException{

		if (comanda == null || comanda.getId() == null || comanda.getPreu() == null
				|| comanda.getPreu().toString().equals("")) {
			throw new BOException("Null comanda to save");
		}
	}

	private void checkComandaId( Comandes comanda ) throws BOException{

		if (comanda == null || comanda.getId() == null) {
			throw new BOException("Null comanda to delete");
		}
	}

	// SETTERS
	public void setComandaDao(ComandaDao comandaDao) {
		this.comandaDao = comandaDao;
	}
	
}
