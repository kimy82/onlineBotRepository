package com.online.services;

import javax.mail.internet.AddressException;

import com.online.exceptions.EmailException;


public interface SendingEmailService{
	public void sendEmail(String textbody, String email,String app) throws EmailException;
	public void sendEmailsTo(String textbody, String[] emails,String app) throws EmailException, AddressException;

}
