package lt.baraksoft.summersystem.portal.controller;

import lt.baraksoft.summersystem.portal.view.SummerhouseView;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by LaurynasC on 2016-04-19.
 */
@ManagedBean
@SessionScoped
public class ReservationController implements Serializable{

    @ManagedProperty(value="#{summerhouseController.selectedSummerhouse}")
    private SummerhouseView selectedSummerhouse;

    private Date dataNuo;
    private Date dataIki;
    private Date validationDateFrom;
    private Date validationDateTo;
    private Date today;

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
    }

    public Date getDataNuo() {
        return dataNuo;
    }

    public Date getDataIki() {//
        return dataIki;
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

    public SummerhouseView getSelectedSummerhouse() {
        return selectedSummerhouse;
    }

    public void setSelectedSummerhouse(SummerhouseView selectedSummerhouse) {
        this.selectedSummerhouse = selectedSummerhouse;
    }

    public void setDataNuo(Date dataNuo) {
        this.dataNuo = dataNuo;
    }

    public void setDataIki(Date dataIki) {
        this.dataIki = dataIki;
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
}
