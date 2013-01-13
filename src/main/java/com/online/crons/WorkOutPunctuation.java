package com.online.crons;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.online.bo.PlatsBo;
import com.online.bo.UsersBo;
import com.online.bo.VotacionsBo;
import com.online.model.Plat;
import com.online.model.Votacio;

public class WorkOutPunctuation implements Job
{
	public void execute(JobExecutionContext context)
	throws JobExecutionException {
		//Calcula les puntuacions dels plats
		System.out.println("Hello Quartz!");
		UsersBo userBo = (UsersBo)context.getJobDetail().getJobDataMap().get("usersBo");
		VotacionsBo votacionsBo = (VotacionsBo)context.getJobDetail().getJobDataMap().get("votacionsBo");
		PlatsBo platsBo = (PlatsBo)context.getJobDetail().getJobDataMap().get("platsBo");
		
		try {
				List<Plat> plats = platsBo.getAll();
				
				for(Plat plat: plats){
					if(plat.getVotacio()==null){
						Votacio votacio = new Votacio();
						votacio.setPlat(plat);						
						votacio.setPunctuacio(3);
						plat.setVotacio(votacio);
					}
					int star1 = votacionsBo.count(plat.getId(), 1);
					int star2 = votacionsBo.count(plat.getId(), 2);
					int star3 = votacionsBo.count(plat.getId(), 3);
					int star4 = votacionsBo.count(plat.getId(), 4);
					int star5 = votacionsBo.count(plat.getId(), 5);
					
					if(star1>=star2 && star1>=star3 && star1>=star4 && star1>=star5){
						plat.getVotacio().setPunctuacio(1); 
					}
					if(star2>=star1 && star2>=star3 && star2>=star4 && star2>=star5){
						plat.getVotacio().setPunctuacio(2); 
					}
					if(star3>=star2 && star3>=star1 && star3>=star4 && star3>=star5){
						plat.getVotacio().setPunctuacio(3); 
					}
					if(star4>=star2 && star4>=star3 && star4>=star1 && star4>=star5){
						plat.getVotacio().setPunctuacio(4); 
					}
					if(star5>=star2 && star5>=star3 && star5>=star4 && star5>=star1){
						plat.getVotacio().setPunctuacio(5); 
					}
					
					platsBo.update(plat);
				}
				
			
		} catch (Exception e) {
			System.out.println("Calcul de votacions!");
		}

		
		
	}	
}
