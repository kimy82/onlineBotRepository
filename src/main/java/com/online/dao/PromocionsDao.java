package com.online.dao;

import java.util.Date;
import java.util.List;

import com.online.model.Promocio;
import com.online.model.PromocioAPartirDe;
import com.online.model.PromocioAssociada;
import com.online.model.PromocioNumComandes;

public interface PromocionsDao{

	void save( Promocio promocio );
	
	void saveAssociada( PromocioAssociada promocio );

	void update( Promocio promocio );
	
	void updateAssociada( PromocioAssociada promocio );
	
	void updateNumUsed(Integer promoId, String data);
	
	void updateNumUsedAssociada(Integer promoId,String data);

	void delete( Promocio promocio );
	
	void deleteAssociada( PromocioAssociada promocio );

	<E extends Promocio> E load( Integer id );
	
	List<PromocioAssociada>   loadAssociadaByCode( String code );
	
	public List<Promocio> loadByCode( String code );
	
	PromocioAssociada loadAssociada( Integer id );
	
	List<Promocio> getAll();
	 
	List<PromocioAssociada> getAllAssociades();
	
	List<PromocioAPartirDe> getPromosAPartirDe(Double importAPartirDe, Date dia,boolean visibility);
	
	List<PromocioNumComandes> getPromosNumComandes(Integer numComandes, Integer temps,boolean visibility);
	
	List<PromocioAPartirDe> getAllAPartirDe();
	
	List<PromocioNumComandes> getAllNumComandes();
	
	List<PromocioAPartirDe> getAllAPartirDe(boolean visibility);
	
	List<PromocioNumComandes> getAllNumComandes(boolean visibility);
	
	Promocio loadWithDates(Integer promoId);
	
	PromocioAssociada loadWithDatesAssociades(Integer promoId);
	
}
