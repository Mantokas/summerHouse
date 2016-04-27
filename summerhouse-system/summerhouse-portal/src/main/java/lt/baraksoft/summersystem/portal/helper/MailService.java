package lt.baraksoft.summersystem.portal.helper;

import javax.ejb.Local;

import lt.baraksoft.summersystem.portal.view.Email;

@Local
public interface MailService {

	void sendMessage(Email email);
}
