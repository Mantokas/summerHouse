package lt.baraksoft.summersystem.dao.impl;

import javax.ejb.Stateless;

import lt.baraksoft.summersystem.dao.PaymentDao;
import lt.baraksoft.summersystem.dao.generic.GenericDao;
import lt.baraksoft.summersystem.model.Payment;

/**
 * Created by LaurynasC on 2016-05-04.
 */
@Stateless
public class PaymentDaoImpl extends GenericDao<Payment, Integer> implements PaymentDao {

}
