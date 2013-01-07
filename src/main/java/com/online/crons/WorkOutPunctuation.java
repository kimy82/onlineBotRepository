package com.online.crons;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class WorkOutPunctuation implements Job
{
	public void execute(JobExecutionContext context)
	throws JobExecutionException {
		//Calcula les puntuacions dels plats
		System.out.println("Hello Quartz!");
		
		ApplicationContext contextSpring = WebApplicationContextUtils.getWebApplicationContext(context);
		Object myDao = contextSpring.getBean("daoBeanName");
		try {
			
				
			
			
		} catch (Exception e) {
			System.out.println("Quartz error!!! enviament mail!");
		}

		
		
	}
	
}
