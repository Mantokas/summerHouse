package lt.baraksoft.summersystem.portal.controller;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
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

import lt.baraksoft.summersystem.dao.ConfigurationEntryDao;
import lt.baraksoft.summersystem.model.ConfigurationEntryEnum;
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
	private static final String RESERVATION_CANCEL_ERROR_MESSAGE = "Rezervacija nebegali būti atšaukta";
	private static final String RESERVATION_CANCEL_ERROR_MESSAGE2 = "Iki pradžios liko mažiau nei ";
	private static final String RESERVATION_CANCEL_ERROR_MESSAGE3 = " dienos";
	private static final String RESERVATION_IS_ARCHIVED_ERROR = "Rezervacija yra negaliojanti!";
	private static final String RESERVATION_IS_ARCHIVED_ERROR2 = "";
	private static final String RESERVATION_CANCEL_SUCCESSFUL = "Rezervacija sėkmingai atšaukta";
	private static final String RESERVATION_CANCEL_SUCCESSFUL2 = "";
	private static final String IMAGE_TOO_LARGE = "Paveiksliukas yra per didelis!";
	private static final String ERROR_MESSAGE = "Klaida";
    private static final String USER_UPDATED = "Duomenys atnaujinti";
    private static final String USER_UPDATED2 = "";
	private static final Long MAX_IMAGE_SIZE = 8000000L;

	@EJB
	private UserViewHelper userViewHelper;

	@Inject
	private FacebookService fbService;

	@Inject
	private ConfigurationEntryDao configurationEntryDao;

	@EJB
	private ReservationViewHelper reservationViewHelper;

	private UserView userView = new UserView();
	private List<ReservationView> myReservations;
	private ReservationView selectedReservation;
	private UserView loggedUser;
	private String email;
	private String password;
	private UploadedFile image;
	private boolean editable;
    private boolean skypeNameEnabled;
    private boolean descriptionEnabled;
    private boolean phoneNumberEnabled;

    @PostConstruct
    public void updateDisabledFields(){
        skypeNameEnabled = Boolean.valueOf(configurationEntryDao.getByType(ConfigurationEntryEnum.SKYPE_FIELD).getValue());
        descriptionEnabled = Boolean.valueOf(configurationEntryDao.getByType(ConfigurationEntryEnum.DESCRIPTION_FIELD).getValue());
        phoneNumberEnabled = Boolean.valueOf(configurationEntryDao.getByType(ConfigurationEntryEnum.TELEPHONE_FIELD).getValue());
    }

    public void updateLoggedUser(){
        loggedUser = userViewHelper.getUserByEmail(loggedUser.getEmail());
    }

	public void collectMyReservations() {
		myReservations = reservationViewHelper.getReservations();
	}

	public void checkReservation() {
		int days = Integer.parseInt(configurationEntryDao.getByType(ConfigurationEntryEnum.DAYS_CANCEL_RESERVATION).getValue());
		if (selectedReservation.getDateFrom().isBefore(LocalDate.now().plusDays(days))) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, RESERVATION_CANCEL_ERROR_MESSAGE,
					RESERVATION_CANCEL_ERROR_MESSAGE2 + days + RESERVATION_CANCEL_ERROR_MESSAGE3);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else if (selectedReservation.isArchived()) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, RESERVATION_IS_ARCHIVED_ERROR, RESERVATION_IS_ARCHIVED_ERROR2);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('cancelReservationDialog').show();");
		}
	}

	public void cancelReservation() {
		reservationViewHelper.cancelReservation(selectedReservation);
		myReservations = reservationViewHelper.getReservations();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, RESERVATION_CANCEL_SUCCESSFUL, RESERVATION_CANCEL_SUCCESSFUL2);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void updateUser() {
		try {
            FacesMessage msg;
			if (image != null && (image.getSize() * 2) < MAX_IMAGE_SIZE) {
				loggedUser.setImage(IOUtils.toByteArray(image.getInputstream()));
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, USER_UPDATED, USER_UPDATED2);
                FacesContext.getCurrentInstance().addMessage(null, msg);
			} else if (image != null) {
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ERROR_MESSAGE, IMAGE_TOO_LARGE);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}
		} catch (IOException e) {
			throw new IllegalStateException("Failed to convert image to byte array!");
		}
		editable = false;
		userViewHelper.save(loggedUser);
	}

	public void logout() {
		loggedUser = null;
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		request.getSession().invalidate();
	}

	public void validateLogin() {
		userView.setEmail(email);
		userView.setPassword(password);
		loggedUser = userViewHelper.findUserByLogin(userView);
		if (loggedUser == null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ERROR_MESSAGE, LOGIN_FAILED);
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

	public void doEdit() {
		editable = true;
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

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

    public boolean isSkypeNameEnabled() {
        return skypeNameEnabled;
    }

    public void setSkypeNameEnabled(boolean skypeNameEnabled) {
        this.skypeNameEnabled = skypeNameEnabled;
    }

    public boolean isDescriptionEnabled() {
        return descriptionEnabled;
    }

    public void setDescriptionEnabled(boolean descriptionEnabled) {
        this.descriptionEnabled = descriptionEnabled;
    }

    public boolean isPhoneNumberEnabled() {
        return phoneNumberEnabled;
    }

    public void setPhoneNumberEnabled(boolean phoneNumberEnabled) {
        this.phoneNumberEnabled = phoneNumberEnabled;
    }
}
