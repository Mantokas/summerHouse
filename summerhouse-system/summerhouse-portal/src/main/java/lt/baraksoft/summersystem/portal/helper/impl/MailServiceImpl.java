package lt.baraksoft.summersystem.portal.helper.impl;

import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import lt.baraksoft.summersystem.portal.helper.MailService;
import lt.baraksoft.summersystem.portal.view.Email;

@Stateless
public class MailServiceImpl implements MailService {

	private class SMTPAuthenticator extends Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			// Paslepti autentifikacijos duomenis (probably i duombaze)
			return new PasswordAuthentication("baraksoft1201@gmail.com", "Baraksoftas");
		}
	}

	@Override
	public void sendMessage(Email email) {
		email.setSender("baraksoft1201@gmail.com");
		try {
			sendEmailMessage(email);
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
	}

	private void sendEmailMessage(Email email) throws MessagingException {
		Properties props = System.getProperties();
		props = new Properties();
		props.put("mail.smtp.user", "baraksoft1201@gmail.com");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		SMTPAuthenticator auth = new SMTPAuthenticator();
		Session session = Session.getInstance(props, auth);
		session.setDebug(false);

		MimeMessage msg = new MimeMessage(session);
		msg.setText(email.getMessageContent());
		msg.setSubject(email.getSubject());
		msg.setFrom(new InternetAddress(email.getSender()));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getRecipient()));

		Transport transport = session.getTransport("smtps");
		transport.connect("smtp.gmail.com", 465, "username", "password");
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
	}

}
