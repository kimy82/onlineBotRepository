package com.online.services;

import java.util.List;
import java.util.ResourceBundle;

import com.online.exceptions.ComandaException;
import com.online.model.Beguda;
import com.online.model.BegudaComanda;
import com.online.model.Comandes;
import com.online.model.Plat;
import com.online.model.PlatComanda;


public interface ComandaService{

	public boolean checkPlatForMoreThanTwoRestaurants(List<PlatComanda> platList, Plat plat) throws ComandaException;
	
	public List<BegudaComanda> addBegudaInList(List<BegudaComanda> begudaList, Beguda beguda,boolean promo) throws ComandaException;
	 
	public String createJSONForShoppingCart(List<PlatComanda> platList, Long id) throws ComandaException;
	
	public String createJSONForBegudaList(List<BegudaComanda> listBeguda) throws ComandaException;
	
	public Double getPreuOfComanda(Comandes comanda) throws ComandaException;

	public boolean checkPlatInList(List<PlatComanda> platList, Plat plat) throws ComandaException;
	
	public String checkComandaProblems(Comandes comanda, ResourceBundle resource) throws ComandaException;
	
	public String checkComandaPromocions(Comandes comanda, ResourceBundle resource) throws ComandaException;
	
}
 