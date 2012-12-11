package com.online.services;

import java.util.List;

import com.online.exceptions.ComandaException;
import com.online.model.Plat;


public interface ComandaService{

	public boolean checkPlatForMoreThanTwoRestaurants(List<Plat> platList, Plat plat) throws ComandaException;
	 
	public String createJSONForShoppingCart(List<Plat> platList, Long id,String[] repetitsVecs) throws ComandaException;

	public boolean checkPlatInList(List<Plat> platList, Plat plat) throws ComandaException;
}
 