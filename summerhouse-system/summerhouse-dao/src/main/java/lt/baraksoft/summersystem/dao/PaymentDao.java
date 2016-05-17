package lt.baraksoft.summersystem.dao;

import lt.baraksoft.summersystem.dao.generic.IGenericDao;
import lt.baraksoft.summersystem.model.Payment;

import javax.ejb.Local;

/**
 * Created by LaurynasC on 2016-05-04.
 */
@Local
public interface PaymentDao extends IGenericDao<Payment, Integer>{
}
