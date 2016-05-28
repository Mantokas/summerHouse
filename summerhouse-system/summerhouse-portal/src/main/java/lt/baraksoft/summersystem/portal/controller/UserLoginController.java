package lt.baraksoft.summersystem.portal.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

import lt.baraksoft.summersystem.portal.helper.FacebookService;
import lt.baraksoft.summersystem.portal.helper.ReservationViewHelper;
import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
import lt.baraksoft.summersystem.portal.view.ReservationView;
import lt.baraksoft.summersystem.portal.view.UserView;

/**
 * Created by LaurynasC on 2016-04-24.
 */

@Named
@SessionScoped
@Stateful
public class UserLoginController implements Serializable {
	private static final long serialVersionUID = -7850630443992388923L;

	private static final String LOGIN_FAILED = "Blogai įvesti prisijungimo duomenys";

	@EJB
	private UserViewHelper userViewHelper;

	@Inject
	private FacebookService fbService;

	@EJB
	private ReservationViewHelper reservationViewHelper;

	private UserView userView = new UserView();
	private List<ReservationView> myReservations;
	private ReservationView selectedReservation;
	private UserView loggedUser;
	private String email;
	private String password;
	private UploadedFile image;

	public void collectMyReservations() {
		myReservations = reservationViewHelper.getReservations();
	}

	public void checkReservation() {
		if (selectedReservation.isArchived()) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Rezervacija yra negaliojanti!", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('cancelReservationDialog').show();");
		}
	}

	public void cancelReservation() {
		reservationViewHelper.cancelReservation(selectedReservation.getId());
		myReservations = reservationViewHelper.getReservations();
	}

	public void updateUser() {
		userViewHelper.save(loggedUser);
	}

	public void logout() {
		loggedUser = null;
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		request.getSession().invalidate();
	}

	public void uploadImage() {
		try {
			loggedUser.setImage(IOUtils.toByteArray(image.getInputstream()));
		} catch (IOException e) {
			throw new IllegalStateException("Failed to convert image to byte array!");
		}
		userViewHelper.save(loggedUser);
	}

	public void validateLogin() {
		userView.setEmail(email);
		userView.setPassword(password);
		loggedUser = userViewHelper.findUserByLogin(userView);
		if (loggedUser == null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Klaida", LOGIN_FAILED);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return;
	}

	public String checkIfLoggedIn() {
		if (loggedUser != null) {
			return "goToLoggedPage";
		} else {
			return "";
		}
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserView getUserView() {
		return userView;
	}

	public void setUserView(UserView userView) {
		this.userView = userView;
	}

	public void setLoggedUser(UserView loggedUser) {
		this.loggedUser = loggedUser;
	}

	public UserView getLoggedUser() {
		return loggedUser;
	}

	public List<ReservationView> getMyReservations() {
		return myReservations;
	}

	public void setMyReservations(List<ReservationView> myReservations) {
		this.myReservations = myReservations;
	}

	public ReservationView getSelectedReservation() {
		return selectedReservation;
	}

	public void setSelectedReservation(ReservationView selectedReservation) {
		this.selectedReservation = selectedReservation;
	}

	public UploadedFile getImage() {
		return image;
	}

	public void setImage(UploadedFile image) {
		this.image = image;
	}

}
