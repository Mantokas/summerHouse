package lt.baraksoft.summersystem.dao.impl;

import lt.baraksoft.summersystem.dao.PaymentDao;
import lt.baraksoft.summersystem.dao.generic.GenericDao;
import lt.baraksoft.summersystem.model.Payment;
import lt.baraksoft.summersystem.model.User;

import javax.ejb.Stateless;
import java.io.Serializable;

/**
 * Created by LaurynasC on 2016-05-04.
 */
@Stateless
public class PaymentDaoImpl extends GenericDao<User, Integer> implements PaymentDao, Serializable {

    private static final long serialVersionUID = -400272313198027073L;

    @Override
    public void create(Payment payment) {
        getEntityManager().persist(payment);
    }
}
