package com.online.setup;

// import java specific packages
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;

import com.online.crons.WorkOutPunctuation;

/**
 * This servlet performs the task of setting up the log4j configuration.
 * <p>
 * This servlet is loaded on startup by specifying the load on startup property
 * in the web.xml. On load, it performs the following activities:
 * <ul>
 * Looks for the log4j configuration file.
 * <ul>
 * Configures the PropertyConfigurator using the log4j configuration file if it
 * finds it, otherwise throws an Error on your Tomcat screen. So make sure to
 * check the Tomcat screen once it starts up. However, you will still be able to
 * access the main application, but wont get any log statements as you would
 * expect. NOTE: This illustrates an important point about Log4J. That it is
 * fail safe. The application will not stop running because Log4J could not be
 * set up.
 * 
 * 
 */

@SuppressWarnings({ "serial" })
public class SetupServlet extends HttpServlet{

	public void init( ServletConfig config ) throws ServletException{

		try {

			JobDetail job = new JobDetail();
			job.setName("Calc_Punt");
			job.setJobClass(WorkOutPunctuation.class);

			CronTrigger trigger = new CronTrigger();
			trigger.setName("Calcul_Puntuacions");

			// s'executa cada dia0 0 * * *
			trigger.setCronExpression("0 0 0 * * ?");
			// s'executa cada 30 segons
			// trigger.setCronExpression("0/30 * * * * ?");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}// end init

	public void destroy(){

	}// end destroy

}
