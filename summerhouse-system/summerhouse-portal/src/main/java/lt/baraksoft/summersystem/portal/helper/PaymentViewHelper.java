package lt.baraksoft.summersystem.portal.helper;

import lt.baraksoft.summersystem.model.User;
import lt.baraksoft.summersystem.portal.view.UserView;

import javax.ejb.Local;

/**
 * Created by LaurynasC on 2016-05-04.
 */
@Local
public interface PaymentViewHelper {

    UserView buildView (User user);

}
