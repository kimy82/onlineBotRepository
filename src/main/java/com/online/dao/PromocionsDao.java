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

	void delete( Promocio promocio );
	
	void deleteAssociada( PromocioAssociada promocio );

	<E extends Promocio> E load( Integer id );
	
	PromocioAssociada loadAssociada( Integer id );
	
	List<Promocio> getAll();
	
	List<PromocioAssociada> getAllAssociades();
	
	List<PromocioAPartirDe> getPromosAPartirDe(Double importAPartirDe, Date dia);
	
	List<PromocioNumComandes> getPromosNumComandes(Integer numComandes, Integer temps);
	
	List<PromocioAPartirDe> getAllAPartirDe();
	
	List<PromocioNumComandes> getAllNumComandes();
	
	Promocio loadWithDates(Integer promoId);
}
