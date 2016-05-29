package lt.baraksoft.summersystem.portal.helper;

import lt.baraksoft.summersystem.portal.controller.UserLoginController;

import javax.ejb.Local;

/**
 * Created by Žygimantas on 2016-05-29.
 */

@Local
public interface AuthorizationService {

    boolean isAdmin();

}
