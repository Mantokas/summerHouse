package lt.baraksoft.summersystem.portal.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import lt.baraksoft.summersystem.portal.helper.MailService;
import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
import lt.baraksoft.summersystem.portal.view.Email;
import lt.baraksoft.summersystem.portal.view.UserView;

@Named
@SessionScoped
public class UserController implements Serializable {
	private static final long serialVersionUID = -4973058412300951890L;
	private static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

	@Inject
	private MailService mailService;
	@Inject
	private UserViewHelper userViewHelper;
	@Inject
	private UserLoginController userLoginController;

	private UserView selectedUser;
	private List<UserView> users;
	private String invitationEmail;

	@PostConstruct
	public void init() {
		users = userViewHelper.getAllUsers();
	}

	public void doApprove() {
		if (!selectedUser.isApproved()) {
			selectedUser.setApproved(true);
			userViewHelper.save(selectedUser);
		}
	}

	public void sendInvitationMessage() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (!invitationEmail.matches(EMAIL_REGEX)) {
			context.addMessage(null, new FacesMessage("Klaida!", "El. paštas įvestas neteisingai!"));
			return;
		}

		UserView oldUser = userViewHelper.getUserByEmail(invitationEmail);
		if (oldUser != null) {
			context.addMessage(null, new FacesMessage("Klaida!", "Toks naudotojas sistemoje jau yra!"));
			return;
		}

		Email email = new Email();
		email.setMessageContent("Klubo narys: " + userLoginController.getLoggedUser().getFirstName()
				+" " + userLoginController.getLoggedUser().getLastName() + " atsiuntė jums pakvietimą prisijungti prie klubo! Spauskite ant nuorodos, kad galėtumėte pradėti registraciją \n http://193.219.91.103:13579/summerhouse-portal");
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

}
