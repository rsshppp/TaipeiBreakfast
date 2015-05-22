package model.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendGmailService {
	private String subject;
	private String text;
	private String gmail;

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public void SendGmail() {
		String host = "smtp.gmail.com";
		int port = 587;
		final String username = "taipeibreakfast@gmail.com";
		final String password = "taipeibreakfas";

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", port);

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("taipeibreakfast@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(gmail));
			message.setSubject(subject);
			message.setText(text);

			Transport transport = session.getTransport("smtp");
			transport.connect(host, port, username, password);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
