package lt.baraksoft.summersystem.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import lt.baraksoft.summersystem.dao.PaymentDao;
import lt.baraksoft.summersystem.dao.generic.GenericDao;
import lt.baraksoft.summersystem.model.Payment;
import lt.baraksoft.summersystem.model.Payment_;
import lt.baraksoft.summersystem.model.Reservation;
import lt.baraksoft.summersystem.model.Reservation_;

import java.util.List;

/**
 * Created by LaurynasC on 2016-05-04.
 */
@Stateless
public class PaymentDaoImpl extends GenericDao<Payment, Integer> implements PaymentDao {

    @Override
    public List<Payment> getUserPayments(int userID) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Payment> criteria = builder.createQuery(Payment.class);
        Root<Payment> root = criteria.from(Payment.class);
        criteria.where(builder.and(builder.equal(root.get(Payment_.user), userID)));
        criteria.select(root);
        return getEntityManager().createQuery(criteria).getResultList();
    }
}
