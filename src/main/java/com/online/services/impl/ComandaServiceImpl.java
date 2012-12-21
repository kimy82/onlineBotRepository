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
import com.online.model.Beguda;
import com.online.model.BegudaComanda;
import com.online.model.Comandes;
import com.online.model.Plat;
import com.online.model.PlatComanda;
import com.online.model.Restaurant;
import com.online.services.ComandaService;
import com.online.utils.Utils;

public class ComandaServiceImpl implements ComandaService{
	
	
	public Double getPreuOfComanda(Comandes comanda) throws ComandaException{
		
		if(comanda==null) return 0.0;
		
		Double preuComanda=0.0;
		List<BegudaComanda> listBeguda =  comanda.getBegudes(); 
		List<PlatComanda> platList = comanda.getPlats();
		if(!listBeguda.isEmpty()){
			for( BegudaComanda begudaComanda : listBeguda){
					
				preuComanda = preuComanda + (begudaComanda.getNumBegudes()*begudaComanda.getBeguda().getPreu());
			}	
		}
		
		if(!platList.isEmpty()){
			for( PlatComanda platComanda : platList){
					
				preuComanda = preuComanda + (platComanda.getNumPlats()*platComanda.getPlat().getPreu());
			}	
		}
		
		return preuComanda;
	}
	
	public String createJSONForBegudaList(List<BegudaComanda> listBeguda) throws ComandaException{
		
		Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		StringBuffer json= new StringBuffer(gson.toJson(listBeguda));
		return json.toString();
		
	}
	
	public List<BegudaComanda>  addBegudaInList(List<BegudaComanda> begudaList, Beguda beguda) throws ComandaException{
		
		boolean existInList = false;
		if(begudaList.size()>0){
			for(BegudaComanda bg : begudaList){
				if(bg.getBeguda().getId().equals(beguda.getId())){
					existInList=true;
					bg.setNumBegudes(bg.getNumBegudes()+1);
				}											
			}				
		}
		if(!existInList){
			BegudaComanda begudaComanda = new BegudaComanda();
			begudaComanda.setBeguda(beguda);
			begudaComanda.setNumBegudes(1);
			begudaList.add(begudaComanda);
		}
		return begudaList;
	}
	
	
	public boolean checkPlatForMoreThanTwoRestaurants(List<PlatComanda> platList, Plat plat) throws ComandaException{ 
		 
		boolean moreThanTwo= false;
		
		Set<String> restaurants = new HashSet<String>();
		
		if(platList.size()>=2){
			for(PlatComanda pl : platList){
				Iterator<Restaurant> restaurantsIterator = pl.getPlat().getRestaurants().iterator();
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

	public String createJSONForShoppingCart(List<PlatComanda> platList, Long id) throws ComandaException{
		
		Double preuComanda= 0.0;
		List<String> platsSring = new ArrayList<String>();	
		Integer numPlats = 0;
			for(PlatComanda pl : platList){			
				platsSring.add(pl.getPlat().getNom());
				preuComanda = preuComanda+(pl.getPlat().getPreu()*pl.getNumPlats());
				numPlats = numPlats+pl.getNumPlats();
			}
			
		
		ComandaCart comandaCart= new  ComandaCart(String.valueOf(preuComanda), platsSring,String.valueOf(numPlats));
		
		Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		StringBuffer json= new StringBuffer(gson.toJson(comandaCart));
		json.setLength(json.length()-1);
		
		json.append(", \"numComanda\" : \""+id+"\" }");
		
		boolean valid =Utils.isValidJSON(json.toString());
		
		return json.toString(); 
		
	}
	
	public boolean checkPlatInList(List<PlatComanda> platList, Plat plat) throws ComandaException{
		for(PlatComanda plt : platList){
			if(plt.getPlat().getId().toString().equals(plat.getId().toString())){
				return true;
			}
		}
		return false;
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


