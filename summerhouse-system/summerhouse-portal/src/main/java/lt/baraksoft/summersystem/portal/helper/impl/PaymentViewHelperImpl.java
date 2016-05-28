package lt.baraksoft.summersystem.portal.helper.impl;

import lt.baraksoft.summersystem.model.User;
import lt.baraksoft.summersystem.portal.helper.PaymentViewHelper;
import lt.baraksoft.summersystem.portal.view.UserView;

import javax.ejb.Stateless;

/**
 * Created by LaurynasC on 2016-05-04.
 */
@Stateless
public class PaymentViewHelperImpl implements PaymentViewHelper {

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

        return userView;
    }
}
