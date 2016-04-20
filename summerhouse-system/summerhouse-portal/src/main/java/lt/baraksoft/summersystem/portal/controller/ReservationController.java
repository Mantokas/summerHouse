package lt.baraksoft.summersystem.portal.controller;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import lt.baraksoft.summersystem.portal.helper.ReservationViewHelper;
import lt.baraksoft.summersystem.portal.view.ReservationView;
import lt.baraksoft.summersystem.portal.view.SummerhouseView;

/**
 * Created by LaurynasC on 2016-04-19.
 */
@ManagedBean
@ViewScoped
public class ReservationController implements Serializable {
	private static final long serialVersionUID = 5810155872071867868L;

	@Inject
	private transient ReservationViewHelper reservationViewHelper;

	private Date reservationFrom;
	private Date reservationTo;
	private Date today = new Date();
	private List<ReservationView> reservationsList;
	private SummerhouseView selectedSummerhouse;

	@PostConstruct
	public void init() {
		reservationFrom = today;
		selectedSummerhouse = (SummerhouseView) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("summerhouse");
		reservationsList = reservationViewHelper.getReservationsBySummerhouse(selectedSummerhouse.getId());
	}

	public void createReservation() {
		ReservationView view = new ReservationView();
		view.setDateFrom(reservationFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		view.setDateTo(reservationTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		view.setSummerhouseID(selectedSummerhouse.getId());
		view.setUserID(4);// TODO set real user id
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

	public Date getToday() {
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}

	public void setToday(Date today) {
		this.today = today;
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
}
