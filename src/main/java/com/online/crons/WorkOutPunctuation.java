package com.online.crons;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.online.bo.UsersBo;

public class WorkOutPunctuation implements Job
{
	public void execute(JobExecutionContext context)
	throws JobExecutionException {
		//Calcula les puntuacions dels plats
		System.out.println("Hello Quartz!");
		UsersBo userBo = (UsersBo)context.getJobDetail().getJobDataMap().get("usersBo");
		try {
			
				System.out.println("eeee");
			
			
		} catch (Exception e) {
			System.out.println("Quartz error!!! enviament mail!");
		}

		
		
	}	
}
