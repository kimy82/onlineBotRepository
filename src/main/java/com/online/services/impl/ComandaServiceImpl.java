package com.online.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.online.exceptions.ComandaException;
import com.online.model.Plat;
import com.online.model.Restaurant;
import com.online.services.ComandaService;
import com.online.utils.Utils;

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

	public String createJSONForShoppingCart(Set<Plat> platList, Long id) throws ComandaException{
		
		Double preuComanda= 0.0;
		List<String> platsSring = new ArrayList<String>();
		for(Plat pl : platList){			
			preuComanda = preuComanda +pl.getPreu();	
			platsSring.add(pl.getNom());
		}
		ComandaCart comandaCart= new  ComandaCart(String.valueOf(preuComanda), platsSring,String.valueOf(platsSring.size()));
		
		Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		StringBuffer json= new StringBuffer(gson.toJson(comandaCart));
		json.setLength(json.length()-1);
		
		json.append(", \"numComanda\" : \""+id+"\" }");
		
		boolean valid =Utils.isValidJSON(json.toString());
		
		return json.toString();
		
	}
	
	public class ComandaCart{
	
		@Expose
		private String preu="0.0";
		
		@Expose
		private List<String> platsNames = new ArrayList<String>();
		
		@Expose
		private String numPlats="0";

		public ComandaCart(String preu, List<String> platsNames,
				String numPlats) {			
			this.preu = preu;
			this.platsNames = platsNames;
			this.numPlats = numPlats;
		}

		public String getPreu() {
			return preu;
		}

		public void setPreu(String preu) {
			this.preu = preu;
		}

		public List<String> getPlatsNames() {
			return platsNames;
		}

		public void setPlatsNames(List<String> platsNames) {
			this.platsNames = platsNames;
		}

		public String getNumPlats() {
			return numPlats;
		}

		public void setNumPlats(String numPlats) {
			this.numPlats = numPlats;
		}
		
		
		
	}
	//GETTERS i SETTERS

	
	//PRIVATE
	
}


