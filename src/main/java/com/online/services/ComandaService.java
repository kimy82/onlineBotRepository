package com.online.services;

import java.util.Set;

import com.online.exceptions.ComandaException;
import com.online.model.Plat;


public interface ComandaService{

	public boolean checkPlatForMoreThanTwoRestaurants(Set<Plat> platList, Plat plat) throws ComandaException;
	
	public String createJSONForShoppingCart(Set<Plat> platList) throws ComandaException;

}
