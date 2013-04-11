package com.online.services.impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.online.exceptions.EmailException;
import com.online.services.SendingEmailService;

public class SendingEmailServiceImpl implements SendingEmailService{
	
	private class SMTPAuthenticator extends Authenticator
	{
	    public PasswordAuthentication getPasswordAuthentication()
	    {
	        return new PasswordAuthentication("joaquim.orra@gmail.com", "Hossegor82");
	    }
	}

	
	public void sendEmail(String textbody, String email,String app) throws EmailException{

		final String username = "joaquim.orra@gmail.com";
		final String password = "Hossegor82";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");


		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("portamu@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("PORTAMU Recover password");
			message.setContent(textbody+createFooter(app), "text/html");
 
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new EmailException(e,e.getMessage());
		}
	}

	public void sendEmailsTo(String textbody, String[] emails, String app) throws EmailException, AddressException{

		final String username = "joaquim.orra@gmail.com";
		final String password = "Hossegor82";

		javax.mail.internet.InternetAddress[] addressTo = new
				javax.mail.internet.InternetAddress[emails.length];

				for (int i = 0; i < emails.length; i++)
				addressTo[i] = new javax.mail.internet.InternetAddress(emails[i]);
		
				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");


				Session session = Session.getInstance(props,
						  new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(username, password);
							}
						  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("portamu@gmail.com"));
			message.setRecipients(Message.RecipientType.BCC,addressTo);
			
			message.setSubject("News from PORTAMU");
			
			message.setContent(textbody+createFooter(app), "text/html");
 
			Transport.send(message);
 
			System.out.println("Done");

		} catch (MessagingException e) {
			throw new EmailException(e,e.getMessage());
		}
	}
	
	//PRIVATE
	private String createFooter(String app){
		String footer="<br><a href=\"http://portamu.com/"+app+"/admin/newsletter.action\" >Visit PORTAMU</a>";
		return footer;
	}
}
