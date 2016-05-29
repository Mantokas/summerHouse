package lt.baraksoft.summersystem.portal.helper;

import lt.baraksoft.summersystem.model.User;
import lt.baraksoft.summersystem.portal.view.PaymentView;
import lt.baraksoft.summersystem.portal.view.UserView;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by LaurynasC on 2016-05-04.
 */
@Local
public interface PaymentViewHelper {

    UserView buildView (User user);
    List<PaymentView> getPaymentByUserID(int userID);
}
