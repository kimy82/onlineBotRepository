package com.online.services.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.online.bo.ClauBo;
import com.online.exceptions.EmailException;
import com.online.model.Clau;
import com.online.services.SendingEmailService;

public class SendingEmailServiceImpl implements SendingEmailService{
	
	private ClauBo				clauBo;
	
	public void sendEmail(String textbody, String email,String app) throws EmailException{

		final String username = "hola@portamu.com";
		final String password = clauBo.getClau("email").getCode();

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.trust", "smtp.portamu.com");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.portamu.com");
		props.put("mail.smtp.port", "9721");



		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("hola@portamu.com"));
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


		final String username = "hola@portamu.com";
		final String password = clauBo.getClau("email").getCode();
		
		javax.mail.internet.InternetAddress[] addressTo = new
				javax.mail.internet.InternetAddress[emails.length];

				for (int i = 0; i < emails.length; i++)
				addressTo[i] = new javax.mail.internet.InternetAddress(emails[i]);
		
				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.ssl.trust", "smtp.portamu.com");
				props.put("mail.smtp.host", "smtp.portamu.com");
				props.put("mail.smtp.port", "9721");


				Session session = Session.getInstance(props,
						  new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(username, password);
							}
						  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("hola@portamu.com"));
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
		String footer="<br><a href=\"https://portamu.com/"+app+"/admin/newsletter.action\" >Visit PORTAMU</a>";
		return footer;
	}

	public void setClauBo(ClauBo clauBo) {
		this.clauBo = clauBo;
	}
	
}
