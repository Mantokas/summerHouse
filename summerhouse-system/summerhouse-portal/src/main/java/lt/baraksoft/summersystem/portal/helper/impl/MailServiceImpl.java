package lt.baraksoft.summersystem.portal.helper.impl;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;

import lt.baraksoft.summersystem.dao.ConfigurationEntryDao;
import lt.baraksoft.summersystem.model.ConfigurationEntryEnum;
import lt.baraksoft.summersystem.portal.helper.MailService;
import lt.baraksoft.summersystem.portal.view.Email;

@Stateless
public class MailServiceImpl implements MailService {

	@Inject
	private ConfigurationEntryDao configurationEntryDao;

	private String senderEmail;
	private String senderPassword;

	@PostConstruct
	public void init() {
		senderEmail = configurationEntryDao.getByType(ConfigurationEntryEnum.EMAIL_USER).getValue();
		senderPassword = configurationEntryDao.getByType(ConfigurationEntryEnum.EMAIL_PW).getValue();
		if (StringUtils.isBlank(senderEmail) || StringUtils.isBlank(senderPassword)) {
			throw new IllegalStateException("senderEmail and senderPassword cannot be null!");
		}
	}

	private class SMTPAuthenticator extends Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(senderEmail, senderPassword);
		}
	}

	@Override
	public void sendMessage(Email email) {
		email.setSender(senderEmail);
		try {
			sendEmailMessage(email);
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
	}

	private void sendEmailMessage(Email email) throws MessagingException {
		Properties props = System.getProperties();
		props = new Properties();
		props.put("mail.smtp.user", senderEmail);
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

	public ConfigurationEntryDao getConfigurationEntryDao() {
		return configurationEntryDao;
	}

	public void setConfigurationEntryDao(ConfigurationEntryDao configurationEntryDao) {
		this.configurationEntryDao = configurationEntryDao;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getSenderPassword() {
		return senderPassword;
	}

	public void setSenderPassword(String senderPassword) {
		this.senderPassword = senderPassword;
	}

}
