package model.service;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

// (-.-)*杜
public class SendMailSMTP{

	  //用於登入
	  private final static String us = "taipeibreakfast@gmail.com";
	  private final static String pa = "taipeibreakfas";
	  private final static String sm = "smtp";
	  private final static String ho = "gmail-smtp.l.google.com";
	  private final static int po = 587;
	  
	  private Properties setProperties(){
//		  String ho=h;
//		  String sm=s;
	      //Set the host smtp address
	      Properties props = new Properties();
	      props.put("mail.transport.protocol", sm);
	      props.put("mail.smtp.starttls.enable","true");
		  props.put("mail.smtp.host", ho);
	      props.put("mail.smtp.port", "587");
	      props.put("mail.smtp.auth", "true");
		  return props;
	  }
	  
	  public SendMailSMTP(){
	  }
	  
	  public boolean send(String MemberMail,String Subject,String Text){
		  boolean result=false;
//		  String us=u;
//		  String pa=p;
//		  String ho=h;
//		  String sm=s;
//		  int po = this.po;
  	      Properties props = setProperties();

		  Session session = Session.getInstance(
				  props, new Authenticator() {
					  protected PasswordAuthentication getPasswordAuthentication() {
						  return new PasswordAuthentication(us, pa);
					  }
				  }								);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(us));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(MemberMail));
			message.setSubject(Subject);
			message.setText(Text);

			Transport transport = session.getTransport(sm);
			transport.connect(ho, po, us, pa);
			Transport.send(message);
			result = true;

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		return result;
	  }
	  
}
