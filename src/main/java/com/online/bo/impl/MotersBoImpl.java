package com.online.bo.impl;

import java.util.Date;
import java.util.List;

import com.online.bo.MotersBo;
import com.online.dao.MotersDao;
import com.online.exceptions.BOException;
import com.online.model.Moters;

public class MotersBoImpl implements MotersBo{

	private MotersDao	motersDao;

	public void save( Moters moter ) throws BOException{
		checkMoterToSave(moter);
		motersDao.save(moter);
	}

	public void update( Moters moter ) throws BOException{

		checkMoterWithId(moter);
		motersDao.update(moter);
	}

	public Moters load( Integer id ) throws BOException{

		if (id == null)
			throw new BOException("NUll id to load");
		return motersDao.load(id);

	}

	public List<Moters> load( Date date ) throws BOException{

		if (date == null) return null; 		
		return motersDao.load(date);

	}
	
	public Moters load( String hora, Date dia ) throws BOException{

		if (dia == null || hora==null || hora.equals("")) return null; 		
		return motersDao.load( hora, dia );
	}

	// PRIVATE METHODS

	private void checkMoterToSave( Moters moter ) throws BOException{

		if (moter == null || moter.getNumeroMoters() == null || moter.getData() == null) {
			throw new BOException("Null moter to save");
		}
	}

	
	private void checkMoterWithId( Moters moter ) throws BOException{

		if (moter == null || moter.getId() == null || moter.getNumeroMoters() == null || moter.getData() == null) {
			throw new BOException("Null moter to save");
		}
	}

	// SETTERS
	public void setMotersDao( MotersDao motersDao ){

		this.motersDao = motersDao;
	}

	
}
