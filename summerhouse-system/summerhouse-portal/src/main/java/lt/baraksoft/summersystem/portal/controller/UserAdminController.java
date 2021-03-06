package lt.baraksoft.summersystem.portal.controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import lt.baraksoft.summersystem.dao.ConfigurationEntryDao;
import lt.baraksoft.summersystem.model.ConfigurationEntry;
import lt.baraksoft.summersystem.model.ConfigurationEntryEnum;
import lt.baraksoft.summersystem.portal.helper.GroupService;
import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
import lt.baraksoft.summersystem.portal.view.UserView;

@Named
@SessionScoped
public class UserAdminController implements Serializable {
	private static final long serialVersionUID = -8711749859957428877L;

	private static final String CHANGES_SAVED = "Pakeitimai išsaugoti";
	private static final String CHANGES_SAVED2 = "";
	private static final String CHANGES_SAVED3 = "Nariai sėkmingai suskirstyti į grupes!";
	private static final String DATE_PATTERN = "yyyy-MM-dd";

	@EJB
	private UserViewHelper userViewHelper;

	@EJB
	private GroupService groupService;

	@Inject
	private ConfigurationEntryDao configurationEntryDao;

	private List<UserView> usersList;
	private UserView selectedUser;
	private String yearlyPayment;
	private String maxUsersSize;
	private String approversSize;
	private String groupSize;
	private String maxEmailsAvailable;
	private String cancelReservationDays;
	private Date reservationsStart;
	private UserView user = new UserView();
	private String points = "";
	private boolean skypeFieldVisible;
	private boolean telephoneFieldVisible;
	private boolean descriptionFieldVisible;
	private Date today;

	@PostConstruct
	public void init() {
		reloadUsersList();
		yearlyPayment = configurationEntryDao.getByType(ConfigurationEntryEnum.YEARLY_PAYMENT_PRICE).getValue();
		maxUsersSize = configurationEntryDao.getByType(ConfigurationEntryEnum.MAX_USERS_SIZE).getValue();
		approversSize = configurationEntryDao.getByType(ConfigurationEntryEnum.APPROVERS_SIZE).getValue();
		maxEmailsAvailable = configurationEntryDao.getByType(ConfigurationEntryEnum.MAX_REC_EMAILS_SENT).getValue();
		cancelReservationDays = configurationEntryDao.getByType(ConfigurationEntryEnum.DAYS_CANCEL_RESERVATION).getValue();
		skypeFieldVisible = Boolean.valueOf(configurationEntryDao.getByType(ConfigurationEntryEnum.SKYPE_FIELD).getValue());
		telephoneFieldVisible = Boolean.valueOf(configurationEntryDao.getByType(ConfigurationEntryEnum.TELEPHONE_FIELD).getValue());
		descriptionFieldVisible = Boolean.valueOf(configurationEntryDao.getByType(ConfigurationEntryEnum.DESCRIPTION_FIELD).getValue());
		groupSize = configurationEntryDao.getByType(ConfigurationEntryEnum.GROUP_SIZE).getValue();

		today = new Date();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
		LocalDate date = LocalDate.parse(configurationEntryDao.getByType(ConfigurationEntryEnum.RESERVATION_START_DATE).getValue(), formatter);
		reservationsStart = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	private void reloadUsersList() {
		usersList = userViewHelper.getAllUsers();
	}

	public void doArchive() {
		selectedUser.setArchived(true);
		userViewHelper.save(selectedUser);
	}

	public void doReset() {
		selectedUser.setArchived(false);
		userViewHelper.save(selectedUser);
		createChangesSuccessMessage(CHANGES_SAVED);
	}

	private void createChangesSuccessMessage(String message) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, message, CHANGES_SAVED2);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void doSaveConfigs() {
		ConfigurationEntry entry = configurationEntryDao.getByType(ConfigurationEntryEnum.YEARLY_PAYMENT_PRICE);
		entry.setValue(yearlyPayment);
		configurationEntryDao.update(entry);

		entry = configurationEntryDao.getByType(ConfigurationEntryEnum.MAX_USERS_SIZE);
		entry.setValue(maxUsersSize);
		configurationEntryDao.update(entry);

		entry = configurationEntryDao.getByType(ConfigurationEntryEnum.APPROVERS_SIZE);
		entry.setValue(approversSize);
		configurationEntryDao.update(entry);

		entry = configurationEntryDao.getByType(ConfigurationEntryEnum.MAX_REC_EMAILS_SENT);
		entry.setValue(maxEmailsAvailable);
		configurationEntryDao.update(entry);

		entry = configurationEntryDao.getByType(ConfigurationEntryEnum.DAYS_CANCEL_RESERVATION);
		entry.setValue(cancelReservationDays);
		configurationEntryDao.update(entry);

		entry = configurationEntryDao.getByType(ConfigurationEntryEnum.SKYPE_FIELD);
		entry.setValue(String.valueOf(skypeFieldVisible));
		configurationEntryDao.update(entry);

		entry = configurationEntryDao.getByType(ConfigurationEntryEnum.TELEPHONE_FIELD);
		entry.setValue(String.valueOf(telephoneFieldVisible));
		configurationEntryDao.update(entry);

		entry = configurationEntryDao.getByType(ConfigurationEntryEnum.DESCRIPTION_FIELD);
		entry.setValue(String.valueOf(descriptionFieldVisible));
		configurationEntryDao.update(entry);

		entry = configurationEntryDao.getByType(ConfigurationEntryEnum.GROUP_SIZE);
		entry.setValue(String.valueOf(groupSize));
		configurationEntryDao.update(entry);

		entry = configurationEntryDao.getByType(ConfigurationEntryEnum.RESERVATION_START_DATE);
		DateFormat df = new SimpleDateFormat(DATE_PATTERN);
		entry.setValue(df.format(reservationsStart));
		configurationEntryDao.update(entry);

		createChangesSuccessMessage(CHANGES_SAVED);
	}

	public void doAddPoints() {
		if (StringUtils.isBlank(points)) {
			RequestContext.getCurrentInstance().execute("PF('pointsDialog').hide()");
			return;
		}

		Integer pts = null;
		try {
			pts = Integer.valueOf(points);
		} catch (NumberFormatException ex) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Klaida!", "Ta�?kai gali susidėti tik i�? skaičių!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		selectedUser.setPoints(selectedUser.getPoints() + pts);
		userViewHelper.save(selectedUser);
		points = "";
		RequestContext.getCurrentInstance().execute("PF('pointsDialog').hide()");

		reloadUsersList();
		createChangesSuccessMessage(CHANGES_SAVED);
	}

	public void calculateGroups() {
		List<UserView> usersList = userViewHelper.getUsersByApprovedArchived(true, false);
		groupService.calculateGroups(usersList);
		reloadUsersList();
		createChangesSuccessMessage(CHANGES_SAVED3);
	}

	public UserViewHelper getUserViewHelper() {
		return userViewHelper;
	}

	public void setUserViewHelper(UserViewHelper userViewHelper) {
		this.userViewHelper = userViewHelper;
	}

	public List<UserView> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<UserView> usersList) {
		this.usersList = usersList;
	}

	public UserView getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(UserView selectedUser) {
		this.selectedUser = selectedUser;
	}

	public UserView getUser() {
		return user;
	}

	public void setUser(UserView user) {
		this.user = user;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getYearlyPayment() {
		return yearlyPayment;
	}

	public void setYearlyPayment(String yearlyPayment) {
		this.yearlyPayment = yearlyPayment;
	}

	public String getMaxUsersSize() {
		return maxUsersSize;
	}

	public void setMaxUsersSize(String maxUsersSize) {
		this.maxUsersSize = maxUsersSize;
	}

	public ConfigurationEntryDao getConfigurationEntryDao() {
		return configurationEntryDao;
	}

	public void setConfigurationEntryDao(ConfigurationEntryDao configurationEntryDao) {
		this.configurationEntryDao = configurationEntryDao;
	}

	public boolean isSkypeFieldVisible() {
		return skypeFieldVisible;
	}

	public void setSkypeFieldVisible(boolean skypeFieldVisible) {
		this.skypeFieldVisible = skypeFieldVisible;
	}

	public boolean isTelephoneFieldVisible() {
		return telephoneFieldVisible;
	}

	public void setTelephoneFieldVisible(boolean telephoneFieldVisible) {
		this.telephoneFieldVisible = telephoneFieldVisible;
	}

	public boolean isDescriptionFieldVisible() {
		return descriptionFieldVisible;
	}

	public void setDescriptionFieldVisible(boolean descriptionFieldVisible) {
		this.descriptionFieldVisible = descriptionFieldVisible;
	}

	public String getApproversSize() {
		return approversSize;
	}

	public void setApproversSize(String approversSize) {
		this.approversSize = approversSize;
	}

	public Date getReservationsStart() {
		return reservationsStart;
	}

	public void setReservationsStart(Date reservationsStart) {
		this.reservationsStart = reservationsStart;
	}

	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	public String getMaxEmailsAvailable() {
		return maxEmailsAvailable;
	}

	public void setMaxEmailsAvailable(String maxEmailsAvailable) {
		this.maxEmailsAvailable = maxEmailsAvailable;
	}

	public String getGroupSize() {
		return groupSize;
	}

	public void setGroupSize(String groupSize) {
		this.groupSize = groupSize;
	}

	public String getCancelReservationDays() {
		return cancelReservationDays;
	}

	public void setCancelReservationDays(String cancelReservationDays) {
		this.cancelReservationDays = cancelReservationDays;
	}
}
