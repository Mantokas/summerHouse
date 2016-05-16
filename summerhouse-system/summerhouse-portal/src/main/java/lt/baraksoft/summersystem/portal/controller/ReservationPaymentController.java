package lt.baraksoft.summersystem.portal.controller;

import lt.baraksoft.summersystem.dao.ReservationDao;
import lt.baraksoft.summersystem.dao.SummerhouseDao;
import lt.baraksoft.summersystem.dao.UserDao;
import lt.baraksoft.summersystem.model.Reservation;
import lt.baraksoft.summersystem.model.Service;
import lt.baraksoft.summersystem.portal.helper.ReservationPaymentHelper;
import lt.baraksoft.summersystem.portal.helper.ReservationViewHelper;
import lt.baraksoft.summersystem.portal.view.PaymentStepEnum;
import lt.baraksoft.summersystem.portal.view.ReservationView;
import lt.baraksoft.summersystem.portal.view.ServiceView;
import lt.baraksoft.summersystem.portal.view.SummerhouseView;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.SynchronizationType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
public class ReservationPaymentController implements Serializable {
    private static final long serialVersionUID = -1250770727961046876L;

    @PersistenceContext(unitName = "summerhousePU", type = PersistenceContextType.EXTENDED, synchronization = SynchronizationType.UNSYNCHRONIZED)
    private EntityManager em;

    @Inject
    private Conversation conversation;

    @EJB
    private ReservationPaymentHelper reservationPaymentHelper;

    @EJB
    private ReservationViewHelper reservationViewHelper;

    @EJB
    ReservationDao reservationDao;

    @EJB
    UserDao userDao;

    @EJB
    SummerhouseDao summerhouseDao;

    @Inject
    private SummerhouseController summerhouseController;

    @Inject
    private SearchController searchController;

    @Inject
    private UserLoginController userLoginController;

    private PaymentStepEnum currentForm = PaymentStepEnum.FIRST;
    private List<LocalDate> reservedDays = new ArrayList<>();
    private List<ReservationView> reservationsList = new ArrayList<>();
    private SummerhouseView selectedSummerhouse;
    private List<ServiceView> selectedServiceViews = new ArrayList<>();
    private List<Service> selectedServices = new ArrayList<>();
    private int activeIndex = 0;
    private BigDecimal summerhouseReservationPrice;
    private BigDecimal servicesReservationPrice;
    private BigDecimal reservationPeriodInWeeks;
    private String disabledDay;
    private Date reservationFrom;
    private Date reservationTo;
    private boolean validMonday = true;
    private LocalDate monday;

    public void initAndBeginConversation() {
        selectedSummerhouse = searchController.getSelectedSummerhouse();
        reservationsList = reservationViewHelper.getReservationsBySummerhouse(selectedSummerhouse.getId());
        buildDateConstraint();
        if (!conversation.isTransient()) {
            conversation.end();
            currentForm = PaymentStepEnum.FIRST;
            activeIndex = 0;
        }
        conversation.begin();
        reservationFrom = getNextMonday();
    }

    public String goToSummerhouses() {
        return "toSummer";
    }

    public boolean isCurrentForm(PaymentStepEnum currentStep) {
        return currentForm == currentStep;
    }

    public void goToSecondStep() {
        currentForm = PaymentStepEnum.SECOND;
        activeIndex = 1;
    }

    public void goToThirdStep(){
        if (conversation.isTransient()) {
            currentForm = PaymentStepEnum.FIRST;
            activeIndex = 0;
            return;
        }
        selectedServices = reservationPaymentHelper.buildEntities(selectedServiceViews);
        //createReservation();
        calculateReservationPrice();
        currentForm = PaymentStepEnum.THIRD;
        activeIndex = 2;
    }

    private void calculateReservationPrice() {
        BigDecimal reservationPeriodInDays = BigDecimal.valueOf(ChronoUnit.DAYS.between(reservationFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                reservationTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));

        reservationPeriodInWeeks = reservationPeriodInDays.divide(new BigDecimal(6.00), BigDecimal.ROUND_HALF_UP);

        summerhouseReservationPrice = selectedSummerhouse.getPrice().multiply (reservationPeriodInWeeks);

        selectedServices.stream().forEach(service -> kazkas(service));
    }

    private void kazkas(Service service){
        BigDecimal a = service.getPrice().multiply(reservationPeriodInWeeks);
        servicesReservationPrice.add(a);
    }

    private void createReservation() {
        Reservation entity = new Reservation();
        entity.setDateFrom(reservationFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        entity.setDateTo(reservationTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        entity.setUser(userDao.get(userLoginController.getLoggedUser().getId()));
        entity.setSummerhouse(summerhouseDao.get(selectedSummerhouse.getId()));
        entity.setServiceList(selectedServices);
        //entity.setPrice();

        reservationDao.save(entity);
    }



    public Date getNextMonday() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.add(Calendar.DAY_OF_MONTH, 7);
        monday = c.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        while (validMonday) {
            reservedDays.stream().forEach(r -> findCorrectMonday(monday, r));
            if (!validMonday) {
                validMonday = true;
                monday = monday.plusDays(7);
            } else
                return Date.from(monday.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        return Date.from(monday.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private void findCorrectMonday(LocalDate monday, LocalDate reservedMonday) {
        if (reservedMonday.compareTo(monday) == 0) {
            validMonday = false;
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

    public List<ServiceView> getSelectedServiceViews() {
        return selectedServiceViews;
    }

    public void setSelectedServiceViews(List<ServiceView> selectedServiceViews) {
        this.selectedServiceViews = selectedServiceViews;
    }

    public boolean isValidMonday() {
        return validMonday;
    }

    public void setValidMonday(boolean validMonday) {
        this.validMonday = validMonday;
    }

    public List<Service> getSelectedServices() {
        return selectedServices;
    }

    public void setSelectedServices(List<Service> selectedServices) {
        this.selectedServices = selectedServices;
    }

    public BigDecimal getSummerhouseReservationPrice() {
        return summerhouseReservationPrice;
    }

    public void setSummerhouseReservationPrice(BigDecimal summerhouseReservationPrice) {
        this.summerhouseReservationPrice = summerhouseReservationPrice;
    }

    public BigDecimal getReservationPeriodInWeeks() {
        return reservationPeriodInWeeks;
    }

    public void setReservationPeriodInWeeks(BigDecimal reservationPeriodInWeeks) {
        this.reservationPeriodInWeeks = reservationPeriodInWeeks;
    }

    public BigDecimal getServicesReservationPrice() {
        return servicesReservationPrice;
    }

    public void setServicesReservationPrice(BigDecimal servicesReservationPrice) {
        this.servicesReservationPrice = servicesReservationPrice;
    }
}
