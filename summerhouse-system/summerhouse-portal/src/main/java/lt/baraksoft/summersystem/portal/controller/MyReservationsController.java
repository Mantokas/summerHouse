package lt.baraksoft.summersystem.portal.controller;

import lt.baraksoft.summersystem.portal.helper.ReservationViewHelper;
import lt.baraksoft.summersystem.portal.view.ReservationView;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by LaurynasC on 2016-05-16.
 */
@Named
@Stateful
@RequestScoped
public class MyReservationsController implements Serializable{

    private static final long serialVersionUID = 5801944329786448085L;

    @Inject
    private ReservationViewHelper reservationViewHelper;

    private List<ReservationView> myReservations;
    private ReservationView selectedReservation;

    @PostConstruct
    private void init(){
        myReservations = reservationViewHelper.getReservations();
    }

    public void checkReservation(){
        if (selectedReservation.isArchived()){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Rezervacija yra negaliojanti!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else{
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('cancelReservationDialog').show();");
        }

    }

    public void cancelReservation(){
        reservationViewHelper.cancelReservation(selectedReservation.getId());
        myReservations = reservationViewHelper.getReservations();

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
}
