package com.flightbooking.app.utils;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
@Component
public class NotificationByEmail {

	private static Logger log = Logger.getLogger(NotificationByEmail.class);
	
	public void mailOTP(String email, String toMessage) throws Exception {
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("flightbookapp@gmail.com", "Password");
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("flightbookapp@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("Flight Booking Notification");
			message.setText(toMessage);

			Transport.send(message);
			log.info(">>>>>Mail Sent.........Kindly check ur mail.....>>>>");

		} catch (MessagingException e) {
			e.getMessage();
			log.info(">>>>>.....Mail SMTP Server is Busy..........>>>>");
		}

	}

}
