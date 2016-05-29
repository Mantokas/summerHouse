package lt.baraksoft.summersystem.portal.helper.impl;

import lt.baraksoft.summersystem.dao.PaymentDao;
import lt.baraksoft.summersystem.model.Payment;
import lt.baraksoft.summersystem.model.User;
import lt.baraksoft.summersystem.portal.helper.PaymentViewHelper;
import lt.baraksoft.summersystem.portal.view.PaymentView;
import lt.baraksoft.summersystem.portal.view.UserView;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LaurynasC on 2016-05-04.
 */
@Stateless
public class PaymentViewHelperImpl implements PaymentViewHelper {

    @Inject
    private PaymentDao paymentDao;

    @Override
    public UserView buildView(User user) {
        UserView userView = new UserView();
        userView.setValidTo(user.getValidTo());
        userView.setFirstName(user.getFirstname());
        userView.setPassword(user.getPassword());
        userView.setEmail(user.getEmail());
        userView.setApproved(user.isApproved());
        userView.setArchived(user.isArchived());
        userView.setLastName(user.getLastname());
        userView.setId(user.getId());
        userView.setPoints(user.getPoints());
        userView.setFacebookId(user.getFacebookId());
        userView.setGroupNumber(user.getGroupNumber());
        userView.setSkypeName(user.getSkypeName());
        userView.setDescription(user.getDescription());
        userView.setPhoneNumber(user.getPhoneNumber());
        return userView;
    }

    @Override
    public List<PaymentView> getPaymentByUserID(int userID) {
        List<Payment> entities;
        List<PaymentView> views = new ArrayList<>();
        entities = paymentDao.getUserPayments(userID);
        entities.stream().forEach(payment -> views.add(buildPaymentView(payment)));

        return views;
    }

    private PaymentView buildPaymentView(Payment payment) {
        PaymentView paymentView = new PaymentView();
        paymentView.setId(payment.getId());
        paymentView.setAmount(payment.getAmount());
        paymentView.setExecutionDate(payment.getExecutionDate());
        paymentView.setPurpose(payment.getPurpose());
        paymentView.setPayer(payment.getUser());

        return paymentView;
    }
}
