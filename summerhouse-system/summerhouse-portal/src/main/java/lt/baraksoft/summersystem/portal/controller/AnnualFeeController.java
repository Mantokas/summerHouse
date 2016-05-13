package lt.baraksoft.summersystem.portal.controller;

import lt.baraksoft.summersystem.dao.PaymentDao;
import lt.baraksoft.summersystem.dao.UserDao;
import lt.baraksoft.summersystem.model.Payment;
import lt.baraksoft.summersystem.model.User;
import lt.baraksoft.summersystem.portal.view.UserView;

import javax.annotation.PostConstruct;

import javax.annotation.Resource;
import javax.ejb.AfterBegin;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import javax.transaction.TransactionSynchronizationRegistry;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LaurynasC on 2016-05-04.
 */

@Named
@ConversationScoped
@Stateful
public class AnnualFeeController implements Serializable {

    private static final long serialVersionUID = 390311249488898840L;

    @PersistenceContext(unitName = "summerhousePU", type = PersistenceContextType.EXTENDED, synchronization = SynchronizationType.UNSYNCHRONIZED)
    private EntityManager em;

    private static final String PAGE_INDEX_REDIRECT = "index?faces-redirect=true";

    @Inject
    private Conversation conversation;

    @Inject
    private UserLoginController userLoginController;

    @Inject
    private PaymentDao paymentDao;

    @Inject
    private UserDao userDao;

    private int activeIndex = 0;
    private String selectedPaymentValue;
    private List<String> clubPayTypes = new ArrayList<>();
    private User loggedUser;
    private String purpose;
    private int amount;
    private int yearLength;
    private CURRENT_FORM currentForm = CURRENT_FORM.PAYMENT_TYPE;

    private enum CURRENT_FORM {
        PAYMENT_TYPE, PAYMENT_PROPERTIES, CONFIRMATION
    }

    @PostConstruct
    public void init() {
        clubPayTypes.add("1 metams");
        clubPayTypes.add("2 metams");
        clubPayTypes.add("3 metams");
    }

    public boolean isCurrentForm(CURRENT_FORM form) {
        return currentForm == form;
    }

    public void calculateSum(){
        yearLength = Character.getNumericValue(selectedPaymentValue.charAt(0));
        amount = yearLength * 20;
    }

    public void checkPaymentAmount() {
        if (!conversation.isTransient()) {
            conversation.end();
            currentForm = CURRENT_FORM.PAYMENT_TYPE;
            activeIndex = 0;
            return;
        }

        conversation.begin();

        if (selectedPaymentValue.equals("")) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Klaida", "Nepasirinkote mokėjimo!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            yearLength = Character.getNumericValue(selectedPaymentValue.charAt(0));
            amount = yearLength * 20;

            UserView loggedUserView = userLoginController.getLoggedUser();

            loggedUser = userDao.get(loggedUserView.getId());
            int points = loggedUser.getPoints();

            if (points < amount) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Klaida", "Mokėjimui reikia " + amount + " taškų, Jūs turite " + points + " taškų");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            else {
                purpose = "Narystės pratęsimas " + selectedPaymentValue;
                loggedUser.setPoints(points - amount);
                currentForm = CURRENT_FORM.PAYMENT_PROPERTIES;
                activeIndex = 1;
            }
        }
    }

    public void createPayment() {
        if (conversation.isTransient()) {
            currentForm = CURRENT_FORM.PAYMENT_TYPE;
            activeIndex = 0;
            return;
        }
        Payment payment = new Payment();
        payment.setAmount(new BigDecimal(amount));
        payment.setExecutionDate(LocalDate.now());
        payment.setPurpose(purpose);

        paymentDao.save(payment);

        loggedUser.getPaymentList().add(payment);

        if (loggedUser.getValidTo() != null) {
            loggedUser.setValidTo(loggedUser.getValidTo().plusDays(365 * yearLength));
        } else {
            loggedUser.setValidTo(LocalDate.now().plusDays(365 * yearLength));
        }
        currentForm = CURRENT_FORM.CONFIRMATION;
        activeIndex = 2;
    }

    public String ok() {
        if (conversation.isTransient()) {
            currentForm = CURRENT_FORM.PAYMENT_TYPE;
            activeIndex = 0;
            return PAGE_INDEX_REDIRECT;
        }
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

    public String cancel() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
        return PAGE_INDEX_REDIRECT;
    }

    public List<String> getClubPayTypes() {
        return clubPayTypes;
    }

    public void setClubPayTypes(List<String> clubPayTypes) {
        this.clubPayTypes = clubPayTypes;
    }

    public String getSelectedPaymentValue() {
        return selectedPaymentValue;
    }

    public void setSelectedPaymentValue(String selectedPaymentValue) {
        this.selectedPaymentValue = selectedPaymentValue;
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public int getYearLength() {
        return yearLength;
    }

    public void setYearLength(int yearLength) {
        this.yearLength = yearLength;
    }
}
