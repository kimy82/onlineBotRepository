package com.online.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.online.exceptions.ComandaException;
import com.online.model.Plat;
import com.online.model.Restaurant;
import com.online.services.ComandaService;

public class ComandaServiceImpl implements ComandaService{
	
	
	
	public boolean checkPlatForMoreThanTwoRestaurants(Set<Plat> platList, Plat plat) throws ComandaException{
		
		boolean moreThanTwo= false;
		
		Set<String> restaurants = new HashSet<String>();
		
		if(platList.size()>=2){
			for(Plat pl : platList){
				Iterator<Restaurant> restaurantsIterator = pl.getRestaurants().iterator();
				while(restaurantsIterator.hasNext()){
					Restaurant rest = restaurantsIterator.next();
					restaurants.add(rest.getNom());
					break;
				}							
			}
			if(restaurants.size()>2){
				throw new ComandaException("S'han assignat mes de dos resturants");
			}		
		}
		
		Iterator<Restaurant> restaurantsIterator = plat.getRestaurants().iterator();
		while(restaurantsIterator.hasNext()){
			Restaurant rest = restaurantsIterator.next();
			restaurants.add(rest.getNom());		
			if(restaurants.size()<=2){
				moreThanTwo=false;
				break;
			}else{
				moreThanTwo=true;
				restaurants.remove(rest.getNom());
			}
		}			
		return moreThanTwo;
	}

	public String createJSONForShoppingCart(Set<Plat> platList) throws ComandaException{
		
		Double preuComanda= 0.0;
		List<String> platsSring = new ArrayList<String>();
		for(Plat pl : platList){			
			preuComanda = preuComanda +pl.getPreu();	
			platsSring.add(pl.getNom());
		}
		ComandaCart comandaCart= new  ComandaCart(preuComanda, platsSring, platsSring.size());
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(comandaCart);		
		
	}
	
	public class ComandaCart{
	
		private Double preu=0.0;
		
		private List<String> platsNames = new ArrayList<String>();
		
		private Integer numPlats=0;

		public ComandaCart(Double preu, List<String> platsNames,
				Integer numPlats) {
			super();
			this.preu = preu;
			this.platsNames = platsNames;
			this.numPlats = numPlats;
		}

		public Double getPreu() {
			return preu;
		}

		public void setPreu(Double preu) {
			this.preu = preu;
		}

		public List<String> getPlatsNames() {
			return platsNames;
		}

		public void setPlatsNames(List<String> platsNames) {
			this.platsNames = platsNames;
		}

		public Integer getNumPlats() {
			return numPlats;
		}

		public void setNumPlats(Integer numPlats) {
			this.numPlats = numPlats;
		}
		
		
		
	}
	//GETTERS i SETTERS

	
	//PRIVATE
	
}


