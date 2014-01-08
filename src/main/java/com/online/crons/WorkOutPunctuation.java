package com.online.crons;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.online.bo.BegudaBo;
import com.online.bo.ConfigRestaurantBo;
import com.online.bo.PlatsBo;
import com.online.bo.RestaurantsBo;
import com.online.bo.VotacionsBo;
import com.online.model.Beguda;
import com.online.model.ConfigRestaurant;
import com.online.model.Plat;
import com.online.model.Restaurant;
import com.online.model.VotacioBeguda;
import com.online.model.VotacioPlat;
import com.online.model.VotacioRestaurant;

public class WorkOutPunctuation implements Job
{
	public void execute(JobExecutionContext context)
	throws JobExecutionException {
		//Calcula les puntuacions dels plats
		System.out.println("Hello Quartz!");
		VotacionsBo votacionsBo = (VotacionsBo)context.getJobDetail().getJobDataMap().get("votacionsBo");
		PlatsBo platsBo = (PlatsBo)context.getJobDetail().getJobDataMap().get("platsBo");
		BegudaBo begudaBo = (BegudaBo)context.getJobDetail().getJobDataMap().get("begudaBo");
		RestaurantsBo restaurantsBo = (RestaurantsBo)context.getJobDetail().getJobDataMap().get("restaurantsBo");	
		ConfigRestaurantBo configRestaurantBo = (ConfigRestaurantBo)context.getJobDetail().getJobDataMap().get("configRestaurantBo");
		
		try {
				List<Restaurant> restaurants = restaurantsBo.getAll(false,false,true);
				for(Restaurant restaurant: restaurants){
					
					netejaConfigObertura(restaurant, restaurantsBo, configRestaurantBo);
					
					int nplats=0;				
					int votacionsPlatsTotals=0;
					Iterator<Plat> plats = restaurant.getPlats().iterator();
					while(plats.hasNext()){
						Plat plat = plats.next();
						if(plat.getVotacio()==null){
							VotacioPlat votacio = new VotacioPlat();
							votacio.setPlat(plat);						
							votacio.setPunctuacio(3);
							plat.setVotacio(votacio);
						}
						int star1 = votacionsBo.count(plat.getId(), 1);
						int star2 = votacionsBo.count(plat.getId(), 2);
						int star3 = votacionsBo.count(plat.getId(), 3);
						int star4 = votacionsBo.count(plat.getId(), 4);
						int star5 = votacionsBo.count(plat.getId(), 5);
						
						int totalVots =star1+star2+star3+star4+star5;
						
						if(totalVots==0){
														
						}else{
							int totalPuntuacio = star1+(star2*2)+(star3*3)+(star4*4)+(star5*5);
							int puntuacio = (totalPuntuacio/totalVots);
							votacionsPlatsTotals=votacionsPlatsTotals+puntuacio;
							plat.getVotacio().setPunctuacio(puntuacio); 
						}					
						nplats= nplats+1;
						plat.setActiu(true);
						platsBo.update(plat);
					}
					if(restaurant.getVotacio()==null){
						VotacioRestaurant votacio = new VotacioRestaurant();
						votacio.setRestaurant(restaurant);						
						votacio.setPunctuacio(3);
						restaurant.setVotacio(votacio);
						restaurantsBo.update(restaurant);
					}else if(nplats==0){
						restaurant.getVotacio().setPunctuacio(3);
					}else{
						int puntuacioRestaurant= votacionsPlatsTotals/nplats;
						restaurant.getVotacio().setPunctuacio(puntuacioRestaurant);
					}
				}
			List<Beguda> begudaList = begudaBo.getAll("vi", true);
				for(Beguda bg : begudaList){
					if(bg.getVotacio()==null){
						VotacioBeguda votacio = new VotacioBeguda();
						votacio.setBeguda(bg);						
						votacio.setPunctuacio(3);
						bg.setVotacio(votacio);
					}
					int star1 = votacionsBo.countBeguda(bg.getId(), 1);
					int star2 = votacionsBo.countBeguda(bg.getId(), 2);
					int star3 = votacionsBo.countBeguda(bg.getId(), 3);
					int star4 = votacionsBo.countBeguda(bg.getId(), 4);
					int star5 = votacionsBo.countBeguda(bg.getId(), 5);
					
					int totalVots =star1+star2+star3+star4+star5;
					
					if(totalVots==0 ){
						bg.getVotacio().setPunctuacio(3);
						
					}else{
						int totalPuntuacio = star1+(star2*2)+(star3*3)+(star4*4)+(star5*5);
						int puntuacio = (totalPuntuacio/totalVots);					
						bg.getVotacio().setPunctuacio(puntuacio);
					}
																				
					begudaBo.update(bg);
				}
	
		} catch (Exception e) {
			System.out.println("Calcul de votacions!");
		}		
		
	}	
	
	private void netejaConfigObertura(Restaurant restaurant, RestaurantsBo restaurantsBo,ConfigRestaurantBo configRestaurantBo){
		
		Set<ConfigRestaurant> confRestaurant =restaurant.getConfigRestaurants();
		Set<ConfigRestaurant> confRestaurantNew = new HashSet<ConfigRestaurant>();
		Set<ConfigRestaurant> confRestaurantToDelete = new HashSet<ConfigRestaurant>(); 
		Date diaAvui = new Date();
		Iterator<ConfigRestaurant> itera = confRestaurant.iterator();
		while(itera.hasNext()){
			ConfigRestaurant cr= itera.next();
			Calendar calConfig = Calendar.getInstance();
			calConfig.setTime(cr.getData());
			Calendar calAvui = Calendar.getInstance();
			calAvui.setTime(diaAvui);
			int dayConfig = calConfig.get(Calendar.DAY_OF_YEAR);
			int dayAvui = calAvui.get(Calendar.DAY_OF_YEAR);
			int yearAvui = calAvui.get(Calendar.YEAR);
			int yearConfig = calConfig.get(Calendar.YEAR);
			if(dayConfig>=dayAvui && yearAvui == yearConfig){
				confRestaurantNew.add(cr);
			}else if(dayConfig<dayAvui && yearAvui == yearConfig){
				confRestaurantToDelete.add(cr);
			}else if(yearAvui < yearConfig){
				confRestaurantToDelete.add(cr);
			}
		}
		restaurant.setConfigRestaurants(confRestaurantNew);
		restaurantsBo.update(restaurant);
		for(ConfigRestaurant config : confRestaurantToDelete){
			configRestaurantBo.delete(config);
		}
	}
}
