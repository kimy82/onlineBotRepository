package com.online.services;

import java.util.List;

import com.online.exceptions.ComandaException;
import com.online.model.Plat;
import com.online.model.PlatComanda;


public interface ComandaService{

	public boolean checkPlatForMoreThanTwoRestaurants(List<PlatComanda> platList, Plat plat) throws ComandaException;
	 
	public String createJSONForShoppingCart(List<PlatComanda> platList, Long id) throws ComandaException;

	public boolean checkPlatInList(List<PlatComanda> platList, Plat plat) throws ComandaException;
}
 