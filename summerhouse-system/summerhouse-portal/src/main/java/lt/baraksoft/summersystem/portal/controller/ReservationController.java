package lt.baraksoft.summersystem.portal.controller;

import lt.baraksoft.summersystem.portal.helper.ReservationViewHelper;
import lt.baraksoft.summersystem.portal.view.ReservationView;
import lt.baraksoft.summersystem.portal.view.SummerhouseView;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by LaurynasC on 2016-04-19.
 */
@ManagedBean
@SessionScoped
public class ReservationController implements Serializable {

    @ManagedProperty(value = "#{summerhouseController.selectedSummerhouse}")
    private SummerhouseView selectedSummerhouse;

    @Inject
    ReservationViewHelper reservationViewHelper;

    private Date reservationFrom;
    private Date reservationTo;
    private Date validationDateFrom;
    private Date validationDateTo;
    private Date today = new Date();
    private List<ReservationView> reservationsList;

//    public Date dateFromSelect;
//    public Date dateToSelect;

//    public void save(){
//        ReservationView reservationView  = new ReservationView();
//        reservationView.setDate_from();
//        reservationView.setDate_to();
//        reservationView.setIsApproved(false);
//        reservationView.setIsArchived(false);
//        reservationView.setSummerhouseID(selectedSummerhouse.getId());
//        reservationView.setUserID();
//    }

    @PostConstruct
    public void init() {
        setValidationDateFrom(today);
        reservationsList = reservationViewHelper.getReservationsBySummerhouse(selectedSummerhouse.getId());
    }

    public void onDateFromSelect(SelectEvent event) {
        Date temp = (Date) event.getObject();
        setValidationDateFrom(temp);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pasirinkta data", format.format(event.getObject())));
    }

    public void onDateToSelect(SelectEvent event) {
        Date temp = (Date) event.getObject();
        setValidationDateTo(temp);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pasirinkta data", format.format(event.getObject())));
    }


    public Date getReservationFrom() {
        return reservationFrom;
    }

    public Date getReservationTo() {//
        return reservationTo;
    }

    public SummerhouseView getSelectedSummerhouse() {
        return selectedSummerhouse;
    }

    public void setSelectedSummerhouse(SummerhouseView selectedSummerhouse) {
        this.selectedSummerhouse = selectedSummerhouse;
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

    public Date getValidationDateFrom() {
        return validationDateFrom;
    }

    public void setValidationDateFrom(Date validationDateFrom) {
        this.validationDateFrom = validationDateFrom;
    }

    public Date getValidationDateTo() {
        return validationDateTo;
    }

    public void setValidationDateTo(Date validationDateTo) {
        this.validationDateTo = validationDateTo;
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
}
