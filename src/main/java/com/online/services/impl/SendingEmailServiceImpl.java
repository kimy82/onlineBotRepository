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
		
		StringBuffer footer = new StringBuffer("<table width=\"100%\" height=\"100%\" background=\"http://www.portamu.com/elteurestaurantacasa/img/elements/bground.jpg\">");
					              	footer.append("<tbody>");
					                  	footer.append("<tr>");
					                  			footer.append("<td>");
					                  				footer.append("<table width=\"700px\" height=\"100%\" align=\"center\" style=\"margin-top:70px;\">");
					                  					footer.append("<tbody>");
					                  						footer.append("<tr>");
					                  							footer.append("<td>");
					                  								footer.append("<table align=\"center\" bordeer-colapse=\"separated\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"width:700px; border-bottom-width:1px;border-top-style:solid;border-right-style:solid;border-bottom-color:#c0c0c0;border-top-width:1px;border-bottom-style:solid;border-top-color:#c0c0c0;border-left-color:#c0c0c0;border-right-color:#c0c0c0;border-left-style:solid;border-right-width:1px;border-left-width:1px\">");
					                  									footer.append("<tbody>");
					                  										footer.append("<tr>");
					                  											footer.append("<td>");
					                  												footer.append("<img src=\"http://www.alexmany.com/fonspor.png\">");
					                  											footer.append("</td>");
					                  										footer.append("</tr>");	
					                  									footer.append("</tbody>");
					                  								footer.append("</table>");
					                  								footer.append("<table cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"margin-top:30px; width:700px; border-bottom-width:1px;border-top-style:solid;border-right-style:solid;border-bottom-color:#c0c0c0;border-top-width:1px;border-bottom-style:solid;border-top-color:#c0c0c0;border-left-color:#c0c0c0;border-right-color:#c0c0c0;border-left-style:solid;border-right-width:1px;border-left-width:1px\">");
					                  									footer.append("<tbody>");
					                  										footer.append("<tr>");
					                  											footer.append("<td style=\"padding:20px; font-family:arial; color:#333; background-color:white; font-size:14px;\">");
					                  												footer.append("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."); 
					                  											footer.append("</td>");
																			footer.append("</tr>");
																		footer.append("</tbody>");
																	footer.append("</table>");
																	footer.append("<table cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"margin-top:30px; width:700px; border-bottom-width:1px;border-top-style:solid;border-right-style:solid;border-bottom-color:#c0c0c0;border-top-width:1px;border-bottom-style:solid;border-top-color:#c0c0c0;border-left-color:#c0c0c0;border-right-color:#c0c0c0;border-left-style:solid;border-right-width:1px;border-left-width:1px\">");
																		footer.append("<tbody>");
																			footer.append("<tr>");
																				footer.append("<td style=\"padding:20px; font-family:arial; color:#333; background-color:white; font-size:14px;\">");
																					footer.append("Disposem d'una selecció de restaurants de Girona que no disposen de servei d'entrega a domicili. La nostra voluntat es tenir una oferta àmplia i de qualitat."); 
																				footer.append("</td>");
																			footer.append("</tr>");
																		footer.append("</tbody>");
																	footer.append("</table>");
																	footer.append("<table cellspacing=\"0\" cellpadding=\"0\" align=\"center\">");
																			footer.append("<tbody>");
																				footer.append("<tr>");
																					footer.append("<td style=\"padding:20px; font-family:arial; color:#333; font-size:12px;\">");
																						footer.append("PORTAMU ROGASO SL · B55163265 · Carrer nord numero 6 local 4 · <a href=\"http://www.twitter.com/portamu\" style=\"text-decoration:none; color:#F79433\" target=\"_blank\">@portamu.com</a>");
																					footer.append("</td>");
																				footer.append("</tr>");
																			footer.append("</tbody>");
																	footer.append("</table>");
																footer.append("</td>");
															footer.append("</tr>");
														footer.append("</tbody>");
													footer.append("</table>");
												footer.append("</td>");
											footer.append("</tr>");			
										footer.append("</table>");
																																																									 footer.append("</tr>");
																																																											 footer.append("</table>");
		
		return footer.toString();
	}

	public void setClauBo(ClauBo clauBo) {
		this.clauBo = clauBo;
	}
	
}
