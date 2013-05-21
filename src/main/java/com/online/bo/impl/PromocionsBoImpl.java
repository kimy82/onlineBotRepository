package com.online.bo.impl;

import java.util.Date;
import java.util.List;

import com.online.bo.PromocionsBo;
import com.online.dao.PromocionsDao;
import com.online.exceptions.BOException;
import com.online.model.Promocio;
import com.online.model.PromocioAPartirDe;
import com.online.model.PromocioAssociada;
import com.online.model.PromocioNumComandes;

public class PromocionsBoImpl implements PromocionsBo{

	private PromocionsDao	promocionsDao;

	public void save( Promocio promocio ) throws BOException{

		checkPromocio(promocio);
		promocionsDao.save(promocio);
	}
	
	public void saveAssociada( PromocioAssociada promocio ) throws BOException{

		checkPromocioAssociada(promocio);
		promocionsDao.saveAssociada(promocio);
	}

	public void update( Promocio promocio ) throws BOException{

		checkPromocioWithId(promocio);
		promocionsDao.update(promocio);
	}
	
	public void updateAssociada( PromocioAssociada promocio ) throws BOException{

		checkPromocioAssWithId(promocio);
		promocionsDao.updateAssociada(promocio);
	}

	public Promocio loadWithDates(Integer promoId) throws BOException{
		if(promoId!=null){
			return	promocionsDao.loadWithDates(promoId);
		}	
		return null;
	}
	
	public PromocioAssociada loadWithDatesAssociades(Integer promoId) throws BOException{
		if(promoId!=null){
			return	promocionsDao.loadWithDatesAssociades(promoId);
		}	
		return null;
	}
	
	public void updateNumUsed( Integer promoId,String data) throws BOException{
		if(promoId!=null){
			promocionsDao.updateNumUsed(promoId,data);
		}		
	}
	
	public void updateNumUsedAssociada(Integer promoId,String data) throws BOException{
		if(promoId!=null){
			promocionsDao.updateNumUsedAssociada(promoId,data);
		}	
	}
	
	public void delete( Promocio promocio ) throws BOException{

		checkPromocioId(promocio);
		promocionsDao.delete(promocio);
	}
	
	public void deleteAssociada( PromocioAssociada promocio ) throws BOException{

		checkPromocioAssId(promocio);
		promocionsDao.deleteAssociada(promocio);
	}


	public <E extends Promocio> E load( Integer id ) throws BOException{

		if (id == null)
			throw new BOException("NUll id to load");
		E p = promocionsDao.load(id);
		return p;

	}
	
	public PromocioAssociada  loadAssociada( Integer id ) throws BOException{

		if (id == null)
			throw new BOException("NUll id to load");
		return promocionsDao.loadAssociada(id);
		

	}
	
	public List<PromocioAssociada>   loadAssociadaByCode( String code ) throws BOException{
		if (code == null || code.equals(""))
			throw new BOException("NUll code to load");
		return promocionsDao.loadAssociadaByCode(code);
	}
	
	public List<Promocio> loadByCode( String code ) throws BOException{
		if (code == null || code.equals(""))
			throw new BOException("NUll code to load");
		return promocionsDao.loadByCode(code);
	}

	public List<Promocio> getAll(){

		return this.promocionsDao.getAll();
	}
	
	public List<PromocioAssociada> getAllAssociades(){

		return this.promocionsDao.getAllAssociades();
	}

	public List<PromocioAPartirDe> getPromosAPartirDe( Double importAPartirDe, Date dia,boolean visibility ) throws BOException{

		if (importAPartirDe == null)
			return null;
		List<PromocioAPartirDe> list = this.promocionsDao.getPromosAPartirDe(importAPartirDe, dia,visibility);
		return list;
	}

	public List<PromocioNumComandes> getPromosNumComandes( Integer numComandes, Integer temps,boolean visibility ) throws BOException{

		List<PromocioNumComandes> list = this.promocionsDao.getPromosNumComandes(numComandes, temps, visibility);
		return list;
	}
	
	public List<PromocioAPartirDe> getAllAPartirDe() throws BOException{
		return this.promocionsDao.getAllAPartirDe();
	}
	
	public List<PromocioNumComandes> getAllNumComandes() throws BOException{
		
		return this.promocionsDao.getAllNumComandes();
	}
	
	public List<PromocioAPartirDe> getAllAPartirDe(boolean visibility) throws BOException{
		return this.promocionsDao.getAllAPartirDe(visibility);
	}
	
	public List<PromocioNumComandes> getAllNumComandes(boolean visibility) throws BOException{
		
		return this.promocionsDao.getAllNumComandes(visibility);
	}
	

	// PRIVATE METHODS
	private void checkPromocio( Promocio promocio ) throws BOException{

		if (promocio == null || promocio.getNom() == null || promocio.getNom().equals("") ) {
			throw new BOException("Null promo to save");
		}
	}
	
	private void checkPromocioAssociada( PromocioAssociada promocio ) throws BOException{

		if (promocio == null || promocio.getNom() == null || promocio.getNom().equals("") ) {
			throw new BOException("Null promo to save");
		}
	}

	private void checkPromocioWithId( Promocio promocio ) throws BOException{

		if (promocio == null || promocio.getId() == null || promocio.getNom() == null || promocio.getNom().equals("")) {
			throw new BOException("Null promocio to save");
		}
	}
	
	private void checkPromocioAssWithId( PromocioAssociada promocio ) throws BOException{

		if (promocio == null || promocio.getId() == null || promocio.getNom() == null || promocio.getNom().equals("")) {
			throw new BOException("Null promocio to save");
		}
	}

	private void checkPromocioId( Promocio promocio ) throws BOException{

		if (promocio == null || promocio.getId() == null) {
			throw new BOException("Null promocio to delete");
		}
	}
	
	private void checkPromocioAssId( PromocioAssociada promocio ) throws BOException{

		if (promocio == null || promocio.getId() == null) {
			throw new BOException("Null promocio to delete");
		}
	}

	// SETTERS

	public void setPromocionsDao( PromocionsDao promocionsDao ){

		this.promocionsDao = promocionsDao;
	}

}
