package lt.baraksoft.summersystem.portal.controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
import lt.baraksoft.summersystem.portal.view.UserView;

@Named
@Stateful
@RequestScoped
public class RegistrationController implements Serializable {
	private static final long serialVersionUID = 4283433840276008899L;

	@Inject
	private UserViewHelper userViewHelper;

	@Inject
	private UserLoginController userLoginController;

	private UserView view;
	private boolean emailExist;
	private boolean dialogVisible;
	private String birthday;

	@PostConstruct
	public void init() {
		view = new UserView();
	}

	public void registerUser() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			view.setBirthday(LocalDate.parse(birthday, formatter));
		} catch (DateTimeParseException ex) {
			context.addMessage(":userRegistrationForm:messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Neteisinga gimimo data!", ""));
		}

		emailExist = userViewHelper.register(view);
		if (!emailExist) {
			context.addMessage(":userRegistrationForm:messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Toks el. paštas jau egzistuoja!", ""));
		}

		List<FacesMessage> messagesList = context.getMessageList();
		if (messagesList.isEmpty()) {
			RequestContext context2 = RequestContext.getCurrentInstance();
			context2.execute("PF('confirmDialog').show();");
		}
	}

	public void setLoggedUser() {
		userLoginController.setLoggedUser(view);
	}

	public UserView getUserView() {
		return view;
	}

	public void setUserView(UserView view) {
		this.view = view;
	}

	public boolean isEmailExist() {
		return emailExist;
	}

	public void setEmailExist(boolean emailExist) {
		this.emailExist = emailExist;
	}

	public boolean isDialogVisible() {
		return dialogVisible;
	}

	public void setDialogVisible(boolean dialogVisible) {
		this.dialogVisible = dialogVisible;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}
