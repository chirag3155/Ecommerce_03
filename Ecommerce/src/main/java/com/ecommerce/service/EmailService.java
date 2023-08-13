package com.ecommerce.service;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailService {
	public boolean sendEmail(String to, String from, String subject, String text) {
		boolean flag = false;

		// logic
		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true"); // secure socket layer
		properties.put("mail.smtp.allow8bitmime", "true");
//		properties.put("mail.smtp.auth", "true");

		final  String username = "chirag.bhargava09@gmail.com";
		final String password = "yrafuxnbxxtjqjwr";
		// get session object
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}

		});
		session.setDebug(true);

		// Step2:Compose Message[text,multimedia]
//				MimeMessage m=new MimeMessage(session);

		try {
			MimeMessage m = new MimeMessage(session);
			// set from
			m.setFrom(new InternetAddress(from));

			// add receiptent to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// adding subject
			m.setSubject(subject);

			// adding text to message
//			m.setText(text);
			m.setContent(text,"text/html");

			// STep:3 Send Message using Transport class
			Transport.send(m);
			flag = true;
			System.out.println("Sent Success.......");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

}
