package com.online.bo;

import java.util.Date;
import java.util.List;

import com.online.exceptions.BOException;
import com.online.model.Promocio;
import com.online.model.PromocioAPartirDe;
import com.online.model.PromocioAssociada;
import com.online.model.PromocioNumComandes;

public interface PromocionsBo{

	void save( Promocio promocio ) throws BOException;
	
	void saveAssociada( PromocioAssociada promocio ) throws BOException;

	void update( Promocio promocio ) throws BOException;
	
	void updateAssociada( PromocioAssociada promocio ) throws BOException;
	
	void updateNumUsed( Integer promoId,String data) throws BOException;
	
	void updateNumUsedAssociada(Integer promoId,String data) throws BOException;

	void delete( Promocio promocio ) throws BOException;
	
	void deleteAssociada( PromocioAssociada promocio ) throws BOException;
	
	List<Promocio> getAll();
	
	List<PromocioAssociada> getAllAssociades();

	<E extends Promocio> E load( Integer id ) throws BOException;
	
	PromocioAssociada  loadAssociada( Integer id ) throws BOException;
	
	List<PromocioAssociada>  loadAssociadaByCode( String code ) throws BOException;
	
	List<PromocioAPartirDe> getPromosAPartirDe(Double importAPartirDe, Date dia)throws BOException;
	
	List<PromocioNumComandes> getPromosNumComandes(Integer numComandes, Integer temps)throws BOException;
	
	List<PromocioAPartirDe> getAllAPartirDe() throws BOException;
	
	List<PromocioNumComandes> getAllNumComandes() throws BOException;

	Promocio loadWithDates(Integer promoId) throws BOException;
	
	PromocioAssociada loadWithDatesAssociades(Integer promoId) throws BOException;
}
