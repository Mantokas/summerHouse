package lt.baraksoft.summersystem.dao;

import javax.ejb.Local;

import lt.baraksoft.summersystem.dao.generic.IGenericDao;
import lt.baraksoft.summersystem.model.Payment;

import java.util.List;

/**
 * Created by LaurynasC on 2016-05-04.
 */
@Local
public interface PaymentDao extends IGenericDao<Payment, Integer> {

    List<Payment> getUserPayments(int userID);
}
