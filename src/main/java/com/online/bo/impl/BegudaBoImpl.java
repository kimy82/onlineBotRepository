package com.online.bo.impl;

import java.util.List;

import com.online.bo.BegudaBo;
import com.online.dao.BegudaDao;
import com.online.exceptions.BOException;
import com.online.model.Beguda;

public class BegudaBoImpl implements BegudaBo{

	private BegudaDao	begudaDao;

	public List<Beguda> getAll(String tipus) throws BOException{
		
		return begudaDao.getAll(tipus);
		
	}
	
	public void save( Beguda beguda ) throws BOException{

		checkBeguda(beguda);
		begudaDao.save(beguda);
	}

	public void update( Beguda beguda ) throws BOException{

		checkBegudaWithId(beguda);
		begudaDao.update(beguda);
	}

	public void delete( Beguda beguda ) throws BOException{

		checkBegudaId(beguda);
		begudaDao.delete(beguda);
	}

	public Beguda load( Long id ) throws BOException{

		if (id == null)
			throw new BOException("NUll id to load");
		return begudaDao.load(id);

	}

	public List<Beguda> getAll(){

		return begudaDao.getAll();
	}

	// PRIVATE METHODS
	private void checkBeguda( Beguda beguda ) throws BOException{

		if (beguda == null || beguda.getNom() == null || beguda.getNom().equals("") || beguda.getPreu() == null
				|| beguda.getPreu().toString().equals("")) {
			throw new BOException("Null beguda to save");
		}
	}

	private void checkBegudaWithId( Beguda beguda ) throws BOException{

		if (beguda == null || beguda.getId() == null || beguda.getNom() == null || beguda.getNom().equals("") || beguda.getPreu() == null
				|| beguda.getPreu().toString().equals("")) {
			throw new BOException("Null beguda to save");
		}
	}

	private void checkBegudaId( Beguda beguda ) throws BOException{

		if (beguda == null || beguda.getId() == null) {
			throw new BOException("Null beguda to delete");
		}
	}

	// SETTERS

	public void setBegudaDao( BegudaDao begudaDao ){

		this.begudaDao = begudaDao;
	}

}
