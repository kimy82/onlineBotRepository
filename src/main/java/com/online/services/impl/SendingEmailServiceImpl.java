package com.online.services.impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
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
	        return new PasswordAuthentication("joaquim.orra@gmail.com", "linda82linda");
	    }
	}

	
	public void sendEmail(String textbody, String email) throws EmailException{

		final String username = "joaquim.orra@gmail.com";
		final String password = "linda82linda";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		//
		props.put("mail.smtp.socketFactory.port", 587);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");


		Session session = Session.getInstance(props,new SMTPAuthenticator());

		try {

			session.setDebug(true);

			Message msg = new MimeMessage(session);
			msg.setText(textbody);
			msg.setSubject("Recover password");
			msg.setFrom(new InternetAddress(username));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(username));

			Transport transport = session.getTransport("smtps");
			transport.connect("smtp.gmail.com", 587, username,password);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();


			System.out.println("Done");

		} catch (MessagingException e) {
			throw new EmailException(e,e.getMessage());
		}
	}

	public void sendEmailsTo(String textbody, String[] emails) throws EmailException, AddressException{

		final String username = "joaquim.orra@gmail.com";
		final String password = "linda82linda";

		javax.mail.internet.InternetAddress[] addressTo = new
				javax.mail.internet.InternetAddress[emails.length];

				for (int i = 0; i < emails.length; i++)
				addressTo[i] = new javax.mail.internet.InternetAddress(emails[i]);
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		//
		props.put("mail.smtp.socketFactory.port", 587);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");


		Session session = Session.getInstance(props,new SMTPAuthenticator());

		try {

			session.setDebug(true);

			Message msg = new MimeMessage(session);
			msg.setText(textbody);
			msg.setSubject("Recover password");
			msg.setFrom(new InternetAddress(username));
			msg.setRecipients(javax.mail.Message.RecipientType .TO, addressTo);

			Transport transport = session.getTransport("smtps");
			transport.connect("smtp.gmail.com", 587, username,password);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();


			System.out.println("Done");

		} catch (MessagingException e) {
			throw new EmailException(e,e.getMessage());
		}
	}
}
