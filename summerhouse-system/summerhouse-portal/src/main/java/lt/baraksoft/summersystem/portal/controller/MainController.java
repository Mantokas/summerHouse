package lt.baraksoft.summersystem.portal.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
public class MainController implements Serializable {
	private static final long serialVersionUID = -3124976676079274504L;
	private static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

	@Inject
	private MailService mailService;

	@Inject
	private UserLoginController userLoginController;

	@EJB
	private UserViewHelper userViewHelper;

	private String recommendationEmail;
	private String errorMessage;

	@PostConstruct
	public void init() {

	}

	public void sendApproveMessage() {
		FacesContext context = FacesContext.getCurrentInstance();
		errorMessage = "";
		if (!recommendationEmail.matches(EMAIL_REGEX)) {
			context.addMessage(null, new FacesMessage("Klaida!", "El. paštas įvestas neteisingai!"));
			return;
		}

		UserView oldUser = userViewHelper.getUserByEmail(recommendationEmail);
		if (oldUser == null || !oldUser.isApproved()) {
			context.addMessage(null, new FacesMessage("Klaida!", "Tokio naudotojo sistemoje nėra arba jis taip pat nėra patvirtintas!"));
			return;
		}

		Email email = new Email();
		email.setMessageContent("Naujas narys: " + userLoginController.getLoggedUser().getEmail()
				+ " atsiuntė jums rekomendacijos prašymą! Prisijunkite prie sistemos, kad galėtumėte patvirtinti naują naudotoją.");
		email.setRecipient(recommendationEmail);
		email.setSubject("Labanoro draugai");
		mailService.sendMessage(email);
		context.addMessage(null, new FacesMessage("Pavyko!", "Rekomendacijos užklausa išsiųsta naudotojui!"));
	}

	public String getRecommendationEmail() {
		return recommendationEmail;
	}

	public void setRecommendationEmail(String recommendationEmail) {
		this.recommendationEmail = recommendationEmail;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}