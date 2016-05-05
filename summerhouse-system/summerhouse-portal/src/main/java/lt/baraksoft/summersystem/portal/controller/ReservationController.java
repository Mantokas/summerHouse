package lt.baraksoft.summersystem.portal.controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import lt.baraksoft.summersystem.portal.helper.ReservationViewHelper;
import lt.baraksoft.summersystem.portal.helper.ServiceViewHelper;
import lt.baraksoft.summersystem.portal.view.ReservationView;
import lt.baraksoft.summersystem.portal.view.ServiceView;
import lt.baraksoft.summersystem.portal.view.SummerhouseView;

/**
 * Created by LaurynasC on 2016-04-19.
 */
@Named
@RequestScoped
public class ReservationController implements Serializable {
	private static final long serialVersionUID = 5810155872071867868L;

	@Inject
	private ReservationViewHelper reservationViewHelper;

	@Inject
	private ServiceViewHelper serviceViewHelper;

	@Inject
	private UserLoginController userLoginController;

	private Date reservationFrom;
	private Date reservationTo;
	private List<ReservationView> reservationsList = new ArrayList<>();
	private SummerhouseView selectedSummerhouse;
	private String disabledDay;
	private List<LocalDate> reservedDays = new ArrayList<>();
	private Boolean isValidMonday = true;
	private LocalDate monday;
	private List<ServiceView> selectedServices = new ArrayList<>();

	@PostConstruct
	public void init() {
		selectedSummerhouse = (SummerhouseView) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("summerhouse");
		reservationsList = reservationViewHelper.getReservationsBySummerhouse(selectedSummerhouse.getId());
		buildDateConstraint();
		reservationFrom = getNextMonday();
	}

	public Date getNextMonday() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		c.add(Calendar.DAY_OF_MONTH, 7);
		monday = c.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		while (isValidMonday) {
			reservedDays.stream().forEach(r -> findCorrectMonday(monday, r));
			if (!isValidMonday) {
				isValidMonday = true;
				monday = monday.plusDays(7);
			} else
				return Date.from(monday.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
		return Date.from(monday.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	private void findCorrectMonday(LocalDate monday, LocalDate reservedMonday) {
		if (reservedMonday.compareTo(monday) == 0) {
			isValidMonday = false;
		}
	}

	private void addReservedDays(ReservationView reservationView) {
		LocalDate dateFrom = reservationView.getDateFrom();
		LocalDate dateTo = reservationView.getDateTo();
		reservedDays.add(dateFrom);
		reservedDays.add(dateTo);
		dateFrom = dateFrom.plusDays(7);
		while (dateFrom.isBefore(dateTo)) {
			reservedDays.add(dateFrom);
			reservedDays.add(dateFrom.minusDays(1));
			dateFrom = dateFrom.plusDays(7);
		}
	}

	private void buildDateConstraint() {
		reservationsList.stream().forEach(r -> addReservedDays(r));
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("\"M-d-yyyy\"");
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (LocalDate item : reservedDays) {
			sb.append(item.format(sdf)).append(", ");
		}
		sb.append("]");

		if (reservedDays.isEmpty()){
			disabledDay = "[\"\"]";
		}
		else {
			disabledDay = sb.toString();
		}

	}

	public void createReservation() {
		ReservationView view = new ReservationView();
		view.setDateFrom(reservationFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		view.setDateTo(reservationTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		view.setSummerhouseID(selectedSummerhouse.getId());
		view.setUserID(userLoginController.getLoggedUser().getId());
		reservationViewHelper.save(view);
	}

	public void doUpdateReservationList() {
		reservationsList = reservationViewHelper.getReservationsBySummerhouse(selectedSummerhouse.getId());
	}

	public Date getReservationTo() {
		return reservationTo;
	}

	public Date getReservationFrom() {
		return reservationFrom;
	}

	public void setReservationFrom(Date reservationFrom) {
		this.reservationFrom = reservationFrom;
	}

	public void setReservationTo(Date reservationTo) {
		this.reservationTo = reservationTo;
	}

	public List<ReservationView> getReservationsList() {
		return reservationsList;
	}

	public void setReservationsList(List<ReservationView> reservationsList) {
		this.reservationsList = reservationsList;
	}

	public SummerhouseView getSelectedSummerhouse() {
		return selectedSummerhouse;
	}

	public void setSelectedSummerhouse(SummerhouseView selectedSummerhouse) {
		this.selectedSummerhouse = selectedSummerhouse;
	}

	public String getDisabledDay() {
		return disabledDay;
	}

	public void setDisabledDay(String disabledDay) {
		this.disabledDay = disabledDay;
	}

	public List<ServiceView> getSelectedServices() {
		return selectedServices;
	}

	public void setSelectedServices(List<ServiceView> selectedServices) {
		this.selectedServices = selectedServices;
	}
}
