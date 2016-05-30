package lt.baraksoft.summersystem.portal.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import lt.baraksoft.summersystem.dao.ConfigurationEntryDao;
import lt.baraksoft.summersystem.model.ConfigurationEntryEnum;
import lt.baraksoft.summersystem.portal.helper.MailService;
import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
import lt.baraksoft.summersystem.portal.view.Email;
import lt.baraksoft.summersystem.portal.view.UserView;

@Named
@SessionScoped
public class UserController implements Serializable {
	private static final long serialVersionUID = -4973058412300951890L;
	private static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

	@EJB
	private MailService mailService;

	@EJB
	private UserViewHelper userViewHelper;

	@Inject
	private UserLoginController userLoginController;

	@Inject
	private ConfigurationEntryDao configurationEntryDao;

	private UserView selectedUser;
	private UserView loggedUser;
	private List<UserView> users;
	private String invitationEmail;
    private int lastGroup = 0;

	@PostConstruct
	public void init() {
		users = userViewHelper.getAllUsers();
		loggedUser = userLoginController.getLoggedUser();
	}

	public void refreshUsers() {
		users = userViewHelper.getAllUsers();
	}

	public void makeSelectedUser(UserView userView) {
		selectedUser = userView;
	}

	public void doApprove() {
		int minApprovers = Integer.valueOf(configurationEntryDao.getByType(ConfigurationEntryEnum.APPROVERS_SIZE).getValue());
		if (!selectedUser.isApproved() && !selectedUser.getApprovers().contains(loggedUser.getEmail())) {
			selectedUser.getApprovers().add(loggedUser.getEmail());
			if (selectedUser.getApprovers().size() >= minApprovers){        //naujai patvirtintam nariui priskiriama paskutine grupe
				selectedUser.setApproved(true);

                users = userViewHelper.getAllUsers();
                users.stream().forEach(userView -> {
                    if (userView.getGroupNumber() > lastGroup){
                        lastGroup = userView.getGroupNumber();
                    }
                });

                List<UserView> lastGroupUsers = userViewHelper.getLastGroupUsers(lastGroup);

                Integer maxSize = Integer.valueOf(configurationEntryDao.getByType(ConfigurationEntryEnum.GROUP_SIZE).getValue());

                if (lastGroupUsers.size() < maxSize){
                    selectedUser.setGroupNumber(lastGroup);
                }
                else{
                    selectedUser.setGroupNumber(lastGroup+1);
                }
			}
			else{
				selectedUser.setApproved(false);
			}
			userViewHelper.save(selectedUser);
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Klaida!", "Jūs jau patvirtinote šį vartotoją!"));
		}
	}

	public String goToDetailedUser(UserView userView) {
		selectedUser = userView;
		return "/userinfo.xhtml?faces-redirect=true";
	}

	public void sendInvitationMessage() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (!invitationEmail.matches(EMAIL_REGEX)) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Klaida!", "El. paštas įvestas neteisingai!"));
			return;
		}

		UserView oldUser = userViewHelper.getUserByEmail(invitationEmail);
		if (oldUser != null) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Klaida!", "Toks naudotojas sistemoje jau yra!"));
			return;
		}

		Email email = new Email();
		email.setMessageContent("Klubo narys: " + loggedUser.getFirstName() + " " + loggedUser.getLastName()
				+ " atsiuntė jums pakvietimą prisijungti prie klubo! Spauskite ant nuorodos, kad galėtumėte pradėti registraciją \n http://193.219.91.103:13579/summerhouse-portal");
		email.setRecipient(invitationEmail);
		email.setSubject("Pakvietimas į klubą \"Labanoro draugai\"");
		mailService.sendMessage(email);
		context.addMessage(null, new FacesMessage("Pavyko!", "Pakvietimas išsiųstas!"));
	}

	public String getInvitationEmail() {
		return invitationEmail;
	}

	public void setInvitationEmail(String invitationEmail) {
		this.invitationEmail = invitationEmail;
	}

	public UserView getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(UserView selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<UserView> getUsers() {
		return users;
	}

	public void setUsers(List<UserView> users) {
		this.users = users;
	}

	public UserView getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(UserView loggedUser) {
		this.loggedUser = loggedUser;
	}

	public ConfigurationEntryDao getConfigurationEntryDao() {
		return configurationEntryDao;
	}

	public void setConfigurationEntryDao(ConfigurationEntryDao configurationEntryDao) {
		this.configurationEntryDao = configurationEntryDao;
	}
}
