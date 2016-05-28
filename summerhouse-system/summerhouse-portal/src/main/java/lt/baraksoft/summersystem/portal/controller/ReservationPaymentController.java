package lt.baraksoft.summersystem.portal.controller;

import lt.baraksoft.summersystem.dao.*;
import lt.baraksoft.summersystem.model.Payment;
import lt.baraksoft.summersystem.model.Reservation;
import lt.baraksoft.summersystem.model.Service;
import lt.baraksoft.summersystem.model.User;
import lt.baraksoft.summersystem.portal.helper.ReservationPaymentHelper;
import lt.baraksoft.summersystem.portal.helper.ReservationViewHelper;
import lt.baraksoft.summersystem.portal.interceptor.Log;
import lt.baraksoft.summersystem.portal.view.*;

import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
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
    private static final String PAGE_INDEX_REDIRECT = "index?faces-redirect=true";

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

    @EJB
    PaymentDao paymentDao;

    @EJB
    ServiceDao serviceDao;

    @Inject
    private SummerhouseController summerhouseController;

    @Inject
    private SearchController searchController;

    @Inject
    private UserLoginController userLoginController;

    @Inject
    private NavigationController navigationController;

    private PaymentStepEnum currentForm = PaymentStepEnum.FIRST;
    private List<LocalDate> reservedDays = new ArrayList<>();
    private List<ServiceView> selectedServiceViews = new ArrayList<>();
    private List<Service> selectedServices = new ArrayList<>();
    private UserView loggedUser;
    private User loggedUserEntity;
    private int activeIndex = 0;
    private BigDecimal reservationPeriodInWeeks;
    private String disabledDay;
    private String reservationNo;
    private boolean validMonday = true;
    private LocalDate monday;
    private ReservationPaymentView reservationPaymentView = new ReservationPaymentView();
    private Date reservationFrom;
    private Date reservationTo;
    private boolean dateIncorrect;

    public String initAndBeginConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
            currentForm = PaymentStepEnum.FIRST;
            activeIndex = 0;
        }

        String a = navigationController.checkLoggedUser();

        if (a.equals("toSignCheck")){
            return "toSignCheck";
        }

        if (!FacesContext.getCurrentInstance().isPostback() && conversation.isTransient()) {
            conversation.begin();

            reservationPaymentView.setSelectedSummerhouse(searchController.getSelectedSummerhouse());
            reservationPaymentView.setReservationsList(reservationPaymentHelper.getReservationsBySummerhouse(reservationPaymentView.getSelectedSummerhouse().getId()));
            buildDateConstraint();
            loggedUser = userLoginController.getLoggedUser();
            reservationPaymentView.setPointsBefore(loggedUser.getPoints());
            loggedUserEntity = userDao.get(loggedUser.getId());
            reservationFrom = getNextMonday();
        }

        return "";
    }

    public String goToSummerhouses() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public boolean isCurrentForm(PaymentStepEnum currentStep) {
        return currentForm == currentStep;
    }

    private void calculateSummerhousePrice(){
        BigDecimal reservationPeriodInDays = BigDecimal.valueOf(ChronoUnit.DAYS.between(reservationFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                reservationTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        reservationPeriodInWeeks = reservationPeriodInDays.divide(new BigDecimal(7.00), BigDecimal.ROUND_HALF_UP);
        reservationPaymentView.setSummerhouseReservationPrice(reservationPaymentView.getSelectedSummerhouse().getPrice().multiply (reservationPeriodInWeeks));
    }

    private boolean checkIsTransient(){
        if (conversation.isTransient()) {
            currentForm = PaymentStepEnum.FIRST;
            activeIndex = 0;
            return true;
        }
        return false;
    }

    public void goToSecondStep() {
        if (checkIsTransient()){
            return;
        }
        dateIncorrect = false;

        try{
            reservationPaymentView.setReservationFrom(reservationFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            reservationPaymentView.setReservationTo(reservationTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }catch (Exception ex){
            dateIncorrect = true;
        }

        reservationPaymentView.getReservationsList().stream().forEach(this::checkReservations);

        if (dateIncorrect){
            createErrorMessage("Neteisingas laikotarpis!", "");
        }
        else{
            calculateSummerhousePrice();

            if (loggedUser.getPoints() - reservationPaymentView.getSummerhouseReservationPrice().intValue() < 0){
                createErrorMessage("Klaida", "Vasarnamio rezervacijai nepakanka pinigų");
            }
            else{
                currentForm = PaymentStepEnum.SECOND;
                activeIndex = 1;
            }
        }
    }

    private void checkReservations(ReservationView reservationView) {
        if (reservationPaymentView.getReservationFrom().isAfter(reservationPaymentView.getReservationTo()) ||
                (reservationPaymentView.getReservationFrom().isBefore(reservationView.getDateTo()) && reservationPaymentView.getReservationTo().isAfter(reservationView.getDateFrom()))
                 ){
            dateIncorrect = true;
        }
    }

    private void createErrorMessage(String first, String second) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, first, second);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void goToThirdStep(){
        if (checkIsTransient()){
            return;
        }
        if (!selectedServiceViews.isEmpty()){
            selectedServiceViews.stream().forEach(serviceView ->  selectedServices.add(serviceDao.get(serviceView.getId())));
            selectedServices.stream().forEach(service -> reservationPaymentView.setServicesReservationPrice
                    (reservationPaymentView.getServicesReservationPrice().add(service.getPrice().
                            multiply(reservationPeriodInWeeks))));
        }

        if (reservationPaymentView.getPointsBefore() - reservationPaymentView.getServicesReservationPrice().intValue() -
                reservationPaymentView.getSummerhouseReservationPrice().intValue() < 0) {
            createErrorMessage("Klaida", "Papildomų paslaugų rezervacijai nepakanka pinigų");
        }
        else{
            createReservation();
            currentForm = PaymentStepEnum.THIRD;
            activeIndex = 2;
        }
    }

    private void createReservation() {
        reservationPaymentView.setReservationFrom(reservationFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        reservationPaymentView.setReservationTo(reservationTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        Reservation entity = new Reservation();
        entity.setDateFrom(reservationPaymentView.getReservationFrom());
        entity.setDateTo(reservationPaymentView.getReservationTo());
        entity.setUser(loggedUserEntity);

        reservationNo = "R".concat(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.MAX)));

        entity.setNr(reservationNo);
        entity.setSummerhouse(summerhouseDao.get(reservationPaymentView.getSelectedSummerhouse().getId()));
        entity.setServiceList(selectedServices);
        entity.setPrice(reservationPaymentView.getServicesReservationPrice().add(reservationPaymentView.getSummerhouseReservationPrice()));

        reservationDao.save(entity);

        reservationPaymentView.setPointsAfter(reservationPaymentView.getPointsBefore() -
                reservationPaymentView.getServicesReservationPrice().intValue() -
                reservationPaymentView.getSummerhouseReservationPrice().intValue());

        loggedUserEntity.setPoints(reservationPaymentView.getPointsAfter());
    }

    private void createReservationPayment() {
        Payment entity = new Payment();
        entity.setPurpose(reservationNo);
        entity.setExecutionDate(LocalDate.now());
        entity.setAmount(reservationPaymentView.getServicesReservationPrice().add(reservationPaymentView.getSummerhouseReservationPrice()));
        entity.setUser(loggedUserEntity);

        paymentDao.save(entity);
    }

    @Log
    public String submitTransaction() {
        if (conversation.isTransient()) {
            currentForm = PaymentStepEnum.FIRST;
            activeIndex = 0;
            return PAGE_INDEX_REDIRECT;
        }

        createReservationPayment();

        try {
            em.joinTransaction();
            em.flush();
            conversation.end();
            return PAGE_INDEX_REDIRECT;
        } catch (OptimisticLockException ole) {
            em.clear();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Klaida", "Apmokėjimas atšauktas. Taškai nenuimti");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return PAGE_INDEX_REDIRECT;
        } catch (PersistenceException pe) {
            em.clear();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Persistence exception", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return PAGE_INDEX_REDIRECT;
        }
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
        reservationPaymentView.getReservationsList().stream().forEach(r -> addReservedDays(r));
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

    public BigDecimal getReservationPeriodInWeeks() {
        return reservationPeriodInWeeks;
    }

    public void setReservationPeriodInWeeks(BigDecimal reservationPeriodInWeeks) {
        this.reservationPeriodInWeeks = reservationPeriodInWeeks;
    }

    public void setReservationPaymentView(ReservationPaymentView reservationPaymentView) {
        this.reservationPaymentView = reservationPaymentView;
    }

    public ReservationPaymentView getReservationPaymentView() {
        return reservationPaymentView;
    }

    public Date getReservationFrom() {
        return reservationFrom;
    }

    public void setReservationFrom(Date reservationFrom) {
        this.reservationFrom = reservationFrom;
    }

    public Date getReservationTo() {
        return reservationTo;
    }

    public void setReservationTo(Date reservationTo) {
        this.reservationTo = reservationTo;
    }

    public UserView getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(UserView loggedUser) {
        this.loggedUser = loggedUser;
    }

    public boolean isDateIncorrect() {
        return dateIncorrect;
    }

    public void setDateIncorrect(boolean dateIncorrect) {
        this.dateIncorrect = dateIncorrect;
    }
}
