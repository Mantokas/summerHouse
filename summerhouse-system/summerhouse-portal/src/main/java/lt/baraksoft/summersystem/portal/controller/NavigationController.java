package lt.baraksoft.summersystem.portal.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import lt.baraksoft.summersystem.dao.ConfigurationEntryDao;
import lt.baraksoft.summersystem.portal.helper.MailService;
import lt.baraksoft.summersystem.portal.view.Email;

/**
 * Created by LaurynasC on 2016-04-18.
 */
@Stateless
@Named
public class NavigationController implements Serializable {
	private static final long serialVersionUID = 2582693109850487119L;

	@Inject
	private UserLoginController userLoginController;

	@Inject
	private ConfigurationEntryDao configurationEntryDao;

	@Inject
	private MailService mailService;

	private int currentTab = 1;

	@PostConstruct
	public void init() {
		// PVZ kaip mail'as siunciamas
		Email email = new Email();
		email.setMessageContent("Sveikiname sėkmingai užsiregistravus mūsų sistemoje!");
		email.setRecipient("mantas.petkeviciuus@gmail.com");
		email.setSubject("Summersystem registration");
		mailService.sendMessage(email);

		// Jei kam reiks pasikurt CE (max naudotoju ir metinis mokestis) mock'as
		// uzkomentintas
		// ConfigurationEntry entry = new ConfigurationEntry();
		// entry.setType(ConfigurationEntryEnum.MAX_USERS_SIZE);
		// entry.setValue("100");
		// configurationEntryDao.save(entry);
		// entry = new ConfigurationEntry();
		// entry.setType(ConfigurationEntryEnum.YEARLY_PAYMENT_PRICE);
		// entry.setValue("20");
		// configurationEntryDao.save(entry);
	}

	public String getActiveClass(int bbs) {
		return bbs == currentTab ? "active" : "";
	}

	public String goToUsersList() {
		currentTab = 2;
		return "toUsers";
	}

	public String goToUserInfo() {
		return "toUserInfo";
	}

	public String goToLoggedUserInfo() {
		return "toLoggedUserInfo";
	}

	public String goToUserRegistration() {
		return "toUserRegistration";
	}

	public String goToSummerhousesSearch() {
		return "toSummerhousesSearch";
	}

	public String goToMain() {
		currentTab = 1;
		return "toMain";
	}

	public String checkLoggedUser() {
		if (userLoginController.getLoggedUser() == null)
			return "toSignCheck";
		return "";
	}

	public String goToPayment() {
		currentTab = 4;
		return "toPayment";
	}

	public String goToMyReservations() {
		currentTab = 5;
		return "toMyReservations";
	}

	public String goToSumerhousesAdministration() {
		currentTab = 6;
		return "toSumerhousesAdministration";
	}

	public String goToUsersAdministration() {
		currentTab = 7;
		return "toUsersAdministration";
	}

	public int getCurrentTab() {
		return currentTab;
	}

	public void setCurrentTab(int currentTab) {
		this.currentTab = currentTab;
	}

	public ConfigurationEntryDao getConfigurationEntryDao() {
		return configurationEntryDao;
	}

	public void setConfigurationEntryDao(ConfigurationEntryDao configurationEntryDao) {
		this.configurationEntryDao = configurationEntryDao;
	}

}
