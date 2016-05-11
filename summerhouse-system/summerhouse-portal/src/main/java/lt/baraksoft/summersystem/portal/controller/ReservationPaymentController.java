package lt.baraksoft.summersystem.portal.controller;

import lt.baraksoft.summersystem.portal.helper.ReservationViewHelper;
import lt.baraksoft.summersystem.portal.view.ReservationView;
import lt.baraksoft.summersystem.portal.view.SummerhouseView;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.SynchronizationType;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by LaurynasC on 2016-05-05.
 */
@Named
@ConversationScoped
@Stateful
public class ReservationPaymentController implements Serializable{
    private static final long serialVersionUID = -1250770727961046876L;

    @PersistenceContext(unitName = "summerhousePU", type = PersistenceContextType.EXTENDED, synchronization = SynchronizationType.UNSYNCHRONIZED)
    private EntityManager em;

    @Inject
    private Conversation conversation;

    @EJB
    private ReservationViewHelper reservationViewHelper;

    @Inject
    private SummerhouseController summerhouseController;

    private int activeIndex = 0;
    private CURRENT_FORM currentForm = CURRENT_FORM.FIRST;
    private Boolean isValidMonday = true;
    private LocalDate monday;
    private List<LocalDate> reservedDays = new ArrayList<>();
    private Date reservationFrom;
    private Date reservationTo;
    private List<ReservationView> reservationsList = new ArrayList<>();
    private String disabledDay;
    private SummerhouseView selectedSummerhouse;

    private enum CURRENT_FORM {
        FIRST, SECOND, THIRD, FOURTH
    }

    @PostConstruct
    public void init() {
        selectedSummerhouse = summerhouseController.getSelectedSummerhouse();
        reservationsList = reservationViewHelper.getReservationsBySummerhouse(selectedSummerhouse.getId());
        buildDateConstraint();
    }

    public boolean isCurrentForm(CURRENT_FORM form) {
        return currentForm == form;
    }

    public void beginConversation(){
        if (!conversation.isTransient()) {
            conversation.end();
            currentForm = CURRENT_FORM.FIRST;
            activeIndex = 0;
            return;
        }

        conversation.begin();

        currentForm = CURRENT_FORM.SECOND;
        activeIndex = 1;
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

    private void buildDateConstraint() {
        reservationsList.stream().forEach(r -> addReservedDays(r));
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("\"M-d-yyyy\"");
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (LocalDate item : reservedDays) {
            sb.append(item.format(sdf)).append(", ");
        }
        sb.append("]");

        if (reservedDays.isEmpty()) {
            disabledDay = "[\"\"]";
        } else {
            disabledDay = sb.toString();
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






    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
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

}
