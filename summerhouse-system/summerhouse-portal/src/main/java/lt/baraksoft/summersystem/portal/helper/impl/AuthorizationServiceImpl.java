package lt.baraksoft.summersystem.portal.helper.impl;

import lt.baraksoft.summersystem.dao.RoleDao;
import lt.baraksoft.summersystem.model.RoleEnum;
import lt.baraksoft.summersystem.portal.controller.UserLoginController;
import lt.baraksoft.summersystem.portal.helper.AuthorizationService;
import lt.baraksoft.summersystem.portal.view.UserView;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Žygimantas on 2016-05-29.
 */
@Stateless
public class AuthorizationServiceImpl implements AuthorizationService{

    @Inject
    private RoleDao roleDao;

    @Inject
    private UserLoginController loginController;

    @Override
    public boolean isAdmin() {
        UserView user = loginController.getLoggedUser();
        if (user == null) {
            return false;
        }
        return RoleEnum.ADMIN.equals(roleDao.getUserRole(user.getEmail()));
    }
}
